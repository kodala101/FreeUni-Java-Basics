/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.program.*;

	public class Hangman extends ConsoleProgram {
		
		private HangmanCanvas canvas;
		
		private HangmanLexicon lexicon = new HangmanLexicon(); 
		
		private static final int TRIES = 8;
		
		private String word;  //secret word
		private String playersWord = "";
		private String playersWordPrevious;
		private String character; //entered symbol
		private char ch; //entered symbol, but changed into char type
		private int tries = TRIES;
		private String ifRepeated;
		
		public void init() { 
			canvas = new HangmanCanvas(); 
			add(canvas); 
		} 
	
		public void run() {
			canvas.reset();
			preparationForGame();
			playingGame();
			winOrLose();
		}
		
		private void preparationForGame() {
			word = lexicon.getWord();
			for (int i = 0; i < word.length(); i++) {
				playersWord = playersWord.concat("-");  //get encrypted word for secret word.
			}
//			println(word); //if you want to check what's the secret word.
			println("Welcome to Hangman!");
			println("The word now looks like this: " + playersWord);
			println("You have " + TRIES + " guesses left.");
		}	
		
		private void playingGame() {
			canvas.displayWord(playersWord);
			while (!playersWord.equals(word) && tries != 0) {
				character = readLine("Your guess: ");
				character = character.toUpperCase();
				ch = character.charAt(0);
				playersWordPrevious = playersWord;
				checkAndReplace();
				if (playersWordPrevious.equals(playersWord) && ch >= 'A' && ch <= 'Z' && !ifRepeated.equals("repeated")) {
					tries--;
					canvas.noteIncorrectGuess(ch, tries);
					println("There are no " + ch + "'s in the word.");
					println("You have " + tries + " guesses left.");
				} else if (ch < 'A' || ch > 'Z') {
					println("Please, enter valid symbol.");
				} else if (!playersWordPrevious.equals(playersWord) && ch >= 'A' && ch <= 'Z' && !ifRepeated.equals("repeated")) {
					println("That guess is correct.");
					println("The word now looks like this: " + playersWord);
					canvas.displayWord(playersWord);
				}
			}
		}
		
		//checkAndReplace() method checks if the player has guessed the symbol correctly, 
        //if it is not already guessed and if so, 
		//method replaces relevant dash with entered symbol.
		
		private String checkAndReplace() { 
			ifRepeated = "";
			for (int j = 0; j < word.length(); j++) {
				if (ch == word.charAt(j) && playersWord.charAt(j) == '-') {  
					playersWord = replace(playersWord, ch, j);
				} else if (ch == word.charAt(j) && playersWord.charAt(j) != '-') {
					ifRepeated = "repeated";									  
					return ifRepeated;
				}
			}
			return playersWord;
		}
		
		private String replace(String string, char ch, int k) { 
			string = string.substring(0, k).concat(string.substring(k+1));
			string = string.substring(0, k).concat(Character.toString(ch)).concat(string.substring(k));
			return string;
		}
		
		private void winOrLose() {
			if (tries == 0) {
				println("You are completely hung.");
				println("The word was: " + word);
				println("You lose.");
			} else {
				println("You guessed the word: " + playersWord);
				println("You win.");
			}
		}
		
	}	