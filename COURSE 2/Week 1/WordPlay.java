import java.io.*;

public class WordPlay {
    public Boolean isVowel(char ch) {
        String str = "AEIOU";
        ch = Character.toUpperCase(ch);
        
        int index = str.indexOf(ch);
        boolean isVowel = true;
        if(index == -1)
            isVowel = false;
        
        return isVowel;
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder newPhrase = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); ++i) {
            char currChar = newPhrase.charAt(i);
            if (isVowel(currChar)) {
                newPhrase.setCharAt(i, ch);
            }
        }
        return newPhrase.toString();
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder newPhrase = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); ++i) {
            char currChar = Character.toLowerCase(newPhrase.charAt(i));
            ch = Character.toLowerCase(ch);
            if (ch == currChar) {
                if (i % 2 == 0) {
                    newPhrase.setCharAt(i, '*'); // odd number location = even index
                } else {
                    newPhrase.setCharAt(i, '+'); // even number location = odd index
                }
            }
        }
        return newPhrase.toString();        
    }
    
    public void testIsVowel() {
        char ch = 'a';
        System.out.println(isVowel(ch));
        ch = 'f';
        System.out.println(isVowel(ch));
    }
    
    public void testReplaceVowels() {
        String phrase = "Hello World";
        char ch = '*';
        System.out.println(phrase + '\n' + replaceVowels(phrase, ch));
        phrase = "AEIOU aeioe AeIoU";
        ch = '#';
        System.out.println(phrase + '\n' + replaceVowels(phrase, ch));
    }
    
    public void testEmphasize() {
        String phrase = "dna ctgaaactga";
        char ch = 'a';
        System.out.println(phrase + '\n' + emphasize(phrase, ch));
        phrase = "Mary Bella Abracadabra";
        ch = 'a';
        System.out.println(phrase + '\n' + emphasize(phrase, ch));
    }
}
