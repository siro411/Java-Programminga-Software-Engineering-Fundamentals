
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records=new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr=new FileResource(filename);
         for(String line:fr.lines()){            
             LogEntry le=WebLogParser.parseEntry(line);
             records.add(le);
            }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
        ArrayList<String> uniqueIps=new ArrayList<String>();
        for(LogEntry le:records){
            String ip=le.getIpAddress();
            if(!uniqueIps.contains(ip)){
                uniqueIps.add(ip);
            }
        }
        return uniqueIps.size();
     }
     
     public void printAllHigherThanNum(int num){
         for(LogEntry le:records){
             int statusCode=le.getStatusCode();
             if(statusCode>num){
                 System.out.println(le);  
             }
         }
        
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> list=new ArrayList<String>();
        for(LogEntry le:records){
            String d=le.getAccessTime().toString();
            if(d.contains(someday)&& !list.contains(le.getIpAddress())){
                list.add(le.getIpAddress());
            }
        }
        return list;
     }
     
     public int countUniqueIPsInRange(int low,int high){
         ArrayList<String> uniqueIpsRanges=new ArrayList<String>();
         for(LogEntry le:records){
             int statusCode=le.getStatusCode();
             if(statusCode>=low && statusCode<=high){
                 String ip=le.getIpAddress();
                 if(!uniqueIpsRanges.contains(ip)){
                     uniqueIpsRanges.add(ip);
                 }
             }
         }        
         return uniqueIpsRanges.size();
     }
     
     
     
}
