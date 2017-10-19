package org.ajexp;

import org.ajexp.annotations.AjxDocument;
import org.ajexp.dto.SimpleDTO;

import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

/**
 * Created by SSh on 19.10.2017.
 */
public class SimpleExporter extends AbstractExporter {
    public SimpleExporter(Collection<?> data) {
        super(data, SimpleDTO.class, AjxLocale.ENGLISH);
    }

    @Override
    protected int exportInternal(OutputStream outputStream, AjxDocument documentMeta, List<ColumnMeta> columnsMeta) throws AjxException {
        return data.size();
    }
}
