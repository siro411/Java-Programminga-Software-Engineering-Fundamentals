
/**
 * Write a description of BabyNamesMiniProject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.math.*;
import java.io.File;
import edu.duke.*;
import org.apache.commons.csv.*;
public class BabyNamesMiniProject {
    public void totalBirths(FileResource fr){
        int totalBirths=0;
        int totalBoys=0;
        int totalGirls=0;
        for(CSVRecord rec:fr.getCSVParser(false)){
            int numBorn=Integer.parseInt(rec.get(2));
            totalBirths+=numBorn;
            if(rec.get(1).equals("M")){
                totalBoys+=numBorn;
            }
            else{
                totalGirls+=numBorn;
            }
        }
        System.out.println("total births "+totalBirths );
        System.out.println("total boys "+totalBoys);
        System.out.println("total Girls "+totalGirls);
    
    }
    
    public int getRank(int year,String name,String gender){
        FileResource fr=new FileResource("../testing/yob"+String.valueOf(year)+"short.csv");
        int rank=0;
        for(CSVRecord rec:fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                rank++;
                //System.out.println("name is "+rec.get(0));
                //System.out.println("rank is "+rank);
                if(rec.get(0).equals(name)){ 
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public String getName(int year,int rank,String gender){
        FileResource fr=new FileResource("../testing/yob"+String.valueOf(year)+"short.csv");
        String name="NO NAME";
        int r=0;
        for(CSVRecord rec:fr.getCSVParser(false)){
            //System.out.println(rec.get(0));
            if(rec.get(1).equals(gender)){
                r++;
                if (r==rank){
                    name=rec.get(0);
                }
                
            }
        }
        return name;
    }
    
    public void whatIsNameInYear(String name,int year,int newYear,String gender){
        int rankInYear=getRank(year,name,gender);
        //System.out.println("rank is "+rankInYear);
        String nameNew=getName(newYear,rankInYear,gender);
        System.out.println(name+" born in "+year+" would be "+nameNew+" if she was born in "+newYear);
    }
    
    public int yearOfHighestRank(String name,String gender){
        DirectoryResource dr=new DirectoryResource();
        int highestRank=-1;
        for(File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            String y=f.getName().substring(3,7);
            //System.out.println("year is "+y);
            int r=getRank(Integer.parseInt(y),name,gender);
            System.out.println("rank is "+r);
            if((highestRank==-1 && r!=-1)||(highestRank>r)){
                highestRank=r;
            }            
        }
        return highestRank;
    
    }
    
    public double getAverageRank(String name,String gender){
        DirectoryResource dr=new DirectoryResource();
        double sumRank=0;
        int num=0;
        for(File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            String y=f.getName().substring(3,7);
            //System.out.println("year is "+y);
            int r=getRank(Integer.parseInt(y),name,gender);
            //System.out.println("rank is "+r);
            num++;
            if(r!=-1){
                sumRank+=r;
            }            
        }
        if(sumRank!=0){
            return sumRank/num ;
        }else{
            return -1.0;
        }
    
    }
    
    public int getTotalBirthsRankedHigher(int year,String name,String gender){
        FileResource fr=new FileResource("../testing/yob"+String.valueOf(year)+"short.csv");
        //int rank=0;
        int numBirth=0;
        for(CSVRecord rec:fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                //rank++;
                //System.out.println("name is "+rec.get(0));
                //System.out.println("rank is "+rank);
                if(rec.get(0).equals(name)){ 
                    return numBirth;
                }else{
                    numBirth+=Integer.parseInt(rec.get(2));
                }
            }
        }
        return -1;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        System.out.println("num of birth higher than rank "+getTotalBirthsRankedHigher(2012,"Emma","M"));
    }
    public void testTotalBirths(){
        FileResource fr=new FileResource();
        totalBirths(fr);
        
    }
    public void testGetRank(){
        System.out.println("The rank is "+getRank(2014,"Isabella","F"));
    }
    
    public void testGetName(){
        System.out.println(getName(2014,3,"F"));
    }
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Isabella",2012,2014,"F");
    }
    public void testYearOfHighestRank(){
        System.out.println(yearOfHighestRank("Mason","M"));
    }
    public void testGetAverageRank(){
        double d=getAverageRank("Jacob","M");
        BigDecimal bd = new BigDecimal(d);
        System.out.println(bd.setScale(2, bd.ROUND_DOWN));
    }
}
