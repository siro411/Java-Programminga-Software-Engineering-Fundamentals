
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alpha;
    private String fullAlpha;
    private String shiftedAlpha1;
    private String fullShiftedAlpha1; 
    private String shiftedAlpha2;
    private String fullShiftedAlpha2;
    private int mainKey1;
    private int mainKey2;
    public CaesarCipherTwo(int key1,int key2){
        alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        fullAlpha=alpha+alpha.toLowerCase();
        shiftedAlpha1=alpha.substring(key1)+alpha.substring(0,key1);
        fullShiftedAlpha1=shiftedAlpha1+shiftedAlpha1.toLowerCase();
        shiftedAlpha2=alpha.substring(key2)+alpha.substring(0,key2);
        fullShiftedAlpha2=shiftedAlpha2+shiftedAlpha2.toLowerCase();
        mainKey1=key1;
        mainKey2=key2;      
    }
    public String encrypt(String input){
        StringBuilder sb=new StringBuilder(input);
        for(int i=0;i<sb.length();i++){
            int idx=fullAlpha.indexOf(sb.charAt(i));
            if(idx!=-1){
                if(i%2==0){
                char newChar=fullShiftedAlpha1.charAt(idx);
                sb.setCharAt(i,newChar);
                }else{
                char newChar=fullShiftedAlpha2.charAt(idx);
                sb.setCharAt(i,newChar);
                }
            }
        }
        return sb.toString();
    }
    public String decrypt(String input){
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1,26 - mainKey2);
        return cc.encrypt(input);
    }
}
