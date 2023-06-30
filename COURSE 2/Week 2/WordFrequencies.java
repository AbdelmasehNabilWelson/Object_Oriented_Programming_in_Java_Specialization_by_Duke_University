import java.util.ArrayList;
import edu.duke.FileResource;
/**
 * @author Abdelmaseh Nabil
 * @version 1/7/2023
 */
public class WordFrequencies{
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        
        FileResource fr = new FileResource();
        for (String s : fr.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int freq = myFreqs.get(index);
                myFreqs.set(index, freq + 1);
            }
        }
    }
    
    public int findIndexOfMax() {
        int maxFreq = myFreqs.get(0), currMax;
        int index = 0;
        for (int i = 1; i < myWords.size(); ++i) {
            currMax = myFreqs.get(i);
            if (currMax > maxFreq) {
                maxFreq = currMax;
                index = i;
            }
        }
        return index;
    }
    
    public void tester() {
        findUnique();
        System.out.println("The number of words = " + myWords.size());
        /*for (int i = 0; i < myWords.size(); ++i) {
            System.out.println(myWords.get(i) + '\t' + myFreqs.get(i));
        }*/
        int maxFreqIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are:" + myWords.get(maxFreqIndex) + " " + myFreqs.get(maxFreqIndex));
    }
} 