/**
 * The StringOperations class performs operations on strings.
 * 
 * @author Diana Despa
 */
public class StringsOperations {
	
	/**
	 * Finds the biggest common prefix of two strings.
	 * 
	 * @param a - first string.
	 * @param b - second string.
	 * @return - the string which represents a common prefix of a and b that
	 * has a maximum length, or null if the two strings don't have a common 
	 * prefix.
	 */
	public static String getCommonPrefix(String a, String b) {
		
		int i, minLen = a.length() < b.length() ? a.length() : b.length();
		
		for (i = 0; i < minLen; i++) {
			if (a.charAt(i) != b.charAt(i)) {
				break;
			} 
		}
		if (i > 0) {
			return a.substring(0, i);
		}
		return null;
	}
}
