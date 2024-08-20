import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AuctionItem {
    private String itemName;
    private double startingPrice;
    private double highestBid;
    private String highestBidder;

    public AuctionItem(String itemName, double startingPrice) {
        this.itemName = itemName;
        this.startingPrice = startingPrice;
        this.highestBid = startingPrice;
        this.highestBidder = "No bids yet";
    }

    public String getItemName() {
        return itemName;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public String getHighestBidder() {
        return highestBidder;
    }

    public boolean placeBid(String bidderName, double bidAmount) {
        if (bidAmount > highestBid) {
            highestBid = bidAmount;
            highestBidder = bidderName;
            return true;
        } else {
            System.out.println("Bid too low. Current highest bid is " + highestBid);
            return false;
        }
    }
}

class AuctionSystem {
    private List<AuctionItem> auctionItems;

    public AuctionSystem() {
        auctionItems = new ArrayList<>();
    }

    public void addAuctionItem(String itemName, double startingPrice) {
        auctionItems.add(new AuctionItem(itemName, startingPrice));
    }

    public void listItems() {
        System.out.println("Auction Items:");
        for (AuctionItem item : auctionItems) {
            System.out.println("- " + item.getItemName() + ": Starting Price = " + item.getHighestBid() + ", Current Highest Bidder = " + item.getHighestBidder());
        }
    }

    public void placeBid(String itemName, String bidderName, double bidAmount) {
        for (AuctionItem item : auctionItems) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                boolean success = item.placeBid(bidderName, bidAmount);
                if (success) {
                    System.out.println(bidderName + " placed a bid of " + bidAmount + " on " + itemName);
                }
                return;
            }
        }
        System.out.println("Item not found.");
    }
}

public class Main {
    public static void main(String[] args) {
        AuctionSystem auctionSystem = new AuctionSystem();
        Scanner scanner = new Scanner(System.in);

        auctionSystem.addAuctionItem("Laptop", 500);
        auctionSystem.addAuctionItem("Smartphone", 300);

        boolean running = true;

        while (running) {
            System.out.println("\n1. List Auction Items");
            System.out.println("2. Place a Bid");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    auctionSystem.listItems();
                    break;
                case 2:
                    System.out.print("Enter item name: ");
                    scanner.nextLine();  // Consume newline
                    String itemName = scanner.nextLine();
                    System.out.print("Enter your name: ");
                    String bidderName = scanner.nextLine();
                    System.out.print("Enter your bid amount: ");
                    double bidAmount = scanner.nextDouble();
                    auctionSystem.placeBid(itemName, bidderName, bidAmount);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
