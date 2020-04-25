package encryptdecrypt;

import java.util.HashMap;
import java.util.Map;

abstract class EncDec {

    public static final Map<Character, Character> encriptor = new HashMap<>();
    public static final Map<Character, Character> decriptor = new HashMap<>();


    abstract public String encrypt(String message) ;

    abstract public String decrypt(String message);
}

class UnicodeEncDec extends EncDec{

    public UnicodeEncDec(int cypher) {

        for (int i = 0; i < 178; i++) {
            char key = (char) i;
            char value = (char) (key + cypher);
            if (value > 178) {
                value = (char) (((key + cypher) - 178));
            }
            encriptor.put(key, value);
            decriptor.put(value, key);
        }

    }
    @Override
    public String encrypt(String message) {
        String result = "";
        for (char letter : message.toCharArray()) {
            result += encriptor.get(letter);
        }
        return result;
    }

    @Override
    public String decrypt(String message) {
        String result = "";
        for (char letter : message.toCharArray()) {
            result += decriptor.get(letter);
        }
        return result;
    }
}


class ShiftEncDec extends EncDec {

    public ShiftEncDec(int cypher) {
        for (int i = 97; i < 123; i++) {
            char key = (char) i;
            char value = (char) (key + cypher);
            if (value > 122) {
                value = (char) (((key + cypher) - 26));
            }
            encriptor.put(key, value);
            decriptor.put(value, key);
        }
        for (int i = 65; i < 91; i++) {
            char key = (char) i;
            char value = (char) (key + cypher);
            if (value > 90) {
                value = (char) (((key + cypher) - 26));
            }
            encriptor.put(key, value);
            decriptor.put(value, key);
        }
        encriptor.put('!', '!');
        decriptor.put('!', '!');
        encriptor.put(' ', ' ');
        decriptor.put(' ', ' ');
    }

    @Override
    public String encrypt(String message) {
        String result = "";
        for (char letter : message.toCharArray()) {
            result += encriptor.get(letter);
        }
        return result;
    }

    @Override
    public String decrypt(String message) {
        String result = "";
        for (char letter : message.toCharArray()) {
            result += decriptor.get(letter);
        }
        return result;
    }
}
