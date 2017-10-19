package org.ajexp;

import org.ajexp.annotations.AjxColumn;
import org.ajexp.annotations.AjxDocument;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by SSh on 19.10.2017.
 */
public class AnnotationExtractor {

    private Class clazz;

    public AnnotationExtractor(Class clazz) {
        this.clazz = clazz;
    }

    public AjxDocument getDocument(){
        return (AjxDocument) clazz.getAnnotation(AjxDocument.class);
    }

    public List<ColumnMeta> getColumnMeta(){
        return getAnnonatedColumns(clazz);
    }

    private Class<?> getMemberType(Member member) {
        return member instanceof Field
                ? ((Field)member).getType()
                : ((Method)member).getReturnType();
    }

    private List<ColumnMeta> getAnnonatedColumns(Class t) {
        List<ColumnMeta> excelColumns = new ArrayList<>();
        for (Field field : t.getDeclaredFields()) {
            AjxColumn cellAnnotation = field.getAnnotation(AjxColumn.class);
            if (cellAnnotation != null) {
                field.setAccessible(true);
                addColumnMeta(excelColumns, field, cellAnnotation);
            }
        }
        for (Method method : t.getDeclaredMethods()) {
            AjxColumn cellAnnotation = method.getAnnotation(AjxColumn.class);
            if (cellAnnotation != null) {
                method.setAccessible(true);
                addColumnMeta(excelColumns, method, cellAnnotation);
            }
        }
        Collections.sort(excelColumns, new Comparator<ColumnMeta>() {
            @Override
            public int compare(ColumnMeta o1, ColumnMeta o2) {
                return o1.getAnnotation().columnOrder() > o2.getAnnotation().columnOrder() ? 1 : -1;
            }
        });
        return excelColumns;
    }

    private void addColumnMeta(List<ColumnMeta> excelColumns, Member method, AjxColumn cellAnnotation) {
        List<ColumnMeta> subColumns = null;
        if (cellAnnotation.complexType()) {
            subColumns = getAnnonatedColumns(getMemberType(method));
        }
        excelColumns.add(new ColumnMeta(cellAnnotation, method, subColumns));
    }
}
