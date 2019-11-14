package com.howtodoinjava.rest.model.login;

import java.util.ArrayList;
import java.util.List;

public class LoginAccounts
{
    private List<Login> loginAccounts;
    
    public List<Login> getLoginAccounts() {
        if(loginAccounts == null) {
            loginAccounts = new ArrayList<>();
        }
        return loginAccounts;
    }
 
    public void setLoginAccounts(List<Login> loginAccounts) {
        this.loginAccounts = loginAccounts;
    }
}
