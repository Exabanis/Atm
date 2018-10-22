//savings account class 
public class SavingsAccount extends Account
{
    private double annualInterestRate = 15;

    public SavingsAccount(int id, double balance)
    {
        super(id, balance);
    }

    public double getMonthlyInterestRate()
    {
        return (this.annualInterestRate / 12 / 100);
    }

    public double getMonthlyInterest()
    {
       return (getBalance() * getMonthlyInterestRate());
    }

}