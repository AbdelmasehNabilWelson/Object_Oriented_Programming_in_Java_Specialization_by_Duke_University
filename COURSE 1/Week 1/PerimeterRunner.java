import edu.duke.*;
import java.io.File;
public class PerimeterRunner {
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
    
    public int getNumPoints(Shape sh) {
        int count = 0;
        for(Point p : sh.getPoints()) {
            count++;
        }
        return count;
    }
    
    public double getAverageLength(Shape sh) {
        double perimeter = getPerimeter(sh);
        int numPoints = getNumPoints(sh);
        return perimeter / numPoints;
    }
    
    public double getLargestSide(Shape sh) {
        double maxDistance = 0, currDistance;
        // get The last Point
        Point prevPoint = sh.getLastPoint();
        for(Point p : sh.getPoints()) {
            currDistance = p.distance(prevPoint);
            maxDistance = Math.max(maxDistance, currDistance);
            prevPoint = p;
        }
        return maxDistance;
    }
    
    public double getLargestX(Shape sh) {
        Point prevPoint = sh.getLastPoint();
        double maxValX = prevPoint.getX();
        for(Point p : sh.getPoints()) {
            maxValX = Math.max(maxValX, p.getX());
            prevPoint = p;
        }
        return maxValX;
    }
    
    public double getLargestPerimeterMultipleFiles() {
         DirectoryResource dr = new DirectoryResource();
         double maxPerm = 0;
         for (File fl : dr.selectedFiles()) {
             FileResource file = new FileResource(fl);
             Shape s = new Shape(file);
             maxPerm = Math.max(maxPerm, getPerimeter(s));
         }
         return maxPerm;
    } 
    
    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double maxPerm = 0, currPerm;
        String name = null;
        for (File fl : dr.selectedFiles()) {
            FileResource file = new FileResource(fl);
            Shape s = new Shape(file);
            currPerm = getPerimeter(s);
            if (currPerm > maxPerm) {
                maxPerm = currPerm;
                name =  fl.getName();
            }
        }
        return name;
    }
    
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + String.format("%.2f", length));
        int numOfPoints = getNumPoints(s);
        System.out.println("Number of Points in The Shape = " + numOfPoints);
        double averageLength = getAverageLength(s);
        System.out.println("The Average Length of The Shape = " + String.format("%.2f", averageLength));
        double largestSide = getLargestSide(s);
        System.out.println("The largest side in the shape = " + String.format("%.2f", largestSide));
        double largestX = getLargestX(s);
        System.out.println("The largest x in the shape = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double perm = getLargestPerimeterMultipleFiles();
        System.out.println("The largest Permiter in a Multiple files is " + String.format("%.2f", perm));
    }
    
    public void testFileWithLargestPerimeter() {
        System.out.println("The name of The file of the largest permiter among multiple files is " 
        +  getFileWithLargestPerimeter());
    }
    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
}
