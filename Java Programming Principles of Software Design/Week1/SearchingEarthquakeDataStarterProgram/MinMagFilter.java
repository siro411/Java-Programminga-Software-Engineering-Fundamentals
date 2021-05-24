
/**
 * Write a description of MinMagFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinMagFilter implements Filter {
    private double magMin;
    private String name;
    public MinMagFilter(double min){
        magMin=min;
        name="MinMag";
    }
    public boolean satisfies(QuakeEntry qe){
        return qe.getMagnitude()>=magMin;
    }
    public String getName(){
        return name;
    }
}
