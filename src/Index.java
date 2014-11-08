import java.util.ArrayList;

/**
 * This execution entry point class handles the parsing, indexing and lookup
 * of words in a text.
 */
public class Index {
	
	/**
	 * The static member wordIndex is a word counter which keeps track of each
	 * word's index in the text.
	 */
	public static int wordIndex = 0;
	
	/**
	 * The solutions array holds the indexes of words having a certain prefix
	 */
	public static ArrayList<Integer> solutions = new ArrayList<Integer>(1);
	
	/**
	 * Execution entry point.
	 *
	 * @param args two strings representing the name of the file that contains
	 * the text to index, and the name of the file containing words to lookup
	 * in the text.
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Usage: java -cp <classpath> Index <text file> <word file>");
			System.exit(1);
		}

		String word;
		RadixTree radix = new RadixTree(); 
		
		FileParser textFile = new FileParser(args[0]);
		textFile.open();
		
		/**
		 * Insert all the words in the radix structure and increment index.
		 */
		while ((word = textFile.getNextWord()) != null) {
			radix.insertWord(word);
			Index.wordIndex++;
		}
		
		textFile.close();

		FileParser prefixFile = new FileParser(args[1]);
		prefixFile.open();
		
		/**
		 * Build the solutions array, print its size and contents, then empty it.
		 */
		while ((word = prefixFile.getNextWord()) != null) {
			
			radix.checkPrefix(word);
			
			System.out.print(Index.solutions.size());
			for (int i : Index.solutions){
				System.out.print(" " + i);
			}
			System.out.println();
			Index.solutions.clear();
			Index.solutions.trimToSize();
		}
		
		prefixFile.close();
	}
}
