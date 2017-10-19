package org.ajexp;

import org.ajexp.annotations.AjxDocument;
import org.ajexp.dto.SimpleDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by SSh on 19.10.2017.
 */
public class AbstractExporterTest extends Assert {

    private List<SimpleDTO> data;
    private SimpleExporter exporter;

    @Before
    public void init(){
        data = Arrays.asList(
                new SimpleDTO(100L, "first"),
                new SimpleDTO(200L, "second"),
                new SimpleDTO(300L, "third")
        );
        exporter = new SimpleExporter(data);
    }

    @Test
    public void testParamsValidation(){
        assertTrue(exporter.isValidParams());
    }

    @Test
    public void testData() throws InvocationTargetException, IllegalAccessException {
        List<ColumnMeta> columnMetaList = exporter.getColumnMeta();
        assertNotNull(columnMetaList);
        testRow(columnMetaList, 0, 100L, "first");
        testRow(columnMetaList, 1, 200L, "second");
        testRow(columnMetaList, 2, 300L, "third");
    }

    private void testRow(List<ColumnMeta> columnMetaList, int rowNum, Long requiredId, String requiredName) throws InvocationTargetException, IllegalAccessException {
        Object id = exporter.getMemberData(columnMetaList.get(0).getMember(), data.get(rowNum));
        assertTrue(id instanceof Long);
        assertEquals(id, requiredId);
        Object name = exporter.getMemberData(columnMetaList.get(1).getMember(), data.get(rowNum));
        assertTrue(name instanceof String);
        assertEquals(name, requiredName);
    }

    @Test
    public void testLocalizedName(){
        AjxDocument document = exporter.getDocumentMeta();
        assertNotNull(document);
        String localizedName = exporter.getLocalizedName(document.value(), AjxLocale.ENGLISH);
        assertEquals(localizedName, "TestDocument");
        String notExistingName = exporter.getLocalizedName(document.value(), AjxLocale.RU);
        assertNull(notExistingName);
    }

    @Test
    public void testExport() throws AjxException {
        assertEquals(exporter.exportData(new ByteArrayOutputStream()), 3);
    }
}
