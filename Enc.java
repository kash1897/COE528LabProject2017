package coe528.project;

//imports
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.lang.Math;
import java.math.*;
import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kobikan2
 */
public class Enc {
    //private instance variables
    private String user;
    //Constructor that holds the username of the user
    public Enc(String user){
        this.user=user;
    }
//Encryption Algorithim
    public void ModE(String bfrenc, String filenme,int key,int ep){
        //Cretes the file name with the ending prefix being the users username
        filenme=filenme+"."+user;
        BufferedReader br;
        File file=new File(filenme);
        BigInteger bi,bep,bkey;
        //File creation if the file does not exist
        try{
            if(file.exists()==false){
                file.createNewFile();
            }
            }catch(IOException ex) {
                System.out.println("\nError reading file.\n\n");
            }
         int k=0,first=0,sec=0,size,end = 0;
         int [] C;
         double beg;
         Random r=new Random();
        char[] arr={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        char[] str=bfrenc.toCharArray();
        String[] M;
        String firststr,secstr; 
       size=str.length/2 +1;
       M=new String[size];
       C=new int[size];
        for(int i=0; i<str.length;i=i+2){
            for(int j=0;j<arr.length;j++){
                if(str.length%2==0){
                    if(str[i]==arr[j]){
                        first=(j+1);
                    }
                    if(str[i+1]==arr[j]){
                        sec=(j+1);
                    }
                }
                else {
                    if(str[i]==arr[j]){
                        first=(j+1);
                    }
                    if(i<=str.length-2){
                    if(str[i+1]==arr[j]){
                        sec=(j+1);
                    }
                    }
                    else{
                        sec=0;
                    }
                    
                }
            }
            if(sec<10)
                secstr="0"+sec;
            else 
                secstr=""+sec;
            M[k]=""+first+secstr;
            System.out.println(M[k]);

            beg=Double.parseDouble(M[k]);
           bi= BigInteger.valueOf((int)beg);
           bep=BigInteger.valueOf(ep);
           bkey=BigInteger.valueOf(key);
            bi=bi.modPow(bep,bkey);
            C[k]=bi.intValue();
            k=k+1;
        }
        
        try{
                  if(file.exists()!=true)
                  file.createNewFile();
                  br=new BufferedReader(new FileReader(filenme));
                  BufferedWriter bw=new BufferedWriter(new FileWriter(filenme,true));
                  for(int i=0;i<k;i++){
                      if(C[i]<100){
                        bw.write("00"+C[i]+"\n");  
                      }
                      else if(C[i]<1000){
                        bw.write("0"+C[i]+"\n");  
                      }
                      else
                      bw.write(C[i]+"\n");
                  }
                  bw.write("end\n");
                  bw.close();
             }catch(FileNotFoundException e){
                 System.out.println("not found");
             }catch(IOException e){
                 System.out.println("unable to read");
             }
        

    }


}
