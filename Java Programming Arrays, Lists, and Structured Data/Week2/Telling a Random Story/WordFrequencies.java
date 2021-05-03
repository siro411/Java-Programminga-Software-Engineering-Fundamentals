
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords=new ArrayList<String>();
        myFreqs=new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr=new FileResource();
        for(String s: fr.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
        }
    }
    
    public int findIndexOfMax(){
        int max=myFreqs.get(0);
        int maxIdx=0;       
        for(int k=0;k<myFreqs.size();k++){
            System.out.println(myFreqs.get(k)+"\t"+myWords.get(k));
            if(max<myFreqs.get(k)){
                maxIdx=k;
                max=myFreqs.get(k);
            }
        }
        return maxIdx;
    }
    public void tester(){
        findUnique();        
        int index=findIndexOfMax();
        System.out.println("Number of unique words:  "+myWords.size());
        System.out.println("The word that occurs most often and its count are: "+myWords.get(index)+" "+myFreqs.get(index));
        
    }
}
