import java.awt.Color;
import acm.graphics.*;
import acm.program.*;

	public class Pyramid extends GraphicsProgram {
		
		private static final int BRICK_WIDTH = 30;
		private static final int BRICK_HEIGHT = 12;
		private static final int BRICKS_IN_BASE = 14;
			
		public void run() {
			
			for (int j=0; j<BRICKS_IN_BASE; j++) {
				
				for (int i=0; i<(BRICKS_IN_BASE - j); i++) {
					double x = ( getWidth() - (BRICK_WIDTH * (BRICKS_IN_BASE - j)) )/2.0 + i*BRICK_WIDTH;
					double y = getHeight() - j*BRICK_HEIGHT;
					GRect brick = new GRect (x, y, BRICK_WIDTH, BRICK_HEIGHT);
					brick.setColor(Color.BLACK);
					add (brick);
				}
				
			}
			
		}
		
	}