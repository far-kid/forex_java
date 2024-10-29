import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Player {
    private String name;
    private double[] portfolio;
    private List<Transaction> transactionHistory; // List to store transactions


    public Player(String name) {
        this.name = name;
        this.portfolio = new double[]{100000, 0, 0 ,0}; //Array to store the portfolio [inr,usd,eur,yen]
        //can change as needed
        this.transactionHistory = new ArrayList<>(); //Empty transaction history
    }
    //get the portfolio in an array format
    public double[] getPortfolio() {
        return portfolio;
    }

    //get the value of individual currency holding
    public double getCurrValue(int index){
        return portfolio[index];
    }

    //set value of individual currency holding
    //CAUTION IT DIRECTLY SETS THE VALUE NOT ADD/SUBTRACT
    public void setPortfolio(int index , double value) {
        portfolio[index] = value;
    }

    //Display the portfolio
    public void displayPortfolio(){
        /*
        * YOUR CURRENT PORTFOLIO  //#AS OF DATE(THE DATE IN THE SIM)
        * INR = XXXXX.XX
        * USD = XXXX.XX
        * EUR ETC*/
    }

    //add transactions //CHECK of date
    public void addTransaction(String soldCurrency, String bCurrency, double soldAmount, double bAmount) {
        Transaction transaction = new Transaction(soldCurrency, bCurrency, soldAmount, bAmount, new Date());
        transactionHistory.add(transaction);
    }
    //display the transaction history
    public void displayTransactionHistory() {
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction); // Customize Transaction's toString method for display
        }
    }
}