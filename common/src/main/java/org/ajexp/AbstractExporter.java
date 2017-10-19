package org.ajexp;

import org.ajexp.annotations.AjxDocument;
import org.ajexp.annotations.AjxName;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

/**
 * Created by SSh on 19.10.2017.
 */
public abstract class AbstractExporter implements AjxExporter {
    protected Collection<?> data;
    protected Class clazz;
    protected AjxLocale locale;
    protected OutputStream outputStream;

    public AbstractExporter(Collection<?> data, Class clazz) {
        this(data, clazz, AjxLocale.NULL);
    }

    public AbstractExporter(Collection<?> data, Class clazz, AjxLocale locale) {
        this.data = data;
        this.clazz = clazz;
        this.locale = locale;
    }

    @Override
    public int exportData(OutputStream outputStream) throws AjxException {
        if (outputStream == null || !isValidParams()) {
            throw new AjxException("invalid params for export");
        }

        return exportInternal(outputStream, getDocumentMeta(), getColumnMeta());
    }

    protected boolean isValidParams() {
        return !(data == null || clazz == null || locale == null);
    }

    protected List<ColumnMeta> getColumnMeta() {
        return new AnnotationExtractor(this.clazz).getColumnMeta();
    }

    protected AjxDocument getDocumentMeta() {
        return new AnnotationExtractor(this.clazz).getDocument();
    }

    protected String getLocalizedName(AjxName[] names, AjxLocale locale) {
        for (AjxName name: names) {
            if (locale.equals(name.locale())) {
                return name.value();
            }
        }
        return null;
    }

    protected Object getMemberData(Member member, Object data) throws InvocationTargetException, IllegalAccessException {
        return member instanceof Field
                ? ((Field)member).get(data)
                : ((Method)member).invoke(data);
    }

    protected abstract int exportInternal(OutputStream outputStream, AjxDocument documentMeta, List<ColumnMeta> columnsMeta)
            throws AjxException;
}
