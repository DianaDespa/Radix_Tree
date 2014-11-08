import java.util.ArrayList;

/**
 * The RadixTree class represents the main structure that handles the insertion
 * and the lookup of the words.
 * 
 * @author Diana Despa
 */
public class RadixTree {
	
	/**
	 * A list of nodes that have a common parent.
	 */
	private ArrayList<Node> nodes = new ArrayList<Node>(1);
	
	/**
	 * Inserts the word in the structure in the proper location, according to
	 * its prefix.
	 * 
	 * @param word - the string to insert in the structure
	 */
	public void insertWord(String word) {
		int i;
		String prefix = null;
		
		/**
		 * Tries to find a common prefix with the nodes on the current level of
		 * the current subtree.
		 */
		for (i = 0; i < this.nodes.size(); i++) {
			prefix = StringsOperations.getCommonPrefix
					(this.nodes.get(i).getInfo(), word);
			if (prefix != null) {
				break;
			}
		}
		
		/**
		 * If a common prefix is not found, a new node is added on the current
		 * level, with a child node that represents the index of the added word.
		 */
		if (i == this.nodes.size()) {
			this.nodes.add(new Node(word));
			this.nodes.get(this.nodes.size() - 1).nextNode = new RadixTree();
			this.nodes.get(this.nodes.size() - 1).nextNode.nodes.
					add(new Node(String.valueOf(Index.wordIndex)));
			
		/**
		 * Else, the common prefix is removed from the word and the rest is 
		 * inserted further in the RadixTree.
		 */
		} else {	
			Node currentNode = this.nodes.get(i);
			String subStr1 = new String
					(currentNode.getInfo().substring(prefix.length()));
			
			currentNode.setInfo(prefix);
			
			/**
			 * If the information in the current node also needs to be separated
			 * in two - the prefix and the rest, a new subtree is created, 
			 * with the root being a node containing the rest of the initial 
			 * node; the children of the initial node are passed on to the new
			 * subtree. 
			 */
			if (!subStr1.equals("")) {
				ArrayList<Node> auxNodes = new ArrayList<Node>(1);
				auxNodes.add(new Node(subStr1));
				auxNodes.get(0).nextNode = currentNode.nextNode;
				RadixTree auxTree = new RadixTree();
				auxTree.nodes = auxNodes;
				currentNode.nextNode = auxTree;
			}
			
			String subStr2 = new String(word.substring(prefix.length()));
			currentNode.nextNode.insertWord(subStr2);
		}
	}

	/**
	 * Searches the RadixTree to find the nodes that form a specific prefix.
	 * 
	 * @param word - the string to search in the structure
	 */
	public void checkPrefix(String word) {
		
		if (word == null) {
			return;
		}
		
		String prefix = null;
		int i;
		for (i = 0; i < this.nodes.size(); i++) {
			prefix = StringsOperations.getCommonPrefix
					(this.nodes.get(i).getInfo(), word);
			if (prefix != null) {
				
				/**
				 * If the common prefix and the word to search are of the same 
				 * length, but not equal, then we can't search any further. 
				 */
				if (this.nodes.get(i).getInfo().length() != word.length() ||
						word.equals(this.nodes.get(i).getInfo())) {
					this.nodes.get(i).nextNode.checkPrefix
						(word.substring(prefix.length()));
				}
				break;
			}
		}
		
		/**
		 * If the full prefix is found, we build the solution with the indexes
		 * of all its descendant leaf nodes.
		 */
		if (word.equals(prefix)) {
			this.getTerminals(this.nodes.get(i).nextNode);
		}
	}
	
	/**
	 * Recursive method which builds the Index.solutions array by finding all 
	 * the leaf nodes of a RadixTree. 
	 * 
	 * @param tree - the root from which to begin the search
	 */
	private void getTerminals(RadixTree tree){		
		
		for (int i = 0; i < tree.nodes.size(); i++){
			if (tree.nodes.get(i).nextNode != null) {
				getTerminals(tree.nodes.get(i).nextNode);
			}
		}
		if (tree.nodes.get(0).nextNode == null) {
			Index.solutions.add(Integer.parseInt(tree.nodes.get(0).getInfo()));
			return;
		}
	}
}