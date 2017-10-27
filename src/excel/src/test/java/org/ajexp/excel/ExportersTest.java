package org.ajexp.excel;

import org.ajexp.AjxException;
import org.ajexp.AjxLocale;
import org.ajexp.excel.dto.SimpleDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by SSh on 25.10.2017.
 */
public class ExportersTest extends Assert {
    private List<SimpleDTO> data;

    @Before
    public void init() {
        data = Arrays.asList(
                new SimpleDTO(100L, "first"),
                new SimpleDTO(200L, "second"),
                new SimpleDTO(300L, "third")
        );
    }

    @Test
    public void testExporterCtor(){
        assertNotNull(new HSSFExporter(Collections.EMPTY_LIST, Void.class));
        assertNotNull(new XSSFExporter(Collections.EMPTY_LIST, Void.class));
        assertNotNull(new SXSSFExporter(Collections.EMPTY_LIST, Void.class));
        assertNotNull(new HSSFExporter(Collections.EMPTY_LIST, Void.class, AjxLocale.NULL));
        assertNotNull(new XSSFExporter(Collections.EMPTY_LIST, Void.class, AjxLocale.NULL));
        assertNotNull(new SXSSFExporter(Collections.EMPTY_LIST, Void.class, AjxLocale.NULL));
    }

    @Test
    public void testHSSFExport() throws AjxException {
        testExporter(new HSSFExporter(data, SimpleDTO.class));
    }

    @Test
    public void testXSSFExport() throws AjxException {
        testExporter(new XSSFExporter(data, SimpleDTO.class));
    }

    @Test
    public void testSXSSFExport() throws AjxException {
        testExporter(new SXSSFExporter(data, SimpleDTO.class));
    }

    private void testExporter(AbstractExcelExporter excelExporter) throws AjxException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int rowCount = excelExporter.exportData(os);
        assertEquals(excelExporter.getData().size() + 1, rowCount);
        assertTrue(os.size() > 0);
    }
}
