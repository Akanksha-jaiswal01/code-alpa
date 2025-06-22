import java.util.Scanner;
import java.util.HashMap;

public class AIChatbot {

    private static final HashMap<String, String> responses = new HashMap<>();

    static {
        responses.put("hi", "Hello! How can I help you today?");
        responses.put("hello", "Hi there! What can I do for you?");
        responses.put("how are you", "I'm just a bot, but I'm doing great!");
        responses.put("what is your name", "I am a simple AI Chatbot built using Java.");
        responses.put("bye", "Goodbye! Have a nice day.");
        responses.put("thanks", "You're welcome!");
        responses.put("help", "You can ask me about our services, working hours, or general queries.");
    }

    public static String getResponse(String input) {
        input = input.toLowerCase().trim();
        if (responses.containsKey(input)) {
            return responses.get(input);
        } else {
            return "I'm sorry, I don't understand that. Can you rephrase?";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("AI Chatbot is ready to chat! Type 'bye' to exit.");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bot: " + getResponse("bye"));
                break;
            }
            System.out.println("Bot: " + getResponse(userInput));
        }

        scanner.close();
    }
}
