package com.rzcoding.service;

import com.rzcoding.client.BankBClient;
import com.rzcoding.dao.AccountMapper;
import com.rzcoding.entity.Account;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService{

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private BankBClient bankBClient;

    @GlobalTransactional()
    public void updAmount(String mode){
        Account account = accountMapper.selectByPrimaryKey("1");

        String result = null;
        if("IN".equalsIgnoreCase(mode)){
            account.setAmount(account.getAmount() + 100);
            // 通过Feign向B银行服务转账
            result = bankBClient.updAmount("OUT");
            System.out.println(result);
        }else{
            account.setAmount(account.getAmount() - 100);
            // 通过Feign向B银行服务转账
            result = bankBClient.updAmount("IN");
        }
        accountMapper.updateByPrimaryKeySelective(account);
        if("BankB-fail".equals(result)){
            throw new RuntimeException("BankB 异常");
        }

//        if("OUT".equalsIgnoreCase(mode)){
//            System.out.println(1/0);
//        }
    }
}
