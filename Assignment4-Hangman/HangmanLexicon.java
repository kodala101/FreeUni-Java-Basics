/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import acm.util.*;
import acmx.export.java.util.ArrayList;

	public class HangmanLexicon {
		
		private int numsOfWords = 0;
		
		private ArrayList lexiconWords;
		
		private RandomGenerator rgen = RandomGenerator.getInstance();
		
		public HangmanLexicon() {
			lexiconWords = new ArrayList();
			try {
				BufferedReader bfrd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
				while (true) {
					String line = bfrd.readLine();
					if (line == null) {
						break;
					}
					lexiconWords.add(line);
					numsOfWords++;
				}
				bfrd.close();
			} catch(IOException e) {
				System.out.println("Sorry, file didn't read.");
			}
		}
	
	/** Returns the number of words in the lexicon. */
		public int getWordCount() {
			return numsOfWords;
		}
	
	/** Returns the word at the specified index. */
		public String getWord() {
			return (String)lexiconWords.get(rgen.nextInt(getWordCount()));
		}   
	}