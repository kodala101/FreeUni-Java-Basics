/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

	public class HangmanCanvas extends GCanvas {
		
		private static final int SCAFFOLD_HEIGHT = 360;
		private static final int BEAM_LENGTH = 144;
		private static final int ROPE_LENGTH = 18;
		private static final int HEAD_RADIUS = 36;
		private static final int BODY_LENGTH = 144;
		private static final int ARM_OFFSET_FROM_HEAD = 28;
		private static final int UPPER_ARM_LENGTH = 72;
		private static final int LOWER_ARM_LENGTH = 44;
		private static final int HIP_WIDTH = 36;
		private static final int LEG_LENGTH = 108;
		private static final int FOOT_LENGTH = 28;
		
		private GLabel currentWord;
		private GLabel incorrectSymbol;
	
	/** Resets the display so that only the scaffold appears */
		public void reset() {
			drawScaffold();
			drawBeam();
			drawRope();
		}
		
		private void drawScaffold() {
			double x1 = getWidth()/2 - BEAM_LENGTH;
			double y1 = getHeight()/2 + 1.3 * LEG_LENGTH;
			double x2 = x1;
			double y2 = y1 - SCAFFOLD_HEIGHT;
			GLine scaffold = new GLine(x1, y1, x2, y2);
			add(scaffold);
		}
		
		private void drawBeam() {
			double x1 = getWidth()/2 - BEAM_LENGTH;
			double y1 = getHeight()/2 + 1.3 * LEG_LENGTH - SCAFFOLD_HEIGHT;
			double x2 = getWidth()/2;
			double y2 = y1;
			GLine beam = new GLine(x1, y1, x2, y2);
			add(beam);
		}
		
		private void drawRope() {
			double x1 = getWidth()/2;
			double y1 = getHeight()/2 + 1.3 * LEG_LENGTH - SCAFFOLD_HEIGHT;
			double x2 = x1;
			double y2 = y1 + ROPE_LENGTH;
			GLine rope = new GLine(x1, y1, x2, y2);
			add(rope);
		}
	
	/**
	 * Updates the word on the screen to correspond to the current
	 * state of the game.  The argument string shows what letters have
	 * been guessed so far; unguessed letters are indicated by hyphens.
	 */
		public void displayWord(String word) {
			currentWord = new GLabel("");
			add(currentWord, getWidth()/2 - BEAM_LENGTH, getHeight()/2 + 1.5 * LEG_LENGTH);
			currentWord.setLabel(word);
			currentWord.setFont("Consolas-18");
		}
	
	/**
	 * Updates the display to correspond to an incorrect guess by the
	 * user.  Calling this method causes the next body part to appear
	 * on the scaffold and adds the letter to the list of incorrect
	 * guesses that appears at the bottom of the window.
	 */
		public void noteIncorrectGuess(char letter, int tries) {
			
			if (tries == 7) {
				double x1 = getWidth()/2 - HEAD_RADIUS;
				double y1 = getHeight()/2 + 1.3 * LEG_LENGTH - SCAFFOLD_HEIGHT + ROPE_LENGTH;
				GOval head = new GOval(x1, y1, 2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
				add(head);
				
				incorrectSymbol = new GLabel(Character.toString(letter));
				add(incorrectSymbol, getWidth()/2 - BEAM_LENGTH + (8 - tries) * 10, getHeight()/2 + 1.5 * LEG_LENGTH + 25);
			} else if (tries == 6) {
				double a1 = getWidth()/2;
				double b1 = getHeight()/2.0 + 1.3 * LEG_LENGTH - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS;
				double a2 = a1;
				double b2 = b1 + BODY_LENGTH;
				GLine body = new GLine(a1, b1, a2, b2);
				add(body);
				
				incorrectSymbol = new GLabel(Character.toString(letter));
				add(incorrectSymbol, getWidth()/2 - BEAM_LENGTH + (8 - tries) * 10, getHeight()/2 + 1.5 * LEG_LENGTH + 25);
			} else if (tries == 5) {
				double z1 = getWidth()/2;
				double j1 = getHeight()/2 + 1.3 * LEG_LENGTH - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD; //an aq wtf
				double z2 = z1 - UPPER_ARM_LENGTH;
				double j2 = j1;
				GLine upperLeftArm = new GLine(z1, j1, z2, j2);
				add(upperLeftArm);
				
				double q1 = z2;
				double r1 = j2;
				double q2 = q1;
				double r2 = j1 + LOWER_ARM_LENGTH;;
				GLine lowerLeftArm = new GLine(q1, r1, q2, r2);
				add(lowerLeftArm);
				
				incorrectSymbol = new GLabel(Character.toString(letter));
				add(incorrectSymbol, getWidth()/2 - BEAM_LENGTH + (8 - tries) * 10, getHeight()/2 + 1.5 * LEG_LENGTH + 25);
			} else if (tries == 4) {
				double z1 = getWidth()/2;
				double j1 = getHeight()/2 + 1.3 * LEG_LENGTH - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
				double z2 = z1 + UPPER_ARM_LENGTH;
				double j2 = j1;
				GLine upperRightArm = new GLine(z1, j1, z2, j2);
				add(upperRightArm);
				
				double q1 = z2;
				double r1 = j2;
				double q2 = q1;
				double r2 = j1 + LOWER_ARM_LENGTH;;
				GLine lowerRightArm = new GLine(q1, r1, q2, r2);
				add(lowerRightArm);
				
				incorrectSymbol = new GLabel(Character.toString(letter));
				add(incorrectSymbol, getWidth()/2 - BEAM_LENGTH + (8 - tries) * 10, getHeight()/2 + 1.5 * LEG_LENGTH + 25);
			} else if (tries == 3) {
				double k1 = getWidth()/2;
				double p1 = getHeight()/2.0 + 1.3 * LEG_LENGTH - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
				double k2 = k1 - HIP_WIDTH;
				double p2 = p1;
				GLine leftHip = new GLine(k1, p1, k2, p2);
				add(leftHip);
				
				double o1 = k2;
				double m1 = p2;
				double o2 = k2;
				double m2 = p2 + LEG_LENGTH;
				GLine leftLeg = new GLine(o1, m1, o2, m2);
				add(leftLeg);
				
				incorrectSymbol = new GLabel(Character.toString(letter));
				add(incorrectSymbol, getWidth()/2 - BEAM_LENGTH + (8 - tries) * 10, getHeight()/2 + 1.5 * LEG_LENGTH + 25);
			} else if (tries == 2) {
				double k1 = getWidth()/2;
				double p1 = getHeight()/2.0 + 1.3 * LEG_LENGTH - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
				double k2 = k1 + HIP_WIDTH;
				double p2 = p1;
				GLine rightHip = new GLine(k1, p1, k2, p2);
				add(rightHip);
				
				double t1 = k2;
				double h1 = p2;
				double t2 = k2;
				double h2 = p2 + LEG_LENGTH;
				GLine rightLeg = new GLine(t1, h1, t2, h2);
				add(rightLeg);
				
				incorrectSymbol = new GLabel(Character.toString(letter));
				add(incorrectSymbol, getWidth()/2 - BEAM_LENGTH + (8 - tries) * 10, getHeight()/2 + 1.5 * LEG_LENGTH + 25);
			} else if (tries == 1) {
				double n1 = getWidth()/2 - HIP_WIDTH;
				double f1 = getHeight()/2.0 + 1.3 * LEG_LENGTH - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
				double n2 = getWidth()/2 - HIP_WIDTH - FOOT_LENGTH;
				double f2 = f1;
				GLine leftFoot = new GLine(n1, f1, n2, f2);
				add(leftFoot);
				
				incorrectSymbol = new GLabel(Character.toString(letter));
				add(incorrectSymbol, getWidth()/2 - BEAM_LENGTH + (8 - tries) * 10, getHeight()/2 + 1.5 * LEG_LENGTH + 25);
			} else if (tries == 0) {
				double n1 = getWidth()/2 + HIP_WIDTH;
				double f1 = getHeight()/2.0 + 1.3 * LEG_LENGTH - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
				double n2 = getWidth()/2 + HIP_WIDTH + FOOT_LENGTH;
				double f2 = f1;
				GLine leftFoot = new GLine(n1, f1, n2, f2);
				add(leftFoot);
				
				incorrectSymbol = new GLabel(Character.toString(letter));
				add(incorrectSymbol, getWidth()/2 - BEAM_LENGTH + (8 - tries) * 10, getHeight()/2 + 1.5 * LEG_LENGTH + 25);
			}
		}
	
	}