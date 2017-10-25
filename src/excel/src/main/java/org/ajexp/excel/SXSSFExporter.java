package org.ajexp.excel;

import org.ajexp.AjxLocale;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.Collection;

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
}
