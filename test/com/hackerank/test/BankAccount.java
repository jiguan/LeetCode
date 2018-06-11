package com.hackerank.test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public interface BankAccount {
    public int getUnits();
    public BankAccount getAccountType();
    public String getCurrency();
    public String getName();

    public static void main(String[] args) {

        List<BankAccount> accounts = new ArrayList<BankAccount>();
        accounts.add(new SavingsAccount("USD", 3));// Savings
        accounts.add(new SavingsAccount("EUR", 2));// Savings
        accounts.add(new CheckingAccount("HUF", 100));// Checking
        accounts.add(new CheckingAccount("COP", 10000));// Checking
        accounts.add(new BrokerageAccount("GBP", 2));// Brokerage
        accounts.add(new BrokerageAccount("INR", 600));// Brokerage
        String TEXT = "I am a {0} account with {1,number,#} units of {2} currency";

        accounts.stream().forEach(account -> System.out.println(MessageFormat.format(TEXT, new Object[]{account.getAccountType().getName(), account.getUnits(), account.getCurrency()})));
    }
}

class BrokerageAccount implements BankAccount {
    private String currency;
    private int units;
    private String name = "Brokerage";

    public BrokerageAccount(String currency, int units) {
        super();
        this.currency = currency;
        this.units = units;
    }

    @Override
    public int getUnits() {
        return units;
    }

    @Override
    public BankAccount getAccountType() {
        return this;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public String getName() {
        return name;
    }
}

class CheckingAccount implements BankAccount {
    private String currency;
    private int units;
    private String name = "Checking";

    public CheckingAccount(String currency, int units) {
        super();
        this.currency = currency;
        this.units = units;
    }

    @Override
    public int getUnits() {
        return units;
    }

    @Override
    public BankAccount getAccountType() {
        return this;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public String getName() {
        return name;
    }
}

class SavingsAccount implements BankAccount {
    private String currency;
    private int units;
    private String name = "Savings";

    public SavingsAccount(String currency, int units) {
        super();
        this.currency = currency;
        this.units = units;
    }

    @Override
    public int getUnits() {
        return units;
    }

    @Override
    public BankAccount getAccountType() {
        return this;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public String getName() {
        return name;
    }
}
