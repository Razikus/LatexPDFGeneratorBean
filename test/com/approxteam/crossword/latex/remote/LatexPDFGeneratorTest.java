/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.approxteam.crossword.latex.remote;

import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author adamrazniewski
 */
public class LatexPDFGeneratorTest {
    
    public LatexPDFGeneratorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }



    /**
     * Test of generatePDF method, of class LatexPDFGenerator.
     */
    @Test
    public void testGeneratePDF() throws Exception {
        System.out.println("generatePDF");
        String document = "\\documentclass[12pt]{article}\n" +
"\\title{This is the title}\n" +
"\\author{Author One \\\\ Author Two}\n" +
"\\date{29 February 2004}\n" +
"\\begin{document}\n" +
"\\maketitle\n" +
"This is the content of this document.\n" +
"This is the 2nd paragraph.\n" +
"\\end{document}";
        LatexPDFGenerator generator = new LatexPDFGenerator();
        
        System.out.println(generator.generatePDF(document));
    }
}
