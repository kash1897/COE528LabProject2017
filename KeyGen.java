package coe528.project;

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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class KeyGen {
    private String prime="prime.txt";
    private ArrayList<Integer> arrl=new ArrayList<>();
    public KeyGen(){
        
    }
    public int Findp(){
        BufferedReader br;
        String pristr;
        int prinum,sizearr;
        Random r=new Random();
        try{
                  br=new BufferedReader(new FileReader(prime));
                     while((pristr=br.readLine())!=null){
                         prinum=Integer.parseInt(pristr);
                         arrl.add(prinum);
                     }
                     br.close();
             }catch(FileNotFoundException e){
                 System.out.println("not found");
             }catch(IOException e){
                 System.out.println("unable to read");
             }
        sizearr=arrl.size();
        return arrl.get(r.nextInt(sizearr));
    }
        public int Finde(int key){
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
            if(cd==1&&ep<500)
                valid=false;
        }
        return ep;
    }
        public int ExtendedEuclidean(int prime, int ep){
        int x=0,y=0;
        prime=prime-1;
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
