
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class CodonCount {
    private HashMap<String,Integer> count;
    
    public CodonCount(){
        count=new HashMap<String,Integer>();
    }
    public void buildCodonMap(int start,String dna){
        count.clear();
        while(start+3<=dna.length()){
            String condon=dna.substring(start,start+3);            
            if(!count.containsKey(condon)){
                count.put(condon,1);
            }else{
                count.put(condon,count.get(condon)+1);
            }            
            start+=3;
        }
        System.out.println("Reading frame starting with 2 results in "+count.size()+" unique codons");
       
    }
    
    public String getMostCommonCodon(){
        int maxCount=0;
        String maxCondon="";
        for(String k:count.keySet()){
            if(count.get(k)>maxCount){
                maxCondon=k;
                maxCount=count.get(k);
            }
        }
        System.out.println("and most common codon is "+maxCondon+" with count "+count.get(maxCondon));
        return maxCondon;
    }
    public void printCodonCounts(int start,int end){
        System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:");
        for(String k:count.keySet()){
             if(count.get(k)>=start&&count.get(k)<=end){
                System.out.println(k+"\t"+count.get(k));
            }
        }
    }
    public void tester(){
        FileResource fr=new FileResource();
        String dna=fr.asString().trim().toUpperCase();
        CodonCount myMap1=new CodonCount();
        CodonCount myMap2=new CodonCount();
        CodonCount myMap3=new CodonCount();
        int s=1;
        int e=5;
        myMap1.buildCodonMap(0,dna);
        String mcc1=myMap1.getMostCommonCodon();
        myMap1.printCodonCounts(s,e); 
        
        myMap2.buildCodonMap(1,dna);
        String mcc2=myMap2.getMostCommonCodon();
        myMap2.printCodonCounts(s,e);
        
        myMap3.buildCodonMap(2,dna);
        String mcc3=myMap3.getMostCommonCodon();
        myMap3.printCodonCounts(s,e);
        
        
        
    }
}
