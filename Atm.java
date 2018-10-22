import java.util.Scanner;
import java.util.Date;
public class Atm
{
    //Atm class field
    private Node accountsList;

    //class Atm constructor
    public Atm()
    {
        setAccountList();
    }

    //method to create each list node
    private class Node
    {
        private int id;
        private double balance;
        private String accType;
        Node next;

        Node(int id, double balance, String accType)
        {
            this.id = id;
            this.balance = balance;
            this.accType = accType;
        }
    }

    //accountList setter, create 10 accounts and link them to create a list of accounts
    public void setAccountList(){
        Node accS1 = new Node(1, 500.69, "Savings");
        Node accS2 = new Node(2, 852.41, "Savings");
        Node accS3 = new Node(3, 293.01, "Savings");
        Node accS4 = new Node(4, 198.31, "Savings");
        Node accS5 = new Node(5, 6973.57, "Savings");
        Node accC1 = new Node(6, 500.69, "Checking");
        Node accC2 = new Node(7, 852.41, "Checking");
        Node accC3 = new Node(8, 293.01, "Checking");
        Node accC4 = new Node(9, 198.31, "Checking");
        Node accC5 = new Node(10, 6973.57, "Checking");

        accS1.next = accS2;
        accS2.next = accS3;
        accS3.next = accS4;
        accS4.next = accS5;
        accS5.next = accC1;
        accC1.next = accC2;
        accC2.next = accC3;
        accC3.next = accC4;
        accC4.next = accC5;
        accC5.next = null;

        this.accountsList = accS1;
    }

    //accountList getter
    public Node getAccountList(){
        return accountsList;
    }

    public static void main(String[] args)
    {
        Atm atm = new Atm();
        Node temp = atm.getAccountList();
        Node lists = temp;
        int id;
        //scanner object used to take input from user
        Scanner in = new Scanner(System.in);

        while (true)
        {
            System.out.print("\nPlease enter your identification number: ");
            id = in.nextInt();
            while (lists != null)
            {
                //checking if the user input match the accounts in the list
                if (id == lists.id && (lists.accType.equals("Savings")))
                {
                    lists.balance = savingsA(lists.id, lists.balance);
                    break;
                }
                else if (id == lists.id && (lists.accType.equals("Checking")))
                {
                    lists.balance = checkingA(lists.id, lists.balance);
                    break;
                }
                lists = lists.next;
            }
            lists = temp;
        }
    }

    //method that handles savings account transactions
    public static double savingsA(int id, double balance)
    {
        SavingsAccount temp = new SavingsAccount(id, balance);
        boolean menu = true;
        //scanner object used to take input from user
        Scanner in = new Scanner(System.in);
        
        while (menu)
        {
            display_menu();
            int option = in.nextInt();
            if (option == 1)
            {
                System.out.println("\nYour balance is R" + (String.format("%.2f", (temp.getBalance() + temp.getMonthlyInterest()))));
            }
            else if(option == 2)
            {
                System.out.println("\nHow much would you like to withdraw?");
                double minus = in.nextDouble();
                temp.withdraw(minus);
            }
            else if(option == 3)
            {
                System.out.println("\nHow much would you like to deposit?");
                double add = in.nextDouble();
                temp.deposit(add);
            }
            else if(option == 4)
            {
                System.out.println("\nGoodbye");
                menu = false;
            }
            else
            {
                System.out.println("Wrong option!\n");
            }
        }
        return temp.getBalance();
    }

    //method that handles checking account transactions
    public static double checkingA(int id, double balance)
    {
        CheckingAccount temp = new CheckingAccount(id, balance);
        boolean menu = true;
        //scanner object used to take input from user
        Scanner in = new Scanner(System.in);
        
        while (menu)
        {
            display_menu();
            int option = in.nextInt();
            if (option == 1)
            {
                System.out.println("\nYour balance is R" + (String.format("%.2f", (temp.getBalance() - temp.getOverdraft()))));
            }
            else if(option == 2)
            {
                System.out.println("\nHow much would you like to withdraw?");
                double minus = in.nextDouble();
                temp.withdraw(minus);
            }
            else if(option == 3)
            {
                System.out.println("\nHow much would you like to deposit?");
                double add = in.nextDouble();
                temp.deposit(add);
            }
            else if(option == 4)
            {
                System.out.println("\nGoodbye");
                menu = false;
            }
            else
            {
                System.out.println("Wrong option!\n");
            }
        }
        return (temp.getBalance() - temp.getOverdraft());
    }

    //menu display method
    public static void display_menu()
    {
        System.out.println("\nChoose a transaction\n");
        System.out.println("Main menu");
        System.out.println("1.check balance");
        System.out.println("2.withdraw");
        System.out.println("3.deposit");
        System.out.println("4.exit\n");
    }
}

class Account
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
        return id;
    }

    public double getBalance()
    {
        return balance;
    }

    public Date getDateCreated()
    {
        return dateCreated;
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

//savings account class 
class SavingsAccount extends Account
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

//Checking Accounts class
class CheckingAccount extends Account
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