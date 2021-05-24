
/**
 * Write a description of EarthQuakeClient2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class EarthQuakeClient2 {
    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData,Filter f){
        ArrayList<QuakeEntry> answer=new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData){
            if(f.satisfies(qe)){
                answer.add(qe);
            }
        }
        return answer;
    }
    public void quakesWithFilter(){
        EarthQuakeParser parser=new EarthQuakeParser();
       // String source="data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list=parser.read(source);
        Filter f1=new MagnitudeFilter(4.0,5.0);
        Filter f2=new DepthFilter(-35000.0,-12000.0);
        ArrayList<QuakeEntry> magList=filter(list,f1);
        ArrayList<QuakeEntry> result=filter(magList,f2);
        for(QuakeEntry qe:result){
            System.out.println(qe);
        }        
        System.out.println("Found "+result.size()+" quakes that match that criteria");
        /*Location japan=new Location(35.42,139.43);
        Filter f1=new DistanceFilter(japan,10000);
        Filter f2=new PhraseFilter("end","Japan");
        ArrayList<QuakeEntry> distList=filter(list,f1);
        ArrayList<QuakeEntry> result=filter(distList,f2);
        for(QuakeEntry qe:result){
            System.out.println(qe);
        }        
        System.out.println("Found "+result.size()+" quakes that match that criteria");*/
    }
    public void testMatchAllFilter(){
        EarthQuakeParser parser=new EarthQuakeParser();
        //String source="data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list=parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf=new MatchAllFilter();
        Filter f1=new MagnitudeFilter(0.0,2.0);
        Filter f2=new DepthFilter(-100000.0 ,-10000.0);
        Filter f3=new PhraseFilter("any","a");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        ArrayList<QuakeEntry> result=filter(list, maf);
        for(QuakeEntry qe:result){
            System.out.println(qe);
        } 
        System.out.println("Found "+result.size()+" quakes that match that criteria");
    }
    
    public void testMatchAllFilter2(){
        EarthQuakeParser parser=new EarthQuakeParser();
        //String source="data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list=parser.read(source);
        MatchAllFilter maf=new MatchAllFilter();
        Filter f1=new MagnitudeFilter(0.0,3.0);
        Location tulsa=new Location(36.1314, -95.9372);
        Filter f2=new DistanceFilter(tulsa,10000);
        Filter f3=new PhraseFilter("any","Ca");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        ArrayList<QuakeEntry> result=filter(list, maf);
        for(QuakeEntry qe:result){
            System.out.println(qe);
        } 
        System.out.println("Found "+result.size()+" quakes that match that criteria");
        System.out.println("Filters used are: "+maf.getName());
    }
}
