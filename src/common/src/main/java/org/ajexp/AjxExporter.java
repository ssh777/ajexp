package org.ajexp;

import java.io.OutputStream;

/**
 * Created by SSh on 19.10.2017.
 */
public interface AjxExporter {
    int exportData(OutputStream outputStream) throws AjxException;
}
