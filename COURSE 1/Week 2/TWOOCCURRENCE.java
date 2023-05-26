public class TWOOCCURRENCE {
	public static boolean twoOccurrences(String stringa, String stringb) {
		int count = 0;
		boolean foundTwoTimes;
		
		for (int i = 0; i < stringb.length() - stringa.length(); ++i) {
			int index = stringb.indexOf(stringa, i);
			if (index != -1) {
				count++;
				i = index;
			}
		}
		
		if (count >= 2) {
			foundTwoTimes = true;
		} else {
			foundTwoTimes = false;
		}
		
		return foundTwoTimes;
	}
	
	public static void test() {
		String a = "by";
		String b = "hello by welcome by";
		
		System.out.println(twoOccurrences(a, b));
		
		a = "two";
		b = "two";
		System.out.println(twoOccurrences(a, b));
		
		a = "tw";
		b = "two to the next world";
		System.out.println(twoOccurrences(a, b));
		
		a = "b";
		b = "paba banana";
		System.out.println(twoOccurrences(a, b));
		System.out.println("\nTEST FINISHED\n");
	}
}
