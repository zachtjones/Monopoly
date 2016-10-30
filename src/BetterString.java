
public class BetterString {

	/**
	 * Adds spaces to the right of a string or trims a string down to the totalLength specified.
	 * @param s The original string. Can be ""
	 * @param totalLength The length of the String to be returned
	 * @return A String with the length specified by the totalLength and the original String
	 */
	public static String padRight(String s, int totalLength) {
		if (s.length() >= totalLength) {
			s = s.substring(0, totalLength);
		} else {
			while (s.length() < totalLength) {
				s += " ";
			}
		}
		
		return s;
	}
	
	/**
	 * Adds spaces to the right of a string or trims a string down to the totalLength specified, using the paddingCharacter
	 * @param s The original string. Can be ""
	 * @param totalLength The length of the String to be returned
	 * @param paddingCharacter The character used to extend the string
	 * @return A String with the length specified by the totalLength and the original String, padded using the paddingCharacter
	 */
	public static String padRight(String s, int totalLength, char paddingCharacter) {
		if (s.length() >= totalLength) {
			s = s.substring(0, totalLength);
		} else {
			while (s.length() < totalLength) {
				s += paddingCharacter;
			}
		}
		
		return s;
	}
}
