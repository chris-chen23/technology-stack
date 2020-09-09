package com.rzcoding.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "bank-b",fallback = FeignFallback.class)
public interface BankBClient {

    @PutMapping("/bank-b/updAmount")
    String updAmount(@RequestParam("mode") String mode);
}