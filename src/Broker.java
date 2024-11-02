import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;//exception handling case left out

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
                
                try{
                    int biweekIndex = Integer.parseInt(values[0]);
                    int soldCurrencyIndex = Integer.parseInt(values[1]);
                    int boughtCurrencyIndex = Integer.parseInt(values[2]);
                    double rate = Double.parseDouble(values[3]);

                    // Populate the exchange rates array
                    exchangeRates[biweekIndex][soldCurrencyIndex][boughtCurrencyIndex][1] = rate;
                }//closed the nested try-cathc block
                catch (NumberFormatException e) {//Catches issues in parsing CSV fields (e.g., missing or malformed numeric data).
                    System.out.println("Error parsing numbers in CSV file line: " + line);
                    //e.printStackTrace();  UNCOMMENT THIS IF YOU WANT TO SEE THE STACK TRACE
                } catch (ArrayIndexOutOfBoundsException e) {//Captures any errors related to indices that exceed array limits (useful if there’s incorrect data).
                    System.out.println("Array index error while processing exchange rate data from CSV.");
                    //e.printStackTrace();  UNCOMMENT THIS IF YOU WANT TO SEE THE STACK TRACE
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to read file at path: " + filePath);
            //e.printStackTrace();  UNCOMMENT THIS IF YOU WANT TO SEE THE STACK TRACE
        }
    }

    //Buy currency
    //REFORMAT THIS
    public void buyCurrency(int biweekIndex, int soldCurrencyIndex, double soldCurrencyAmount, int boughtCurrencyIndex) {
        Scanner scanner = new Scanner(System.in);//put the scanner line outside of the try block.
        //ADD ERROR
        //edit- atharva here, ok ill add the handling
        try{
            System.out.println("Press 1 to buy with known bought currency amount");//CHANGE THE WORDING
            System.out.println("Press 2 to buy with known sold currency amount"); //SAME CHANGE WORDINGedit- atharva here changed the wordings a little bit looked fine to me
            //ERRORS FOR WHEN OTHER THAN 1-2 IS PRESSED
            //edit- atharva here- okay boss
            int choice = scanner.nextInt();
            if (choice != 1 && choice != 2) {
                System.out.println("Invalid choice. Please enter 1 or 2.");
                return;
            }//for when anything else is given in input
            
            if (choice == 1) {
                //calc amount of curr bought
                System.out.println("Enter the currency you want to buy");
                //atharva here-idhar input nahi lena kya?
                double boughtcurr = player.getCurrValue(soldCurrencyIndex) * exchangeRates[biweekIndex][soldCurrencyIndex][boughtCurrencyIndex][0];
                //set the value
                player.setPortfolio(boughtCurrencyIndex , (player.getCurrValue(boughtCurrencyIndex)+boughtcurr));
                System.out.println("You bought:" +boughtcurr+" ");
            }
            
//atharva here
//isnt line 88 to line 93 repeated? it will run without any given selectionof choice==1 or choice==2?

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
        catch (ArrayIndexOutOfBoundsException e) {//To avoid errors if invalid indices are accessed in the exchangeRates array.
            System.out.println("Invalid index for currencies or biweeks.");
             //e.printStackTrace();  UNCOMMENT THIS IF YOU WANT TO SEE THE STACK TRACE
        }
        catch (InputMismatchException e) {//Catches invalid inputs where a number is expected
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input from scanner buffer
             //e.printStackTrace();  UNCOMMENT THIS IF YOU WANT TO SEE THE STACK TRACE
        }
}

