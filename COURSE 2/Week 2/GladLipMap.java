import edu.duke.*;
import java.util.*;
/**
 * @author (Abdelmaseh Nabil) 
 * @version (7/4/2023)
 */
public class GladLipMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> consideredLabels;
    private ArrayList<String> wordSeen;
    private int countWords = 0;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";    
    
    public GladLipMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLipMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {        
        String[] labels = {"adjective", "noun", "color", "country", "name",
        "animal", "timeframe", "verb", "fruit"};
        myMap = new HashMap<String, ArrayList<String>>();
        for(String s : labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s, list);
        }
        
        wordSeen = new ArrayList<String>();
        consideredLabels = new ArrayList<String>();
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return "" + myRandom.nextInt(50) + 5;
        }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        
        String label = w.substring(first+1,last);
        if (!consideredLabels.contains(label) && !label.equals("number")) {
            consideredLabels.add(label);
        }
        
        String sub = null;
        while (true) {
            sub = getSubstitute(label);
            if (!wordSeen.contains(sub)) {
                wordSeen.add(sub);
                countWords++;
                break;
            }
        }
        //System.out.println("prefix = " + prefix + " sub = " + sub + " suffix = " + suffix);
        return prefix+sub+suffix;
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                //System.out.println(processWord(word));
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                //System.out.println(processWord(word));
                story = story + processWord(word) + " ";
            }
        }
        System.out.println("Number of words seen = " + countWords);
        return story;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
        System.out.println('\n');
    }
    
    private int totalWordsInMap() {
        int total = 0;
        for (String s : myMap.keySet()) {
            total += myMap.get(s).size();
        }
        return total;
    }
    
    private int totalWordsConsidered() {
        if (myMap.size() == 3) {
            if (myMap.containsKey("noun") && myMap.containsKey("color") && myMap.containsKey("adjective")) {
                return totalWordsInMap();
            }
        }
        
        int sum = 0;
        for (String s : consideredLabels) {
            //System.out.println(s);
            sum += myMap.get(s).size();
        }
        return sum;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        
        System.out.println("Total number of words in a Map " + totalWordsInMap() + '\n');
        System.out.println("Total number of words considered in a story " + totalWordsConsidered());
    }    
}