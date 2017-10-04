package com.redhat.fuse.sample;

public class TestBean {

    private String decrypted;

    public TestBean(String decrypted) {
        this.decrypted = decrypted;
        System.out.println("constructor: " + decrypted);
    }

    public String getDecrypted() {
        return decrypted;
    }

    public void setDecrypted(String decrypted) {
        this.decrypted = decrypted;
    }

    public String print() {
        return "decrypted message: " + decrypted;
    }
}
