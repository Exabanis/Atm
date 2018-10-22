//Checking Accounts class
public class CheckingAccount extends Account
{
    private double overdraft = 2000.00;

    public CheckingAccount(int id, double balance)
    {
        super(id, balance);
        this.setBalance(balance + getOverdraft());
    }

    public double getOverdraft()
    {
        return overdraft;
    }
}