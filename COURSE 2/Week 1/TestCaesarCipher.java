import edu.duke.FileResource;
/**
 * This class contains a test on the CaesarCipherOOP Class
 * The functions are the same that on the CaesarBreaker Class
 * @author (Abdelmaseh) 
 * @version (29/6/2023)
 */ 
public class TestCaesarCipher {
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
        return index;
    }
    
    public String breakCaesarCipher(String input) {
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - (4);
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        
        CaesarCipherOOP cc = new CaesarCipherOOP(dkey);
        return cc.decrypt(input);
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        
        CaesarCipherOOP cc = new CaesarCipherOOP(18);
        String encrypted = cc.encrypt(input);
        String decrypted = cc.decrypt(encrypted);
        
        System.out.println("The encrypted message: " + encrypted);
        System.out.println("The Original Decryption: " + decrypted);
        System.out.println("The Decryption Using frequency: " + breakCaesarCipher(input));
    }
}
