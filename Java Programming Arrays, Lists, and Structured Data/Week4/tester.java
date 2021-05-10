
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class tester {
   public void testerCaesarCipher(){
       CaesarCipher cc=new CaesarCipher(4);
       FileResource fr=new FileResource();
       String encrypted=cc.encrypt(fr.asString());
       System.out.println(encrypted);
       System.out.println(cc.decrypt(encrypted));
   }
   public void testerCaesarCracker(){
       //CaesarCracker cb=new CaesarCracker();     
       CaesarCracker cb=new CaesarCracker('a');             
       FileResource fr=new FileResource();
       System.out.println("key is "+cb.getKey(fr.asString()));
       System.out.println(cb.decrypt(fr.asString()));
    }
    public void testerVigenereCipher(){
        int[] key={17,14,12,4};
        VigenereCipher vc=new VigenereCipher(key);
        FileResource fr=new FileResource();
        String encrypted=vc.encrypt(fr.asString());
        System.out.println(encrypted);
        System.out.println(vc.decrypt(encrypted));
        
    }
}
