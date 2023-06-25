import java.io.*;
import edu.duke.*;
public class CaesarCipher {
    public String encrypt(String input, int key) {
        String alphabet = "";
        StringBuilder encrypted = new StringBuilder(input);
        int i = 0;
        for (i = 0; i < 26; ++i) {
            alphabet += (char) ('A' + i);
        }
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        for (i = 0; i < input.length(); ++i) {
            char currChar = encrypted.charAt(i);
            int index = alphabet.indexOf(Character.toUpperCase(currChar));
            if (index != -1) {
                char newChar = shiftedAlphabet.charAt(index);
                if (Character.isLowerCase(currChar)) {
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                } else {
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
        String alphabet = "";
        StringBuilder encrypted = new StringBuilder(input);
        int i = 0;
        for (i = 0; i < 26; ++i) {
            alphabet += (char) ('A' + i);
        }
        
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        
        for (i = 0; i < input.length(); ++i) {
            char currChar = encrypted.charAt(i);
            int index = alphabet.indexOf(Character.toUpperCase(currChar));
            if (index != -1) {
                char newChar;
                if (i % 2 == 0){
                    newChar = shiftedAlphabet1.charAt(index);
                } else {
                    newChar = shiftedAlphabet2.charAt(index);
                }
                
                if (Character.isLowerCase(currChar)) {
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                } else {
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
    
    public void testCaesar () {
        int key = 23;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        /*String message = "First Legion";
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
        message = "First Legion";
        key = 17;
        encrypted = encrypt(message, key);*/
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public void testEncryptTwoKeys() {
        String phrase = "First Legion";
        int key1 = 23, key2 = 17;
        System.out.println(encryptTwoKeys(phrase, key1, key2));
    }
    
    public void quizAnswer() {
        String str = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key = 15;
        System.out.println("One key : " + encrypt(str, key));
        System.out.println("Two keys : " + encryptTwoKeys(str, 8, 21));
    }
}
