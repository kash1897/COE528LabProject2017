
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
            ArrayList <Integer> arrd=new ArrayList<>();
        ArrayList <Integer> primearr=new ArrayList<>();
        ArrayList <Integer> eparr=new ArrayList<>(); 
    public int ModE(String bfrenc, String filenme,int key) throws IOException{
        BufferedReader br;
        File file=new File(filenme);
        Enc cons=new Enc();
        BigInteger bi,bep,bkey;
        if(file.exists()==false){
            file.createNewFile();
        }
         int k=0,first=0,sec=0,size,end = 0,ep;
         int [] C;
         double beg;
         Random r=new Random();
        char[] arr={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        char[] str=bfrenc.toCharArray();
        String[] M;
        String firststr,secstr;
        ep= cons.GCD(key);  
       size=str.length/2 +1;
       M=new String[size];
       C=new int[size];
        for(int i=0; i<str.length;i=i+2){
            for(int j=0;j<arr.length;j++){
                if(str[i]==arr[j]){
                    first=(j+1);
                }
                if(str[i+1]==arr[j]){
                    sec=(j+1);
                }
            }
            if(sec<10)
                secstr="0"+sec;
            else 
                secstr=""+sec;
            M[k]=""+first+secstr;
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
                  for(int i=0;i<=k;i++){
                      bw.write(C[i]);
                  }
                  
                  bw.close();
             }catch(FileNotFoundException e){
                 System.out.println("not found");
             }catch(IOException e){
                 System.out.println("unable to read");
             }
        
        return cons.ExtendedEuclidean(key, ep);
    }
    public int GCD(int key){
        Random r=new Random();
        int ep=0,cd=0;
        BigInteger k,biep,GCD;
        boolean valid=true;
        k=BigInteger.valueOf(key-1);
        while(valid){
            ep=r.nextInt(key-1);
            biep=BigInteger.valueOf(ep);
            GCD=k.gcd(biep);
            cd=GCD.intValue();
            if(cd==1)
                valid=false;
        }
        return ep;
    }
    
    public int Publickey(int key, int ep){
        Enc call=new Enc();
        int prime,epmul=0, eppro=0,sub=-1,dkey;
        prime=key-1;
        while(sub!=0){
            while(eppro<=prime){
           epmul++;
           eppro=ep*epmul;
                System.out.println(epmul);
            }
            arrd.add(epmul-1);
            primearr.add(prime);
            eparr.add(ep);
            sub=prime-eppro+ep;
            prime=ep;
            ep=sub;
            epmul=0;
            eppro=0;
        }
        dkey= call.ExtendedEuclidean(prime, ep);
      return dkey;  
    }
    public int ExtendedEuclidean(int prime, int ep){
        int x=0,y=0;
        Enc e=new Enc();
           for(int i=-10000;i<5000;i++){
               for(int j=-10000;j<5000;j++){
                   x=j*prime+ep*i;
               if(x==1){
                  y=i;
               }
           }

           }
           return y;
    }
}
