import java.util.Date;
import java.util.Scanner;
import java.util.InputMismatchException;//added this for error

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String playerName = null;
        
        System.out.println("Hello Welcome to the Forex Simulator");

        try {
            System.out.println("Enter the name of the Player:");
            playerName = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred while reading the player's name.");
            return;
        }
        
        //init everything
        Player player = new Player(playerName);
        Broker broker = new Broker(player);
            //added try catch block to chekc for error wrt externals files
            
            try {
                broker.populateExchangeRatesFromCSV("FILE PATH");
            } catch (Exception e) {//if the file path is incorrect, the file cannot be read, or some data in the file is problematic
                System.out.println("Error populating exchange rates.");
                 //e.printStackTrace();  UNCOMMENT THIS IF YOU WANT TO SEE THE STACK TRACE
            }
            //
        //


        System.out.println("Hello "+playerName+". Be ready to be rich by trading"); //change this
        System.out.println("Your current Demo Portfolio is ");
        player.displayPortfolio();
        System.out.println("This is a BiWeekly Trading Sim based on the data from Year 22-23");
        System.out.println("Today's date is 1 January 2022!");

        for (int i = 0; i <24; i++) {
            System.out.println("Today is :" +"date");//add the date function
            player.displayPortfolio(new Date());//CHANGE THIS INTO PROPER SYNTAX
            while(true){
                System.out.println("Press 1 to see your Portfolio");
                System.out.println("Press 2 to see Current Currency Exchange Rates");
                System.out.println("Press 3 to BUY/Exchange Currency");
                System.out.println("Press 4 to Continue to next week");
                 
                try {
                    int input = scanner.nextInt();
                    if (input == 1) {
                        player.displayPortfolio();
                    } else if (input == 2) {
                        // Placeholder for viewing exchange rates
                    } else if (input == 3) {
                        broker.buyCurrency(0, 0, 0, 0);
                    } else if (input == 4) {
                        break;
                    } else {
                        System.out.println("Please enter a valid option (1-4).");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // Clear invalid input
                }
            }
        }
        scanner.close();
    }
}
