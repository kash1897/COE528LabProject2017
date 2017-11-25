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
public class Findphi {
    int n;
    public int Find(){
        Findp prime=new Findp();
        int p,q,phi;
        p=prime.Findp();
        q=p;
        while(q==p)
            q=prime.Findp();
        n=p*q;
        phi=(p-1)*(q-1);
        return phi;
    }
    
}
