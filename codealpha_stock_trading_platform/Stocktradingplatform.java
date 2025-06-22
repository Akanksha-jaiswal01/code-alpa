import java.util.*;

class Stock {
    String symbol;
    double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol.toUpperCase();
        this.price = price;
    }
}

class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double balance;

    public Portfolio(double initialBalance) {
        this.balance = initialBalance;
    }

    public void buyStock(Stock stock, int quantity) {
        double cost = stock.price * quantity;
        if (balance >= cost) {
            holdings.put(stock.symbol, holdings.getOrDefault(stock.symbol, 0) + quantity);
            balance -= cost;
            System.out.println("✅ Bought " + quantity + " shares of " + stock.symbol);
        } else {
            System.out.println("❌ Insufficient balance to buy " + stock.symbol);
        }
    }

    public void sellStock(Stock stock, int quantity) {
        int currentQty = holdings.getOrDefault(stock.symbol, 0);
        if (currentQty >= quantity) {
            holdings.put(stock.symbol, currentQty - quantity);
            balance += stock.price * quantity;
            System.out.println("✅ Sold " + quantity + " shares of " + stock.symbol);
        } else {
            System.out.println("❌ Not enough shares to sell " + stock.symbol);
        }
    }

    public void displayPortfolio(Map<String, Stock> market) {
        System.out.println("\n---- 📊 Portfolio Summary ----");
        System.out.printf("Available Balance: ₹%.2f\n", balance);
        if (holdings.isEmpty()) {
            System.out.println("No holdings yet.");
        } else {
            for (String symbol : holdings.keySet()) {
                int qty = holdings.get(symbol);
                Stock stock = market.get(symbol);
                if (stock != null) {
                    double value = qty * stock.price;
                    System.out.printf("%s: %d shares | Current Value: ₹%.2f\n", symbol, qty, value);
                } else {
                    System.out.println(symbol + ": Stock not found in market data.");
                }
            }
        }
    }
}

public class Stocktradingplatform {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Stock> market = new HashMap<>();
        market.put("AAPL", new Stock("AAPL", 150.00));
        market.put("GOOGL", new Stock("GOOGL", 2800.00));
        market.put("TSLA", new Stock("TSLA", 700.00));

        Portfolio portfolio = new Portfolio(10000); // Initial balance

        int choice = 0;

        while (choice != 5) {
            System.out.println("\n---- 📈 Stock Trading Platform ----");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); // clear newline after nextInt
            } else {
                System.out.println("❗ Please enter a valid number.");
                sc.nextLine(); // clear the invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n📃 Available Stocks:");
                    for (Stock stock : market.values()) {
                        System.out.printf("%s - ₹%.2f\n", stock.symbol, stock.price);
                    }
                    break;

                case 2:
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = sc.hasNextLine() ? sc.nextLine().trim().toUpperCase() : "";
                    if (!market.containsKey(buySymbol)) {
                        System.out.println("❌ Invalid stock symbol.");
                        break;
                    }

                    System.out.print("Enter quantity: ");
                    if (sc.hasNextInt()) {
                        int buyQty = sc.nextInt();
                        sc.nextLine(); // clear newline
                        portfolio.buyStock(market.get(buySymbol), buyQty);
                    } else {
                        System.out.println("❗ Quantity must be a number.");
                        sc.nextLine(); // clear invalid input
                    }
                    break;

                case 3:
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = sc.hasNextLine() ? sc.nextLine().trim().toUpperCase() : "";
                    if (!market.containsKey(sellSymbol)) {
                        System.out.println("❌ Invalid stock symbol.");
                        break;
                    }

                    System.out.print("Enter quantity: ");
                    if (sc.hasNextInt()) {
                        int sellQty = sc.nextInt();
                        sc.nextLine(); // clear newline
                        portfolio.sellStock(market.get(sellSymbol), sellQty);
                    } else {
                        System.out.println("❗ Quantity must be a number.");
                        sc.nextLine(); // clear invalid input
                    }
                    break;

                case 4:
                    portfolio.displayPortfolio(market);
                    break;

                case 5:
                    System.out.println("👋 Exiting platform. Thank you!");
                    break;

                default:
                    System.out.println("❗ Invalid choice. Try again.");
            }
        }

        sc.close();
    }
}