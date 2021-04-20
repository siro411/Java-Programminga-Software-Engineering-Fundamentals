import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int n=0;
        for(Point pt:s.getPoints()){
            n=n+1;
        }
        return n;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        return getPerimeter(s)/getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            if(largestSide<currDist){
                largestSide=currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return largestSide;
    }
     

    public double getLargestX(Shape s) {
        // Put code here
        double largestX=s.getLastPoint().getX();
        for(Point p:s.getPoints()){
            if(largestX<p.getX()){
                largestX=p.getX();
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerim=0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);// replace this code
            Shape s = new Shape(fr);
            double curPerim=getPerimeter(s);
            if(largestPerim<curPerim){
                largestPerim=curPerim;
            }
        }
            
        return largestPerim;
        
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerim=0;
        String fileWithLargestPerim="";
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);// replace this code
            Shape s = new Shape(fr);
            double curPerim=getPerimeter(s);
            if(largestPerim<curPerim){
                fileWithLargestPerim=f.getName();
            }
        }
        return fileWithLargestPerim;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("number of points = " + getNumPoints(s));
        System.out.println("Average length is "+getAverageLength(s));
        System.out.println("Largest length is "+getLargestSide(s));
        System.out.println("Largest X is "+getLargestX(s));
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
         System.out.println("Largest perimeter is "+ getLargestPerimeterMultipleFiles());
         System.out.println("The file with largest perimeter is "+getFileWithLargestPerimeter());
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeterMultipleFiles();
    }
}
