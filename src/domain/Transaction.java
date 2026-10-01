package domain;

import java.time.LocalDateTime;

public class Transaction {
    private String accountNumber;
    private Type type;
    private String id;
    private Double amount;
    private LocalDateTime timestamp;
    private String note;

    public Transaction(String accountNumber, Type type, String id,
                       Double amount, LocalDateTime timestamp, String note) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.id = id;
        this.amount = amount;
        this.timestamp = timestamp;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
