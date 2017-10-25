package org.ajexp.excel;

import org.ajexp.AjxException;
import org.ajexp.AjxLocale;
import org.ajexp.excel.dto.SimpleDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by SSh on 25.10.2017.
 */
public class ExporterTests extends Assert {
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
    public void testExport() throws AjxException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        new HSSFExporter(data, SimpleDTO.class).exportData(os);
        assertTrue(os.size() > 0);
    }
}
