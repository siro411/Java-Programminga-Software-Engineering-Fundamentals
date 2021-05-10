
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la=new LogAnalyzer();
        //la.readFile("short-test_log");
        la.readFile("weblog3-short_log");
        la.printAll();
    }
    public void testUniqueIP(){
        LogAnalyzer la=new LogAnalyzer();
        //la.readFile("weblog-short_log");
        //la.readFile("short-test_log");
        la.readFile("weblog1_log");
        System.out.println("There are "+la.countUniqueIPs()+" IPs");
        la.printAllHigherThanNum(400);
        System.out.println("This day has "+la.uniqueIPVisitsOnDay("Mar 24").size()+" visits");
        //System.out.println("This day has "+la.uniqueIPVisitsOnDay("Sep 30").size()+" visits");
        System.out.println("There are "+la.countUniqueIPsInRange(200,299)+" IPs status code from 200 to 299");
        //System.out.println("There are "+la.countUniqueIPsInRange(300,399)+" IPs status code from 300 to 399");
    }
    public void tester(){
        LogAnalyzer la=new LogAnalyzer();
        //la.readFile("weblog3-short_log");
        la.readFile("weblog1_log");
        HashMap<String, Integer> counts=la.countVisitsPerIP();
        for(String key:counts.keySet()){
            System.out.println(key+"\t"+counts.get(key));
        }
        System.out.println("max Number of counts is "+la.mostNumberVisitsByIP(counts));
        System.out.println("max Counts IP are "+la.iPsMostVisits(counts));
        HashMap<String, ArrayList<String>> days=la.iPsForDays();
        for(String k:days.keySet()){
            System.out.println(k+"\t"+days.get(k));
        }
        System.out.println("most visits day is "+la.dayWithMostIPVisits(days));
        String d="Mar 17";
        //System.out.println(days.get(d).toString());
        System.out.println("most visits ips on "+d+" is "+la.iPsWithMostVisitsOnDay(days,d));
    }
}
