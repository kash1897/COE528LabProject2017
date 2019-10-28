/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author Kobikan2
 */
public class Findd {
    int prime;
    int e;
    public Findd(int prime,int e){
        this.prime=prime;
        this.e=e;
    }
            public int ExtendedEuclidean(){
        int x=0,y=0;
        prime=prime-1;
           for(int i=-10000;i<5000;i++){
               for(int j=-10000;j<5000;j++){
                   x=j*prime+e*i;
               if(x==1){
                  y=i;
               }
           }

           }
           return y;
    }
}
