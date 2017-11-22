
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
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
    public int ModD(int key,int d,String filenme) throws IOException{
        BufferedReader br;
        File file=new File(filenme);
        Enc cons=new Enc();
        BigInteger bi,bep,bkey;
        if(file.exists()==false){
            file.createNewFile();
        }
         int k=0,first=0,sec=0,size,end = 0,ep,s;
         int [] C;
         double beg;
         Random r=new Random();

        char[] arr={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};        
        try{
                  if(file.exists()!=true)
                  file.createNewFile();
                  br=new BufferedReader(new FileReader(filenme));
                  BufferedWriter bw=new BufferedWriter(new FileWriter(filenme,true));
                  Scanner check=new Scanner(filenme);
                  while(check.hasNext()){
                  s=Integer.parseInt(br.readLine());
                  }

                  
             }catch(FileNotFoundException e){
                 System.out.println("not found");
             }catch(IOException e){
                 System.out.println("unable to read");
             }
        
        return cons.ExtendedEuclidean(key, ep);
    }
    
}
