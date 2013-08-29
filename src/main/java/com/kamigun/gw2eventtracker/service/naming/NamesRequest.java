/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamigun.gw2eventtracker.service.naming;

/**
 *
 * @author shinobi
 */
public class NamesRequest {

    public static final String LANG_EN = "en";
    public static final String LANG_DE = "de";
    public static final String LANG_ES = "es";
    public static final String LANG_FR = "fr";
    private String lang = LANG_EN;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
