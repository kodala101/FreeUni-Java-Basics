import java.awt.Color;
import acm.graphics.*;
import acm.program.*;

	public class Target extends GraphicsProgram {
		
		private static final int BIG_OVAL_RADIUS = 72;
		private static final double MID_OVAL_RADIUS = 118.8/2.54;   //(1.65*72/2.54)
		private static final double SMALL_OVAL_RADIUS = 54.72/2.54;  //(0.76*72/2.54)
		
		public void run() {
			drawBigOval();
			drawMidOval();
			drawSmallOval();
		}
		
		private void drawBigOval() {
			double x = (getWidth() - 2*BIG_OVAL_RADIUS)/2;
			double y = (getHeight() - 2*BIG_OVAL_RADIUS)/2;
			GOval bigoval = new GOval (x, y, 2*BIG_OVAL_RADIUS, 2*BIG_OVAL_RADIUS);
			bigoval.setColor(Color.BLACK);
			bigoval.setFillColor(Color.RED);
			bigoval.setFilled(true);
			add (bigoval);
		}
		
		private void drawMidOval() {
			double x = (getWidth() - 2*MID_OVAL_RADIUS)/2;
			double y = (getHeight() - 2*MID_OVAL_RADIUS)/2;
			GOval midoval = new GOval (x, y, 2*MID_OVAL_RADIUS, 2*MID_OVAL_RADIUS);
			midoval.setColor(Color.BLACK);
			midoval.setFillColor(Color.WHITE);
			midoval.setFilled(true);
			add (midoval);
		}
		
		private void drawSmallOval() {
			double x = (getWidth() - 2*SMALL_OVAL_RADIUS)/2;
			double y = (getHeight() - 2*SMALL_OVAL_RADIUS)/2;
			GOval smalloval = new GOval (x, y, 2*SMALL_OVAL_RADIUS, 2*SMALL_OVAL_RADIUS);
			smalloval.setColor(Color.BLACK);
			smalloval.setFillColor(Color.RED);
			smalloval.setFilled(true);
			add (smalloval);
		}
		
	}