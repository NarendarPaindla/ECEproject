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
}
