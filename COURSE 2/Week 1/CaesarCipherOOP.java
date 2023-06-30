/**
 * This class the same functions as in CaesarCipher Class but the implementations using OOP concepts
 * @author (Abdelmaseh) 
 * @version (29/6/2023)
 */ 
public class CaesarCipherOOP {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipherOOP(int key) {
        mainKey = key;
        alphabet = "";
        for (int i = 0; i < 26; ++i) {
            alphabet += (char) ('A' + i);
        }
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < input.length(); ++i) {
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
    
    public String decrypt(String input) {
        CaesarCipherOOP cc = new CaesarCipherOOP(26 - mainKey);
        return cc.encrypt(input);
    }
}
