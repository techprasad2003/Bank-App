package com.example.bank.controller;

import com.example.bank.model.Account;
import com.example.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankService service;

    @PostMapping("/create")
    public String create(@RequestBody Account account) {
        return service.createAccount(account);
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam String id, @RequestParam String password, @RequestParam int amount) {
        return service.deposit(id, password, amount);
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam String id, @RequestParam String password, @RequestParam int amount) {
        return service.withdraw(id, password, amount);
    }

    @GetMapping("/balance")
    public String balance(@RequestParam String id, @RequestParam String password) {
        return service.checkBalance(id, password);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam String id, @RequestParam String password) {
        return service.deleteAccount(id, password);
    }

    @PostMapping("/send")
    public String sendMoney(@RequestParam String senderId, @RequestParam String password,
                            @RequestParam String receiverId, @RequestParam int amount) {
        return service.sendMoney(senderId, password, receiverId, amount);
    }
}
