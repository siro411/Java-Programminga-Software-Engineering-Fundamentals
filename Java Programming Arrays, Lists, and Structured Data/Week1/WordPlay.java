
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch){
        String vowel="aeiouAEIOU";
        if(vowel.indexOf(ch)!=-1){
            return true;
        }else{
            return false;
        }
    }
    
    public String replaceVowels(String phrase,char ch){
        StringBuilder sb=new StringBuilder(phrase);
        for(int i=0;i<sb.length();i++){
            char currChar=sb.charAt(i);
            if(isVowel(currChar)){
                sb.setCharAt(i,ch);
            }
        }
        return sb.toString();
    }
    
    public String emphasize(String phrase,char ch){
        StringBuilder sb=new StringBuilder(phrase);
        for(int i=0;i<sb.length();i++){
            if(Character.toLowerCase(sb.charAt(i))==ch || Character.toUpperCase(sb.charAt(i))==ch){
                if(i%2==0){
                    sb.setCharAt(i,'*');
                }else{
                    sb.setCharAt(i,'+');
                }
            }
        }
        return sb.toString();
    }
    public void testIsVowel(){
        System.out.println(isVowel('F'));
        System.out.println(isVowel('a'));
    }
    
    public void testReplaceVowels(){
        System.out.println(replaceVowels("Hello world",'*'));
    
    }
    
    public void testEmphasize(){
        System.out.println(emphasize("dna ctgaaactga",'a'));
        System.out.println(emphasize("Mary Bella Abracadabra",'a'));   
    }
}
