public class Certificate {
    private CSR csr;
    private String signature;

    public Certificate(String dataToUnpack) {
        String[] data = dataToUnpack.split("\0");
        String[] temp = new String[4];
        if (data.length < 6) {
            if (data.length < 4) {
                temp[0] = data[0];
                temp[1] = "";
                temp[2] = "";
                temp[3] = "";
                data = temp;
            }
            this.csr = new CSR(data[0], data[1], data[2]);
            this.signature = data[3];
        } else {
            this.csr = new CSR(data[0], data[1], data[2], data[3], data[4]);
            this.signature = data[5];
        }
    }

    public Certificate(CSR csr) {
        this.csr = csr;
        this.signature = "unsigned";
    }


    @Override
    public String toString() {
        String[] temp = {this.csr.toString(), this.signature};
        return String.join("\0", temp);
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