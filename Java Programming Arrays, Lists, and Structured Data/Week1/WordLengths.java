
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordLengths {
    public void countWordLengths(FileResource resource,int[] counts){
        for(String word:resource.words()){
            int idx=0;
            for( int i=0;i<word.length();i++){
                char ch=word.charAt(i);
                if(Character.isLetter(ch)||ch=='-' ||ch=='\''){
                    idx++;
                }
            }            
            counts[idx]+=1;
            //System.out.println(word+' '+idx);
        }

    
        for(int k=0;k<counts.length;k++){
                System.out.println(counts[k]);
        }
    }   
    public int indexOfMax(int[] values){
        int max=0;
        for(int i=0;i<values.length;i++){
            if(values[i]>values[max]){
                max=i;
            }
        }
        return max;
        
    }
    public void testCountWordLengths(){
        FileResource fr=new FileResource();
        int[] counts=new int[31];
        countWordLengths(fr,counts);
        System.out.println("The max index is "+indexOfMax(counts));
    }
}
