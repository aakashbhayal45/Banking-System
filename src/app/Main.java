package app;

import service.BankService;
import service.impl.BankServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        BankService bankService=new BankServiceImpl();
        boolean running=true;
        System.out.println("$Welcome to Console Bank$ ");
        while(running){
        System.out.println("""
                1) Open Account
                2) Deposit
                3) Withdraw
                4) Transfer
                5) Account Statement
                6) List Accounts
                7) Search Account by Customer Name
                0) Exit
                """);
        System.out.print("CHOOSE: ");
        String choice= input.nextLine().trim();
        System.out.println("CHOICE: "+ choice);

        switch(choice){
            case "1"-> openAccount(input,bankService);
            case "2"-> deposit(input,bankService);
            case "3"-> withdrawn(input,bankService);
            case "4"-> transfer(input,bankService);
            case "5"-> accountStatment(input,bankService);
            case "6"-> listAccount(input,bankService);
            case "7"-> searchAccount(input,bankService);
            case "0"-> running=false;
         }

        }


    }

    private static void openAccount(Scanner input,BankService bankService) {
        System.out.println("Customer name: ");
        String name =input.nextLine().trim();
        System.out.println("Customer email: ");
        String email =input.nextLine().trim();
        System.out.println("Account Type (SAVING/CURRENT): ");
        String type =input.nextLine().trim();
        System.out.println("Initial deposit (optional,blank for 0): ");
        String amountStr =input.nextLine().trim();
        if(amountStr.isBlank()) amountStr="0";
        double initial=Double.valueOf(amountStr);
        String accountNumber=bankService.openAccount(name,email,type);
        if(initial>0){
            bankService.deposit(accountNumber,initial,"Initial Deposit");
        }
        System.out.println("Account opened: "+accountNumber);
    }

    private static void deposit(Scanner input,BankService bankService) {
        System.out.println("Account number: ");
        String accountNumber=input.nextLine().trim();
        System.out.println("Amount");
        double amount=Double.valueOf(input.nextLine().trim());
        bankService.deposit(accountNumber,amount,"Deposit");
        System.out.println("Deposited");
    }

    private static void withdrawn(Scanner input ,BankService bankService) {
        System.out.println("Account number: ");
        String accountNumber=input.nextLine().trim();
        System.out.println("Amount");
        double amount=Double.valueOf(input.nextLine().trim());
        bankService.withdraw(accountNumber,amount,"withdrawal");
        System.out.println("withdrawn");
    }

    private static void transfer(Scanner input,BankService bankService) {
        System.out.println("From Account: ");
        String from=input.nextLine().trim();
        System.out.println("To Account: ");
        String to=input.nextLine().trim();
        System.out.println("Amount");
        double amount=Double.valueOf(input.nextLine().trim());
        bankService.transfer(from,to,amount,"Transfer");
        System.out.println("withdrawn");
    }

    private static void accountStatment(Scanner input,BankService bankService) {
        System.out.println("Account number: ");

        String account=input.nextLine().trim();


        bankService.getStatement(account).forEach(t->{
            System.out.println(t.getTimestamp()+" | "+t.getType()+" | "+t.getAmount()+" | "+t.getNote());
        });

    }

    private static void listAccount(Scanner input,BankService bankService) {
        bankService.listAccount().forEach(a->{
            System.out.println(a.getAccountNumber()+" | "+a.getAccountType()+" | "+a.getBalance());
        });
    }

    private static void searchAccount(Scanner input,BankService bankService) {
        System.out.println("Customer name contains: ");
        String q=input.nextLine().trim();
        bankService.searchAccountByCustomerName(q).forEach(account->{ System.out.println(account.getAccountNumber()+" | "+
                account.getAccountType()+" | "+account.getBalance());
        });
    }
}
