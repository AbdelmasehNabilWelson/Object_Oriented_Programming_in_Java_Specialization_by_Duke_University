import edu.duke.*;
import java.util.*;
import java.io.File;
/**
 * @author (Abdelmaseh Nabil) 
 * @version (7/4/2023)
 */
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> mapWordsToFiles;
    public WordsInFiles () {
        mapWordsToFiles = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        for (String s : fr.words()) {
            if (mapWordsToFiles.containsKey(s)) {
                String fileName = f.getName();
                ArrayList<String> currList = mapWordsToFiles.get(s);
                if (!currList.contains(fileName))
                    currList.add(fileName);
                mapWordsToFiles.put(s, currList);
            } else {
                ArrayList<String> newList = new ArrayList<String>();
                newList.add(f.getName());
                mapWordsToFiles.put(s, newList);
            }
        }
    }
    
    private void buildWordFileMap() {
        mapWordsToFiles.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber() {
        int maxNum = -1;
        for (String s : mapWordsToFiles.keySet()) {
            maxNum = Math.max(maxNum, mapWordsToFiles.get(s).size());
        }
        return maxNum;
    }
    
    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList <String> words = new ArrayList<String>();
        for (String s : mapWordsToFiles.keySet()) {
            if (mapWordsToFiles.get(s).size() == number) {
                words.add(s);
            }
        }
        return words;
    }
    
    private void printFilesIn(String word) {
        System.out.println("Files That contains the word: " + word);
        ArrayList<String> list = mapWordsToFiles.get(word);
        for(String s : list) {
            System.out.println(s);
        }
    }
    
    public void tester() {
        buildWordFileMap();
        //int num = maxNumber();
        //System.out.println("The maximum number of Files any word is in = " + num);
    
        // graded quiz 13 
        /*
         int num = 4;
         ArrayList<String> wordsInMaxNumOfFiles = wordsInNumFiles(num);
         System.out.println("The number of words are in " + num + " of files = " + wordsInMaxNumOfFiles.size());*/
         
        // question 14
        //printFilesIn("sea");
        
        // question 15
        printFilesIn("laid");
    }
}
