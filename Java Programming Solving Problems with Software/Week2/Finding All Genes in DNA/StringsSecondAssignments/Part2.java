
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa,String stringb){
        int startIndex=0;
        int sum=0;
        while(true){
            int currIndex=stringb.indexOf(stringa,startIndex);
            if(currIndex==-1){
                break;
            }
            sum++;
            startIndex=currIndex+stringa.length();
        }
        return sum;
    }
    public void testHowMany(){
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println(howMany("AA", "ATAAAA"));
    }
}
