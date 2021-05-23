
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class LargestQuakes {
    public int indexOfLargest(ArrayList<QuakeEntry> quakeData){
        int maxIdx=0;
        for(int k=0;k<quakeData.size();k++){
            if(quakeData.get(k).getMagnitude()>quakeData.get(maxIdx).getMagnitude()){
                maxIdx=k;
            }
        }
        return maxIdx;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData,int howMany){
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        for(int j=0;j<howMany;j++){            
            int maxIdx=indexOfLargest(quakeData);            
            ret.add(quakeData.get(maxIdx));
            quakeData.remove(quakeData.get(maxIdx));
        }

        return ret;
    }
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
       // String source="data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list=parser.read(source);
        System.out.println("read "+list.size()+" quakes data");
        /*for(QuakeEntry qe:list){
            System.out.println(qe);
        }*/
        ArrayList<QuakeEntry> larList=getLargest(list,5);
        for(QuakeEntry qe:larList){
            System.out.println(qe);
        }
        
    
    }
    
}
