
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kobikan2
 */
public class EncDec {
    public void ModE(String bfrenc, String filenme,int key){
        BufferedReader br;
        File file=new File(filenme);
         int k=0,first=0,sec=0,size;
        char[] arr={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        char[] str=bfrenc.toCharArray();
        String[] M;
        String firststr,secstr;
       size=str.length/2 +1;
       M=new String[size];
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
        }
        
        try{
                  if(file.exists()!=true)
                  file.createNewFile();
                  
                  br=new BufferedReader(new FileReader(filenme));
                  BufferedWriter bw=new BufferedWriter(new FileWriter(filenme,true));
                  
             }catch(FileNotFoundException e){
                 System.out.println("not found");
             }catch(IOException e){
                 System.out.println("unable to read");
             }
    }
    public void ModD(){
        
    }
}
