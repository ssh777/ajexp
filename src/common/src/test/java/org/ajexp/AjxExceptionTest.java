package org.ajexp;

import org.ajexp.dto.SimpleDTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

/**
 * Created by SSh on 19.10.2017.
 */
public class AjxExceptionTest extends Assert {

    @Test(expected = AjxException.class)
    public void test11() throws AjxException {
        new SimpleExporter(null, null).exportData(null);
    }

    @Test(expected = AjxException.class)
    public void test12() throws AjxException {
        new SimpleExporter(Collections.EMPTY_LIST, SimpleDTO.class).exportData(null);
    }

    @Test(expected = AjxException.class)
    public void test2() throws AjxException {
        throw new AjxException(new Exception("test"));
    }
}
