
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filterList;
    private String name;
    public MatchAllFilter(){
        filterList=new ArrayList<Filter>();
        name="";
    }
    public void addFilter(Filter f){
        filterList.add(f);
    }
    public boolean satisfies(QuakeEntry qe){
        for(Filter f:filterList){
            if(!f.satisfies(qe)){
                return false;
            }
        }
        return true;
        
    }
    public String getName(){
        for(Filter f:filterList){        
            name+=f.getName()+" ";
        }
        return name;
    }
    
    
}
