/**
 * Write a description of Assignment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.File;
import edu.duke.*;
import org.apache.commons.csv.*;
public class Assignment {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestRec=null;
        for (CSVRecord currentRec:parser){
            if (coldestRec == null){
                coldestRec=currentRec;
            }else{
                double currentTemp=Double.parseDouble(currentRec.get("TemperatureF"));
                
                double coldestTemp=Double.parseDouble(coldestRec.get("TemperatureF")); 
                if(currentTemp<coldestTemp &&currentTemp!=-9999){
                    coldestRec=currentRec;
                }
            }
            
        }
        return coldestRec;
    
    }
    
    public void testColdestHourInFile(){
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        CSVRecord rec=coldestHourInFile(parser);
        System.out.println("the coldest temp "+rec.get("TemperatureF")+" occurred at "+rec.get("DateUTC"));   
    }
    
    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestSoFar=null;
        String filename="";
        for (File f : dr.selectedFiles()) {
            FileResource fr=new FileResource(f);            
            CSVRecord current=coldestHourInFile(fr.getCSVParser());
            if (coldestSoFar==null){
                coldestSoFar=current;
            }else{
                double currentTemp=Double.parseDouble(current.get("TemperatureF"));
                double lowestTemp=Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(currentTemp<lowestTemp){
                    coldestSoFar=current;
                    filename=f.getName();
                }
            }
            
        }
        return filename;
        }
    
    public void testFileWithColdestTemperature(){
        String f=fileWithColdestTemperature();
        System.out.println("Coldest day was in file "+f);
        FileResource fr=new FileResource("../nc_weather/"+f.split("-")[1]+"/"+f);
        System.out.println("Coldest temperature on that day was "+coldestHourInFile(fr.getCSVParser()).get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        //SimpleDateFormat origDateFormat = new SimpleDateFormat("M/dd/yyyy hh:mm");
        //SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        for(CSVRecord record :fr.getCSVParser()){
            //System.out.println(newDateFormat.format(origDateFormat.parse(record.get("DateUTC")))+record.get("TemperatureF"));
            System.out.println(record.get("DateUTC")+" "+record.get("TemperatureF"));
    
        }
    
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord coldestRec=null;
        for (CSVRecord currentRec:parser){
            
            try{
                if (coldestRec == null){
                    coldestRec=currentRec;
                }else{               
                    double currentHumidity=Double.parseDouble(currentRec.get("Humidity"));               
                    double coldestHumidity=Double.parseDouble(coldestRec.get("Humidity")); 
                    if(currentHumidity<coldestHumidity){
                        coldestRec=currentRec;
                    }
                }
            }catch(Exception e){
            
            }            
        }
        return coldestRec;
    }
    
    public void testlowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumiditySoFar=null;
        for (File f : dr.selectedFiles()) {
            FileResource fr=new FileResource(f);            
            CSVRecord current=lowestHumidityInFile(fr.getCSVParser());
            if(current.get("Humidity")!=null){
                if (lowestHumiditySoFar==null){
                    lowestHumiditySoFar=current;
                }else{               
                    double currentHumidity=Double.parseDouble(current.get("Humidity"));
                    double lowestHumidity=Double.parseDouble(lowestHumiditySoFar.get("Humidity"));
                    if(currentHumidity<lowestHumidity){
                        lowestHumiditySoFar=current;
                    }
                }       
            }
            
        }
        return lowestHumiditySoFar;
    }
    
    public void testlowestHumidityInManyFiles(){
        CSVRecord csv=lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        int num=0;
        double sum=0.0;
        for(CSVRecord record:parser){
            num++;
            sum=sum+Double.parseDouble(record.get("TemperatureF"));
        }
        return sum/num;
    }
    public void testAverageTemperatureInFile(){
        FileResource fr=new FileResource();
        System.out.println("Average temperature in file is "+averageTemperatureInFile(fr.getCSVParser()));
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value){
        int num=0;
        double sum=0.0;
        for(CSVRecord record:parser){
            if(Double.parseDouble(record.get("Humidity"))>value){
                sum=sum+Double.parseDouble(record.get("TemperatureF"));
                num++;
            }
        }
        if(num!=0){
            return sum/num;
        }else{
            return -1;
        }
        
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr=new FileResource();
        double result=averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80);
        if (result==-1){
        System.out.println("No temperatures with that humidity");
        }else{
        System.out.println("Average Temp when high Humidity is "+result);
        }
    
    }
    
    
    
}