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
                  BufferedWriter bw=new BufferedWriter(new FileWriter(prime,true));
                     while((pristr=br.readLine())!=null){
                         prinum=Integer.parseInt(pristr);
                         arrl.add(prinum);
                     }
             }catch(FileNotFoundException e){
                 System.out.println("not found");
             }catch(IOException e){
                 System.out.println("unable to read");
             }
        sizearr=arrl.size();
        return arrl.get(r.nextInt(sizearr));
    }
    public int Finde(){
        return 0;
    }
}
