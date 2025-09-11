package VCashAppArray;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VCA2Transaction {
    private static int nextTransactionId = 1;

    private int transactionId;
    private String senderId;
    private String receiverId;
    private BigDecimal amount;
    private String transactionType;
    private LocalDateTime transactionDate;
    private String status;

    public VCA2Transaction(String senderId, String receiverId, BigDecimal amount, String transactionType, String status) {
        this.transactionId = nextTransactionId++;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = LocalDateTime.now();
        this.status = status;
    }

    // Getters and Setters
    public int getTransactionId() {
        return transactionId;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public String getStatus() {
        return status;
    }
}
