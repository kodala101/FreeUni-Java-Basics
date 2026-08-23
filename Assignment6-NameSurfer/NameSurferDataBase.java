/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.*;
import java.util.HashMap;

	public class NameSurferDataBase implements NameSurferConstants {
		
		HashMap<String, String> data;
		
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
		public NameSurferDataBase(String filename) {
			data = new HashMap<String, String>();
			try {
				BufferedReader rd = new BufferedReader(new FileReader(filename));
				while (true) {
					String line = rd.readLine();
					if (line == null) break;
					data.put(Name(line), line);
				}
				rd.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
		public NameSurferEntry findEntry(String name) {
			if (data.containsKey(name)) {
				NameSurferEntry entry = new NameSurferEntry(data.get(name));
				return entry;
			}
			return null;
		}
		
		private String Name(String line) {
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == ' ') {
					return line.substring(0, i);
				}
			}
			return null;
		}
		
	}