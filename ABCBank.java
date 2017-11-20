/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.lab5;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

/**
 *
 * @author kashyappandya
 */
public class ABCBank {
    private static ArrayList<Client> db = new ArrayList();
    private String file = "database.txt";
    
    public boolean Authenticate(String s){
        //System.out.println("In authenticate");
        boolean is = false;
        String line = null;
        
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            
            while((line = br.readLine()) != null) {
                String[] wordLine = line.split(" ");
                for (String word : wordLine) {
                    if (s.equals(word)){
                        is = true;
                    }
                }
            }   
            br.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + file + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '"+ file + "'");
        }
        if (is == true){
            return true;
        }
        return false; //if user is in file, returns true...NEEDS TO BE CHANGED TO RETURN TRUE ONCE
        //FILE READER IS WORKING
    }
    
    public int Index(String u){
        //Search file for that particular index...and then return the line number as the 
        //index parameter
        String line = null;
        int i;
        int j = 0;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            Scanner sc = new Scanner(new File(file));
            //ABCBank k = new ABCBank();
            while((line = br.readLine()) != null) {
                boolean verify = line.toLowerCase().contains(u.toLowerCase());
                //System.out.println(line);
                if(verify){
                    i = j;
                    br.close();
                    sc.close();
                    return i;
                }
                j++;
            }
            br.close();
            sc.close();
        } 
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + file + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '"+ file + "'");
        }
        return 0;
    }
    
    public static void main(String[] args){
        BufferedWriter bw = null;
        boolean logout = false; 
        
        //Write to file initialized 
        try {
            File file = new File("database.txt");
            
            if (!file.exists()) {
                file.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(file, true));
            //System.out.println("File written Successfully");
        } 
        catch (IOException ioe) {
	   ioe.printStackTrace();
	}
        
        do{
            Scanner scan = new Scanner(System.in);
            ABCBank ABC = new ABCBank();
            
            System.out.println("Welcome to the ABC International Bank. \nPlease enter your role [Manager or Customer]: (Press Enter to exit)");
            String role = scan.nextLine();
            if (!(role.equals("Manager") || role.equals("manager") || role.equals("Customer") || role.equals("customer") || role.equals(""))){
                System.out.println("Please enter the appropriate role again.");
                role = scan.nextLine();
            }
            if (role.equals("")){
                break;
            }
            
            System.out.println("\nPlease enter your username: ");
            String username = scan.nextLine();
            //DOES THIS USER EXIST? CHECK FILE.
            if (!username.equals("admin")){
                do{
                    if(!ABC.Authenticate(username)){
                        System.out.println("Login denied. Username does not exist within file.");
                        System.out.println("\nPlease enter your username: ");
                        username = scan.nextLine();
                    }
                } 
                while(!ABC.Authenticate(username));
            }
            System.out.println("\nPlease enter your password: ");
            String password = scan.nextLine();
            if (!password.equals("admin")){
                do{
                    if(!ABC.Authenticate(password)){
                        System.out.println("Login denied. Password does not match stored password.");
                        System.out.println("\nPlease enter your password: ");
                        password = scan.nextLine();
                    }
                }
                while(!ABC.Authenticate(password));
            }
            if (password.equals("")){
                throw new IllegalArgumentException("ERROR: Username, password, or role field cannot be blank.\n");
            }
               
            if (role.equals("Manager") || role.equals("manager")){
                //System.out.println("M");
                //ABC.db.clear();
                ABC.db.add(Manager.getInstance());
                
                //index = 0;
                if(!ABC.Authenticate(username)){
                    try{
                        bw.write("Username: " + username + " Password: " + password + " Role: " + role);  
                    }
                    catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
                do{
                    System.out.println("\nEnter A to add user profiles. Enter R to remove user profiles. Enter L to logout.");
                    String input = scan.nextLine();
                    
                    if (input.equals("A") || input.equals("a")){
                        System.out.println("\nEnter username: ");
                        String u = scan.nextLine();
                        //if user is not in file, add user...
                        if (!ABC.Authenticate(u)){
                            System.out.println("\nEnter password: ");
                            String p = scan.nextLine();
                            ABC.db.add(new Customer(u, p, "Customer"));
                            try{
                                bw.write("\nUsername: " + u + " Password: " + p + " Role: Customer");
                                bw.close();
                                bw = new BufferedWriter(new FileWriter(ABC.file, true));
                            }
                            catch (IOException ioe) {
                                ioe.printStackTrace();
                            }
                            
                            System.out.println("\nEnter Chequing amount: (Enter '00' to set a default balance of $20.)");
                            double c = scan.nextDouble();
                            Customer mainCustomer = (Customer) ABC.db.get(ABC.db.size() - 1);
                            if (c == 0.0){
                                mainCustomer.setchequing(20.00);
                            }
                            else{
                                mainCustomer.setchequing(c);
                            }
                            
                            System.out.println("\nEnter Savings amount: (Enter '00' for no savings account.)");
                            double s = scan.nextDouble();
                            scan.nextLine();
                            mainCustomer.setsavings(s);
                        }
                        else 
                            throw new IllegalArgumentException("User is already in database."); 
                    }
                    
                    if (input.equals("R") || input.equals("r")){
                        System.out.println("\nEnter username of profile owner: ");
                        String toBeRemoved = scan.nextLine();
                        
                        int indexCheck = ABC.Index(toBeRemoved);
                        //System.out.println(indexCheck);
                        Customer c = (Customer) ABC.db.get(indexCheck);
                        //System.out.println("\n\nIn remove.");
                        
                        try {
                            File in = new File(ABC.file);
                            if (!in.isFile()) {
                                System.out.println("Parameter is not an existing file");
                                return;
                            }
                            
                            File tempFile = new File(in.getAbsolutePath() + ".tmp");
                            BufferedReader br = new BufferedReader(new FileReader(ABC.file));
                            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                            String line ;
                            
                            while ((line = br.readLine()) != null) {
                                if (!line.trim().equals(c.toString())) {
                                    pw.println(line);
                                    pw.flush();
                                }
                            }
                            pw.close();
                            br.close();
                            
                            if (!in.delete()) {
                                System.out.println("Could not delete file");
                                return;
                            }
                            if (!tempFile.renameTo(in))
                                System.out.println("Could not rename file");
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }  
                    }
                    if (input.equals("L") || input.equals("l")){
                        logout = true;
                        System.out.println("\nLogout successful.\n\n");
                    }
                }
                while(logout != true);
            }
            
            if (role.equals("Customer") || role.equals("customer")){
                //System.out.println("C");
                logout = false;
                System.out.println("\nGood day, " + username.toUpperCase() + "! \nWhat would you like to do?");
                do{
                    System.out.println("\nEnter T to transfer money. Enter B to check balance.  Enter L to logout.");
                    String firstIn = scan.nextLine(); 
                    if (firstIn.equals("L") || firstIn.equals("l")){
                        logout = true; 
                        System.out.println("\nLogout successful.\n\n");
                    }
                    
                    if (firstIn.equals("B") || firstIn.equals("b")){
                        //find the username in the file using FileReader, and then return the line
                        //where the username was found (which is the index)...IMPLEMENT FILEREADER
                        int index = ABC.Index(username);
                        //System.out.println("index is: " + index);
                        //System.out.println("Size:" + ABC.db.size());
                        Customer c = (Customer) ABC.db.get(index);
                        //System.out.println("After ABC.db.get(i)\n\n");
                        System.out.println("\nWhich account balance would you like to inquire? Enter C for chequing. Enter S for savings.");
                        String acc = scan.nextLine();
                        if (acc.equals("C") || acc.equals("c")){
                            System.out.println("\nChequing Balance: $" + c.getchequing());
                        }
                        if (acc.equals("S") || acc.equals("s")){
                            if (c.getsavings() != 0){
                                System.out.println("\nSavings Balance: $" + c.getsavings());
                            }
                            else{
                                System.out.println("\nNo savings account.");
                            }  
                        }
                    }
                    
                    if (firstIn.equals("T") || firstIn.equals("t")){
                        int index = ABC.Index(username);
                        Customer c = (Customer) ABC.db.get(index);
                        System.out.println("\nEnter S for Chequing to Savings. Enter C for Savings to Chequing. ");
                        String transfer = scan.nextLine();
                        System.out.println("\nHow much money should be transferred? ");
                        double transferAmount = scan.nextDouble();
                        
                        if (transfer.equals("S") ||transfer.equals("s")){
                            c.getAccount().transferToSaving(transferAmount);
                        }
                        if (transfer.equals("C") || transfer.equals("c")){
                             c.getAccount().transferToChequing(transferAmount);
                        }
                        scan.nextLine();
                    }
                }
                while (logout != true);   
            }
        }
        while (logout == true);
        
        try{
            bw.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
