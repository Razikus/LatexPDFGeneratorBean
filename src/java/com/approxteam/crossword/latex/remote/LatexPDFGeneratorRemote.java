/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.approxteam.crossword.latex.remote;

import javax.ejb.Remote;

/**
 *
 * @author adamrazniewski
 */
@Remote
public interface LatexPDFGeneratorRemote {
    public byte[] generatePDF(String clues, String document) throws IllegalArgumentException;
}
