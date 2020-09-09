package com.rzcoding.controller;

import com.rzcoding.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/bank-a")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/updAmount")
    public ResponseEntity updAmount(@RequestParam(value = "mode",required = false,defaultValue = "IN") String mode){
        accountService.updAmount(mode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
