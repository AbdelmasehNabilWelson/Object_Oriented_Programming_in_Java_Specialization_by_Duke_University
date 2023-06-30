import edu.duke.FileResource;
/**
 * This class contains a test on the CaesarCipherTwo Class
 * The functions are the same that on the CaesarBreaker Class
 * @author (Abdelmaseh) 
 * @version (29/6/2023)
 */
public class TestCaesarCipherTwo {
    private int[] countLetters(String str) {
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
    
    private int maxIndex(int[] freqs) {
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
    
    private int getKey(String encrypted) {
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - (4);
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }    
    
    private String halfOfString(String message, int start) {
        String str = "";
        for (int i = start; i  < message.length(); i += 2) {
            str += message.charAt(i);
        }
        return str;
    }
    
    public String decrypt(String encrypted) {
        CaesarCipher ceaser = new CaesarCipher();
        int dkey = getKey(encrypted);
        return ceaser.encrypt(encrypted, 26 - dkey);
    }    
    
    public String breakCaesarCipher(String input) {
        String encryptedWithEvenLocationsChar = halfOfString(input, 0);
        String encryptedWithOddLocationsChar = halfOfString(input, 1);
        int key1 = getKey(encryptedWithEvenLocationsChar);
        int key2 = getKey(encryptedWithOddLocationsChar);
        System.out.println("Key1 = " + key1 + ", key2 = " + key2);
        
        String decryptedEven = decrypt(encryptedWithEvenLocationsChar);
        String decryptedOdd = decrypt(encryptedWithOddLocationsChar);
        String decrypted = "";
        int indexEven = 0;
        int indexOdd = 0;
        for (int i = 0; i < input.length(); ++i) {
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
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        
        CaesarCipherTwo cc = new CaesarCipherTwo(17, 3);
        String encrypted = cc.encrypt(input);
        String decrypted = cc.decrypt(encrypted);
        
        System.out.println("The encrypted message: " + encrypted);
        System.out.println("The Original Decryption: " + decrypted);
        String decryptedUsingFreq = breakCaesarCipher(encrypted);
        System.out.println("The Decryption Using frequency: " + decryptedUsingFreq);
    }
}
