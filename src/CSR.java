public class CSR {
    private String ip;
    private String phoneNumber;
    private String publicKey;
    private String expiryDate;
    private String extras;

    public CSR(String ip, String phoneNumber, String publicKey) {
        this.ip = ip;
        this.phoneNumber = phoneNumber;
        this.publicKey = publicKey;
        this.expiryDate = "";
        this.extras = "";
    }

    public CSR(String ip, String phoneNumber, String publicKey, String expiryDate, String extras) {
        this.ip = ip;
        this.phoneNumber = phoneNumber;
        this.publicKey = publicKey;
        this.expiryDate = expiryDate;
        this.extras = extras;
    }

    @Override
    public String toString() {
        String[] temp = new String[5];
        temp[0] = this.ip;
        temp[1] = this.phoneNumber;
        temp[2] = this.publicKey;
        temp[3] = this.expiryDate;
        temp[4] = this.extras;
        return String.join("\0", temp);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }
}