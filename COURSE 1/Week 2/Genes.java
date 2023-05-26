
public class Genes {
	public static void main(String[] args) {
		
		System.out.println("Test for two Occurrences");
		TWOOCCURRENCE.test();
		
		System.out.println("Test for finding one gene in strand of DNA");
		FindingOneGene.testFindGene();
		
		
		
		System.out.println("Test for Mutliple gnees in strand of DNA");
		FindingMultipleGenes.testFindMoreGenes();
		
		// quiz Finding All Genes in DNA question 1
		FindingOneGene st = new FindingOneGene();
		String str = st.findGene("AATGCTAACTAGCTGACTAAT");
		System.out.println(str);
		
		
	}
	
}
