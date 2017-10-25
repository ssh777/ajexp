package org.ajexp;

import org.ajexp.annotations.AjxColumn;
import org.ajexp.annotations.AjxDocument;
import org.ajexp.annotations.AjxName;
import org.ajexp.dto.SimpleDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by SSh on 19.10.2017.
 */
public class AnnotationExtractorTest extends Assert {

    private Class clazz;

    @Before
    public void init(){
        clazz = SimpleDTO.class;
    }

    @Test
    public void testDocumentMeta() {
        AjxDocument document = new AnnotationExtractor(clazz).getDocument();
        assertNotNull(document);
        AjxName[] name = document.value();
        assertTrue(name != null && name.length == 1);
        assertEquals("TestDocument", name[0].value());
    }

    @Test
    public void testColumnMeta() {
        List<ColumnMeta> columnMetaList = new AnnotationExtractor(clazz).getColumnMeta();
        assertNotNull(columnMetaList);
        assertTrue(columnMetaList.size() == 3);
        assertNotNull(columnMetaList.get(0).getAnnotation());
        testIdField(columnMetaList);
        testNameField(columnMetaList);
        testComplexTypeField(columnMetaList);
    }

    private void testNameField(List<ColumnMeta> columnMetaList) {
        AjxColumn column = columnMetaList.get(1).getAnnotation();
        assertEquals(2, column.columnOrder());
        AjxName[] name = column.headerName();
        assertTrue(name != null && name.length == 1);
        assertEquals("name", name[0].value());
        assertEquals(AjxLocale.RU, name[0].locale());
    }

    private void testIdField(List<ColumnMeta> columnMetaList) {
        AjxColumn column = columnMetaList.get(0).getAnnotation();
        assertEquals(column.columnOrder(), 1);
        AjxName[] name = column.headerName();
        assertTrue(name != null && name.length == 1);
        assertEquals("id", name[0].value());
        assertEquals(AjxLocale.ENGLISH, name[0].locale());
    }

    private void testComplexTypeField(List<ColumnMeta> columnMetaList) {
        ColumnMeta columnMeta = columnMetaList.get(2);
        AjxColumn columnAnnotation = columnMeta.getAnnotation();
        assertEquals(columnAnnotation.columnOrder(), 3);
        assertTrue(columnAnnotation.complexType());
        AjxName[] name = columnAnnotation.headerName();
        assertTrue(name.length == 0);
        List<ColumnMeta> subColumns = columnMeta.getSubColumns();
        assertNotNull(subColumns);
        assertTrue(subColumns.size() == 1);
        ColumnMeta subColumn = subColumns.get(0);
        AjxColumn subColumnAnnotation = subColumn.getAnnotation();
        AjxName[] subName = subColumnAnnotation.headerName();
        assertEquals("type", subName[0].value());
        assertEquals(AjxLocale.ENGLISH, subName[0].locale());
    }
}
