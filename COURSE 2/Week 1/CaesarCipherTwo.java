/**
 * This class the same functions as in CaesarCipher Class but the implementations using OOP concepts
 * The difference between this class and the CaesarCipherOOP Class is that this class uses 
 * the encryption and decryption algorithm using two keys.
 * @author (Abdelmaseh) 
 * @version (29/6/2023)
 */ 
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2) {
        mainKey1 = key1;
        mainKey2 = key2;
        alphabet = "";
        for (int i = 0; i < 26; ++i) {
            alphabet += (char) ('A' + i);
        }        
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < input.length(); ++i) {
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
    
    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cc.encrypt(input);
    }
}
