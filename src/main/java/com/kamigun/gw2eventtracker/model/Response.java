/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamigun.gw2eventtracker.model;

/**
 *
 * @author shinobi
 */
public class Response {

    public static final String RESULT_OK = "ok";
    public static final String RESULT_KO = "ko";
    private String result = RESULT_OK;
    private String message;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
