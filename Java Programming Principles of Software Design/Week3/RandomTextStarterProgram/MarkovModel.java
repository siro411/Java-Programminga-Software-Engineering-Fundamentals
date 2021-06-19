
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int myN;
    
    public MarkovModel(int N) {
        myN=N;
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
        int index=myRandom.nextInt(myText.length()-myN);
        String key=myText.substring(index,index+myN);
        sb.append(key);
        for(int k=0; k < numChars-myN; k++){
            ArrayList<String> follows=getFollows(key);
            //System.out.println("key "+key+" "+follows);
            if(follows.size()==0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next=follows.get(index);
            sb.append(next);
            key=key.substring(1)+next;
        }
        
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key){
        ArrayList<String> follows=new ArrayList<String>();
        int pos=0;
        int klen=key.length();
        while(myText.indexOf(key,pos)!=-1 && myText.indexOf(key,pos)<(myText.length()-klen)){
            int index=myText.indexOf(key,pos);
            follows.add(myText.substring(index+klen,index+klen+1));
            pos=index+klen;
        }        
        return follows;
    }
}
