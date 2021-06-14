
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>   {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String[] qe1Title=qe1.getInfo().split(" ");
        String[] qe2Title=qe2.getInfo().split(" ");        
        String q1LastWord=qe1Title[qe1Title.length-1];
        String q2LastWord=qe2Title[qe2Title.length-1];
        if( q1LastWord.compareTo(q2LastWord)<0){
            return -1;
        }
        if( q1LastWord.compareTo(q2LastWord)>0){
            return 1;
        }
        return Double.compare(qe1.getMagnitude(),qe2.getMagnitude());
    
    }
}
