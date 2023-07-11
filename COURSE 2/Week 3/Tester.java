import java.util.*;
/**
 * This class contains tests to methods on other classes
 * and answers to the quizes in this week
 * @author (Abdelmaseh Nabil) 
 * @version (7/11/2023)
 */
public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("short-test_log");
        lg.printAll();
        int uniqueIPs = lg.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs");
        lg.printAllHigherThanNum(200);
        
        String someday = "Sep 14";
        ArrayList<String> iPs = lg.uniqueIPVisitsOnDay(someday);
        System.out.println("Number of visits in " + someday + " are " + iPs.size());
        
        someday = "Sep 30";
        iPs = lg.uniqueIPVisitsOnDay(someday);
        System.out.println("Number of visits in " + someday + " are " + iPs.size() + '\n');
        
        int low = 200;
        int high = 299;
        System.out.println(lg.countUniqueIPsInRange(low, high));
        low = 300;
        high = 399;
        System.out.println(lg.countUniqueIPsInRange(low, high));
    }
    
    public void testIPsPerCounts() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("short-test_log");
        
        HashMap<String, Integer> counts = lg.countVisitsPerIP();
        System.out.println(counts);
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("weblog3-short_log");
        
        HashMap<String, Integer> counts = lg.countVisitsPerIP();
        System.out.println("The maximum number of visits to this website by a single IP address " + 
        lg.mostNumberVisitsByIP(counts));
    }
    
    public void testiPsMostVisits() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("weblog3-short_log");
        
        HashMap<String, Integer> counts = lg.countVisitsPerIP();
        System.out.println("The maximum number of visits to this website by a single IP address " + 
        lg.mostNumberVisitsByIP(counts));
        
        ArrayList<String> IPs = lg.iPsMostVisits(counts);
        for(String IP : IPs) {
            System.out.println(IP);
        }
    }
    
    public void testMapDaysToIPs() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("weblog1_log");
        
        lg.mapDaysToIPs();
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("weblog3-short_log");
        
        System.out.println("The day with most IP visits " + lg.dayWithMostIPVisits(lg.mapDaysToIPs()));
    }
    
    public void testiPsWithMostVisitsOnDay() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("weblog3-short_log");
        
        HashMap<String, ArrayList<String>> myMap = lg.mapDaysToIPs();
        String day = lg.dayWithMostIPVisits(myMap);
        ArrayList<String> listOfIPs = lg.iPsWithMostVisitsOnDay(myMap, day);
        
        System.out.println("IPs On " + day + " are:");
        for(String IP : listOfIPs) {
            System.out.println(IP);
        }
    }
    
    public void findingUniIPAddQuizAnswers() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("weblog1_log");
       
        lg.printAllUniqueHigherThanNum(400);
        
        String someday = "Mar 17";
        ArrayList<String> iPs = lg.uniqueIPVisitsOnDay(someday);
        System.out.println("Number of visits in " + someday + " are " + iPs.size());
         
        int low = 200;
        int high = 299;
        System.out.println(lg.countUniqueIPsInRange(low, high));   
    }
    
    public void countingWebsitesVisitsQuizAnswers() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("weblog1_log");
        
        HashMap<String, Integer> counts = lg.countVisitsPerIP();
        System.out.println("1- the most number of visits by IP = " + lg.mostNumberVisitsByIP(counts) + '\n');
       
        System.out.println("2- The IPs with the most visits in the file are : ");
        ArrayList<String> list = lg.iPsMostVisits(counts);
        for(String ip : list) {
            System.out.println(ip);
        }
        System.out.println();
        
        System.out.println("3- The day with the most IP visits :" );
        String day = lg.dayWithMostIPVisits(lg.mapDaysToIPs());
        System.out.println(day + '\n');
        
        day = "Mar 17";
        ArrayList<String> iPsOnDay = lg.iPsWithMostVisitsOnDay(lg.mapDaysToIPs(), day);
        System.out.println("4- IPs On " + day + " are:");
        for(String IP : iPsOnDay) {
            System.out.println(IP);
        }
        System.out.println();
    }
    
    public void graddedQuizAnswers() {
        System.out.println("1- The ToString method is modified to include a String parameter." + '\n');
        
        System.out.println("2- In readFile, the log entries were not stored in records." + '\n');
        
        System.out.println("3- same as you have written." + '\n');
        
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("weblog2_log");
        System.out.println("4- The number of unique IP addresses in the file = " + 
        lg.countUniqueIPs() + '\n');
        
        String day = "Sep 24";
        System.out.println("5- The number of unique IP visits on the day " + day 
        + " are " + lg.uniqueIPVisitsOnDay(day).size() + '\n');
        
        System.out.print("6- ");
        int low = 400;
        int high = 499;
        System.out.println(lg.countUniqueIPsInRange(low, high));
        System.out.println();
        
        HashMap<String, Integer> counts = lg.countVisitsPerIP();
        System.out.println("7- The most number of visits by IP = " + 
        lg.mostNumberVisitsByIP(counts) + '\n');
        
        ArrayList<String> list = lg.iPsMostVisits(counts);
        System.out.println("8- The IPs with most visits are : " + list + '\n');
        
        day = lg.dayWithMostIPVisits(lg.mapDaysToIPs());
        System.out.println("9- the day with the most IP visits " + day + '\n');
        
        day = "Sep 30";
        ArrayList<String> iPsOnDay = lg.iPsWithMostVisitsOnDay(lg.mapDaysToIPs(), day);
        System.out.println("10- IPs On " + day + " are:");
        for(String IP : iPsOnDay) {
            System.out.println(IP);
        }     
    }
}
