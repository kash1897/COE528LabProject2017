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
import java.io.*;
/**
 *
 * @author kashyappandya
 */
public class Login {
    private String file = "User_Profiles.txt";
    private static int count = 0;
            
    public boolean Auth(String x, String select){
        try {
            String line = null;
            int keepCount = 0;
            FileReader Reader = new FileReader(file);
            BufferedReader writer = new BufferedReader(Reader);
            keepCount = 0;
            
            if(select.equals("user")){
                while((line = writer.readLine()) != null) {
                    String[] wordLine = line.split(" ");
                    for (String word : wordLine){
                        if (x.equals(word)){
                            writer.close();
                            count = keepCount;
                            //System.out.println("\n\n\njh" + count);
                            return true;
                        }
                    }
                    keepCount++;
                } 
            }
            
            else{
                while((line = writer.readLine()) != null){
                    String[] wordLine = line.split(" ");
                    for(String word: wordLine){
                        if(keepCount == count){
                            if(x.equals(word)){
                                writer.close();
                                return true;
                            }  
                        }
                    }
                    keepCount++;
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
        
        String filetle = "";
        int prime = 0,e = 0,phi=0,n=0,e2=0;
        //initialized method calling
//        Findp modp = new Findp();
//        Finde mode = new Finde();
//        Findd modd;
EncDec init;
EncDec algo;
         Findphi modphi = new Findphi();
        try {
            //Creates User text file to hold login information
            File file = new File("User_Profiles.txt");
            //If file does not exists then it creates it
            if (!file.exists()) {
                file.createNewFile();
            }
            writer = new BufferedWriter(new FileWriter(file, true));
            if(!main.Auth("admin", "user")){
                writer.write("Username: admin Password: pass Hash: " + (new Hash().getHashMD5("pass")) + "\n");
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
            Hash securityKey = new Hash();
            
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
            if(!username.equals("")){
                do{
                    if(username.equals("admin")){
                        if(!login.Auth(username, "user")){
                            System.out.println("Login denied. Username or Password does not exist.");
                            System.out.println("\nUsername: ");
                            username = sc.nextLine();
                        }
                    }
                    if(!username.equals("admin")){
                        if(!login.Auth(username, "user")){
                            System.out.println("Login denied. Username or Password does not exist.");
                            System.out.println("\nUsername: ");
                            username = sc.nextLine();
                        }
                    }
                } 
                while(!login.Auth(username, "user"));
            }
            
            System.out.println("\nPassword:");
            String password = sc.nextLine();
            if(!password.equals("pass") || !password.equals("")){
                String hash = securityKey.getHashMD5(password);
                if(!login.Auth(hash, "password")){
                    do{
                        if(!login.Auth(hash, "pass")){
                            System.out.println("Login denied. Username or Password does not match.");
                            System.out.println("\nPassword:");
                            hash = sc.nextLine();
                        }
                    }
                    while(!login.Auth(hash, "password"));
                }
            }
            
            if(access.equals("Administrator") || access.equals("administrator")){
                do{
                    System.out.println("\nSelect:\n1. Add Profile\n2. Remove Profile\n3. Logout");
                    String ad1 = sc.nextLine();
                    logout = false;
      
                    if(ad1.equals("1")){
                        System.out.println("\nEnter username: ");
                        String u = sc.nextLine();
                        do{
                            if(!login.Auth(u, "user")){
                                System.out.println("\nEnter password: ");
                                String p = sc.nextLine();
                                init=new RSA(username);
                                prime = init.Findp();
                                e = init.Finde(prime);
                                phi=modphi.Find();
                                n=modphi.n;
                                e2=init.Finde(phi);
                                
                                String gotHash = null;
                                gotHash = securityKey.getHashMD5(p);
                                
                                try{
                                    writer.write("Username: " + u + " Password: " + p + " Key: " + prime +" e: " + e +" phi: "+phi+" n: "+n+" e2: "+e2+ " Hash: " + gotHash + "\n");
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
                    logout = false;
                    System.out.println("\nSelect:\n1. Encryption\n2. Decryption\n3. Backup\n4. Logout");
                    String uc = sc.nextLine();
                    
                    String check;       
                    String code;
                    String type;
                    boolean valid=false;
//                    Enc encrypt =new Enc(username);

                    if(uc.equals("1")){
                        System.out.println("Which method would you like to use, RSA or Modular(Mod)");
                        type=sc.nextLine();
                        if(type.equalsIgnoreCase("mod")){
                            algo=new Modular(username);
                        System.out.println("Type the word you want to encypt");
                        code = sc.nextLine();
                        try{
                             br = new BufferedReader(new FileReader(login.file));
                            
                            while((check = br.readLine()) != null) {
                                if(check.contains(username)){
                                String[] wordLine = check.split(" ");
                                for (int i=0;i<wordLine.length;i++) {
                                    if(wordLine[i].equals("Key:"))
                                        prime=Integer.parseInt(wordLine[i+1]);
                                    System.out.println(prime);
                                    if(wordLine[i].equals("e:"))
                                        e=Integer.parseInt(wordLine[i+1]);
                                }
                            }
                            }   
                            br.close();  
                        }catch(FileNotFoundException ex) {
                            System.out.println("\nUnable to open file.\n\n");                
                        }
                        catch(IOException ex) {
                            System.out.println("\nError reading file.\n\n");
                        }
                        System.out.println("What file do you want it to be stored under?");
                        while(valid==false){
                        filetle=sc.nextLine();
                        File test=new File(filetle+"."+username);
                        File testdec=new File(filetle+"."+username+"d");
                        if(test.exists()==false)
                            valid=true;
                        else if (testdec.exists()==false)
                            valid=true;
                        else
                                System.out.println("Enter another name");
                        }
                        algo.Enc(code, filetle, prime, e);
                        }
                        else if(type.equalsIgnoreCase("rsa")){
                            algo=new RSA(username);
                            System.out.println("Type the word you want to encypt");
                        code = sc.next();
                        try{
                             br = new BufferedReader(new FileReader(login.file));
                            
                            while((check = br.readLine()) != null) {
                                 if(check.contains(username)){
                                String[] wordLine = check.split(" ");
                                for (int i=0;i<wordLine.length;i++) {
                                    if(wordLine[i].equals("phi:"))
                                        phi=Integer.parseInt(wordLine[i+1]);
                                    if(wordLine[i].equals("n:"))
                                        n=Integer.parseInt(wordLine[i+1]);
                                    if(wordLine[i].equals("e2:"))
                                        e2=Integer.parseInt(wordLine[i+1]);
                                    System.out.println(e2);
                                }
                                 }
                            }   
                            br.close();  
                        }catch(FileNotFoundException ex) {
                            System.out.println("\nUnable to open file.\n\n");                
                        }
                        catch(IOException ex) {
                            System.out.println("\nError reading file.\n\n");
                        }
                        System.out.println("What file do you want it to be stored under?");
                        while(valid==false){
                        filetle=sc.next();
                        File test=new File(filetle+"."+username);
                        File testdec=new File(filetle+"."+username+"d");
                        if(test.exists()==false)
                            valid=true;
                        else if (testdec.exists()==false)
                            valid=true;
                        else
                                System.out.println("Enter another name");
                        }
                        algo.Enc(code, filetle, n, e);
                        }
                    }
                
                    if(uc.equals("2")){
                        System.out.println("what is the file you want to decrypt");
                        filetle=sc.nextLine();
                        String filetlem=filetle+".mod."+username;
                        String filetler=filetle+".rsa."+username;;
                        File temp=new File(filetlem);
                        File tempr=new File(filetler);
                        if(temp.exists()){
                            try{
                                br = new BufferedReader(new FileReader(login.file));

                                while((check = br.readLine()) != null) {
                                     String[] wordLine = check.split(" ");
                                     for (int i=0;i<wordLine.length;i++) {
                                         if(wordLine[i].equals("Key:"))
                                        prime=Integer.parseInt(wordLine[i+1]);
                                         if(wordLine[i].equals("e:"))
                                        e=Integer.parseInt(wordLine[i+1]);
//                                         System.out.println(prime);
//                                         if(wordLine[i].equals("d0:"))
//                                             d0=Integer.parseInt(wordLine[i+1]);
//                                         System.out.println(d0);
                                     }
                                 }   
                                 br.close();  
                             }catch(FileNotFoundException ex) {
                                 System.out.println("\nUnable to open file.\n\n");                
                             }
                             catch(IOException ex) {
                                 System.out.println("\nError reading file.\n\n");
                             }
                        algo=new Modular(username);
                       
                        algo.Dec(prime,algo.ExtendedEuclidean(prime,e), filetle);
                        }
                        else if(tempr.exists()){
                            try{
                                br = new BufferedReader(new FileReader(login.file));

                                while((check = br.readLine()) != null) {
                                     String[] wordLine = check.split(" ");
                                     for (int i=0;i<wordLine.length;i++) {
                                         if(wordLine[i].equals("phi:"))
                                        phi=Integer.parseInt(wordLine[i+1]);
                                         if(wordLine[i].equals("e2:"))
                                        e2=Integer.parseInt(wordLine[i+1]);
                                         if(wordLine[i].equals("n:"))
                                             n=Integer.parseInt(wordLine[i+1]);
                                     }
                                 }   
                                 br.close();  
                             }catch(FileNotFoundException ex) {
                                 System.out.println("\nUnable to open file.\n\n");                
                             }
                             catch(IOException ex) {
                                 System.out.println("\nError reading file.\n\n");
                             }
                        algo=new RSA(username);
                        
                        algo.Dec(prime,algo.ExtendedEuclidean(n,e2), filetle);
                        
                    }
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