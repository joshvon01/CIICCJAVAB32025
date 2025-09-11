package VCashAppArray;

import java.math.BigDecimal;
import java.util.UUID;

public class VCA1User {
    private String userId;
    private String name;
    private String email;
    private String mobileNumber;
    private String passwordHash;
    private BigDecimal balance;

    public VCA1User(String name, String email, String mobileNumber, String passwordHash) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.passwordHash = passwordHash;
        this.balance = new BigDecimal("0.00");
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
