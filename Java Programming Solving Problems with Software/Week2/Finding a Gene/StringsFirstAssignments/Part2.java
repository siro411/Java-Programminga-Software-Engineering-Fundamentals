
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna,String startCodon,String stopCodon){
        if(Character.isLowerCase(dna.charAt(0))){
            startCodon=startCodon.toLowerCase();
            stopCodon=stopCodon.toLowerCase();
        }
        int startPos=dna.indexOf(startCodon);
        if(startPos==-1){
            return "";
        }
        int endPos=dna.indexOf(stopCodon);
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
        String d="gatgctataat";
        String e="ATGATGGTTAAA";
        String startCodon="ATG";
        String stopCodon="TAA";
        System.out.println(a+" "+findSimpleGene(a,startCodon,stopCodon));
        System.out.println(b+" "+findSimpleGene(b,startCodon,stopCodon));
        System.out.println(c+" "+findSimpleGene(c,startCodon,stopCodon));
        System.out.println(d+" "+findSimpleGene(d,startCodon,stopCodon));
        System.out.println(e+" "+findSimpleGene(e,startCodon,stopCodon));
    }
}
