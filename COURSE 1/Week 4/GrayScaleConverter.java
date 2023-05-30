import edu.duke.*;
import java.io.File;
/**
 * This class has methods to converte in image to its grayscale and negative.
 * @author (Duketolearn Team with my respected modifiacations) 
 */
public class GrayScaleConverter {
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outputImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel p : inImage.pixels()) {
            Pixel newP = inImage.getPixel(p.getX(), p.getY());
            int average = (newP.getRed() + newP.getGreen() + newP.getBlue()) / 3;
            newP.setRed(average);
            newP.setGreen(average);
            newP.setBlue(average);
            outputImage.setPixel(p.getX(), p.getY(), newP);
        }
        return outputImage;
    }
    
    public void convertManyImagesToGray() {
        DirectoryResource dr = new DirectoryResource();
        for (File fr : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(fr);
            ImageResource gray = makeGray(inImage);
            gray.draw();
            String currName = inImage.getFileName();
            String newName = "Gray-" + currName;
            gray.setFileName(newName);
            gray.save();
        }
    }
    
    public ImageResource makePhotographicNegatives(ImageResource inImage) {
        ImageResource outputImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel p : inImage.pixels()) {
            Pixel newP = inImage.getPixel(p.getX(), p.getY());
            newP.setRed(Math.abs(255 - newP.getRed()));
            newP.setGreen(Math.abs(255 - newP.getGreen()));
            newP.setBlue(Math.abs(255 - newP.getBlue()));
            outputImage.setPixel(p.getX(), p.getY(), newP);
        }
        return outputImage;
    }
    
    public void convertManyImagesToPhotographicNegatives() {
        DirectoryResource dr = new DirectoryResource();
        for (File fr : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(fr);
            ImageResource negative = makePhotographicNegatives(inImage);
            negative.draw();
            String currName = inImage.getFileName();
            String newName = "Negative-" + currName;
            negative.setFileName(newName);
            negative.save();
        }
    }
    
    public void testGray() {
        ImageResource inImage = new ImageResource();
        ImageResource gray = makeGray(inImage);
        gray.draw();
    }
    
    public void testmakePhotographicNegatives() {
        ImageResource inImage = new ImageResource();
        ImageResource negative = makePhotographicNegatives(inImage);
        negative.draw();
    }
}
