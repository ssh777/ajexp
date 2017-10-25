package org.ajexp.excel.dto;

import org.ajexp.AjxLocale;
import org.ajexp.annotations.AjxColumn;
import org.ajexp.annotations.AjxDocument;
import org.ajexp.annotations.AjxName;

/**
 * Created by SSh on 19.10.2017.
 */
@AjxDocument(value = @AjxName(value = "TestDocument", locale = AjxLocale.ENGLISH))
public class SimpleDTO {
    @AjxColumn(headerName = @AjxName(value = "id", locale = AjxLocale.ENGLISH), columnOrder = 1)
    private Long id;

    private String name;

    @AjxColumn(headerName = {}, columnOrder = 3, complexType = true)
    private SimpleEnum type;

    public SimpleDTO() {
    }

    public SimpleDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @AjxColumn(headerName = @AjxName(value = "name", locale = AjxLocale.RU), columnOrder = 2)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
