import java.util.ArrayList;
import edu.duke.FileResource;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author Abdelmaseh Nabil
 * @version 1/7/2023
 */
public class CharactersInPlay {
    private ArrayList<String> charactersNames;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay() {
        charactersNames = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    
    public void update(String person) {
        int index = charactersNames.indexOf(person);
        if (index == -1) {
            charactersNames.add(person);
            counts.add(1);
        } else {
            int freq = counts.get(index);
            counts.set(index, freq + 1);
        }
    }
    
    public void findAllCharacters() {
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            int index = line.indexOf(".");
            if (index != -1) {
                String name = line.substring(0, index);
                update(name);
            }
        }
    }
    
    public int getMainCharacter() {
        int maxFreq = counts.get(0), currFreq;
        int index = 0;
        for (int i = 1; i < charactersNames.size(); ++i) {
            currFreq = counts.get(i);
            if (currFreq > maxFreq) {
                maxFreq = currFreq;
                index = i;
            }
        }
        return index;        
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < charactersNames.size(); ++i) {
            int currFreq = counts.get(i);
            if (currFreq >= num1 && currFreq <= num2) {
                System.out.println(charactersNames.get(i));
            }
        }
    }
   
    public void tester() {
        findAllCharacters();
        int mainCharIndex = getMainCharacter();
        System.out.println("The main Character is " + charactersNames.get(mainCharIndex) + '\t'
        + "The number of Speaking parts = " + counts.get(mainCharIndex));
    }
}
