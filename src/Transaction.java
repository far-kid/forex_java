import java.util.Date;

public class Transaction {

    private String soldCurrency; //currency sold
    private String bCurrency; //currency bought
    private double soldAmount; //quantity sold
    private double bAmount; //quantity bought

    private Date timestamp;
    //a memo for the transaction

    public Transaction(String soldCurrency, String bCurrency, double soldAmount, double bAmount, Date timestamp) {
        this.soldCurrency = soldCurrency;
        this.bCurrency = bCurrency;
        this.soldAmount = soldAmount;
        this.bAmount = bAmount;
        this.timestamp = new Date(); //check the usage for date//check the implimentation of date
    }

    //toString
    @Override
    public String toString() {
        return "Transaction{" +
                "soldCurrency='" + soldCurrency + '\'' +
                ", bCurrency='" + bCurrency + '\'' +
                ", soldAmount=" + soldAmount +
                ", bAmount=" + bAmount +
                ", timestamp=" + timestamp +
                '}';
    }
}