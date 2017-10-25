package org.ajexp.excel;

import org.ajexp.AjxException;
import org.ajexp.AjxLocale;
import org.ajexp.ColumnMeta;
import org.ajexp.annotations.AjxDocument;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

/**
 * Created by SSh on 25.10.2017.
 */
public class SXSSFExporter extends AbstractExcelExporter {
    public SXSSFExporter(Collection<?> data, Class clazz) {
        super(data, clazz);
    }

    public SXSSFExporter(Collection<?> data, Class clazz, AjxLocale locale) {
        super(data, clazz, locale);
    }

    protected Workbook createWorkbook() {
        return new SXSSFWorkbook();
    }

    @Override
    protected Sheet createSheet(AjxDocument documentMeta, Workbook workbook) {
        Sheet sheet = super.createSheet(documentMeta, workbook);
        ((SXSSFSheet)sheet).trackAllColumnsForAutoSizing();
        return sheet;
    }
}
