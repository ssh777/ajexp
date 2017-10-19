package org.ajexp;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by SSh on 19.10.2017.
 */
public class AjxExceptionTest extends Assert {

    @Test(expected = AjxException.class)
    public void test1() throws AjxException {
        throw new AjxException("test");
    }

    @Test(expected = AjxException.class)
    public void test2() throws AjxException {
        throw new AjxException(new Exception("test"));
    }
}
