/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class GladLibMap {
    private HashMap<String,ArrayList<String>> myMap;
    private Random myRandom;
    private ArrayList<String> usedList;
    private ArrayList<String> usedCat;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        myMap=new HashMap<String,ArrayList<String>>();
        String[] files={"adjective","noun","color","country","name","animal","timeframe","verb","fruit"};
        for(String s:files){
            ArrayList<String> list= readIt(source+"/"+s+".txt");
            myMap.put(s,list);
        }       
        usedList=new ArrayList<String>();
        usedCat=new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }       
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){       
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }        
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        if(usedCat.indexOf(w.substring(first+1,last))==-1){
            usedCat.add(w.substring(first+1,last));
        }
        String sub = getSubstitute(w.substring(first+1,last));
        while (true){           
            if(usedList.indexOf(sub)!=-1){
                sub = getSubstitute(w.substring(first+1,last));
            }else{
                usedList.add(sub);
                break;
            }
        }
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        System.out.println("\n");
        usedList.clear();
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        
        System.out.println("\nThe total number of words are "+usedList.size());
    }
    
    public int totalWordsInMap(){
        int sum=0;
        for(String key:myMap.keySet()){
            sum+=myMap.get(key).size();
        
        }
        return sum;
    }
    
    public int totalWordsConsidered(){
        int numCat=0;
        for(String s:myMap.keySet()){
            if(usedCat.indexOf(s)!=-1){
                numCat+=myMap.get(s).size();
                System.out.println(s);
            }
        }
        return numCat;
        
    } 

}
