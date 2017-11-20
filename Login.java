   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kobikan2
 */
import java.io.*;
import java.util.*;
public class Login {
    private String log="login.txt";
    private String temptxt;
    private int key;
    public Login(){
        
    }
 public void  Inittxt(File x) throws IOException{
if(x.exists()==false){
    x.createNewFile();
}
 }
 
 public boolean Authenticate() throws IOException{
     File login=new File("login.txt");
             BufferedReader br=null;
             Scanner scan=new Scanner(System.in);
             String s,user,pass;
             String arr[];
             try{
                  br=new BufferedReader(new FileReader(log));
                  BufferedWriter bw=new BufferedWriter(new FileWriter(log,true));
             }catch(FileNotFoundException e){
                 System.out.println("not found");
             }catch(IOException e){
                 System.out.println("unable to read");
             }
      System.out.println("Type your username");
      user= scan.next();
      System.out.println("Type your password");
      pass= scan.next();
      while((s=br.readLine())!=null){
          arr=s.split(" ");
          if(arr[0].equals(user)&&arr[1].equals(pass)){
              return true;
          }
                 }
      return false;
 }
 public void Create() throws IOException{

             BufferedReader br=null;
             Scanner scan=new Scanner(System.in);
             String s,user,pass;
             KeyGen k=new KeyGen();
             try{
                  br=new BufferedReader(new FileReader(log));
                  BufferedWriter bw=new BufferedWriter(new FileWriter(log,true));
                     System.out.println("Type your username");
                     user= scan.next();
                     System.out.println("Type your password");
                     pass= scan.next();
                     if(br.readLine()!=null)
                     bw.newLine();
                     bw.write(user+" "+pass);
                     bw.close();
                     br.close();
                     temptxt=user;
                     key=k.Findp();
             }catch(FileNotFoundException e){
                 System.out.println("not found");
             }catch(IOException e){
                 System.out.println("unable to read");
             }
 }
    public static void main(String[] args) throws IOException {
             File log=new File("login.txt");
             Login l =new Login();
             l.Inittxt(log);
             BufferedReader br=null;
             Scanner scan=new Scanner(System.in);
             String s,userfile;
             boolean cont=true;
             EncDec ecr;
             try{
                  br=new BufferedReader(new FileReader(log));
                  BufferedWriter bw=new BufferedWriter(new FileWriter(log,true));
             }catch(FileNotFoundException e){
                 System.out.println("not found");
             }catch(IOException e){
                 System.out.println("unable to read");
             }
             try{
                 br.close();
             }catch(IOException e){
                 System.out.println("");
             }catch(NullPointerException e){
                 System.out.println("");
             }
             while(cont==true){
             System.out.println("Do you want to Login or create a new Login(l or c)");
             s=scan.next();
             if(s.equals("l")){
                 l.Authenticate();
                 ecr=new EncDec();
                 System.out.println("Enter a string to be encrypted");
                 String enc=scan.next();
                 ecr.ModE(enc,l.temptxt,l.key);
             }
             else if(s.equals("c")){
                 l.Create();
             }
                 System.out.println("Do you want to Login");
                 if(scan.next().equals("y"))
                     cont=true;
                 else 
                     cont=false;
             }
    }
}
