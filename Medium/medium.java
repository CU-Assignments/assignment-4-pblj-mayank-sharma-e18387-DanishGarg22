import java.util.*;

class CardCollection {
    private final Map<String, List<String>> cardMap;

    public CardCollection() {
        cardMap = new HashMap<>();
    }

    public void addCard(String symbol, String cardName) {
        cardMap.computeIfAbsent(symbol, k -> new ArrayList<>()).add(cardName);
    }

    public List<String> getCardsBySymbol(String symbol) {
        return cardMap.getOrDefault(symbol, Collections.emptyList());
    }

    public void displayAllCards() {
        for (Map.Entry<String, List<String>> entry : cardMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

public class CardCollectionApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection collection = new CardCollection();

        collection.addCard("Hearts", "Ace of Hearts");
        collection.addCard("Hearts", "King of Hearts");
        collection.addCard("Spades", "Queen of Spades");
        collection.addCard("Diamonds", "Jack of Diamonds");
        collection.addCard("Clubs", "10 of Clubs");

        while (true) {
            System.out.println("\nCard Collection System");
            System.out.println("1. Find Cards by Symbol");
            System.out.println("2. Display All Cards");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter card symbol (Hearts, Spades, Diamonds, Clubs): ");
                    String symbol = scanner.nextLine();
                    List<String> cards = collection.getCardsBySymbol(symbol);
                    if (cards.isEmpty()) {
                        System.out.println("No cards found for " + symbol);
                    } else {
                        System.out.println("Cards of " + symbol + ": " + cards);
                    }
                    break;
                case 2:
                    collection.displayAllCards();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
