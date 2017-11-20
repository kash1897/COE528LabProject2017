/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.lab5;

/**
 *
 * @author kashyappandya
 */
public class bankAccount {
    private double chequing;
    private double savings;
    private double balance;
    private boolean hasBankAccount; 
    
    public bankAccount(){
        if (this.hasBankAccount == true){
            throw new IllegalArgumentException ("There can only be one bank account per person.");
        }
        //this.balance = 20.00;
        this.hasBankAccount = true;
    }
    
    public void transferToChequing(double amount){
        if (amount <= savings){
        savings = savings - amount;
        chequing = chequing + amount;
        }
        else
           throw new IllegalArgumentException ("Cannot transfer an amount larger than the current balance.");
    }
    
    public void transferToSaving(double amount){
        if (amount <= this.chequing){
        this.chequing = this.chequing - amount;
        this.savings = this.savings + amount;
        }
        else
           throw new IllegalArgumentException ("Cannot transfer an amount larger than the current balance.");
    }
    
    public double getChequing(){
        return chequing; 
    }
    
    public double getSaving(){
        return savings; 
    }
    
    public double getBalance(){
        balance = chequing + savings;
        return balance;
    }
    
    public void setChequing(double c){
        chequing = c;
        balance = balance + chequing;
    }
    
    public void setSaving(double s){
        savings = s;
        balance = balance + savings;
    }
}
