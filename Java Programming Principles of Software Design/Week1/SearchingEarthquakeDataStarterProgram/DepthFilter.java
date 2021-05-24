
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
    private double depMin;
    private double depMax;
    private String name;
    public DepthFilter(double min,double max){
        depMin=min;
        depMax=max;
        name="Depth";
    }
    public boolean satisfies(QuakeEntry qe){
        return qe.getDepth()<=depMax&&qe.getDepth()>=depMin;
    }
    public String getName(){
        return name;
    }
}
