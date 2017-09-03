/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.approxteam.crossword.latex.remote;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author adamrazniewski
 */
@Stateless
public class LatexPDFGenerator implements LatexPDFGeneratorRemote {

    @Override
    public byte[] generatePDF(String clues, String document) throws IllegalArgumentException {
        String documentUUID = getTempUUID();
        String cluesUUID = getTempUUID();
        try {
            saveToFile(clues, cluesUUID + ".tex");
            String cluesPlaceHolder = LatexPlaceholder.CLUESNAMING.getPlaceholder();
            document = document.replace(cluesPlaceHolder, cluesUUID);
            saveToFile(document, documentUUID + ".tex");
            Process pdfLatexProcess = new ProcessBuilder().command("pdflatex", "-halt-on-error " + documentUUID + ".tex").inheritIO().start();
            int exitCode = pdfLatexProcess.waitFor();
            System.out.println("exitCode = " + exitCode);
            if(exitCode == 0) {
                byte[] generatedPDF = readFromFile(documentUUID + ".pdf");
                removeFilesStartingAt(documentUUID);
                removeFilesStartingAt(cluesUUID);
                return generatedPDF;
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(LatexPDFGenerator.class.getName()).log(Level.SEVERE, null, ex);
            return new byte[0];
        }
        return new byte[0];
        
    }
    
    public static void saveToFile(String content, String path) throws IOException {  
        Files.write(Paths.get(path), content.getBytes());
    }
    
    public static byte[] readFromFile(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }
    
    public static void removeFilesStartingAt(String path) throws IOException {
        
        final String finalPath = path;
        Consumer<Path> action = new Consumer<Path>() {
            @Override
            public void accept(Path t) {
               if(t.toString().contains(finalPath)) {
                   try {
                       Files.delete(t);
                   } catch (IOException ex) {
                       Logger.getLogger(LatexPDFGenerator.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            }
        };
        Files.newDirectoryStream(Paths.get("./")).iterator().forEachRemaining(action);
    }
    
    public static String getTempUUID() {
        String tempUUID = UUID.randomUUID().toString().replaceAll("-", "");
        return tempUUID;
    }
}
