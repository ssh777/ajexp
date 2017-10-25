package org.ajexp.excel;


import org.ajexp.AbstractExporter;
import org.ajexp.AjxException;
import org.ajexp.AjxLocale;
import org.ajexp.ColumnMeta;
import org.ajexp.annotations.AjxColumn;
import org.ajexp.annotations.AjxDocument;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.util.Collection;
import java.util.Formatter;
import java.util.List;

/**
 * Created by SSh on 29.09.2017.
 */
public abstract class AbstractExcelExporter extends AbstractExporter {

    private static final BorderStyle BORDER_STYLE = BorderStyle.valueOf((short) 1);

    public AbstractExcelExporter(Collection<?> data, Class clazz) {
        super(data, clazz);
    }

    public AbstractExcelExporter(Collection<?> data, Class clazz, AjxLocale locale) {
        super(data, clazz, locale);
    }

    protected abstract Workbook createWorkbook();

    @Override
    protected int exportInternal(OutputStream outputStream, AjxDocument documentMeta, List<ColumnMeta> columnsMeta) throws AjxException {
        try (Workbook workbook = createWorkbook()) {
            Sheet sheet = createSheet(documentMeta, workbook);

            int rowCount = 0;
            int colCount = 0;

            Row headerRow = sheet.createRow(rowCount++);
            colCount = writeHeaders(workbook, 0, columnsMeta, "", headerRow, locale);

            for (Object dataObject : data) {
                Row dataRow = sheet.createRow(rowCount++);
                writeRowData(workbook, 0, dataRow, columnsMeta, dataObject);
            }

            adjustColumnsWidth(columnsMeta, sheet, colCount);

            if (documentMeta == null || documentMeta.autoFilter()) {
                sheet.setAutoFilter(new CellRangeAddress(0, rowCount - 1, 0, colCount - 1));
            }

            workbook.write(outputStream);
            return rowCount;
        } catch (IOException | IllegalAccessException | InvocationTargetException e) {
            throw new AjxException(e);
        }
    }

    protected Sheet createSheet(AjxDocument documentMeta, Workbook workbook) {
        Sheet sheet;
        if (documentMeta != null && getLocalizedName(documentMeta.value(), locale) != null) {
            sheet = workbook.createSheet(getLocalizedName(documentMeta.value(), locale));
        }
        else {
            sheet = workbook.createSheet();
        }
        return sheet;
    }

    private void adjustColumnsWidth(List<ColumnMeta> columnsMeta, Sheet sheet, int colCount) {
        for (int i = 0; i < colCount; i++) {
            AjxColumn annotation = columnsMeta.get(i).getAnnotation();
            if (annotation.autoSize()) {
                sheet.autoSizeColumn(i);
            }
            else {
                int width = annotation.width();
                sheet.setColumnWidth(i, width);
            }
        }
    }

    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());

        headerStyle.setFont(font);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        headerStyle.setBorderBottom(BORDER_STYLE);
        headerStyle.setBorderLeft(BORDER_STYLE);
        headerStyle.setBorderRight(BORDER_STYLE);
        headerStyle.setBorderTop(BORDER_STYLE);

        return headerStyle;
    }

    private CellStyle createDataCellStyle(Workbook workbook) {
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setBorderBottom(BORDER_STYLE);
        dataStyle.setBorderLeft(BORDER_STYLE);
        dataStyle.setBorderRight(BORDER_STYLE);
        dataStyle.setBorderTop(BORDER_STYLE);
        return dataStyle;
    }

    private int writeHeaders(Workbook workbook, int startCol, List<ColumnMeta> excelColumns, String parent, Row headerRow, AjxLocale locale) {
        CellStyle headerStyle = createHeaderCellStyle(workbook);
        int colCount = startCol;
        for (ColumnMeta meta : excelColumns) {
            AjxColumn annotation = meta.getAnnotation();
            String localizedHeaderName = getLocalizedName(annotation.headerName(), locale);
            String headerLabel = (parent != null && !parent.isEmpty() ? parent : "")
                    + (localizedHeaderName != null && !localizedHeaderName.isEmpty() ? localizedHeaderName : "");
            if (annotation.complexType() && meta.getSubColumns() != null) {
                colCount = writeHeaders(workbook, colCount, meta.getSubColumns(),
                        headerLabel + (parent != null && !parent.isEmpty() ? "." : ""), headerRow, locale);
            }
            else {
                Cell cell = headerRow.createCell(colCount++);
                cell.setCellValue(headerLabel);
                cell.setCellStyle(headerStyle);
            }
        }
        return colCount;
    }

    private int writeRowData(Workbook workbook, int startCol, Row dataRow, List<ColumnMeta> excelColumns, Object data) throws InvocationTargetException, IllegalAccessException {
        CellStyle dataStyle = createDataCellStyle(workbook);
        int colCount = startCol;
        for (ColumnMeta meta : excelColumns) {
            Member member = meta.getMember();
            AjxColumn annotation = meta.getAnnotation();
            if (annotation.complexType() && meta.getSubColumns() != null) {
                Object cellData = getMemberData(member, data);
                if (cellData != null) {
                    colCount = writeRowData(workbook, colCount, dataRow, meta.getSubColumns(), cellData);
                } else {
                    createEmptyCell(colCount++, dataRow, dataStyle);
                }
            }
            else {
                Object cellData = getMemberData(member, data);
                if (cellData != null) {
                    createCell(colCount++, dataRow, dataStyle, meta, annotation, cellData);
                }
                else {
                    createEmptyCell(colCount++, dataRow, dataStyle);
                }
            }
        }
        return colCount;
    }

    private void createCell(int colIndex, Row dataRow, CellStyle dataStyle, ColumnMeta meta, AjxColumn annotation, Object cellData) {
        String value = null;
        if (meta.isFormatted()) {
            try (Formatter formatter = createFormatter()) {
                value = formatter.format(annotation.format(), cellData).toString();
            }
        }
        else {
            value = String.valueOf(cellData);
        }
        Cell cell = dataRow.createCell(colIndex);
        cell.setCellValue(value);
        cell.setCellStyle(dataStyle);
    }

    private Formatter createFormatter() {
        Formatter formatter;
        if (locale != AjxLocale.NULL) {
            formatter = new Formatter(locale.getLocale());
        }
        else {
            formatter = new Formatter();
        }
        return formatter;
    }

    private void createEmptyCell(int colIndex, Row dataRow, CellStyle dataStyle) {
        Cell cell = dataRow.createCell(colIndex);
        cell.setCellStyle(dataStyle);
        cell.setCellValue("");
    }
}
