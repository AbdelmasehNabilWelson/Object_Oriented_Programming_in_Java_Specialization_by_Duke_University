import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * @author (Duketolearn Team with my own respected modifiations) 
 */
public class BabyBirths {
    public void printNames() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false); // false means that this file does not have a header tow
        for (CSVRecord rec: parser) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) + 
                " Gender " + rec.get(1) + " NumBorn " + rec.get(2));
            }
        }
    }
    
    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0, totalGirls = 0;
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec: parser) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            } else {
                totalGirls += numBorn;
            }
        }
        System.out.println("Total Number of Births = " + totalBirths);
        System.out.println("Total Number of Boys' Births = " + totalBoys);
        System.out.println("Total Number of Girls' Births = " + totalGirls);
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
}