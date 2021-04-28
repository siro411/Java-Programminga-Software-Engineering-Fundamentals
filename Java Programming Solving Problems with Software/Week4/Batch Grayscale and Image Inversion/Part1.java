
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.File;
import edu.duke.*;
public  class Part1 {
    public ImageResource makeGray(ImageResource inImage){
        ImageResource output=new ImageResource(inImage.getWidth(),inImage.getHeight());
        for(Pixel p:output.pixels()){
            Pixel inPixel=inImage.getPixel(p.getX(),p.getY());
            int red=inPixel.getRed();
            int blue=inPixel.getBlue();
            int green=inPixel.getGreen();  
            int avg=(red+blue+blue)/3;
            p.setRed(avg);
            p.setBlue(avg);
            p.setGreen(avg);
        }
        return output;
    }
    public void batchGrayScale(){
        DirectoryResource dr=new DirectoryResource();
        for(File f: dr.selectedFiles()){
            ImageResource inImage=new ImageResource(f);
            ImageResource output=makeGray(inImage);
            String newName="gray-"+inImage.getFileName();
            output.setFileName(newName);
            output.draw();
            output.save();
        }
    }
}
