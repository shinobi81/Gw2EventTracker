/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamigun.gw2eventtracker.model.gw2;

/**
 *
 * @author shinobi
 */
public class GwServiceError {

    private Integer error;
    private Integer product;
    private Integer module;
    private Integer line;
    private String text;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getModule() {
        return module;
    }

    public void setModule(Integer module) {
        this.module = module;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
