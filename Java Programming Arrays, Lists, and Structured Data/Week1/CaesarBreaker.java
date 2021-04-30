
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarBreaker {
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
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs=countLetters(encrypted);
        int maxIdx=maxIndex(freqs);
        int dkey=maxIdx-4;
        if(maxIdx<4){
            dkey=26-(4-maxIdx);
        }
        return cc.encrypt(encrypted, 26-dkey);
        
    }
    public String halfOfString(String message,int start){
        String str="";
        for(int k=start;k<message.length();k+=2){
            str+=message.charAt(k);
        }
        return str;
    }
    public int getKey(String s){
        int[] counts=countLetters(s);
        int maxIdx=maxIndex(counts);
        int dkey=maxIdx-4;
        if(maxIdx<4){
            dkey=26-(4-maxIdx);
        }
        return dkey;
    }
    public String decryptTwoKeys(String encrypted){
        String s1=halfOfString(encrypted,0);
        String s2=halfOfString(encrypted,1);
        int key1=getKey(s1);
        int key2=getKey(s2);
        System.out.println("key1 is "+key1+" key2 is "+key2);
        CaesarCipher cc = new CaesarCipher();       
        String ds1=cc.encrypt(s1,26-key1);
        String ds2=cc.encrypt(s2,26-key2);
       // System.out.println("ds1 is "+ds1+" ds2 is "+ds2);        
        int ds1len=ds1.length();
        int ds2len=ds2.length();
        System.out.println("ds1len is "+ds1len+" ds2len is "+ds2len);        
        if(ds1len!=ds2len){
            if(ds1len>ds2len){
                ds2+=" ";
            }else{
                ds1+=" ";
            }
        }
        String s="";
        for(int i=0;i<ds1.length();i++){
            s+=ds1.substring(i,i+1)+ds2.substring(i,i+1);
        }
        return s;
    }
    public void testDecrypt(){
        System.out.println(decrypt("CFOPQ IBDFLK XQQXZH BXPQ CIXKH!"));
    }
    public void testHalfOfString(){
        System.out.println(halfOfString("Qbkm Zgis",0));
        System.out.println(halfOfString("Qbkm Zgis",1));
    }
    public void testDecryptTwoKeys(){       
        /*CaesarCipher cc = new CaesarCipher(); 
        int key1=2;
        int key2=20;
        System.out.println(cc.encryptTwoKeys(encrypted,26-key1,26-key2));
        */
        // System.out.println(decryptTwoKeys("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu")); 
        //System.out.println(decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
        //FileResource fr=new FileResource();
        //System.out.println(decryptTwoKeys(fr.asString())); 

    }
    
    public void e8(){
        String encrypted="Top ncmy qkff vi vguv vbg ycpx";
        String s1=halfOfString(encrypted,0);
        String s2=halfOfString(encrypted,1);        
        CaesarCipher cc = new CaesarCipher();
        int key1=2;
        int key2=20;
        String ds1=cc.encrypt(s1,26-key1);
        String ds2=cc.encrypt(s2,26-key2);
        //System.out.println("ds1 is "+ds1+" ds2 is "+ds2);        
        int ds1len=ds1.length();
        int ds2len=ds2.length();
        if(ds1len!=ds2len){
            if(ds1len>ds2len){
                ds2+=" ";
            }else{
                ds1+=" ";
            }
        }
        String s="";
        for(int i=0;i<ds1.length();i++){
            s+=ds1.substring(i,i+1)+ds2.substring(i,i+1);
        }
        System.out.println(s);
    }
    
 
}
