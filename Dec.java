package coe528.project;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kobikan2
 */
public class Dec {
    ArrayList <String> Narr;
    private String user;
        public Dec(String user){
        this.user=user;
    }
    public void ModD(int key,int d,String filenme) {
        Narr=new ArrayList<>();
        Random r=new Random();
        BufferedReader br;
        filenme=filenme+"."+user;
        String filede=filenme+"d";
        File file=new File(filenme);
        File filedec=new File(filede);
        String decs,alphas="",fin="";
        String N;
        char[] arr={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        int narrsize;
        int [] enc;
        int [] dec;
        BigInteger bd,bkey;
        BigInteger[] barr;
       try{       if(filedec.exists()!=true)
                  filedec.createNewFile();
                  br=new BufferedReader(new FileReader(filenme));
                  BufferedWriter bw=new BufferedWriter(new FileWriter(filenme,true));
                  while((N=br.readLine()).equals("end")==false){
                      Narr.add(N);
                  }
                  br.close();
             }catch(FileNotFoundException e){
                 System.out.println("not found1");
             }catch(IOException e){
                 System.out.println("unable to read");
             }
        if(file.exists()==false){
            System.out.println("File does not exist");
        }
        else{
         bd=BigInteger.valueOf(d);
         bkey=BigInteger.valueOf(key);
        
                narrsize=Narr.size();
        enc= new int[narrsize];
         dec= new int[narrsize];
        barr= new BigInteger[narrsize];
        for(int i=0;i<narrsize;i++){
            enc[i]=Integer.parseInt(Narr.get(i));
            barr[i]=BigInteger.valueOf(enc[i]);

            barr[i]=barr[i].modPow(bd, bkey);

            dec[i]=barr[i].intValue();
            decs=""+dec[i];
            System.out.println(decs);
            for(int h=1;h<=26;h++){
                for(int j=1;j<=26;j++){
                 if(j<10)
                    alphas=h+"0"+j;
                else 
                    alphas=""+h+j;
                if(alphas.equals(decs))
                    fin=fin+arr[h-1]+arr[j-1];
                
                 
                }
                if((h+"00").equals(decs))
                     fin=fin+arr[h-1];             
            }
            
        }
            System.out.println(fin);

        try{

                  br=new BufferedReader(new FileReader(filede));
                  BufferedWriter bw=new BufferedWriter(new FileWriter(filede,true));
                  bw.write(fin);
                  bw.close();
                  br.close();
             }catch(FileNotFoundException e){
                 System.out.println("not found");
             }catch(IOException e){
                 System.out.println("unable to read");
             }

        
    }
    }
    
}
