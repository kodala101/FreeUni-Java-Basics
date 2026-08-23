/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.*;

	public class Breakout extends GraphicsProgram {
	
	/** Width and height of application window in pixels */
		public static final int APPLICATION_WIDTH = 400;
	
	/** Dimensions of game board (usually the same) */
		private static final int WIDTH = APPLICATION_WIDTH;
	
	/** Dimensions of the paddle */
		private static final int PADDLE_WIDTH = 60;
		private static final int PADDLE_HEIGHT = 10;
	
	/** Offset of the paddle up from the bottom */
		private static final int PADDLE_Y_OFFSET = 30;
	
	/** Number of bricks per row */
		private static final int NBRICKS_PER_ROW = 10;
	
	/** Number of rows of bricks */
		private static final int NBRICK_ROWS = 10;
	
	/** Separation between bricks */
		private static final int BRICK_SEP = 4;
	
	/** Width of a brick */
		private static final int BRICK_WIDTH =
		  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;
	
	/** Height of a brick */
		private static final int BRICK_HEIGHT = 8;
	
	/** Radius of the ball in pixels */
		private static final int BALL_RADIUS = 10;
	
	/** Offset of the top brick row from the top */
		private static final int BRICK_Y_OFFSET = 70;
	
	/** Number of turns */
		private static final int NTURNS = 3;
		
		private RandomGenerator rgen = RandomGenerator.getInstance();
		
		private GRect paddle;
		private GOval ball;
		private double vx;
		private double vy;
		
		public void run() {
			addMouseListeners();
			createDesign();
			startPlaying();
		}
		
		private void createDesign() {
			createBricks();
			createPaddle();
			createBall();
		}
		
		private void createBricks() {
			for (int i = 0; i < NBRICK_ROWS; i++) {
				for (int j = 0; j < NBRICKS_PER_ROW; j++) {
					double x = ( WIDTH - NBRICKS_PER_ROW * BRICK_WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP ) / 2.0 + j * (BRICK_WIDTH + BRICK_SEP);
					double y = BRICK_Y_OFFSET + i * (BRICK_HEIGHT + BRICK_SEP);
					GRect brick = new GRect (BRICK_WIDTH, BRICK_HEIGHT);
					add (brick, x, y);
					if (i==0 || i==1) {
						brick.setFillColor(Color.RED);
						brick.setColor(Color.RED);
						brick.setFilled(true);
					}
					if (i==2 || i==3) {
						brick.setFillColor(Color.ORANGE);
						brick.setColor(Color.ORANGE);
						brick.setFilled(true);
					}
					if (i==4 || i==5) {
						brick.setFillColor(Color.YELLOW);
						brick.setColor(Color.YELLOW);
						brick.setFilled(true);
					}
					if (i==6 || i==7) {
						brick.setFillColor(Color.GREEN);
						brick.setColor(Color.GREEN);
						brick.setFilled(true);
					}
					if (i==8 || i==9) {
						brick.setFillColor(Color.CYAN);
						brick.setColor(Color.CYAN);
						brick.setFilled(true);
					}
				}
			}
		}
		
		private void createPaddle() {
			paddle = new GRect (PADDLE_WIDTH, PADDLE_HEIGHT);
			double x = (WIDTH - PADDLE_WIDTH)/2.0;
			double y = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
			paddle.setFilled(true);
			add(paddle, x, y);
		}
		
		public void mouseMoved(MouseEvent e) {
			double paddleMovedX = e.getX() - PADDLE_WIDTH/2;
			double paddleMovedY = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;;
			if (e.getX() < PADDLE_WIDTH/2) {  //this if statement keeps the paddle within the left and right bounds.
				paddle.setLocation(0, paddleMovedY);
			} else if (e.getX() > WIDTH - PADDLE_WIDTH/2) {
				paddle.setLocation(WIDTH - PADDLE_WIDTH, paddleMovedY);
			} else {
				paddle.setLocation(paddleMovedX, paddleMovedY);
			}
		}
		
		private void createBall() {
			double ballX = WIDTH/2 - BALL_RADIUS;
			double ballY = getHeight()/2 - BALL_RADIUS;
			ball = new GOval (2 * BALL_RADIUS, 2 * BALL_RADIUS);
			ball.setFilled(true);
			add(ball, ballX, ballY);
		}
		
		private void startPlaying() {
			int turns = NTURNS;
			vy = 3;
			vx = rgen.nextDouble(1.0, 3.0);
			if (rgen.nextBoolean()) vx = -vx; 
			while (true) {
				if ( ball.getX() <= 0 || ball.getX() >= (WIDTH - 2 * BALL_RADIUS) && getCollidingObject() == null && turns != 0 ) {
					vx = -vx;
				} else if ( ball.getY() <= 0 || getCollidingObject() == paddle && turns != 0 ) {
					vy = -vy;	
				} else if ( getCollidingObject() != paddle & getCollidingObject() != null && turns != 0 ) {
					remove(getCollidingObject());
					vy = -vy;
				} else if ( ball.getY() >= getHeight() - 2 * BALL_RADIUS && turns != 0 ) {
					turns -=1;
					ball.setLocation(WIDTH/2 - BALL_RADIUS, getHeight()/2 - BALL_RADIUS);
					pause(500);
				} else if (turns == 0) {
					break;
				}
				if (turns != 0) {
					ball.move(vx, vy);
					pause(10);
				}
			}
		}
		
		//this method returns paddle if any of the ball corners touch the paddle and returns relevant object for another situations
		private GObject getCollidingObject() {
			if ( getElementAt(ball.getX(), ball.getY()) == paddle || 
				 getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) == paddle || 
				 getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) == paddle || 
				 getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS) == paddle 
			   ) {
				return paddle;  
			} else if ( getElementAt(ball.getX(), ball.getY()) != null && getElementAt(ball.getX(), ball.getY()) != paddle ) {
				
				return getElementAt(ball.getX(), ball.getY());
				
			} else if ( getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) != null && getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) != paddle ) {
				
				return (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()));
				
			} else if ( getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) != null && getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) != paddle ) {     
				
				return ( getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) );
				
			} else if ( getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS) != null && getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS) != paddle ) {
				
				return (getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS));
				
			}
			return null;
		}
			
	}
	
	