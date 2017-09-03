/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.approxteam.crossword.latex.remote;

import java.io.Serializable;

/**
 *
 * @author adamr
 */
public enum LatexPlaceholder implements Serializable{
    CLUESNAMING("%%CLUES%%");
    
    private String placeholder;

    private LatexPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getPlaceholder() {
        return placeholder;
    }
    
    
    
}
