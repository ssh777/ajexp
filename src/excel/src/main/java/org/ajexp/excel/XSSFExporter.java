package org.ajexp.excel;

import org.ajexp.AjxLocale;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Collection;

/**
 * Created by SSh on 19.10.2017.
 */
public class XSSFExporter extends AbstractExcelExporter {
    public XSSFExporter(Collection<?> data, Class clazz) {
        super(data, clazz);
    }

    public XSSFExporter(Collection<?> data, Class clazz, AjxLocale locale) {
        super(data, clazz, locale);
    }

    protected Workbook createWorkbook() {
        return new XSSFWorkbook();
    }

}
