package com.rzcoding.client;

import org.springframework.stereotype.Component;

/**
 * @author Chris
 * @description
 * @date 2020/8/1 00:00
 */
@Component
public class FeignFallback implements BankBClient {
    @Override
    public String updAmount(String mode) {
        return "BankB-fail";
    }
}
