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
public class Customer extends Client{
    private String username;
    private String password;
    private String role;
    private bankAccount account;
    //ArrayList<bankAccount> accounts = new ArrayList();
    
    public Customer (String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
        this.account = new bankAccount();
    }
    
    public bankAccount getAccount(){
        return account;
    }
    
    public double getchequing(){
        return this.account.getChequing();
    }
    
    public void setchequing(double c){
        this.account.setChequing(c);
        System.out.println("\nValue has been added to chequing account.");
    }
    
    public double getsavings(){
        return this.account.getSaving();
    }
    
    public void setsavings(double s){
        this.account.setSaving(s);
        if (s != 0){
            System.out.println("\nValue has been added to savings account.");
        }
        else
            System.out.println("\nNo savings account has been initialized.");
        
    }
    
    @Override
   public String getUsername(){
        return this.username;
    }
    @Override
    public String getPassword(){
        return this.password;
    }
    @Override
    public String getrole(){
        return this.role;
    }
    
    @Override
    public void setUsername(String name){
        this.username = name;
    }
    @Override
    public void setPassword(String password){
        this.password = password;
    }
    @Override
    public void setrole(String role){
        this.role = role;
    }
    @Override
    public String toString(){
        return "Username: " + this.username + " Password: " + this.password + " Role: " + this.role;
    }
}
