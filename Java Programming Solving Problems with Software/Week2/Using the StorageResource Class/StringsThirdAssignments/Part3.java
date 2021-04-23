
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part3 {
    public int findStopCodon(String dna,int startIndex,String stopCodon){
        int currIndex=dna.indexOf(stopCodon,startIndex);
        while(currIndex!=-1){
            if((currIndex-startIndex)%3==0){
            return currIndex;
            }
            else{
                currIndex=dna.indexOf(stopCodon,currIndex+1);
            }
        }
        return dna.length();        
    }
    
    
    public String findGene(String dna){
        int startIndex=dna.indexOf("ATG");
        if(startIndex==-1){ 
            return "";
        }
        int firstTAAEndIndex=findStopCodon(dna,startIndex,"TAA");
        int firstTAGEndIndex=findStopCodon(dna,startIndex,"TAG");
        int firstTGAEndIndex=findStopCodon(dna,startIndex,"TGA");
        int minIndex=Math.min(firstTAAEndIndex,Math.min(firstTAGEndIndex,firstTGAEndIndex));
        if(minIndex==dna.length()){
            return "";
        }
        return dna.substring(startIndex,minIndex+3);    
    }
    
    
    
    public void printAllGenes(String dna){
        
        while(true){
            if(findGene(dna).isEmpty()){
                break;
            }
            System.out.println(findGene(dna));
            System.out.println(dna.indexOf(findGene(dna)));
            System.out.println(findGene(dna).length());
            dna=dna.substring(dna.indexOf(findGene(dna))+findGene(dna).length());
        }
    }
    
    public StorageResource getAllGenes(String dna){
        StorageResource geneList=new StorageResource();
        while(true){
            if(findGene(dna).isEmpty()){
                break;
            }
            System.out.println(dna.length());
            System.out.println("in findGene is "+findGene(dna));
            System.out.println(dna.indexOf(findGene(dna)));
            System.out.println(findGene(dna).length());
            geneList.add(findGene(dna));
            dna=dna.substring(dna.indexOf(findGene(dna))+findGene(dna).length());
        }
        return geneList;
    }
    public int countChar(String dna,String targetChar){
        int cnt=0;
        int startIndex=0;
        while(true){
            startIndex=dna.indexOf(targetChar,startIndex);
            if(startIndex==-1){
                break;
            }
            cnt++;
            startIndex=startIndex+1;
        }
        return cnt;
    }
    
    public float cgRatio(String dna){
        
        int numOfC=countChar(dna,"C");
        //System.out.println(numOfC);
        int numOfG=countChar(dna,"G");
        //System.out.println(numOfG);
        return (float)(numOfC+numOfG)/dna.length();

    }
 
    public void procssGenes(StorageResource sr){
        int numLongerThan9=0;
        int numLarger35=0;
        String longestGene="";
        for (String s :sr.data()){
            if(s.length()>9){
                System.out.println("longer than 9 is "+s);
                numLongerThan9++;
            }
            if(cgRatio(s)>0.35){
                System.out.println("cgratio>0.35 "+s);
                numLarger35++;
            }
            if(longestGene.length()<s.length()){
                longestGene=s;
            }
            
        }
        System.out.println("the number of Strings longer than 9 "+numLongerThan9);
        System.out.println("longest gene is "+longestGene);
    }
    public void testProcessGenes(){
        String a="ATGTTTTTTTTTTGATGGTGCCCCCCTAACC";
        String b="ATGTGATTT";
        String c="ATGCGCGCGCGCTAGCGCG";
        String d="atgatgtttcctgtccacttctaaattcttgtcttagtga".toUpperCase();
        procssGenes(getAllGenes(a));
        procssGenes(getAllGenes(b));
        procssGenes(getAllGenes(c));
        procssGenes(getAllGenes(d));
        
    }
    
    public void testNewDNA(){
        FileResource fr = new FileResource("dna/brca1line.fa");
        String dna = fr.asString();
        dna=dna.toUpperCase();
        System.out.println(dna);
        procssGenes(getAllGenes(dna));
    }
}
