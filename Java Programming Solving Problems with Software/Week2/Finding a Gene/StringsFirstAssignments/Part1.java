
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna){
        int startPos=dna.indexOf("ATG");
        if(startPos==-1){
            return "";
        }
        int endPos=dna.indexOf("TAA");
        if(endPos==-1){
            return "";
        }
        if((endPos-startPos)%3==0){
            return dna.substring(startPos,endPos+3);
        }
        else{
            return "";
        }
    }
    
    public void testSimpleGene(){
        String a="TTATTTA";
        String b="AATGGGAAA";
        String c="AGTGTAGTA";
        String d="ATGATGGTTTAAA";
        String e="ATGATGGTTAAA";
        System.out.println(a+" "+findSimpleGene(a));
        System.out.println(b+" "+findSimpleGene(b));
        System.out.println(c+" "+findSimpleGene(c));
        System.out.println(d+" "+findSimpleGene(d));
        System.out.println(e+" "+findSimpleGene(e));
    }
}
