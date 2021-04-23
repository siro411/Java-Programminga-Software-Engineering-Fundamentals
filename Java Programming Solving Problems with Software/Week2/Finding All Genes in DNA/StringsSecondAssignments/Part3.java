
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon(String dna,int startIndex,String stopCodon){
        int endIndex=dna.indexOf(stopCodon,startIndex);
        if((endIndex-startIndex)%3==0){
            return endIndex;
        }
        return dna.length();        
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
    
    
    
    public void printAllGenes(String dna){
        while(true){
            if(findGene(dna).isEmpty()){
                break;
            }
            System.out.println(findGene(dna));
            dna=dna.substring(dna.indexOf(findGene(dna))+findGene(dna).length());
        }
    }
    
    public int countGenes(String dna){
        int startIndex=0;
        int sum=0;
        while(true){
            if(findGene(dna).isEmpty()){
                break;
            }
            System.out.println(findGene(dna));
            sum++;
            dna=dna.substring(dna.indexOf(findGene(dna))+findGene(dna).length());
        }
        return sum;
    }
    public void testCountGenes(){
        System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
    }
}
