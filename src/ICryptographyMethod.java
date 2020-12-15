interface ICryptographyMethod {
    String encrypt(String message);

    String decrypt(String data);

    void init();
}
