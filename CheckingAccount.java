package org.texman.bank;

//Checking Accounts class
public class CheckingAccount extends Account
{
    private double overdraft = 2000.00;

    public CheckingAccount(int id, double balance)
    {
        super(id, balance);
        this.setBalance(balance + getOverdraft());//to allow the parent withdraw method to minus overdraft
    }

    public double getOverdraft()
    {
        return overdraft;
    }
}