/**
 * Reads a chosen CSV file of country exports and Implement specific operations on the data.
 * 
 * @author Abdelmaseh Nabil Welson
 * 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    // assigment function 1
    public CSVParser tester() {
        FileResource fr = new FileResource();
        return fr.getCSVParser();
    }
    
    // assigment function 2
    public String countryInfo(CSVParser parser, String country) {
        // initialize String to store the informations
        String informations = "NOT FOUND";
        // look for the Country column
        for(CSVRecord record : parser) {
            // look at the country column
            String currCountry = record.get("Country");
            // if country found return information about this country else return not found
            if (currCountry.equals(country)) {
                informations = currCountry + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
                break;
            }
        }
        return informations;
    }
    
    // assigment function 3
    // void method names listExportersTwoProducts(CSVParser parser, String exportItme1, String exportItem2)
    // print names of all counries that have exportItem1 and exportItem2 
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    // assigment function 4
    public void numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem)) {
                count++;
            }
        }
        System.out.println(count);
    }
    
    // assigment function 5
    public void bigExports(CSVParser parser, String money) {
        for (CSVRecord record : parser) {
            String amount = record.get("Value (dollars)");
            if (amount.length() > money.length()) {
                System.out.println(record.get("Country") + " " + amount);
            }
        }
    }
    
    public void testCountryInfo(String country) {
        CSVParser Parser = tester();
        System.out.println(countryInfo(Parser, country));
    }
    
    public void testListExportersTwoProducts(String exportItem1, String exportItem2) {
        CSVParser parser = tester();
        listExportersTwoProducts(parser, exportItem1, exportItem2);
    }
    
    public void testnumberOfExporters(String exportItem) {
        CSVParser parser = tester();
        numberOfExporters(parser, exportItem);
    }
    
    public void testBigExports(String money) {
        CSVParser parser = tester();
        bigExports(parser, money);
    }
    
    public void whoExportsCoffee() {
        // FileResource fr = new FileResource();
        // CSVParser parser = fr.getCSVParser();
        CSVParser parser = tester();
        listExporters(parser, "coffee");
    }
    
    public void whoExportsTea() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "tea");
    }
    
    public void whoExportsClothing() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "clothing");
    }
}
