package com.dropit.backend_drop_it.util;

public class SequenceGenerator {

    public String AlphaNumeric(long length) {
        StringBuilder seq = new StringBuilder();

        for (int i = 0; i < length; i++) {

            int randomNumber = (int)(Math.random() * 1000 / 10);

            switch (randomNumber % 3) {
                case 0 -> seq.append(randomNumber % 10);
                case 1 -> seq.append((char)(randomNumber % 26 + 65));
                case 2 -> seq.append((char)(randomNumber % 26 + 97));
            }

        }

        return seq.toString();
    }
}
