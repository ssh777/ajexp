package org.ajexp.excel;

import org.ajexp.AjxLocale;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Collection;

/**
 * Created by SSh on 19.10.2017.
 */
public class HSSFExporter extends AbstractExcelExporter {
    public HSSFExporter(Collection<?> data, Class clazz) {
        super(data, clazz);
    }

    public HSSFExporter(Collection<?> data, Class clazz, AjxLocale locale) {
        super(data, clazz, locale);
    }

    @Override
    protected Workbook createWorkbook() {
        return new HSSFWorkbook();
    }
}
