
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part1 {
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
    
    public void testFindStopCodon(){
        String dna="ATGTGAGATAGATGG";
        System.out.println(findStopCodon(dna,0,"TAG"));
        
    
    }
    
    public String findGene(String dna){
        int firstStartCodon=dna.indexOf("ATG");
        if(firstStartCodon==-1){ 
            return "";
        }
        int firstTAAEndCodon=findStopCodon(dna,firstStartCodon,"TAA");
        int firstTAGEndCodon=findStopCodon(dna,firstStartCodon,"TAG");
        int firstTGAEndCodon=findStopCodon(dna,firstStartCodon,"TGA");
        int minIndex=Math.min(firstTAAEndCodon,Math.min(firstTAGEndCodon,firstTGAEndCodon));
        if(minIndex==dna.length()){
            return "";
        }
        return dna.substring(firstStartCodon,minIndex+3);    
    }
    
    public void testFindGene(){
        String a="TAAAAAA";
        String b="ATGTTTTAGGGGGGGG";
        String c="ATGTTTTTTTGATTTTTTTAGGGG";
        String d="ATGTTTTTTTTT";
        System.out.println("Strings are "+a+" "+findGene(a));
        System.out.println("Strings are "+b+" "+findGene(b));
        System.out.println("Strings are "+c+" "+findGene(c));
        System.out.println("Strings are "+d+" "+findGene(d));
    }
    
    public void printAllGenes(String dna){
        
        while(true){
            if(findGene(dna).isEmpty()){
                break;
            }
            System.out.println(findGene(dna));
            dna=dna.substring(dna.indexOf(findGene(dna))+findGene(dna).length());
        }
    }
    
    public StorageResource getAllGenes(String dna){
        StorageResource geneList=new StorageResource();
        while(true){
            if(findGene(dna).isEmpty()){
                break;
            }
            //System.out.println(findGene(dna));
            geneList.add(findGene(dna));
            dna=dna.substring(dna.indexOf(findGene(dna))+findGene(dna).length());
        }
        return geneList;
    }
    
    public void testGetAllGenes(){
        String s="ATGTTTTTTGA";
        StorageResource geneList=getAllGenes(s);
        for(String g : geneList.data()){
            System.out.println(g);
        }
    }
}

