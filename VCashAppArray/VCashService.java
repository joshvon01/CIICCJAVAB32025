package VCashAppArray;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

public class VCashService {
    private ArrayList<VCA1User> users = new ArrayList<>();
    private ArrayList<VCA2Transaction> transactions = new ArrayList<>();

    public VCashService() {
        // You can pre-populate with some data for testing
        users.add(new VCA1User("AAA", "AAA@example.com", "1234567890", "AAA"));
        users.get(0).setBalance(new BigDecimal("1000.00"));
        users.add(new VCA1User("BBB", "BBB@example.com", "0987654321", "BBB"));
        users.get(1).setBalance(new BigDecimal("500.00"));
    }

    public VCA1User registerUser(String name, String email, String mobileNumber, String password) {
        // Check if user already exists
        boolean emailExists = users.stream().anyMatch(user -> user.getEmail().equals(email));
        boolean mobileExists = users.stream().anyMatch(user -> user.getMobileNumber().equals(mobileNumber));
        if (emailExists || mobileExists) {
            System.out.println("Error: User with this email or mobile number already exists.");
            return null;
        }

        VCA1User newUser = new VCA1User(name, email, mobileNumber, password);
        users.add(newUser);
        System.out.println("User registered successfully. Your user ID is: " + newUser.getUserId());
        return newUser;
    }

    public Optional<VCA1User> loginUser(String email, String password) {
        return users.stream()
            .filter(user -> user.getEmail().equals(email) && user.getPasswordHash().equals(password))
            .findFirst();
    }

    public boolean sendMoney(String senderId, String receiverId, BigDecimal amount) {
        Optional<VCA1User> senderOpt = users.stream().filter(u -> u.getUserId().equals(senderId)).findFirst();
        Optional<VCA1User> receiverOpt = users.stream().filter(u -> u.getUserId().equals(receiverId)).findFirst();

        if (senderOpt.isPresent() && receiverOpt.isPresent()) {
            VCA1User sender = senderOpt.get();
            VCA1User receiver = receiverOpt.get();

            if (sender.getBalance().compareTo(amount) >= 0) {
                sender.setBalance(sender.getBalance().subtract(amount));
                receiver.setBalance(receiver.getBalance().add(amount));
                transactions.add(new VCA2Transaction(senderId, receiverId, amount, "Send Money", "Completed"));
                return true;
            } else {
                System.out.println("Error: Insufficient balance.");
                transactions.add(new VCA2Transaction(senderId, receiverId, amount, "Send Money", "Failed"));
                return false;
            }
        }
        System.out.println("Error: Sender or receiver not found.");
        return false;
    }

    public ArrayList<VCA2Transaction> getUserTransactions(String userId) {
        ArrayList<VCA2Transaction> userTransactions = new ArrayList<>();
        for (VCA2Transaction t : transactions) {
            if (t.getSenderId().equals(userId) || t.getReceiverId().equals(userId)) {
                userTransactions.add(t);
            }
        }
        return userTransactions;
    }

    public Optional<VCA1User> getUserByMobile(String mobileNumber) {
        return users.stream()
            .filter(user -> user.getMobileNumber().equals(mobileNumber))
            .findFirst();
    }
}
