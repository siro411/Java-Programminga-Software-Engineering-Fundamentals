
/**
 * Write a description of Export here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class Export {
    public String countryinfo(CSVParser parser,String country){
        String result="";
        for(CSVRecord record :parser){
            String exportCountry=record.get("Country");
            if(exportCountry.contains(country)){
                result=exportCountry+": "+record.get("Exports")+": "+record.get("Value (dollars)");
            }
        
        }
        return result;
    }
    
    public void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2){
        for(CSVRecord record:parser){
            String exports=record.get("Exports");
            if(exports.contains(exportItem1)&& exports.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        
        }
    }
    
    public int numberOfExporters(CSVParser parser,String exportItem){
        int num=0;
        for(CSVRecord record:parser){
            String exports=record.get("Exports");
            if(exports.contains(exportItem)){
                num++;
            }        
        }
        return num;
    
    }
    
    public void bigExports(CSVParser parser,String value){
        for(CSVRecord record:parser){
            String valueBigger=record.get("Value (dollars)");
            if(valueBigger.length()>value.length()){
                System.out.println(record.get("Country")+" "+record.get("Value (dollars)"));
            }
        
        
        }
        
    
    }
    public void tester(){
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        System.out.println(countryinfo(parser,"Nauru"));
        CSVParser parser1=fr.getCSVParser();
        listExportersTwoProducts(parser1,"gold","diamonds");
        CSVParser parser2=fr.getCSVParser();
        System.out.println(numberOfExporters(parser2,"sugar"));
        CSVParser parser3=fr.getCSVParser();
        bigExports(parser3,"$999,999,999,999”");
    }
}
