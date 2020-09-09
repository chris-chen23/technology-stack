package com.rzcoding.service;

import com.rzcoding.dao.AccountMapper;
import com.rzcoding.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountService{
    @Autowired
    private AccountMapper accountMapper;

    public void updAmount(String mode){
        Account account = accountMapper.selectByPrimaryKey("1");
        if("IN".equalsIgnoreCase(mode)){
            account.setAmount(account.getAmount() + 100);
            account.setUpdateTime(new Date().getTime() / 1000);
        }else{
            account.setAmount(account.getAmount() - 100);
            account.setUpdateTime(new Date().getTime() / 1000);
        }
        accountMapper.updateByPrimaryKeySelective(account);

        if("OUT".equalsIgnoreCase(mode)){
            System.out.println(1/0);
        }
    }

}
