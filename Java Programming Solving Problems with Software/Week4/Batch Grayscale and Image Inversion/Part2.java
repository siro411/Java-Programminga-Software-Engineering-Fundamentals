
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.File;
import edu.duke.*;
public class Part2 {
    public ImageResource makeInversion(ImageResource inImage){
        ImageResource output=new ImageResource(inImage.getWidth(),inImage.getHeight());
        for(Pixel p:output.pixels()){
            Pixel inPixel=inImage.getPixel(p.getX(),p.getY());
            int red=inPixel.getRed();
            int blue=inPixel.getBlue();
            int green=inPixel.getGreen();  
            p.setRed(255-red);
            p.setBlue(255-blue);
            p.setGreen(255-green);
        }
        return output;
    }
    public void selectAndConvert(){
        DirectoryResource dr=new DirectoryResource();
        for(File f: dr.selectedFiles()){
            ImageResource inImage=new ImageResource(f);
            ImageResource output=makeInversion(inImage);
            String newName="inverted-"+inImage.getFileName();
            output.setFileName(newName);
            output.draw();
            output.save();
        }
    }
}
