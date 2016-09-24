package org.tdd.counterstring;

/**
 * Created by Tom on 9/3/2016.
 */
/*
Developed through TDD - development guided by tests

It was quite fun to pass each test starting from the simplest case of class
requirements.
 */
public class CounterString {

    //    Improved design based on House Of Test Generator
    public static String generate(int length, String delimiter) {
        StringBuilder string = new StringBuilder();
        int interimStringLen;
        int digit = length;
        while (digit > 0) {

            if (digit == 1) {
                string.insert(0, delimiter);
            } else {
                string.insert(0, digit + delimiter);
            }
            interimStringLen = string.length();
            digit = length - interimStringLen;
        }
        return string.toString();
    }

    public static String generate(int length) {
        String delimiter = "*";
        return generate(length, delimiter);
    }

    public static String generate_originalVersion(
            int length, String delimiter) {

        StringBuilder string = new StringBuilder();
        switch (length) {
            case 0:
                return "";
            case 1:
                return "*";
        }
        int interimStringLen;
        int digit;
        while (true) {
            interimStringLen = string.length();
            digit = length - interimStringLen;

            if (digit > 1) {
                string.insert(0, digit + delimiter);
            } else {
                if (digit == 1) string.insert(0, delimiter);
                break;
            }
        }
        return string.toString();
    }

}
