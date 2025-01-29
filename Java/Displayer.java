package Project_7;

import java.util.List;
//Added a Displayer Class so it would make things easier to change the Text Prompts

public class Displayer {
    private DataBase database;

    public Displayer(DataBase database) {
        this.database = database;
    }

    public void displayHeader(String title) {
        System.out.println("=========================================================================================================================================================");
        System.out.println("                                                                " + title + "        ");
        System.out.println("=========================================================================================================================================================");
    }

    public void SubscriptionType() {
        System.out.println("Select the type of subscription:");
        System.out.println("[1] Magazine");
        System.out.println("[2] Streaming");
        System.out.println("[3] Freemium");
    }

    public void EnterChoice() {
        System.out.print("Enter your choice (1-3): ");
    }

    public void SubscriptionName() {
        System.out.print("Enter Subscription name: ");
    }

    public void Message(String subscriptionType) {
        System.out.println(subscriptionType + " subscription added successfully!");
    }

    public void InvalidChoice() {
        System.out.println("Invalid subscription type provided.");
    }


    public void RemoveMSG (String subscriptionType) {
        System.out.println(subscriptionType + " subscription removed successfully.");
    }

    public void NotFoundMSG (String subscriptionType) {
        System.out.println(subscriptionType + " subscription not found.");
    }

    public void CancelledMSG (String subscriptionType) {
        System.out.println(subscriptionType + " subscription cancelled.");
    }

    public void viewAllSubscriptions() {
        List<Magazine> magazines = database.getMagazines();
        List<Streaming> streamings = database.getStreamings();
        List<Freemium> freemiums = database.getFreemiums();

        System.out.println("Magazines that you have :");
        for (Magazine magazine : magazines) {
            System.out.println("Name: " + magazine.getName());
            System.out.println("Starting Date: " + magazine.getStartDate());
            System.out.println("End Date: " + magazine.getEndDate());
            System.out.println("Status " + magazine.getStatus());
            System.out.println("Delivery Type " + magazine.getDeliveryType());
            System.out.println("=========================================================================================================================================================");
        }
        System.out.println("Streamings that you have : ");
        for (Streaming stream : streamings) {
            System.out.println("Name: " + stream.getName());
            System.out.println("Starting Date: " + stream.getStartDate());
            System.out.println("End Date: " + stream.getEndDate());
            System.out.println("Status " + stream.getStatus());
            System.out.println("Device Limit " + stream.getDevicelimit());
            System.out.println("=========================================================================================================================================================");
        }
        System.out.println("Freemiums that you have :");
        for (Freemium free : freemiums) {
            System.out.println("Name: " + free.getName());
            System.out.println("Starting Date: " + free.getStartDate());
            System.out.println("End Date: " + free.getEndDate());
            System.out.println("Status " + free.getStatus());
            System.out.println("Trial Period: " + free.getTrialPeriod() + " days");
            System.out.println("=========================================================================================================================================================");
        }
    }
}