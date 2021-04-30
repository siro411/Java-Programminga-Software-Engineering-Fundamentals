
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestCaesarCipher {
    public int[] countLetters(String message){
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
    public int maxIndex(int[] counts){
        int maxIdx=0;
        for(int i=0;i<counts.length;i++){
            if(counts[maxIdx]<counts[i]){
                maxIdx=i;
            }
        }
        return maxIdx;
    }
    public void simpleTests(){
        FileResource fr=new FileResource();
        CaesarCipher cc=new CaesarCipher(18);
        String encrypted=cc.encrypt(fr.asString());
        System.out.println("encrypted is "+encrypted);
        System.out.println("cc.decrpt is "+cc.decrypt(encrypted));
        System.out.println("breakCaesarCipher is "+breakCaesarCipher(encrypted));
    }
    public String breakCaesarCipher(String input){
        int[] counts=countLetters(input);
        int maxIdx=maxIndex(counts);
        int dkey=maxIdx-4;
        if(maxIdx<4){
            dkey=26-(4-maxIdx);
        }
        CaesarCipher cc_dkey=new CaesarCipher(dkey);
        return cc_dkey.decrypt(input);
    }
    
}
