
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
/**
 * @author (Abdelmaseh Nabil Welson) 
 */
public class MiniProject {
    // method 1
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
    
    // method 2
    public int getRank(int year, String name, String gender) {
        int rank = -1, count = 0;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec: parser) {
            if (rec.get(1).equals(gender)) {
                count++;
                if (rec.get(0).equals(name)) {
                    rank = count;
                    break;
                }
            }
        }
        return rank;
    }
    
    // method 3
    public String getName(int year, int rank, String gender) {
        int count = 0;
        String name = "NO Name";
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec: parser) {
            if (rec.get(1).equals(gender)) {
                count++;
                if (count == rank) {
                    name = rec.get(0);
                    break;
                }
            }
        }
        return name;
    }
    
    // merhod 4
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        
        String pron = "he";
        if (gender.equals("F")) {
            pron = "she";
        }
        System.out.println(name + " born in " + year + " would be " + newName
        + " if " + pron + " was born in " + newYear + ".");
    }
    
    // method 5
    public int yearOfHighestRank(String name, String gender) {
        int year = -1, rank = Integer.MAX_VALUE;
        
        String fileName = null;
        DirectoryResource dr = new DirectoryResource();
        for (File fr : dr.selectedFiles()) {
            int count = 0;
            FileResource file = new FileResource(fr);
            CSVParser parser = file.getCSVParser(false);
            for (CSVRecord record : parser) {
                if (record.get(1).equals(gender)) {
                    count++;
                    if (record.get(0).equals(name) && count < rank) {
                        fileName = fr.getName();
                        year = Integer.parseInt(fileName.substring(3, 7));
                        rank = count;
                        break;
                    }
                }
            }
        }        
        return year;
    }
    
    // method 6
    public double getAverageRank(String name, String gender) {
        double totalRank = 0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File fr : dr.selectedFiles()) {
            String fileName = fr.getName();
            int rank = getRank(Integer.parseInt(fileName.substring(3, 7)), name, gender);
            if (rank != -1) {
                count++;
                totalRank += rank;
            }
        }
        
        double averageRank = -1;
        if (count != 0) {
            averageRank = totalRank / count;
            String formatted = String.format("%.2f", averageRank);
            averageRank = Double.parseDouble(formatted);
        }
        
        
        return averageRank;
    }
    
    // method 7
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int toatlBirths = 0;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser) {
            if (record.get(1).equals(gender)) {
                if (record.get(0).equals(name)) {
                    break;
                }
                toatlBirths += Integer.parseInt(record.get(2));
            }
        }
        return toatlBirths;
    }
    // test method 2
    public void testGetRank() {
        int year = 2012;
        String name = "Mason", gender = "M";
        System.out.println(getRank(year, name, gender));
        gender = "F";
        System.out.println(getRank(year, name, gender));
    }
    // test method 3
    public void testGetName() {
        int year = 2012, rank = 5;
        String gender = "F";
        System.out.println(getName(year, rank, gender));
        rank = 6;
        System.out.println(getName(year, rank, gender));
        rank = 4;
        gender = "M";
        System.out.println(getName(year, rank, gender));
        rank = 6;
        System.out.println(getName(year, rank, gender));
    }
}
