   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kobikan2
 */
package coe528.project;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;
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
        BufferedReader br = null;
        Login main = new Login();
        
        int prime=0,e=0,d0=0;
        KeyGen k=new KeyGen();
        
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
                                prime= k.Findp();
                                e=k.Finde(prime);
                                d0=k.ExtendedEuclidean(prime,e);
                                try{
                                    writer.write("Username: " + u + " Password: " + p + " Key: "+prime+" e: "+e+" d0: "+d0+ "\n");
                                    writer.close();
                                    writer = new BufferedWriter(new FileWriter(login.file, true));
                                }
                                catch(IOException ioe) {
                                    ioe.printStackTrace();
                                }
                                System.out.println("\nUser has been added");
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
                do{
                    System.out.println("Select:\n1. Encryption\n2. Decryption\n3. Backup\n4. Logout");
                    String uc = sc.next();
                    String check;       
                    String code;
                    Enc encrypt =new Enc();
                    Dec decrypt =new Dec();
                    if(uc.equals("1")){
                        System.out.println("Type the word you want to encypt");
                        code=sc.next();
                        try{
                             br = new BufferedReader(new FileReader(login.file));
                            
                            while((check = br.readLine()) != null) {
                                String[] wordLine = check.split(" ");
                                for (int i=0;i<wordLine.length;i++) {
                                    if(wordLine[i].equals("Key:"))
                                        prime=Integer.parseInt(wordLine[i+1]);
                                    if(wordLine[i].equals("e:"))
                                        e=Integer.parseInt(wordLine[i+1]);
                                }
                            }   
                            br.close();  
                        }catch(FileNotFoundException ex) {
                            System.out.println("\nUnable to open file.\n\n");                
                        }
                        catch(IOException ex) {
                            System.out.println("\nError reading file.\n\n");
                        }
                        encrypt.ModE(code, username, prime, e);
                    }
                
                    if(uc.equals("2")){
                        try{
                                br = new BufferedReader(new FileReader(login.file));

                                while((check = br.readLine()) != null) {
                                     String[] wordLine = check.split(" ");
                                     for (int i=0;i<wordLine.length;i++) {
                                         if(wordLine[i].equals("Key:"))
                                        prime=Integer.parseInt(wordLine[i+1]);
                                         if(wordLine[i].equals("d0:"))
                                             d0=Integer.parseInt(wordLine[i+1]);
                                         
                                     }
                                 }   
                                 br.close();  
                             }catch(FileNotFoundException ex) {
                                 System.out.println("\nUnable to open file.\n\n");                
                             }
                             catch(IOException ex) {
                                 System.out.println("\nError reading file.\n\n");
                             }
                        System.out.println(prime+""+d0+username);
                        decrypt.ModD(prime, d0, username);
                    }
                
                    if(uc.equals("3")){
                        System.out.println("\nBackup Facility.\nPlease enter the path, name, and extension of the file you wish to backup.");
                        String f = sc.nextLine();
                        Backup sign = new Backup();
                        sign.backupAlgorithm(f);
                    }
                
                   if(uc.equals("4")){
                       logout = true;
                       System.out.println("\nLogout successful.");
                   }
                }
                while(logout !=true);  
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