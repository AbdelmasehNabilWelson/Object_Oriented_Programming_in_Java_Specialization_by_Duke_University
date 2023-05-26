import java.util.ArrayList;

public class FindingMultipleGenes {
	public static int findStopCodon(String dnaStr, int startIndex, String stopCodon) {
		int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
		while (currIndex != -1) {
			int diff = currIndex - startIndex;
			if (diff % 3 == 0) {
				return currIndex;
			} else {
				currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
			}
		}
		
		return -1;
	}
	
	public static ArrayList<String> findMoreGenes(String dna) {
		ArrayList<String> genes = new ArrayList<>();
		
		int minIndex = 0;
		while (true) {
			int startIndex = dna.indexOf("ATG", minIndex);
			if (startIndex == -1) break;
			
			int taaIndex = findStopCodon(dna, startIndex, "TAA");
			int tagIndex = findStopCodon(dna, startIndex, "TAG");
			int tgaIndex = findStopCodon(dna, startIndex, "TGA");
			// int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
			
			
			if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
				minIndex = tgaIndex;
			} else {
				minIndex = taaIndex;
			}
			
			if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
				minIndex = tagIndex;
			} 
			
			// System.out.println(startIndex + " " + minIndex + " " + taaIndex + " " + tgaIndex + " " + tagIndex);
			
			if (minIndex == -1) break;
			
			genes.add(dna.substring(startIndex, minIndex + 3));
		}
		return genes;
	}
	
	
	
	public static void testFindStop() {
		String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
		int dex = findStopCodon(dna, 0, "TAA");
		if (dex != 9) {
			System.out.println("Error on 9");
		} 
		dex = findStopCodon(dna, 9, "TAA");
		if (dex != 21) {
			System.out.println("Error on 21");
		}
		dex = findStopCodon(dna, 1, "TAA");
		if (dex != -1) {
			System.out.println("Error on 26");
		}
		dex = findStopCodon(dna, 0, "TAG");
		if (dex != -1) {
			System.out.println("Error on 26 TAG");
		}
		System.out.println("Finished");		
	}
	
	private static void printAllGenes(ArrayList<String> genes) {
		for (String i : genes) {
			System.out.println(i);
		}
	}
	
	private static void test(String dna) {
		ArrayList<String> genes = findMoreGenes(dna);
		
		System.out.println("All Genes in the String : " + dna);
		if (genes.isEmpty()) {
			System.out.println("No Genes");
		} else {
			printAllGenes(genes);
		}
		
		System.out.println("\nTEST FINISHED\n");
	}
	
	public static void testFindMoreGenes() {
		String dna1 = "CGATGATCGCATGATTCATGCTTAAATAAAGCTCA";
		test(dna1);
		
		String dna2 = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
		test(dna2);
		
		String dna3 = "";
		test(dna3);
		
		String dna4 = "ATGATCATAAGAAGATAATAGAGGGCCATGTAA";
		test(dna4);
	}
}
