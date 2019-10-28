/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Kobikan2
 */
public class Findp {
        private String prime="prime.txt";
    private ArrayList<Integer> arrl=new ArrayList<>();
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
}
