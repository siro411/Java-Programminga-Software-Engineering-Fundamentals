
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa,String stringb){
        int firstPos=stringb.indexOf(stringa);
        if(firstPos==-1){
            return false;
        }
        else{
            int secondPos=stringb.indexOf(stringa,firstPos+1);
            if(secondPos==-1){
                return false;
            }else{
                return true;
            }
        }
    
    }
    
    public String lastPart(String stringa,String stringb){
        int startPos=stringb.lastIndexOf(stringa);
        if(startPos==-1){
            return stringb;
        }else{
            return stringb.substring(startPos);
        }
    }
    
    public void test2Occurrences(){
        String a="zoo";
        String b="forest";
        System.out.println("The stringa is "+a+".The string b is "+b+".a in b more than twice "+twoOccurrences(a,b));
        System.out.println("last part is "+lastPart(a,b));
    }
}
