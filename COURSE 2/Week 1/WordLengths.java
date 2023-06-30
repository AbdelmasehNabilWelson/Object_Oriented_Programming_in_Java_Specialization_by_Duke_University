import java.io.*;
import edu.duke.*;
/**
 * Functioons
 * CountWordLengths: counts the number of words for each different word length in the file 
 * Resource If a word has a non-letter as the first or last character, it should not be 
 * counted as part of the word length.
 *  - For any words equal to or larger than the last index of the counts array.
 *    count them as the largest size represented in the counts array.
 * testCountWordLengths: Is a test for the function CountWordLengths.
 * @author (Abdelmaseh Nabil)
 * @version (2023-6-28)
 */
public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        int wordLength;
        for (String s : resource.words()) {
            wordLength = s.length();
            char firstChar = s.charAt(0);
            char lastChar = s.charAt(wordLength - 1);
            if (!Character.isLetter(firstChar)) {
                wordLength--;
            }
            if (!Character.isLetter(lastChar)) {
                wordLength--;
            }
            
            if (wordLength >= counts.length) {
                counts[counts.length - 1]++;
            } else {
                counts[wordLength]++;
            }
            
        }
    }
    
    public int indexOfMax(int[] values) {
        int index = 0;
        int maxLength = values[0];
        for (int i = 1; i < values.length; ++i) {
            if (values[i] > maxLength) {
                maxLength = values[i];
                index = i;
            }
        }
        return index;
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for (int i = 0; i < counts.length; ++i) {
            if (counts[i] != 0)
                System.out.println(counts[i] + " words of length " + i);
        }
        
        System.out.println("Index of maximum length = " + indexOfMax(counts));
     }
}
