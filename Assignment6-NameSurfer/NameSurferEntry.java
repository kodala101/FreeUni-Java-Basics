/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.StringTokenizer;

	public class NameSurferEntry implements NameSurferConstants {
	
		String[] namesData;		
		
	/**
	 * Creates a new NameSurferEntry from a data line as it appears
	 * in the data file.  Each line begins with the name, which is
	 * followed by integers giving the rank of that name for each
	 * decade.
	 */
		public NameSurferEntry(String line) {
			StringTokenizer tkn = new StringTokenizer(line);
			namesData = new String[NDECADES + 1];
			for (int i = 0; i < NDECADES + 1; i++) {
				namesData[i] = tkn.nextToken();
			}
		}
	
	/**
	 * Returns the name associated with this entry.
	 */
		public String getName() {
			return namesData[0];
		}
	
	/**
	 * Returns the rank associated with an entry for a particular
	 * decade.  The decade value is an integer indicating how many
	 * decades have passed since the first year in the database,
	 * which is given by the constant START_DECADE.  If a name does
	 * not appear in a decade, the rank value is 0.
	 */
		public int getRank(int decade) {
			return Integer.parseInt(namesData[decade]);
		}
		
	}