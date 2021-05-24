
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter {
    private double magMin;
    private double magMax;
    private String name;
    public MagnitudeFilter(double min,double max){
        magMin=min;
        magMax=max;
        name="Magnitude";
    }
    public boolean satisfies(QuakeEntry qe){
        return qe.getMagnitude()<=magMax&&qe.getMagnitude()>=magMin;
        
    }
    public String getName(){
        return name;
    }
}
