public class Certificate {
    private CSR csr;
    private String signature;

    public Certificate(CSR csr, String signature) {
        this.csr = csr;
        this.signature = signature;
    }
    public Certificate(CSR csr) {
        this.csr = csr;
    }

    @Override
    public String toString() {
        String [] temp = {this.csr.toString(),this.signature};
        return String.join("\0",temp);
    }

    public CSR getCsr() {
        return csr;
    }

    public void setCsr(CSR csr) {
        this.csr = csr;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }


}