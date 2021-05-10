
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
     
     public HashMap<String, Integer> countVisitsPerIP(){
        HashMap<String, Integer> counts=new HashMap<String, Integer>();
        for(LogEntry le:records){
            String ip=le.getIpAddress();
            if(!counts.containsKey(ip)){
                counts.put(ip,1);            
            }else{
                counts.put(ip,counts.get(ip)+1);
            }
        }
        return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> countsIP){
         int maxCounts=0;
         for(String k:countsIP.keySet()){
            if(countsIP.get(k)>maxCounts){
            maxCounts=countsIP.get(k);
            }
            }
         return maxCounts;
        }
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> countsIP){ 
        ArrayList<String> maxCountsIP=new ArrayList<String>();
        int maxCounts=0;      
         for(String k:countsIP.keySet()){
            if(countsIP.get(k)>=maxCounts){
                maxCounts=countsIP.get(k);
            }
         }
         for(String ip:countsIP.keySet()){
            if(countsIP.get(ip)==maxCounts&&!maxCountsIP.contains(ip)){
                maxCountsIP.add(ip);
            }
            }
         return maxCountsIP;
        }
        
     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> days=new HashMap<String, ArrayList<String>>();
         for(LogEntry le:records){
             String d=le.getAccessTime().toString().substring(4,10);
             if(!days.containsKey(d)){
                ArrayList<String> ipPerDay=new ArrayList<String>();
                ipPerDay.add(le.getIpAddress());
                days.put(d,ipPerDay);
             }else{
                if(!days.get(d).contains(le.getIpAddress())){
                    days.get(d).add(le.getIpAddress());
                }
             }
         }
         return days;
        
     }
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> days){
        int numIP=0;
        String day="";
        for(String k:days.keySet()){
            if(numIP<=days.get(k).size()){
                numIP=days.get(k).size();
                day=k;
            }
        }
        return day;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>>days,String d){
        ArrayList<String> ipList = new ArrayList<String>();
        HashMap<String, Integer> counts=new HashMap<String, Integer>();
        for(LogEntry le:records){
            String ip=le.getIpAddress();
            if(le.getAccessTime().toString().contains(d)){
                if(!counts.containsKey(ip)){
                    counts.put(ip,1);            
                }else{
                    counts.put(ip,counts.get(ip)+1);
                }
            }
        }

        /*int maxCounts=0;
        for(String k:counts.keySet()){
            if(counts.get(k)>=maxCounts){
                maxCounts=counts.get(k);
            }
        }
        System.out.println("maxCounts is "+maxCounts);
        for(String k:counts.keySet()){
            if(counts.get(k)==maxCounts&& !ipList.contains(k)){
                ipList.add(k);
            }
        }*/
        ipList=iPsMostVisits(counts);
        return ipList;
     
     }
    }
