public class CSR {
    private String ip;
    private String phoneNumber;
    private String publicKey;
    
    private String signedCertificate;

    public CSR(String ip, String phoneNumber, String publicKey) {
        this.ip = ip;
        this.phoneNumber = phoneNumber;
        this.publicKey = publicKey;
    }

    @Override
    public String toString() {
        String[] temp = new String[3];
        temp[0] = this.ip;
        temp[1] = this.phoneNumber;
        temp[2] = this.publicKey;
        return String.join("\0", temp);
    }
    
    public void setSignedCertificate(String content) {
        this.signedCertificate = content;
    }

    public String getSignedCertificate() {
        return signedCertificate;
    }
}
