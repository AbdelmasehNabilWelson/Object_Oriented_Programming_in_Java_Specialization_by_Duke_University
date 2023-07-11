import java.util.*;
import edu.duke.*;
import java.text.SimpleDateFormat;
/**
 * This class contains different methods to analyze the log files.
 * @author (Abdelmaseh Nabil) 
 * @version (7/11/2023)
 */
public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }
    
    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for(String l : fr.lines()) {
            records.add(WebLogParser.parseEntry(l));
        }
    }
    
    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }
    
    public void printAllHigherThanNum(int num) {
        System.out.println("LogEntries that have status code greater than " + num);
        for (LogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
        System.out.println();
    }
    
    public void printAllUniqueHigherThanNum(int num) {
        ArrayList<Integer> statusCodes = new ArrayList<Integer>();
        for (LogEntry le : records) {
            int code = le.getStatusCode();
            if(code > num && !statusCodes.contains(code)) {
                System.out.println(code);
                statusCodes.add(code);
            }
        }
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        System.out.println("Unique visits in the day: " + someday);
        ArrayList<String> iPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String str = le.getAccessTime().toString();
            //System.out.println(str);
            if (str.contains(someday)) {
                String ipAddr = le.getIpAddress();
                if (!iPs.contains(ipAddr)) {
                    iPs.add(ipAddr);
                }
            }
        }
        return iPs;
    }
    
    public int countUniqueIPsInRange(int low, int high) {
        System.out.println("The number of unique IP addresses in the range [" + low + ", " + high + "]");
        ArrayList<String> iPs = new ArrayList<String>();
        for (LogEntry le : records) {
            int statCode = le.getStatusCode();
            if (statCode >= low && statCode <= high) {
                String ipAddr = le.getIpAddress();
                if (!iPs.contains(ipAddr)) {
                    iPs.add(ipAddr);
                }
            }
        }
        return iPs.size();    
    }
    
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> iPsWithCounts = new HashMap<String, Integer>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!iPsWithCounts.containsKey(ip)) {
                iPsWithCounts.put(ip, 1);
            } else {
                iPsWithCounts.put(ip, iPsWithCounts.get(ip) + 1);
            }
        }
        return iPsWithCounts;
    }
    
    public int mostNumberVisitsByIP(HashMap<String, Integer> visitsPerIP) {
        int maxNum = -1;
        for (String IP : visitsPerIP.keySet()) {
            maxNum = Math.max(maxNum, visitsPerIP.get(IP));
        }
        return maxNum;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        int maxNumOfVisits = mostNumberVisitsByIP(counts);
        ArrayList<String> IPsWithMaxCount = new ArrayList<String>();
        for(String IP : counts.keySet()) {
            if (counts.get(IP) == maxNumOfVisits) {
                IPsWithMaxCount.add(IP);
            }
        }
        return IPsWithMaxCount;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> daysWithIPs) {
        int maxNum = -1;
        String day = "";
        for (String d : daysWithIPs.keySet()) {
            int si = daysWithIPs.get(d).size();
            if (si > maxNum) {
                maxNum = si;
                day = d;
            }
            //System.out.println("Max = " + maxNum + " , day " + day);
        }
        return day;
    }
    
    public HashMap<String, ArrayList<String>> mapDaysToIPs() {
        HashMap<String, ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>();
        for(LogEntry lg : records) {
            Date date = lg.getAccessTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d");
            String formattedDay = dateFormat.format(date);

            
            String IP = lg.getIpAddress();
            //System.out.println(formattedDay + " " + IP);
            
            if(myMap.containsKey(formattedDay)) {
                ArrayList<String> list = myMap.get(formattedDay);
                list.add(IP);
                myMap.put(formattedDay, list);
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(IP);
                myMap.put(formattedDay, list);
            }
            //printHashMap(myMap);
        }
        return myMap;
    }
    
    private void printHashMap(HashMap<String, ArrayList<String>> counts) {
        for(String st : counts.keySet()) {
            System.out.println(st);
            for(String list : counts.get(st)) {
                System.out.println(list);
            }
        }
        System.out.println();
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> daysWithIPs, String day) {
        ArrayList<String> list = daysWithIPs.get(day);
        
        HashMap<String, Integer> iPCountInDay = new HashMap<String, Integer>();
        for(String iP : list) {
            if (!iPCountInDay.containsKey(iP)) {
                iPCountInDay.put(iP, 1);
            } else {
                iPCountInDay.put(iP, iPCountInDay.get(iP) + 1);
            }
        }
        return iPsMostVisits(iPCountInDay);
    }
    
    public void printAll() {
        for(LogEntry le : records) {
            System.out.println(le);
        }
        System.out.println();
    }
}
