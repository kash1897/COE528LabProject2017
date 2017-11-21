/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kashyappandya
 */
public class Login {
    public String file = "User_Profiles.txt";
    
    public boolean Auth(String x, String select){
        String line = null;
        
        try {
            FileReader Reader = new FileReader(file);
            BufferedReader writer = new BufferedReader(Reader);
            
            while((line = writer.readLine()) != null) {
                String[] wordLine = line.split(" ");
                for (String word : wordLine) {
                    if (x.equals(word)){
                        return true;
                    }
                }
            }   
            writer.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("\nUnable to open file.\n\n");                
        }
        catch(IOException ex) {
            System.out.println("\nError reading file.\n\n");
        }
        return false;
    }
    
    
    public static void main(String[] args){
        boolean logout = false; 
        BufferedWriter writer = null;
        BufferedReader reader = null;
        Login main = new Login();
        
        try {
            File file = new File("User_Profiles.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            writer = new BufferedWriter(new FileWriter(file, true));
            if(!main.Auth("admin", "user")){
                writer.write("Username: admin Password: pass\n");
            }
            writer.close();
            writer = new BufferedWriter(new FileWriter(file, true));
        } 
        catch (IOException ioe) {
	   ioe.printStackTrace();
	}
        
        do{
            Scanner sc = new Scanner(System.in);
            Login login = new Login();
            
            System.out.println("Welcome to XYZ Encryption/Decryption and Backup Tool.\n");
            
            System.out.println("Enter access level (Administrator or User; Press Enter to exit):");
            String access = sc.nextLine();
            if(access.equals("")){
                break;
            }
            while(!(access.equals("Administrator") || access.equals("administrator") || access.equals("User") || access.equals("user"))){
                System.out.println("\nPlease enter appropriate access level:");
                access = sc.nextLine();
            }
            
            System.out.println("\nUsername:");
            String username = sc.nextLine();
            if(!username.equals("admin")){
                do{
                    if(!username.equals("admin")){
                        if(!login.Auth(username, "user")){
                            System.out.println("Login denied. Username does not exist.");
                            System.out.println("\nUsername: ");
                            username = sc.nextLine();
                        }
                    }
                } 
                while(!login.Auth(username, "user"));
            }
            
            System.out.println("\nPassword:");
            String password = sc.nextLine();
            if(!password.equals("admin") || !password.equals(" ")){
                do{
                    if(!login.Auth(password, "pass")){
                        System.out.println("Login denied. Password does not match.");
                        System.out.println("\nPassword:");
                        password = sc.nextLine();
                    }
                }
                while(!login.Auth(password, "pass"));
            }
            
            if(access.equals("Administrator") || access.equals("administrator")){
                do{
                    System.out.println("\nSelect:\n1. Add Profile\n2. Remove Profile\n3. Logout");
                    String ad1 = sc.nextLine();
                    if(ad1.equals("1")){
                        System.out.println("\nEnter username: ");
                        String u = sc.nextLine();
                        do{
                            if(!login.Auth(u, "user")){
                                System.out.println("\nEnter password: ");
                                String p = sc.nextLine();
                                try{
                                    writer.write("Username: " + u + " Password: " + p + " Key: \n");
                                    writer.close();
                                    writer = new BufferedWriter(new FileWriter(login.file, true));
                                }
                                catch(IOException ioe) {
                                    ioe.printStackTrace();
                                }
                            }
                            else{
                                System.out.println("\nUsername already exists.\nPlease try again.\n");
                            }
                        }
                        while(!login.Auth(u, "user"));
                    }
                    
                    if(ad1.equals("2")){
                        System.out.println("\nEnter username of profile to be removed: ");
                        String remove = sc.nextLine();
                        String line;
                        if(login.Auth(remove, "user")){
                            try {
                                File in = new File(login.file);
                                if (!in.isFile()) {
                                    System.out.println("Parameter is not an existing file");
                                    break;
                                }
                                File tFile = new File(in.getAbsolutePath() + ".tmp");
                                BufferedReader buffreader = new BufferedReader(new FileReader(login.file));
                                PrintWriter pwriter = new PrintWriter(new FileWriter(tFile));
          
                                while ((line = buffreader.readLine()) != null) {
                                    if (!line.trim().contains("Username: " + remove)) {
                                        pwriter.println(line);
                                        pwriter.flush();
                                    }
                                }
                                pwriter.close();
                                buffreader.close();
                                if (!in.delete()) {
                                    System.out.println("Could not delete file");
                                    return;
                                }
                                if (!tFile.renameTo(in))
                                    System.out.println("Could not rename file");
                            } catch (FileNotFoundException ex) {
                                ex.printStackTrace();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            System.out.println("\nUser has been removed.");
                        }  
                        else{
                            System.out.println("\nUser does not exist.");
                        }
                    }
                    
                    if(ad1.equals("3")){
                        logout = true;
                        System.out.println("\nLogout Successful.");
                    }
                }
                while(logout != true);
            }
            
            if(access.equals("User") || access.equals("user")){
                System.out.println("Select:\n1. Encryption\n2. Decryption\n3. Backup");
                String uc = sc.nextLine();
                
                if(uc.equals("1")){
                    
                }
                
                if(uc.equals("2")){
                    
                }
                
                if(uc.equals("3")){
                    
                }
            }
            
            System.out.println("\n");
        }
        while(logout == true);
        
        try{
            writer.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        System.out.println("\nThank you for using the tool.");   
    }  
}
