
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class CharactersInPlay {
    private ArrayList<String> name;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay(){
        name=new ArrayList<String>();
        counts=new  ArrayList<Integer>();
    }
    public void update(String person){
        int idx=name.indexOf(person);
        if(idx==-1){
            name.add(person);
            counts.add(1);
        }else{
            int value=counts.get(idx);
            counts.set(idx,value+1);
        }
        
    }
    
    public void findAllCharacters(){
        name.clear();
        counts.clear();
        FileResource fr=new FileResource();
        for(String line:fr.lines()){
            int idx = line.lastIndexOf('.');
            if(idx!=-1){
            String p=line.split("\\.")[0];
            update(p);
            }
            
        }
    
    }
    
    public void charactersWithNumParts(int num1,int num2){
     for(int t=0;t<name.size();t++){
            if(counts.get(t)>=num1 &&counts.get(t)<=num2){
            System.out.println(name.get(t)+"\t"+counts.get(t));}
        }
    
    }
    public void tester(){
        findAllCharacters();
        for(int k=0;k<name.size();k++){
            if(counts.get(k)>1){
            System.out.println(name.get(k)+"\t"+counts.get(k));}
        }
        
        charactersWithNumParts(10,15);
    }
}
