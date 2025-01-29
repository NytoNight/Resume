package Project_7;

import java.util.Scanner;
import java.util.List;

//Controller Class Handles all the Primary Function of our Program, that being Create
//Remove,Update and Cancel with Filter and Search Features Added

public class Controller implements ICANCEL, ICREATE, IREMOVE, IUPDATE {
    DataBase database;
    Scanner read;
    Displayer displayer;

    public Controller(DataBase database, Scanner read) {
        this.database = database;
        this.read = read;
        this.displayer = new Displayer(database);
    }


    @Override
    public void create() {
        displayer.displayHeader("Creating a new Subscriptions");
        displayer.SubscriptionType();
        displayer.EnterChoice();
        int choice = read.nextInt();
        read.nextLine();
        String name;
        String startDate;
        String endDate;
        switch (choice) {
            case 1:
                displayer.SubscriptionName();
                name = read.nextLine();
                startDate = ErrorHandling.getValidDate(read, "Enter start date (yyyy-MM-dd): ");
                endDate = ErrorHandling.getValidDate(read, "Enter end date (yyyy-MM-dd): ");
                System.out.print("Enter the Delivery Type [Physical or Online]: ");
                String deliveryType = read.nextLine();
                Magazine magazine = new Magazine(startDate, endDate, "Active", name, deliveryType);
                database.add_magazine(magazine);
                displayer.Message("Magazine");
                break;
            case 2:
                displayer.SubscriptionName();
                name = read.nextLine();
                System.out.print("Enter Device Limit: ");
                int deviceLimit = read.nextInt();
                read.nextLine();
                startDate = ErrorHandling.getValidDate(read, "Enter start date (yyyy-MM-dd): ");
                endDate = ErrorHandling.getValidDate(read, "Enter end date (yyyy-MM-dd): ");
                Streaming streaming = new Streaming(startDate, endDate, "Active", name, deviceLimit);
                database.addStreaming(streaming);
                displayer.Message("Streaming");
                break;
            case 3:
                displayer.SubscriptionName();
                name = read.nextLine();
                startDate = ErrorHandling.getValidDate(read, "Enter start date (yyyy-MM-dd): ");
                endDate = ErrorHandling.getValidDate(read, "Enter end date (yyyy-MM-dd): ");
                System.out.print("Enter Trial Period (in days): ");
                int trialPeriod = read.nextInt();
                Freemium freemium = new Freemium(startDate, endDate, "Active", name, trialPeriod);
                database.addFreemium(freemium);
                displayer.Message("Freemium");
                break;
            default:
                displayer.InvalidChoice();
                return;
        }
        System.out.println(" ");
    }


    @Override
    public void update() {
        displayer.displayHeader("Updating a Subscriptions");
        displayer.SubscriptionType();
        displayer.EnterChoice();
        int choice = read.nextInt();
        read.nextLine();
        System.out.println("Updating the subscription");
        displayer.SubscriptionName();
        String name = read.nextLine();
        String startDate = ErrorHandling.getValidDate(read, "Enter new start date (yyyy-MM-dd): ");
        String endDate = ErrorHandling.getValidDate(read, "Enter new end date (yyyy-MM-dd): ");
        switch (choice) {
            case 1:
                System.out.print("Enter Delivery Type[Online or Physical]: ");
                String deliveryType = read.nextLine();
                Magazine magazine = new Magazine(startDate, endDate, "Active", name, deliveryType);
                database.updateMagazine(magazine, 0);
                System.out.println("Updated the Magazine Subscription!!");
                break;
            case 2:
                System.out.print("Enter Device Limit: ");
                int deviceLimit = read.nextInt();
                Streaming streaming = new Streaming(startDate, endDate, "Active", name, deviceLimit);
                database.updateStreaming(streaming, 0);
                System.out.println("Updated the Streaming Subscription!!");
                break;
            case 3:
                System.out.print("Enter New Trial Period: ");
                int TrialPeriod = read.nextInt();
                Freemium freemium = new Freemium(startDate, endDate, "Active", name, TrialPeriod);
                database.updateFreemium(freemium, 0);
                System.out.println("Updated the Freemium Subscription!!");
                break;
            default:
                displayer.InvalidChoice();
                return;
        }

        System.out.println(" ");
    }

    @Override
    public void cancel() {
        displayer.displayHeader("Cancelling a Subscriptions");
        displayer.SubscriptionType();
        displayer.EnterChoice();
        int choice = read.nextInt();
        System.out.println("Canceling the subscription");
        read.nextLine();
        displayer.SubscriptionName();
        String name = read.nextLine();
        switch (choice) {
            case 1:
                Magazine magazine = new Magazine("Cancelled", "Cancelled", "Cancelled", name, "Cancelled");
                database.updateMagazine(magazine, 0);
                displayer.CancelledMSG("Magazine");
                break;
            case 2:
                Streaming streaming = new Streaming("Cancelled", "Cancelled", "Cancelled", name, 0);
                database.updateStreaming(streaming, 0);
                displayer.CancelledMSG("Streaming");
                break;
            case 3:
                Freemium freemium = new Freemium("Cancelled", "Cancelled", "Cancelled", name, 0);
                database.updateFreemium(freemium, 0);
                displayer.CancelledMSG("Freemium");
                break;
            default:
                displayer.InvalidChoice();
                break;
        }
        System.out.println(" ");
    }


