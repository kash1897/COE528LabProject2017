/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author kashyappandya
 */
public class Backup {
    public void backupAlgorithm(String nameOfFile){
        Scanner scan = new Scanner(System.in);
        String f = nameOfFile;
        String currentLine;
        int count = 0;
        try{
            File filetoback = new File(f);
            do{
                if(filetoback.exists() && !filetoback.isDirectory() ){
                    File tempFile = new File(filetoback.getAbsolutePath() + ".tmp");
                    BufferedReader buffreader = new BufferedReader(new FileReader(filetoback));
                    PrintWriter pwriter = new PrintWriter(new FileWriter(tempFile));
                            
                    while ((currentLine = buffreader.readLine()) != null) {
                        pwriter.println(currentLine);
                        pwriter.flush();
                    }
                    
                    pwriter.close();
                    buffreader.close();
                    String name = filetoback.getName();
                    
                    String[] namearr = name.split("");
                    
                    for(int i = 0; i < namearr.length; i++){
                        //System.out.println(namearr[i] + "\n");
                        if (namearr[i].equals(".")){
                            count = i;
                        }
                    }
                    
                    namearr[count - 1] = namearr[count - 1] + "_backup";
                              
                    name = "";
                    for(int i = 0; i < namearr.length; i++){
                        name += namearr[i];
                    }
                    
                    File changeto = new File(name);
                    if (!tempFile.renameTo(changeto)){
                        System.out.println("Could not rename file");
                    } 
                    
                    System.out.println("\nBackup complete.");
                }
                else{
                    System.out.println("\nThe file you are trying to backup does not exist. Please try again.");
                    System.out.println("\nBackup.\nPlease enter the path, name, and extension of the file you wish to backup.");
                    f = scan.nextLine();
                    filetoback = new File(f);
                }
            }
            while(!filetoback.exists());
            
            System.out.println("\n");
        
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
