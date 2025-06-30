package com.example.bank.service;

import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private AccountRepository accountRepo;

    public String createAccount(Account account) {
        if (accountRepo.existsById(account.getId())) {
            return "ID already exists";
        }
        if (account.getAmount() <= 1000) {
            return "Initial amount must be more than 1000";
        }
        accountRepo.save(account);
        return "Account created successfully";
    }

    public String deposit(String id, String password, int amount) {
        Account acc = accountRepo.findByIdAndPassword(id, password);
        if (acc == null) return "Invalid credentials";

        acc.setAmount(acc.getAmount() + amount);
        accountRepo.save(acc);
        return "Deposit successful";
    }

    public String withdraw(String id, String password, int amount) {
        Account acc = accountRepo.findByIdAndPassword(id, password);
        if (acc == null) return "Invalid credentials";

        if (acc.getAmount() < amount) return "Insufficient funds";

        acc.setAmount(acc.getAmount() - amount);
        accountRepo.save(acc);
        return "Withdraw successful";
    }

    public String checkBalance(String id, String password) {
        Account acc = accountRepo.findByIdAndPassword(id, password);
        if (acc == null) return "Invalid credentials";
        return "Your balance is: " + acc.getAmount();
    }

    public String deleteAccount(String id, String password) {
        Account acc = accountRepo.findByIdAndPassword(id, password);
        if (acc == null) return "Invalid credentials";

        accountRepo.delete(acc);
        return "Account deleted successfully";
    }

    public String sendMoney(String senderId, String password, String receiverId, int amount) {
        Account sender = accountRepo.findByIdAndPassword(senderId, password);
        if (sender == null) return "Invalid sender credentials";

        Optional<Account> optReceiver = accountRepo.findById(receiverId);
        if (!optReceiver.isPresent()) return "Recipient not found";

        if (sender.getAmount() < amount) return "Insufficient balance";

        Account receiver = optReceiver.get();
        sender.setAmount(sender.getAmount() - amount);
        receiver.setAmount(receiver.getAmount() + amount);

        accountRepo.save(sender);
        accountRepo.save(receiver);
        return "Money transferred successfully";
    }
}
