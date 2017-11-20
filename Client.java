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
public abstract class Client {
    private boolean accountStatus;
    
    public Client(){
        this.accountStatus = true; 
    }
    
    public abstract String getUsername();
    public abstract String getPassword();
    public abstract String getrole();
    
    public abstract void setUsername(String name);
    public abstract void setPassword(String password);
    public abstract void setrole(String role);
    public abstract String toString();

    
}
