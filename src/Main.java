import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Welcome to the Forex Simulator");

        System.out.println("Enter the name of the Player:");
        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();

        //init everything
        Player player = new Player(playerName);
        Broker broker = new Broker(player);
        broker.populateExchangeRatesFromCSV("FILE PATH");

        //


        System.out.println("Hello "+playerName+". Be ready to be rich by trading"); //change this
        System.out.println("Your current Demo Portfolio is ");
        player.displayPortfolio();
        System.out.println("This is a BiWeekly Trading Sim based on the data from Year 22-23");
        System.out.println("Today's date is 1 January 2022!");

        for (int i = 0; i <24; i++) {
            System.out.println("Today is :" +"date"); //add the date function
            while(true){
                System.out.println("Press 1 to see your Portfolio");
                System.out.println("Press 2 to see Current Currency Exchange Rates");
                System.out.println("Press 3 to BUY/Exchange Currency");
                System.out.println("Press 4 to Continue to next week");
                int input = scanner.nextInt();
                if(input == 1){
                    player.displayPortfolio();
                }
                if(input == 2){
                    //BUILD THIS FUNCTION IN BROKER CLASS
                }
                if(input == 3){
                    broker.buyCurrency(0,0,0,0);
                }
                if(input == 4){
                    break;
                }
            }
        }
    }
}