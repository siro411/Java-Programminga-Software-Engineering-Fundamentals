/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part2 {
    public int countChar(String dna,String targetChar){
        int cnt=0;
        int startIndex=0;
        while(true){
            startIndex=dna.indexOf(targetChar,startIndex);
            if(startIndex==-1){
                break;
            }
            cnt++;
            startIndex=startIndex+1;
        }
        return cnt;
    }
    
    public float cgRatio(String dna){
        
        int numOfC=countChar(dna,"C");
        System.out.println(numOfC);
        int numOfG=countChar(dna,"G");
        System.out.println(numOfG);
        return (float)(numOfC+numOfG)/dna.length();

    }
    public void testCgRatio(){
        String dna="ATGCCATAG";
        System.out.println(cgRatio(dna));
        
    }
    
    
    
}
