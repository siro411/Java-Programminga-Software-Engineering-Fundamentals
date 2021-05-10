import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String ss="";
        for(int k=whichSlice;k<message.length();k+=totalSlices){
            char ch=message.charAt(k);
            ss+=ch;
        }
        return ss;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc=new CaesarCracker(mostCommon);
        for(int i=0;i<klength;i++){
            key[i]=cc.getKey(sliceString(encrypted,i,klength));
        }
        return key;
    }


    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dic=new HashSet<String>();
        for(String line:fr.lines()){
            dic.add(line.toLowerCase());        
        }
        return dic;
    
    }
    
    public int countWords(String message,HashSet<String> dictionary){
        String[] words=message.toLowerCase().split("\\W+");
        int count=0;
        for(String w:words){
            if(dictionary.contains(w)){
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted,HashSet<String> dictionary){
        int maxNum=0;
        int maxKey=1;
        for(int k=1;k<101;k++){
            int[] key=tryKeyLength(encrypted,k,mostCommonCharIn(dictionary));
            VigenereCipher vc=new VigenereCipher(key);
            String decrypted=vc.decrypt(encrypted);
            int num=countWords(decrypted,dictionary);
            if(maxNum<num){
                maxNum=num;
                maxKey=k;
            }
        }
        System.out.println("klength is "+maxKey);
        int[] key=tryKeyLength(encrypted,maxKey,'e');        
        VigenereCipher vc=new VigenereCipher(key);
        System.out.println("the key is "+vc.toString());
        String decrypted=vc.decrypt(encrypted);
        System.out.println("This file contains "+maxNum+" words out of "+decrypted.split("\\W+").length+" words.");
        return decrypted;
    }
    
    public void breakVigenere () {
        FileResource fr=new FileResource();
        String encrypted=fr.asString();
        HashMap<String,HashSet<String>> lanMap=new HashMap<String,HashSet<String>>();
        String[] lan={"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese","Spanish"};
        //String[] lan={"French"};
        for(String s:lan){
            FileResource frDict=new FileResource("dictionaries/"+s);
            HashSet<String> dict=readDictionary(frDict);
            lanMap.put(s,dict);
        }
        breakForAllLangs(encrypted,lanMap);
        //int[] k=tryKeyLength(encrypted,38,'e');        
        //System.out.println(Arrays.toString(k));
        //VigenereCipher vc=new VigenereCipher(k);
        //System.out.println("valid words are "+countWords(vc.decrypt(encrypted),dict));
        //System.out.println("decrypted msg is "+breakForLanguage(encrypted,dict));
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
	HashMap<Character, Integer> map=new HashMap<Character,Integer>();
        int count=0;
        int max=0;
        char mocci='\0';
        for(String w:dictionary){
            for(int i=0;i<w.length();i++){
                char ch=w.charAt(i);
                if(!map.containsKey(ch)){
                    map.put(ch,1);
                    count++;                  
                }else{
                    map.put(ch,map.get(ch)+1);
                }
                
            }            
        }
        for(char c:map.keySet()){
            if(max<map.get(c)){
                max=map.get(c);
                mocci=c;
            }
        }
        return mocci;
    }
    
    
    public void breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> languages){
        int maxCount=0;
        String maxLanguage="";
        for(String lan:languages.keySet()){
            System.out.println("The language is "+lan);
            String decrypted=breakForLanguage(encrypted,languages.get(lan));
            System.out.println(decrypted);
            int count=countWords(decrypted,languages.get(lan));
            if(maxCount<count){
                maxCount=count;
                maxLanguage=lan;
            }
        }
        System.out.println(maxLanguage+" has the greatest Number of valid words which is "+maxCount);
    }
    public void tester(){
        /*String s="abcdefghijklm";
        for(int i=0;i<3;i++){
        System.out.println(sliceString(s,i,3));
        }
        for(int i=0;i<4;i++){
        System.out.println(sliceString(s,i,4));
        }
        for(int i=0;i<5;i++){
        System.out.println(sliceString(s,i,5));
        }*/
        FileResource fr=new FileResource();
        String encrypted=fr.asString();
        System.out.println(Arrays.toString(tryKeyLength(encrypted,5,'e')));
       
    }
}
