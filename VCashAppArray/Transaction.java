package VCashAppArray;

import java.util.UUID;

/**
 * Represents a single transaction, tracking its type, amount, sender, and recipient.
 */
public class Transaction {
    private String transactionId;
    private String userId;
    private String type; // "cash-in" or "transfer"
    private double amount;
    private String senderId; 
    private String recipientId;

    // Constructor for cash-in transactions
    public Transaction(String userId, String type, double amount) {
        this.transactionId = UUID.randomUUID().toString();
        this.userId = userId;
        this.type = type;
        this.amount = amount;
    }
    
    // Constructor for transfer transactions
    public Transaction(String senderId, String recipientId, String type, double amount) {
        this.transactionId = UUID.randomUUID().toString();
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.type = type;
        this.amount = amount;
    }

    // Getters
    public String getTransactionId() { return transactionId; }
    public String getUserId() { return userId; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getSenderId() { return senderId; }
    public String getRecipientId() { return recipientId; }
}
