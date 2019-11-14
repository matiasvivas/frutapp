package com.howtodoinjava.rest.dao;

import com.howtodoinjava.rest.model.login.Login;
import com.howtodoinjava.rest.model.login.LoginAccounts;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAO
{
    private static LoginAccounts list = new LoginAccounts();
    
    static 
    {
        list.getLoginAccounts().add(new Login("admin@admin.com", "password"));
    }
    
    public LoginAccounts getLoginAccounts()
    {
        return list;
    }
    
    public void addLoginAccount(Login login) {
        list.getLoginAccounts().add(login);
    }
}
