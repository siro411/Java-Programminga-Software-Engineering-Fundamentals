
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alpha;
    private String fullAlpha;
    private String shiftedAlpha;
    private String fullShiftedAlpha;
    private int mainKey;
    public CaesarCipher(int key){
        alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        fullAlpha=alpha+alpha.toLowerCase();
        shiftedAlpha=alpha.substring(key)+alpha.substring(0,key);
        fullShiftedAlpha=shiftedAlpha+shiftedAlpha.toLowerCase();
        mainKey=key;
    }
    public String encrypt(String input){
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
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
}
