package com.msg.proj.utils;

public class Validator {
    public boolean isCnp(String cnp) {
        return cnp.matches("^[0-9]{13}$");
    }

    public boolean isAlphabetic(String string, int minLength, int maxLength) {
        return string.matches("[a-zA-Z ]{" + minLength + "," + maxLength + "}");
    }

    public boolean isAlphaNumeric(String string, int minLength, int maxLength) {
        return string.matches("[a-zA-Z0-9 ]{" + minLength + "," + maxLength + "}");
    }

    public boolean isTxtFilename(String string) {
        return string.matches("[a-zA-Z0-9]{1,}\\.txt");
    }
}
