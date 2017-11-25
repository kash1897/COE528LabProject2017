/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Kobikan2
 */
public class RSA extends EncDec{
    
    //private instance variables
    private String user;
     private ArrayList <String> Narr;
    //Constructor that holds the username of the user
    public RSA(String user){
        this.user=user;
    }
    
    //Encryption Algorithim
    @Override
    public void Enc(String bfrenc, String filenme,int key,int ep){
        //Cretes the file name with the ending prefix being the users username
        filenme=filenme+".rsa."+user;
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
         int k=0,first=0,sec=0,third=0,size,end = 0;
         int [] C;
         double beg;
         Random r=new Random();
        char[] arr={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        char[] str=bfrenc.toCharArray();
        String[] M;
        String thirdstr,secstr; 
       size=str.length/2 +1;
       M=new String[size];
       C=new int[size];
        for(int i=0; i<str.length;i=i+3){
            for(int j=0;j<arr.length;j++){
                if(str.length%3==0){
                    if(str[i]==arr[j]){
                        first=(j+1);
                    }
                    if(str[i+1]==arr[j]){
                        sec=(j+1);
                    }
                 if(str[i+2]==arr[j]){
                        third=(j+1);
                    }
                }
                else if(str.length%2==1){
                    if(str[i]==arr[j])
                        first=(j+1);
                    
                    if(str[i+1]==arr[j])
                        sec=(j+1);
                    
                    if(i<=str.length-2){
                    if(str[i+2]==arr[j])
                        third=(j+1);
                    
                    }
                    else{
                        third=0;
                    }
                    
                }
                else{
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
                    if(i<=str.length-3){
                    if(str[i+2]==arr[j]){
                        third=(j+1);
                    }
                    }
                    else{
                        third=0;
                    }
                    
                }
            }
            if(sec<10)
                secstr="0"+sec;
            else 
                secstr=""+sec;
            if(third<10)
                thirdstr="0"+third;
            else 
                thirdstr=""+third;
            M[k]=""+first+secstr+thirdstr;
            System.out.println(M[k]);

            beg=Double.parseDouble(M[k]);
            System.out.println(beg);
           bi= BigInteger.valueOf((int)beg);
                       System.out.println(bi);
           bep=BigInteger.valueOf(ep);
                       System.out.println(ep);
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

   @Override
   public void Dec(int key,int d,String filenme) {
        Narr=new ArrayList<>();
        Random r=new Random();
        BufferedReader br;
        filenme=filenme+".rsa."+user;
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
                      System.out.println(N);
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
                    for(int k=1;k<=26;k++){
                        if(k<10&&j<10)
                            alphas=h+"0"+j+"0"+k;
                        if(k<10)
                            alphas=h+j+"0"+k;
                 if(j<10)
                    alphas=h+"0"+j+k;
                else 
                    alphas=""+h+j+k;
                if(alphas.equals(decs))
                    fin=fin+arr[h-1]+arr[j-1];
                }
                 if((""+h+j+"00").equals(decs))
                     fin=fin+arr[h-1]+arr[j-1]; 
                }
                if((h+"0000").equals(decs))
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
