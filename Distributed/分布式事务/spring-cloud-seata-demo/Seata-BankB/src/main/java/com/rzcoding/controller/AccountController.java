package com.rzcoding.controller;

import com.rzcoding.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/bank-b")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PutMapping("/updAmount")
    public ResponseEntity updAmount(@RequestParam(value = "mode",required = false,defaultValue = "IN") String mode){
        accountService.updAmount(mode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
