import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Broker {
    private Player player;

    public Broker(Player player) {
        this.player = player;
    }
    String currency[] ={"INR" , "USD" , "EUR" , "YEN"};
    //inr is 0 usd 1 eur 2 yen 3
    private double[][][][] exchangeRates = new double[24][4][4][1];
    {
        //the first is no of biweeks(12*2) , next 2 are no of currencies  and the last is the rate
        // we are using array to store arrays to store the data
        // 4D Array: [biweek][currencyFrom][currencyTo][exchangeRate]
        //if lets say we want to buy usd[1] and we have inr[0] in biweek 3
        // [3][0][1][0]
    }

    //method to populate the array
    public void populateExchangeRatesFromCSV(String filePath) {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Skip header
                if (line.startsWith("biweek")) {
                    continue;
                }
                String[] values = line.split(",");
                int biweekIndex = Integer.parseInt(values[0]);
                int soldCurrencyIndex = Integer.parseInt(values[1]);
                int boughtCurrencyIndex = Integer.parseInt(values[2]);
                double rate = Double.parseDouble(values[3]);

                // Populate the exchange rates array
                exchangeRates[biweekIndex][soldCurrencyIndex][boughtCurrencyIndex][1] = rate;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Buy currency
    //REFORMAT THIS
    public void buyCurrency(int biweekIndex, int soldCurrencyIndex, double soldCurrencyAmount, int boughtCurrencyIndex) {
        //ADD ERROR
        System.out.println("Press 1 to buy when  bought currency amount is known");//CHANGE THE WORDING
        System.out.println("Press 2 to buy when sold currency amount is known"); //SAME CHANGE WORDING
        //ERRORS FOR WHEN OTHER THAN 1-2 IS PRESSED
        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();
        if (choise == 1) {
            //calc amount of curr bought
            System.out.println("Enter the currency you want to buy");
            double boughtcurr = player.getCurrValue(soldCurrencyIndex) * exchangeRates[biweekIndex][soldCurrencyIndex][boughtCurrencyIndex][0];
            //set the value
            player.setPortfolio(boughtCurrencyIndex , (player.getCurrValue(boughtCurrencyIndex)+boughtcurr));
            System.out.println("You bought:" +boughtcurr+" ");
        }

        //calc amount of curr bought
        double boughtcurr = player.getCurrValue(soldCurrencyIndex) * exchangeRates[biweekIndex][soldCurrencyIndex][boughtCurrencyIndex][0];
        //set the value
        player.setPortfolio(boughtCurrencyIndex , (player.getCurrValue(boughtCurrencyIndex)+boughtcurr));

        //reduce the soldcurr value


        //after everything
        player.addTransaction("A" , "A" ,0,0);//FORMAT TO CORRECT THINGY
        player.displayPortfolio();
        //IF POSSIBLE ADD BROKER TRANSACTION COST ETC
    }
}

