/**
 * The Node class represents the nodes of the Radix Tree, each of them 
 * containing a piece of information (the member info) and a reference to a 
 * subtree (the member nextNode). 
 * 
 * @author Diana Despa
 */
public class Node {
	
	private String info = null;
	RadixTree nextNode = null;
	
	/**
	 * Class constructor.
	 */
	public Node(){
		this.info = new String("");
	}
	
	/**
	 * Class constructor specifying the value of info.
	 * @param info - the string to be passed to the info member.
	 */
	public Node(String info){
		this.info = info;
	}
	
	/**
	 * Getter for member info.
	 */
	public String getInfo(){
		return this.info;
	}
	
	/**
	 * Setter for member info.
	 */
	public void setInfo(String info){
		this.info = info;
	}
}
