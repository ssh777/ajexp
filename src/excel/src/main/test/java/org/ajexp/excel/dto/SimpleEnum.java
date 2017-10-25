package org.ajexp.excel.dto;

import org.ajexp.AjxLocale;
import org.ajexp.annotations.AjxColumn;
import org.ajexp.annotations.AjxName;

/**
 * Created by SSh on 25.10.2017.
 */
public enum SimpleEnum {
    NONE("NONE"),
    SIMPLE("SIMPLE");

    @AjxColumn(headerName = @AjxName(value = "type", locale = AjxLocale.ENGLISH), columnOrder = 1)
    String name;

    SimpleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
