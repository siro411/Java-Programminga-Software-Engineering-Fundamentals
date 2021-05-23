import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe:quakeData ){
            if(qe.getMagnitude()>magMin){
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe:quakeData ){
            double curDist=qe.getLocation().distanceTo(from);
            
            if(curDist/1000<distMax){
                answer.add(qe);
            }
        }

        return answer;
    }
    
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth ,
    double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe:quakeData ){
            double curDepth=qe.getDepth();
            
            if(curDepth>minDepth&&curDepth<maxDepth){
                answer.add(qe);
            }
        }

        return answer;
    }
    
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String pos,
    String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe:quakeData ){
            String title=qe.getInfo();
            String[] splited =title.split(" ");
            if(pos=="start"){
                String s=splited[0];
                if(s.equals(phrase)){
                    answer.add(qe);                    
                }
            }else if(pos=="end"){
                String e=splited[splited.length-1];
                //System.out.println(e);
                if(e.equals(phrase)){
                    answer.add(qe);                    
                }
            }else{
                int idx=title.indexOf(phrase);
                if(idx!=-1){
                    answer.add(qe); 
                }
            }
        }

        return answer;
    
    
    }
    
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source="data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> qdepth = filterByDepth(list,-8000.0,-5000.0);
        System.out.println("Find quakes with depth between -8000.0 and -5000.0");
        for(QuakeEntry qe:qdepth){
            System.out.println(qe);
        }
        System.out.println("Found "+qdepth.size()+" quakes that match that criteria");
    }
    
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source="data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        /*ArrayList<QuakeEntry> qPhrase = filterByPhrase(list,"end","California");
        for(QuakeEntry qe:qPhrase){
            System.out.println(qe);
        }*/
        ArrayList<QuakeEntry> qPhrase = filterByPhrase(list,"any","Creek");
        for(QuakeEntry qe:qPhrase){
            System.out.println(qe);
        }
       /*ArrayList<QuakeEntry> qPhrase = filterByPhrase(list,"start","Explosion");
        for(QuakeEntry qe:qPhrase){
            System.out.println(qe);
       }*/
        System.out.println("Found "+qPhrase.size()+" quakes that match that criteria");
        
    }
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        String source="data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> bigQuakes = filterByMagnitude(list,5.0);
        for (QuakeEntry qe : bigQuakes) {
            System.out.println(qe);
        }
        System.out.println("Found "+bigQuakes.size()+" quakes that match that criteria");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source="data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        /*Location city = new Location(35.988, -78.907);
        ArrayList<QuakeEntry> closeToDurham=filterByDistanceFrom(list,1000,city);
        for (QuakeEntry qe : closeToDurham) {
            System.out.println(qe);
        }*/
        // This location is Bridgeport, CA
        Location city2 =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> closeToBridgeport=filterByDistanceFrom(list,1000,city2);
        for (QuakeEntry qe : closeToBridgeport) {
            System.out.println(qe.getLocation().distanceTo(city2)/1000+" "+qe.getInfo());
        }
        System.out.println("Found "+closeToBridgeport.size()+" quakes that match that criteria");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
       
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
