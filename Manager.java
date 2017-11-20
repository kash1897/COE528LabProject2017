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
public class Manager extends Client{
    private String username;
    private String password;
    private String role;
    private static boolean accountExists;
    private static Manager m = null;
            
    private Manager (String username, String password, String role){
        if(accountExists == true){
            throw new IllegalArgumentException("\nCannot create another Manager account.\n");
        }
        this.username = username;
        this.password = password;
        this.role = role;
        accountExists = true;
    }
 
    public static Manager getInstance(){
        if (m == null){
            m = new Manager("admin", "admin", "Manager");
        }
        return m;
    }
    
    @Override
    public String getUsername(){
        return username;
    }
    @Override
    public String getPassword(){
        return password;
    }
    @Override
    public String getrole(){
        return role;
    }
    
    @Override
    public void setUsername(String name){
        username = name;
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
        return "Username: " + username + " Password: " + password + " Role: " + role;
    }
}
