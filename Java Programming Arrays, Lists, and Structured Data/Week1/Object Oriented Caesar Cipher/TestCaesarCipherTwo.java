
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestCaesarCipherTwo {
    private int[] countLetters(String message){
        String alpha="abcdefghijklmnopqrstuvwxyz";
        int[] counts=new int[26];
        for(int k=0;k<message.length();k++){
            char ch=Character.toLowerCase(message.charAt(k));
            int idx=alpha.indexOf(ch);
            if(idx!=-1){
                counts[idx]+=1;
            }
        }
        return counts;
    }
    private int maxIndex(int[] counts){
        int maxIdx=0;
        for(int i=0;i<counts.length;i++){
            if(counts[maxIdx]<counts[i]){
                maxIdx=i;
            }
        }
        return maxIdx;
    }
    private String halfOfString(String message,int start){
        String str="";
        for(int k=start;k<message.length();k+=2){
            str+=message.charAt(k);
        }
        return str;
    }
    public void simpleTests(){
        FileResource fr=new FileResource();
        CaesarCipherTwo cct=new CaesarCipherTwo(17,3);
        String encrypted=cct.encrypt(fr.asString());
        System.out.println("encrypt is "+encrypted);
        System.out.println("decrypt is "+cct.decrypt(encrypted));        
        System.out.println(breakCaesarCipher(encrypted));
    }
    
    public String breakCaesarCipher(String input){
        String s1=halfOfString(input,0);
        String s2=halfOfString(input,1);
        int[] counts1=countLetters(s1);
        int maxIdx1=maxIndex(counts1);
        int dkey1=maxIdx1-4;
        if(maxIdx1<4){
            dkey1=26-(4-maxIdx1);
        }
        
        int[] counts2=countLetters(s2);
        int maxIdx2=maxIndex(counts2);
        int dkey2=maxIdx2-4;
        if(maxIdx2<4){
            dkey2=26-(4-maxIdx2);
        }
        
        CaesarCipherTwo bcc = new CaesarCipherTwo(26-dkey1,26-dkey2);       
        String ds=bcc.encrypt(input);
        return ds;      
    }
    
}