    @Override
    public void remove() {
        displayer.displayHeader("Removing a Subscriptions");
        displayer.SubscriptionType();
        displayer.EnterChoice();
        int choice = read.nextInt();
        read.nextLine();
        displayer.SubscriptionName();
        String name = read.nextLine();

        switch (choice) {
            case 1:
                Magazine magazine = database.searchMagazine(name);
                if (magazine != null) {
                    database.removeMagazine(magazine);
                    displayer.RemoveMSG("Magazine");
                } else {
                    displayer.NotFoundMSG("Magazine");
                }
                break;
            case 2:
                Streaming streaming = database.searchStreams(name);
                if (streaming != null) {
                    database.removeStreaming(streaming);
                    displayer.RemoveMSG("Streaming");
                } else {
                    displayer.NotFoundMSG("Streaming");
                }
                break;
            case 3:
                Freemium freemium = database.searchFreemium(name);
                if (freemium != null) {
                    database.removeFreemium(freemium);
                    displayer.RemoveMSG("Freemium");
                } else {
                    displayer.NotFoundMSG("Freemium");
                }
                break;
            default:
                displayer.InvalidChoice();
                break;
        }
    }

    public void History() {
        displayer.displayHeader("Viewing ALL Subscriptions");
        System.out.println("Viewing all subscriptions...");
        displayer.viewAllSubscriptions();
    }

    public void search() {
        displayer.displayHeader("Searching for a Existing Subscription");
        displayer.SubscriptionType();
        displayer.EnterChoice();
        int choice = read.nextInt();
        read.nextLine();
        displayer.SubscriptionName();
        String name = read.nextLine();
        switch (choice) {
            case 1:
                Magazine magazine = database.searchMagazine(name);
                if (magazine != null) {
                    System.out.println("Magazine found: " + magazine.getName() + " - Status: " + magazine.getStatus());
                } else {
                    displayer.NotFoundMSG("Magazine");
                }
                break;
            case 2:
                Streaming streaming = database.searchStreams(name);
                if (streaming != null) {
                    System.out.println("Streaming found: " + streaming.getName() + " - Status: " + streaming.getStatus());
                } else {
                    displayer.NotFoundMSG("Streaming");
                }
                break;
            case 3:
                Freemium freemium = database.searchFreemium(name);
                if (freemium != null) {
                    System.out.println("Freemium found: " + freemium.getName() + " - Status: " + freemium.getStatus());
                } else {
                    displayer.NotFoundMSG("Freemium");
                }
                break;
            default:
                displayer.InvalidChoice();
                break;
        }
    }


    public void filter() {
        displayer.displayHeader("Filtering ALL Subscriptions");
        displayer.SubscriptionType();
        displayer.EnterChoice();
        int choice = read.nextInt();
        read.nextLine();
        System.out.println("Enter the status to filter by [Active or Cancelled]: ");
        String status = read.nextLine();
        System.out.println("Results");
        switch (choice) {
            case 1:
                List<Magazine> filteredMagazines = database.filterMagazines(status);
                if (filteredMagazines.isEmpty()) {
                    System.out.println("No magazines found with status: " + status);
                } else {
                    System.out.println("Filtered Magazines:");
                    for (Magazine magazine : filteredMagazines) {
                        System.out.println("Name: " + magazine.getName() + " - Status: " + magazine.getStatus());
                    }
                }
                break;
            case 2:
                List<Streaming> filteredStreams = database.filterStreams(status);
                if (filteredStreams.isEmpty()) {
                    System.out.println("No Streaming Sites found with status: " + status);
                } else {
                    System.out.println("Filtered Streaming Sites Subscription");
                    for (Streaming streaming : filteredStreams) {
                        System.out.println("Name: " + streaming.getName() + " - Status: " + streaming.getStatus());
                    }
                }
                break;
            case 3:
                List<Freemium> filteredFreemiums = database.filterFreemiums(status);
                if (filteredFreemiums.isEmpty()) {
                    System.out.println("No freemiums found with status: " + status);
                } else {
                    System.out.println("Filtered Freemium Subscription:");
                    for (Freemium freemium : filteredFreemiums) {
                        System.out.println("Name: " + freemium.getName() + " - Status: " + freemium.getStatus());
                    }
                }
                break;
            default:
                System.out.println("Invalid subscription type provided.");
                break;
        }
    }
}