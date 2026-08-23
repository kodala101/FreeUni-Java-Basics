/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.ArrayList;
import java.util.Arrays;
import acm.io.*;
import acm.program.*;
import acm.util.*;

	public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
		/* Private instance variables */
		private int nPlayers;
		private String[] playerNames;
		private YahtzeeDisplay display;
		private RandomGenerator rgen = new RandomGenerator();
		private int[] values;
		private int category;
		private int[][] table;
 
		public static void main(String[] args) {
			new Yahtzee().start(args);
		}
	
		public void run() {
			IODialog dialog = getDialog();
			nPlayers = dialog.readInt("Enter number of players");
			playerNames = new String[nPlayers];
			for (int i = 1; i <= nPlayers; i++) {
				playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
			}
			display = new YahtzeeDisplay(getGCanvas(), playerNames);
	
			table = new int[N_CATEGORIES][nPlayers];
			for (int m = 0; m < N_CATEGORIES; m++) {
				for (int n = 0; n < nPlayers; n++) {
					table[m][n] = -1;
				}
			}
			
			for (int r = 0; r < nPlayers; r++) {
				table[6][r] = 0;
				table[7][r] = 0;
				table[15][r] = 0;
				table[16][r] = 0;
			}

			playGame();
		}
	
		private void playGame() {
			for (int i = 0; i < nPlayers * N_SCORING_CATEGORIES; i++) {
				display.printMessage(playerNames[i % nPlayers] + "'s turn! Click \"Roll Dice\" button to roll the dice.");
				getDiceValues(i);
				choseCategory(i);
				checkAndfillCategory(i);
			}
			total();
			winner();
		}
	
		private int[] getDiceValues(int i) {
			values = new int[N_DICE];
			for (int j = 0; j < N_DICE; j++) {
				values[j] = rgen.nextInt(1, 6);
			}
			display.waitForPlayerToClickRoll(i % nPlayers + 1);
			display.displayDice(values);
			for (int h = 0; h < 2; h++) {
				display.printMessage("Select the dice you wish to re-roll and and click \"Roll Again\".");
				display.waitForPlayerToSelectDice();
				for (int k = 0; k < N_DICE; k++) {
					if (display.isDieSelected(k)) {
						values[k] = rgen.nextInt(1, 6);
					}
				}
				display.displayDice(values);
			}
			return values;
		}
	
		private void choseCategory(int i) {
			display.printMessage("Select a category for this roll");
			category = display.waitForPlayerToSelectCategory();
			while (true) {
				if (table[category - 1][i % nPlayers] != -1) {
					category = display.waitForPlayerToSelectCategory();
				}
				if (table[category - 1][i % nPlayers] == -1) {
					break;
				}
			}
		}
	
		private void checkAndfillCategory(int i) {
				switch (category) {
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
					int score = 0;
					for (int q = 0; q < N_DICE; q++) {
						if (values[q] == category) score += values[q]; 
					}
					display.updateScorecard(category, i % nPlayers + 1, score);
					table[category - 1][i % nPlayers] = score;
					upperScoreAndBonus(i);
					updateLowerScore(i);
					break;
				case 15:
					int score15 = 0;
					for (int q = 0; q < N_DICE; q++) score15 += values[q];
					display.updateScorecard(category, i % nPlayers + 1, score15);
					table[category - 1][i % nPlayers] = score15;
					upperScoreAndBonus(i);
					updateLowerScore(i);
					break;
				case 9:
					if (checkThreeSimilar()) {
						int score9 = 0;
						for (int s = 0; s < N_DICE; s++) {
							score9 += values[s];
						}
						display.updateScorecard(category, i % nPlayers + 1, score9);
						table[category - 1][i % nPlayers] = score9;
					} else {
						display.updateScorecard(category, i % nPlayers + 1, 0);
						table[category - 1][i % nPlayers] = 0;
					}
					upperScoreAndBonus(i);
					updateLowerScore(i);
					break;
				case 10:
					if (checkForeSimilar()) {
						int score10 = 0;
						for (int s = 0; s < N_DICE; s++) {
							score10 += values[s];
						}
						display.updateScorecard(category, i % nPlayers + 1, score10);
						table[category - 1][i % nPlayers] = score10;
					} else {
						display.updateScorecard(category, i % nPlayers + 1, 0);
						table[category - 1][i % nPlayers] = 0;
					}
					upperScoreAndBonus(i);
					updateLowerScore(i);
					break;
				case 11:
					if (checkFullHouse()) {
						display.updateScorecard(category, i % nPlayers + 1, 25);
						table[category - 1][i % nPlayers] = 25;
					} else {
						display.updateScorecard(category, i % nPlayers + 1, 0);
						table[category - 1][i % nPlayers] = 0;
					}
					upperScoreAndBonus(i);
					updateLowerScore(i);
					break;
				case 12:
					if (checkSmallStraight()) {
						display.updateScorecard(category, i % nPlayers + 1, 30);
						table[category - 1][i % nPlayers] = 30;
					} else {
						display.updateScorecard(category, i % nPlayers + 1, 0);
						table[category - 1][i % nPlayers] = 0;
					}
					upperScoreAndBonus(i);
					updateLowerScore(i);
					break;
				case 13:
					if (checkBigStraight()) {
						display.updateScorecard(category, i % nPlayers + 1, 40);
						table[category - 1][i % nPlayers] = 40;
					} else {
						display.updateScorecard(category, i % nPlayers + 1, 0);
						table[category - 1][i % nPlayers] = 0;
					}
					upperScoreAndBonus(i);
					updateLowerScore(i);
					break;
				case 14:
					if (checkYatzee()) {
						display.updateScorecard(category, i % nPlayers + 1, 50);
						table[category - 1][i % nPlayers] = 50;
					} else {
						display.updateScorecard(category, i % nPlayers + 1, 0);
						table[category - 1][i % nPlayers] = 0;
					}
					upperScoreAndBonus(i);
					updateLowerScore(i);
					break;
				}
		}
		
		private boolean checkThreeSimilar() {
			for (int i = 0; i < N_DICE - 2; i++) {
				for (int j = i + 1; j < N_DICE - 1; j++) {
					for (int k = j + 1; k < N_DICE; k++) {
						if (values[i] == values[j] && values[j] == values[k]) {
							return true;
						}
					}
				}
			}
			return false;
		}
		
		private boolean checkForeSimilar() {
			for (int i = 0; i < N_DICE - 3; i++) {
				for (int j = i + 1; j < N_DICE - 2; j++) {
					for (int k = j + 1; k < N_DICE - 1; k++) {
						for (int f = k + 1; f < N_DICE; f++) {
							if (values[i] == values[j] && values[j] == values[k] && values[k] == values[f]) {
								return true;
							}
						}
					}
				}
			}
			return false;
		}
		
		private boolean checkFullHouse() {
			if (checkThreeSimilar() && qountQuontityOfDiffNums(values) == 12) {
				return true;
			}
			return false;
		}
		
		private boolean checkSmallStraight() {
			int[] straight1 = {1, 2, 3, 4};
			int[] straight2 = {2, 3, 4, 5};
			int[] straight3 = {3, 4, 5, 6};
			ArrayList<Integer> arraylist1 = new ArrayList<>();
			ArrayList<Integer> arraylist2 = new ArrayList<>();
			for (int i = 0; i < values.length; i++) {
				arraylist1.add(values[i]);
				arraylist2.add(values[i]);
			}
			for (int j = 0; j < arraylist1.size(); j++) {
				arraylist1.remove(j);
				int[] array = new int[arraylist1.size()];
				for (int z = 0; z < arraylist1.size(); z++) {
					array[z] = arraylist1.get(z);
				}
				Arrays.sort(array);
				if (Arrays.equals(array, straight1) || Arrays.equals(array, straight2) || Arrays.equals(array, straight3)) {
					return true;
				}
				arraylist1.add(j, arraylist2.get(j));
			}
			return false;
		}
		
		private boolean checkBigStraight() {
			if (qountQuontityOfDiffNums(values) == 20) {
				return true;
			}
			return false;
		}
		
		private boolean checkYatzee() {
			if (qountQuontityOfDiffNums(values) == 0) {
				return true;
			}
			return false;
		}
		
		private int qountQuontityOfDiffNums(int[] array) {
			int n = 0;
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array.length; j++) {
					if (array[i] != array[j]) {
						n++;
					}
				}
			}
			return n;
		}
		
		private void upperScoreAndBonus(int i) {
			if (isUpperScoreCompleted(i)) {
				int upperScore = 0;
				for (int h = 0; h < UPPER_SCORE - 1; h++) {
					upperScore += table[h][i % nPlayers]; 
				}
				display.updateScorecard(UPPER_SCORE, i % nPlayers + 1, upperScore);
				table[UPPER_SCORE - 1][i % nPlayers] = upperScore;
				if (upperScore > 63) {
					display.updateScorecard(UPPER_BONUS, i % nPlayers + 1, 35);
					table[UPPER_BONUS - 1][i % nPlayers] = 35;
				} else {
					display.updateScorecard(UPPER_BONUS, i % nPlayers + 1, 0);
					table[UPPER_BONUS - 1][i % nPlayers] = 0;
				}
			}
		}
		
		private boolean isUpperScoreCompleted(int i) {
			for (int h = 0; h < UPPER_SCORE - 1; h++) {
				if (table[h][i % nPlayers] == -1) {
					return false;
				}
			}
			return true;
		}
		
		private void updateLowerScore(int i) {
			if (isLowerScoreCompleted(i)) {
				int lowerScore = 0;
				for (int h = THREE_OF_A_KIND - 1; h < LOWER_SCORE - 1; h++) {
					lowerScore += table[h][i % nPlayers];
				}
				display.updateScorecard(LOWER_SCORE, i % nPlayers + 1, lowerScore);
				table[LOWER_SCORE - 1][i % nPlayers] = lowerScore;
			}
		}
		
		private boolean isLowerScoreCompleted(int i) {
			for (int h = THREE_OF_A_KIND - 1; h < LOWER_SCORE - 1; h++) {
				if (table[h][i % nPlayers] == -1) {
					return false;
				}
			}
			return true;
		}
		
		private void total() {
			for (int i = 1; i <= nPlayers; i++) {
				display.updateScorecard(TOTAL, i, table[UPPER_SCORE - 1][i - 1] + table[LOWER_SCORE - 1][i - 1] + table[UPPER_BONUS - 1][i - 1]);
				table[TOTAL - 1][i - 1] = table[UPPER_SCORE - 1][i - 1] + table[LOWER_SCORE - 1][i - 1] + table[UPPER_BONUS - 1][i - 1];
			}
		}
		
		private void winner() {
			int sth = 0;
			for (int i = 1; i <= nPlayers; i++) {
				for (int j = 1; j <= nPlayers; j++) {
					if (table[TOTAL - 1][i - 1] >= table[TOTAL - 1][j - 1]) {
						sth++;
					}
				}
				if (sth == nPlayers) {
					display.printMessage("Congratulations," + playerNames[i - 1] + ", you're the winner with a total score of " + table[TOTAL - 1][i - 1] + "!");
				}
				sth = 0;
			}
		}
	
	}