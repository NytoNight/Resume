package Project_7;
import java.util.InputMismatchException;
import java.util.Scanner;
//Group 2 - Irvin David - Ryan Bojos - Mart Navarro
public class Main {
    public static void main(String[] args) {
        DataBase database = new DataBase();
        Scanner read = new Scanner(System.in);
        Controller controller = new Controller(database, read);
        while (true) {
            System.out.flush();
            System.out.println("=========================================================================================================================================================");
            System.out.println("                                                                Sub-Scribe Management System        ");
            System.out.println("=========================================================================================================================================================");
            System.out.println("Select the Number corresponding to the option you wish to do today:");
            System.out.println("[1] Create a new subscription");
            System.out.println("[2] Update an existing subscription");
            System.out.println("[3] Cancel a subscription");
            System.out.println("[4] View History and All Subscriptions Details");
            System.out.println("[5] Filter and check Status of a Subscriptions");
            System.out.println("[6] Search for a subscription");
            System.out.println("[7] Remove a subscription");
            System.out.println("[8] Exit the program");
            System.out.println("=========================================================================================================================================================");
            System.out.print("Enter your choice: ");
            try {
                int choice = read.nextInt();
                read.nextLine();
                switch (choice) {
                    case 1:
                        controller.create(); //Process is stored in the Controller Class and only calls it from there
                        break;
                    case 2:
                        controller.update();//Process is stored in the Controller Class and only calls it from there
                        break;
                    case 3:
                        controller.cancel();//Process is stored in the Controller Class and only calls it from there
                        break;
                    case 4:
                        controller.History();//Process is stored in the Displayer Class which gets it's info from the DataBase and the Controller only acts as a Bridge between them
                        break;
                    case 5:
                        controller.filter();//Filter would use status [Active or Cancelled], The logistics of this is inside the Controller class and the Filter function class is in the Database, the Controller only gets the info and acts as a bridge as well
                        break;
                    case 6:
                        controller.search();//The Process is similar to the filter feature but it searches for the Name of the Subscription instead of it's status
                        break;
                    case 7:
                        controller.remove();//Process is stored in the Controller Class then it directly removes the Subscription from the Database Class.
                        break;
                    case 8:
                        System.out.println("Closing System.... Thank you for using the Subscription Management System! ");
                        read.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException error) {
                System.out.println("Invalid Choice, Please Input one option from the Prompt");
                read.next();
            }
        }
    }
}