import java.io.*;
import edu.duke.*;
/**
 * Functions
 * 1- countLetters: counts the alphabet letters in a String
 * 2- maxIndex: Finds the index of maximum element in an array
 * 3- getKey: Finds the key used to encrypt a message using Ceaser Cipher algorithm using frequency analysis
 * 4- decrypt: decrypts an ecrypted message with Ceaser Cipher algorithm and the key is known.
 * 5- halfOfString: Returns the half of the input string 
 *      if the start is 0 return the string with chars at even index locations.
 *      if the start is 1 return the string with chars at odd index locations.
 * 6- decryptTwoKeys: Decrypts an ecrypted message with Ceaser Cipher algorithm with two keys and these 
 *    two keys are unkown.
 * 7- testDecrypt: Is a test to the function decrypt.
 * 8- testHalfOfString: Is a test to the function halfOfString.
 * 8- testDecryptTwoKeys: Is a test to the function decryptTwoKeys.
 * @author Abdelmaseh Nabil
 * @version 2023-6-28
 */
public class CaesarBreaker {
    public int[] countLetters(String str) {
        int[] counters = new int[26];
        char ch;
        for (int i = 0; i < str.length(); ++i) {
            ch = Character.toLowerCase(str.charAt(i));
            if (ch >= 'a' && ch <= 'z') {
                counters[ch - 'a']++;
            }
        }
        return counters;
    }
    
    public int maxIndex(int[] freqs) {
        int maxOcc = -1;
        int index = -1;
        for (int i = 0; i < freqs.length; ++i) {
            if (freqs[i] > maxOcc) {
                maxOcc = freqs[i];
                index = i;
            }
        }
        return index ;
    }
    
    public int getKey(String encrypted) {
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - (4);
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }
    
    public String decrypt(String encrypted) {
        CaesarCipher ceaser = new CaesarCipher();
        int dkey = getKey(encrypted);
        return ceaser.encrypt(encrypted, 26 - dkey);
    }
    
    public String halfOfString(String message, int start) {
        String str = "";
        for (int i = start; i  < message.length(); i += 2) {
            str += message.charAt(i);
        }
        return str;
    }
    
    public String decryptTwoKeys(String encrypted) {
        String encryptedWithEvenLocationsChar = halfOfString(encrypted, 0);
        String encryptedWithOddLocationsChar = halfOfString(encrypted, 1);
        int key1 = getKey(encryptedWithEvenLocationsChar);
        int key2 = getKey(encryptedWithOddLocationsChar);
        System.out.println("Key1 = " + key1 + ", key2 = " + key2);
        
        String decryptedEven = decrypt(encryptedWithEvenLocationsChar);
        String decryptedOdd = decrypt(encryptedWithOddLocationsChar);
        String decrypted = "";
        int indexEven = 0;
        int indexOdd = 0;
        for (int i = 0; i < encrypted.length(); ++i) {
            if (i % 2 == 0) {
                decrypted += decryptedEven.charAt(indexEven);
                indexEven += 1;
            } else {
                decrypted += decryptedOdd.charAt(indexOdd); 
                indexOdd += 1;                
            }
        }
        return decrypted;
    }
    
    public void testDecrypt() {
        CaesarCipher ceaser = new CaesarCipher();
        String message = "Just a test string with lots of eeeeeeeeeeeeeeeees";
        int key = 23;
        String encryptedMess = ceaser.encrypt(message, key);        
        String decryptedMess = decrypt(encryptedMess);
        System.out.println("The original message : " + message);
        System.out.println("The encrypted message : " + encryptedMess);
        System.out.println("The decrypted message : " + decryptedMess);
    }
    
    public void testHalfOfString() {
        String message = "Qbkm Zgis";
        System.out.println("Original Message " + message);
        System.out.println("Test with start 0 is " + halfOfString(message, 0));
        System.out.println("Test with start 1 is " + halfOfString(message, 1));
    }
    
    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource();
        String encrptedWithTwoKeys = fr.asString();
        //String encrptedWithTwoKeys = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        String decrypted = decryptTwoKeys(encrptedWithTwoKeys);
        System.out.println(decrypted);
    }
}
