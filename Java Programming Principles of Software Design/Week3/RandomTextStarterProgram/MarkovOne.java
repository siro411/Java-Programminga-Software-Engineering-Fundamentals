
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */
import java.util.*;


public class MarkovOne {
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index=myRandom.nextInt(myText.length()-1);
        String key=myText.substring(index,index+1);
        sb.append(key);
        for(int k=0; k < numChars-1; k++){
            ArrayList<String> follows=getFollows(key);

            if(follows.size()==0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next=follows.get(index);
            sb.append(next);
            key=next;
        }
        
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key){
        ArrayList<String> follows=new ArrayList<String>();
        int pos=0;
        while(myText.indexOf(key,pos)!=-1 && myText.indexOf(key,pos)<(myText.length()-1)){
            int index=myText.indexOf(key,pos);
            follows.add(myText.substring(index+1,index+2));
            pos=index+1;
        }        
        return follows;
    }
}

