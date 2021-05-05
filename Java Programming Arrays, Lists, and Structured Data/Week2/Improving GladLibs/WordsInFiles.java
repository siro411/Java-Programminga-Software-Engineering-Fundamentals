
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.File;
import edu.duke.*;
public class WordsInFiles {
    private HashMap<String,ArrayList<String>> count;
    public WordsInFiles(){
        count=new HashMap<String,ArrayList<String>>();
    }
    public void addWordsFromFile(File f){
        
        FileResource fr=new FileResource(f);
        String filename=f.getName();
        for(String word:fr.words()){
            if(!count.containsKey(word)){ 
                ArrayList<String> li=new ArrayList<String>();                
                li.add(filename);
                count.put(word,li);
                       
            }else{
                if(count.get(word).indexOf(filename)==-1){                             
                    count.get(word).add(filename);
                }
            }
        }
        
    }
    
    public void buildWordFileMap(){
        count.clear();
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles()){
            addWordsFromFile(f);
        }
        for(String k:count.keySet()){
            System.out.println(k+"\t"+count.get(k));           
            
        }
    
    }
    
    
    public int maxNumber(){
        int maxNum=0;
        //int maxWord="";
        for(String k:count.keySet()){
            if(count.get(k).size()>maxNum){
             maxNum=count.get(k).size();            
            }
        }
        return maxNum;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> wfList=new ArrayList<String>();
        for(String k:count.keySet()){
        if(count.get(k).size()==number)
            wfList.add(k);
        }
        return wfList;
    }
    
    public void printFilesIn(String word){
        System.out.println(count.get(word));           
            
        
    }
    public void tester(){
        buildWordFileMap();
        int max=maxNumber();
        System.out.println("max number in files is "+max);
        System.out.println("the 5 times words are "+wordsInNumFiles(5).size());
        System.out.println("the 4 times words are "+wordsInNumFiles(4).size());
        System.out.println("sad appears in the files: ");
        printFilesIn("sad");
        System.out.println("red appears in the files: ");
        printFilesIn("red");
        
    }
}
