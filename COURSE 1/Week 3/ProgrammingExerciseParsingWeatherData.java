/**
 * @author (Abdelmaseh Nabil Welson) 
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ProgrammingExerciseParsingWeatherData {
    // The first function of the Assignment 
    public CSVRecord coldestHourInFile(CSVParser parser) {
        //start with min nothing
        CSVRecord min = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            if (currentRow.get("TemperatureF").equals("-9999"))
                continue;
            min = minOfTwo(min, currentRow);
        }
        return min;
    }
    
    // The second function of the Assignment
    public String[] fileWithColdestTemperature() {
        CSVRecord min = null;
        DirectoryResource dr = new DirectoryResource();
        String coldestFileName = "";
        String coldestFilePath = "";
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            
            if (min == null) {
                min = current;
            } else {
                double currentTemp = Double.parseDouble(current.get("TemperatureF"));
                double minTemp = Double.parseDouble(min.get("TemperatureF"));
                
                if (currentTemp == -9999 || minTemp == -9999)
                    continue;
                if (currentTemp < minTemp) {
                    min = current;
                    coldestFileName = f.getName();
                    coldestFilePath = f.getPath();
                }
            }
        }
        
        String[] fileData = {coldestFileName, coldestFilePath};
        return fileData;
    }
    
    // The Third function
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord min = null;
        for (CSVRecord record : parser) {
            if (!record.get("Humidity").equals("N/A")) {
                min = minOfTwoHumidity(min, record);
            }
        }
        return min;
    }
    
    // The fourth function
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord min = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            min = minOfTwoHumidity(min, current);
        }
        return min;
    }
    
    // The 5th function
    public double averageTemperatureInFile(CSVParser parser) {
        double totalTemp = 0;
        int count = 0;
        for (CSVRecord record : parser) {
            totalTemp += Double.parseDouble(record.get("TemperatureF"));
            count++;
        }
        String formattedNumber = String.format("%.4f", totalTemp / count);
        return Double.parseDouble(formattedNumber);
    }
    
    // The last function
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double totalTemp = 0;
        int count = 0;
        for (CSVRecord record : parser) {
            if (!record.get("Humidity").equals("N/A")) {
                double humidity = Double.parseDouble(record.get("Humidity"));
                if (humidity >= value) {
                    totalTemp += Double.parseDouble(record.get("TemperatureF"));
                    count++;
                }
            }
        }
        String formattedNumber = String.format("%.3f", totalTemp / count);
        return Double.parseDouble(formattedNumber);
    }
   
    public CSVRecord minOfTwoHumidity(CSVRecord min, CSVRecord current) {
        if (min == null) {
            min = current;
        } else {
            double currentHumidity = Double.parseDouble(current.get("Humidity"));
            double minHumidity = Double.parseDouble(min.get("Humidity"));
            
            if (currentHumidity < minHumidity) {
                min = current;
            }
        }
        return min;
    }
    
    public CSVRecord minOfTwo(CSVRecord min, CSVRecord current) {
        if (min == null) {
            min = current;
        } else {
            double currentTemp = Double.parseDouble(current.get("TemperatureF"));
            double minTemp = Double.parseDouble(min.get("TemperatureF"));
                
            if (currentTemp < minTemp) {
                min = current;
            }
        }
        return min;
    }
    
    // The test for the first function in the Assignment
    public void testColdestHourInFile() {
        FileResource f = new FileResource();
        CSVParser parser = f.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("Coldest temperature was " + coldest.get("TemperatureF") + " at " + coldest.get("TimeEDT"));
    }
    
    // The test for the first function in the Assignment
    public void  testFileWithColdestTemperature() {
        String[] fileData = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + fileData[0]);
        
        FileResource fr = new FileResource(fileData[1]);
        CSVParser parser = fr.getCSVParser();
      
        CSVRecord current = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was " + current.get("TemperatureF"));
        
        CSVParser newParser = fr.getCSVParser();
        System.out.println("All the Temperatures on the coldest day were:");
        for (CSVRecord record : newParser) {
            System.out.println(record.get("DateUTC") + " " + record.get("TemperatureF"));
        }
    }
    
    // Test for third function 
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        String lowest = String.format("%.2f", Double.parseDouble(csv.get("Humidity")));
        System.out.println("Lowest Humidity was " + lowest + " at " + csv.get("DateUTC"));
    }
    
    // Test for the fourth function
    public void testLowestHumidityInManyFiles() {
        CSVRecord record = lowestHumidityInManyFiles();
        String lowest = String.format("%.2f", Double.parseDouble(record.get("Humidity")));
        System.out.println("Lowest Humidity was " + lowest + " at " + record.get("DateUTC"));
    }
    
    // test for the 5th function
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp =  averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + averageTemp);
    }
    
    // The test of the Last function
    public void  testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80;
        double averageTemp = averageTemperatureWithHighHumidityInFile(parser, value);
        testValueOfAverageTemp(averageTemp);
    }
    
    public void testValueOfAverageTemp(double averageTemp) {
        if (averageTemp == 0) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average Temp when high Humidity is " + averageTemp);
        }       
    }
}
