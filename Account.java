package org.texman.bank;

import java.util.Date;

//Account class
public class Account
{
    //class account fields
    private int id;
    private double balance;
    private Date dateCreated;

    public Account()
    {
        this.id = 0;
        this.balance = 0;
        this.dateCreated = new Date();
    }

    public Account(int id, double balance)
    {
        this.id = id;
        this.balance = balance;
        this.dateCreated = new Date();
    }

    public int getId()
    {
        return this.id;
    }

    public double getBalance()
    {
        return this.balance;
    }

    public Date getDateCreated()
    {
        return this.dateCreated;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    void withdraw(double minus)
    {
        if (minus < balance)
        {
            this.balance -= minus;
        }
        else
        {
            System.out.println("Insufficient funds");
        }
    }

    void deposit(double add)
    {
        this.balance += add;
    }
}