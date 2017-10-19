package org.ajexp;

import java.util.Locale;

/**
 * Created by SSh on 29.09.2017.
 */
public enum AjxLocale {
    NULL(null),
    ENGLISH(Locale.ENGLISH),
    US(Locale.US),
    UK(Locale.UK),
    FRENCH(Locale.FRENCH),
    GERMAN(Locale.GERMAN),
    ITALIAN(Locale.ITALIAN),
    JAPANESE(Locale.JAPANESE),
    KOREAN(Locale.KOREAN),
    CHINESE(Locale.CHINESE),
    SIMPLIFIED_CHINESE(Locale.SIMPLIFIED_CHINESE),
    TRADITIONAL_CHINESE(Locale.TRADITIONAL_CHINESE),
    FRANCE(Locale.FRANCE),
    GERMANY(Locale.GERMANY),
    ITALY(Locale.ITALY),
    JAPAN(Locale.JAPAN),
    KOREA(Locale.KOREA),
    CANADA(Locale.CANADA),
    CANADA_FRENCH(Locale.CANADA_FRENCH),
    RU(new Locale("ru", "RU"));

    Locale locale;

    AjxLocale(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }
}
