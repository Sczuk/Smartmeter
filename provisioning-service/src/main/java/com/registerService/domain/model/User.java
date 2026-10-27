package com.registerService.domain.model;

import com.registerService.domain.exception.user.InvalidUserValuesException;
import com.registerService.domain.model.enums.Role;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class User {

    private UUID id;

    private String email;

    private String name;

    private String password;

    private Role role;

    private List<Smartmeter> smartmeters;

    private Timestamp created_at;

    public User(){}

    public User(String email, String name, String password, Role role, Timestamp created_at) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
        this.created_at = created_at;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setSmartmeters(List<Smartmeter> smartmeters){
        this.smartmeters = smartmeters;
    }

    public void setEmail(String email) {
        if(email.contains("@")){
            this.email = email;
            return;
        }
        throw new InvalidUserValuesException("Email "+email+" invalid");
    }

    public void setName(String name) {
        if(name.isBlank() || name.length() < 5 || name.length() > 100){
            throw new InvalidUserValuesException("name "+name+" invalid");
        }
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public List<Smartmeter> getSmartmeters(){
        return smartmeters;
    }
    public Timestamp getCreated_at() {
        return created_at;
    }
}
