package com.example.model;

public class User {
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private Role role;
    private Long  managerId;
    public User() { }
    public User(Long id,String fullName,String email,String password,Role role,Long managerId){
        this.id=id;
        this.fullName=fullName;
        this.email=email;
        this.password=password;
        this.role=role;
        this.managerId=managerId;

    }

    //getters
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getFullName(){
        return fullName;
    }
    public void setFullName(String fullName){
        this.fullName=fullName;
    }
    public String getEmail(){
        return email;
    } 
    public void setEmail(String email){
        this.email=email;
    }
    public String getPassword(){
        return password;
    } public void setPassword(String password){
        this.password=password;
    }
    public Role getRole(){
        return role;
    } public void setRole(Role role){
        this.role=role;
    }
    public Long getManagerId(){
        return managerId;
    } 
    public void setManagerId(Long managerId){
        this.managerId=managerId;
    }
}
