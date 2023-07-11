import edu.duke.FileResource;
import java.util.HashMap;
/**
 * Class to find how many times each codon occurs in a strand of dna based on reading Frames
 * There are three different Reading Frames 
 * The first reading frame starts at position 0 and has the codons: “CGT”, “TCA”, “AGT” 
 * and “TCA”.
 * The second reading frame starts at position 1 (ignoring the first C character) and 
 * has the codons: “GTT”, “CAA”, “GTT”, “CAA”.
 * The third reading frame starts at position 2 (ignoring the first two characters CG) 
 * and has the codons: “TTC”, “AAG”, “TTC”. 
 * @author (Abdelmaseh Nabil) 
 * @version (7/3/2023)
 */
public class CodonCount {
    HashMap<String, Integer> dnaMap;
    public CodonCount() {
        dnaMap = new HashMap<String, Integer>();
    }
    
    private void buildCodonMap(int start, String dna) {
        for (int i = start; i < dna.length() && i < dna.length() - 3; i += 3) {
            String codon = dna.substring(i, 3 + i);
            if(dnaMap.containsKey(codon)) {
                dnaMap.put(codon, dnaMap.get(codon) + 1);
            } else {
                if (Character.isLetter(codon.charAt(codon.length() - 1)))
                    dnaMap.put(codon, 1);
            }
        }         
    }
    
    private String getMostCommonCodon() {
        int max = -1;
        String codon = "";
        for (String s : dnaMap.keySet()) {
            int currMax = dnaMap.get(s);
            if (currMax > max) {
                max = currMax;
                codon = s;
            }
        }
        return codon;
    }
    
    private void printCodonCounts(int start, int end) {
        int value;
        System.out.println("Counts of codons between " + start + " and " + end +  " inclusive are:");
        for (String s : dnaMap.keySet()) {
            value = dnaMap.get(s);
            if (value >= start && value <= end) {
                System.out.println(s + " " + value);
            }
        }
    }
    
    private String codonAppersExactlyNtimes(int n) {
        for (String dna : dnaMap.keySet()) {
            if (dnaMap.get(dna) == n) {
                return dna;
            }
        }
        return "NotFound";
    }
    
    private void printCodon(String dna, int startFrame, CodonCount cc, int start, int end) {
        cc.buildCodonMap(startFrame, dna);
        System.out.println("Reading frame starting with " + startFrame +  " results in " + 
        cc.dnaMap.size() + " unique codons");
        String mostCommonCodon = cc.getMostCommonCodon();
        System.out.println(" and Most common codon is " + mostCommonCodon + " wih count " + 
        cc.dnaMap.get(mostCommonCodon));
        cc.printCodonCounts(start, end);
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
        CodonCount c1 = new CodonCount();
        printCodon(dna, 0, c1, 1, 7);
        int n = 4;
        System.out.println("Codon appers " + n + " times is " + c1.codonAppersExactlyNtimes(n));
        c1 = new CodonCount();
        printCodon(dna ,1, c1, 1, 5);
        System.out.println("Codon appers " + n + " times is " + c1.codonAppersExactlyNtimes(n));        
        c1 = new CodonCount();
        printCodon(dna, 2, c1, 1, 5);        
        System.out.println("Codon appers " + n + " times is " + c1.codonAppersExactlyNtimes(n));        
    }
}

