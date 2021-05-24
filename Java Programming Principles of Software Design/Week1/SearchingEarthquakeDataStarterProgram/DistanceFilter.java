
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location destLoc;
    private double maxDist;
    private String name;
    public DistanceFilter(Location loc,double dist){
        destLoc=loc;
        maxDist=dist;
        name="Distance";
    }
    public boolean satisfies(QuakeEntry qe){
        return qe.getLocation().distanceTo(destLoc)/1000<maxDist;
    }
    public String getName(){
        return name;
    }
}
