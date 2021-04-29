
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipher {
    public String encrypt(String input,int key){
        String alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlpha=alpha.substring(key)+alpha.substring(0,key);
        String fullAlpha=alpha.toLowerCase();
        String fullShiftedAlpha=shiftedAlpha+shiftedAlpha.toLowerCase();
        StringBuilder sb=new StringBuilder(input);
        for(int i=0;i<sb.length();i++){
            int idx=fullAlpha.indexOf(sb.charAt(i));
            if(idx!=-1){
            char newChar=fullShiftedAlpha.charAt(idx);
            sb.setCharAt(i,newChar);
            }
        }
        return sb.toString();
    }
    public String encryptTwoKeys(String input,int key1,int key2){
        StringBuilder sb=new StringBuilder(input);
        String alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String fullAlpha=alpha.toLowerCase();
        for(int i=0;i<sb.length();i+=2){
            String shiftedAlpha=alpha.substring(key1)+alpha.substring(0,key1);
            String fullShiftedAlpha=shiftedAlpha+shiftedAlpha.toLowerCase(); 
            int idx=fullAlpha.indexOf(sb.charAt(i));            
            if(idx!=-1){
                char newChar=fullShiftedAlpha.charAt(idx);    
                sb.setCharAt(i,newChar);                
            }
        }
        for(int j=0;j<sb.length();j+=2){
            String shiftedAlpha2=alpha.substring(key2)+alpha.substring(0,key2);
            String fullShiftedAlpha2=shiftedAlpha2+shiftedAlpha2.toLowerCase(); 
            int idx2=fullAlpha.indexOf(sb.charAt(j));                        
            if(idx2!=-1){
                char newChar2=fullShiftedAlpha2.charAt(idx2);    
                sb.setCharAt(j,newChar2);                
            }

        }
        return sb.toString();    
    }    
    public void testEncrypt(){
        System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
        System.out.println(encrypt("First Legion", 17));
        System.out.println(encrypt("First Legion", 23));
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));

    }
    
    
    public void testCaesar(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key=23;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public void testEncryptTwoKeys(){
        System.out.println(encryptTwoKeys("First Legion", 23,17));
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",8,21));
    }
}
