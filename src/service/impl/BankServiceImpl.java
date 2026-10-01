package service.impl;

import domain.Account;
import domain.Customer;
import domain.Transaction;
import domain.Type;
import exception.AccountNotFoundException;
import exception.InsufficientFundException;
import exception.ValidationException;
import repository.AccountRepository;
import repository.CustomerRepository;
import repository.TransactionRepository;
import service.BankService;
import util.Validation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService  {
    AccountRepository accountRepository=new AccountRepository();
    TransactionRepository transactionRepository=new TransactionRepository();
    CustomerRepository customerRepository=new CustomerRepository();
    private final Validation<String> validateName=name->{
        if(name==null||name.isBlank()) throw new ValidationException("Name is required");
    };
    private final Validation<String> validateEmail=email->{
        if(email==null||!email.contains("@")) throw new ValidationException("Email is required");
    };
    private final Validation<String> validateType=Type->{
        if(Type==null||!(Type.equalsIgnoreCase("SAVING")||Type.contains("CURRENT")))
            throw new ValidationException("Type must be SAVING or CURRENT");
    };
    private final Validation<Double> validateAmountPositive=amount->{
        if(amount==null||amount<0 )
            throw new ValidationException("Please enter valid amount");
    };
    @Override
    public String openAccount(String name, String email, String accountType) {
        validateName.validate(name);
        validateEmail.validate(email);
        validateType.validate(accountType);
        String customerId= UUID.randomUUID().toString();
        Customer c=new Customer(customerId,name,email);
        customerRepository.save(c);
        //String accountNumber=UUID.randomUUID().toString();
        String accountNumber = getAccountNumber();
        Account account=new Account(accountNumber,customerId,(double)0,accountType);
        accountRepository.save(account);
        return accountNumber;
    }

    @Override
    public List<Account> listAccount() {
        return accountRepository.findAll().stream()
                .sorted(Comparator.comparing(Account::getAccountNumber))
                .collect(Collectors.toList());
    }

    @Override
    public void deposit(String accountNumber, double amount, String note) {
        validateAmountPositive.validate(amount);
        Account account=accountRepository.findNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: "+accountNumber));
        account.setBalance(account.getBalance()+amount);
        Transaction transaction=new Transaction(account.getAccountNumber(),Type.DEPOSIT,
               UUID.randomUUID().toString(), amount, LocalDateTime.now(),note);
        transactionRepository.add(transaction);
    }

    @Override
    public void withdraw(String accountNumber, double amount, String note) {
        validateAmountPositive.validate(amount);

        Account account=accountRepository.findNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: "+accountNumber));
    if(account.getBalance().compareTo(amount)<0){
        throw new RuntimeException("Insufficient Balance");
    }
    account.setBalance(account.getBalance()-amount);
        Transaction transaction=new Transaction(account.getAccountNumber(),Type.WITHDRAW,
                UUID.randomUUID().toString(), amount, LocalDateTime.now(),note);
        transactionRepository.add(transaction);
    }

    @Override
    public void transfer(String fromAcc, String toAcc, double amount, String note) {
        validateAmountPositive.validate(amount);

        if(fromAcc.equals(toAcc)){
        throw new ValidationException("Cannot transfer to your own account");
    }
    Account from =accountRepository.findNumber(fromAcc)
            .orElseThrow(()-> new AccountNotFoundException("Account not found: "+fromAcc));
        Account to=accountRepository.findNumber(toAcc)
                .orElseThrow(()-> new AccountNotFoundException("Account not found: "+toAcc));
        if(from.getBalance().compareTo(amount)<0){
            throw new InsufficientFundException("Insufficient Balance");
        }
        from.setBalance(from.getBalance()-amount);
        to.setBalance(to.getBalance()+amount);
        transactionRepository.add(new Transaction(from.getAccountNumber(),Type.TRANSFER_OUT,
                UUID.randomUUID().toString(), amount, LocalDateTime.now(),note));
        transactionRepository.add(new Transaction(to.getAccountNumber(),Type.TRANSFER_IN,
                UUID.randomUUID().toString(), amount, LocalDateTime.now(),note));
    }

    @Override
    public List<Account> searchAccountByCustomerName(String q) {
        String query=(q==null)?"":q.toLowerCase();
        List<Account> result=new ArrayList<>();
        for(Customer c : customerRepository.findAll()){
            if(c.getName().toLowerCase().contains(query)){
                result.addAll(accountRepository.findByCustomerId(c.getId()));
            }
        }
        return result;
    }

    private String getAccountNumber() {
        int size=accountRepository.findAll().size()+1;
        return String.format("AC%06d",size );
    }

    @Override
    public List<Transaction> getStatement(String account) {
        return transactionRepository.findByAccount(account).stream()
                .sorted(Comparator.comparing(Transaction::getTimestamp))
                .collect(Collectors.toList());
    }
}
