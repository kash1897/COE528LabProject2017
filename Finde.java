/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author Kobikan2
 */
public class Finde {
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
}
