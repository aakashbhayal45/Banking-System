package repository;

import domain.Transaction;

import java.util.*;

public class TransactionRepository {
    private  final Map<String, List<Transaction>> txBYAccount=new HashMap<>();

    public void add(Transaction transaction) {
        List<Transaction> list=txBYAccount.computeIfAbsent(transaction.getAccountNumber(),k ->new ArrayList<>());
        list.add(transaction);
    }

    public List<Transaction> findByAccount(String account) {
        return new ArrayList<>(txBYAccount.getOrDefault(account, Collections.emptyList()));

    }

}
