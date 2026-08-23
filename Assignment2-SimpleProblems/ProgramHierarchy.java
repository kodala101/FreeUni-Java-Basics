import acm.graphics.*;
import acm.program.*;

	public class ProgramHierarchy extends GraphicsProgram {
		
		private static final int RECT_WIDTH = 150;
		private static final int RECT_HEIGHT = 60;
		private static final int MIDDLE_LINE_LENGTH = 55;
		private static final int LENGTH_BETWEEN_RECTS = 40;
		
		public void run() {
			programRect();
			graphicsProgramRect();
			consoleProgramRect();
			dialogProgramRect();
			drawLines();
			addLabels();
		}
		
		private void programRect() {
			double x = ( getWidth() - RECT_WIDTH )/2;
			double y = ( getHeight()/2 - MIDDLE_LINE_LENGTH/2 - RECT_HEIGHT );
			GRect programrect = new GRect (x, y, RECT_WIDTH, RECT_HEIGHT);
			add (programrect);
		}
		
		private void graphicsProgramRect() {
			double x = ( getWidth() - 3*RECT_WIDTH - 2*LENGTH_BETWEEN_RECTS )/2;
			double y = ( getHeight()/2 + MIDDLE_LINE_LENGTH/2 );
			GRect graphicsrect = new GRect (x, y, RECT_WIDTH, RECT_HEIGHT);
			add (graphicsrect);
		}
		
		private void consoleProgramRect() {
			double x = ( getWidth() - RECT_WIDTH )/2;
			double y = ( getHeight()/2 + MIDDLE_LINE_LENGTH/2 );
			GRect consolerect = new GRect (x, y, RECT_WIDTH, RECT_HEIGHT);
			add (consolerect);
		}
		
		private void dialogProgramRect() {
			double x = ( getWidth() - RECT_WIDTH )/2 + LENGTH_BETWEEN_RECTS + RECT_WIDTH;
			double y = ( getHeight()/2 + MIDDLE_LINE_LENGTH/2 );
			GRect dialogrect = new GRect (x, y, RECT_WIDTH, RECT_HEIGHT);
			add (dialogrect);
		}
		
		private void drawLines() {
			
			double x1 = getWidth()/2;
			double y1 = getHeight()/2 - MIDDLE_LINE_LENGTH/2;
			double x2 = getWidth()/2;
			double y2 = getHeight()/2 + MIDDLE_LINE_LENGTH/2;
			add ( new GLine (x1, y1, x2, y2) );
			
			double x3 = x1;
			double y3 = y1;
			double x4 = x1 - RECT_WIDTH - LENGTH_BETWEEN_RECTS;
			double y4 = y2;
			add ( new GLine (x3, y3, x4, y4) );
			
			double x5 = x1;
			double y5 = y1;
			double x6 = x1 + RECT_WIDTH + LENGTH_BETWEEN_RECTS;
			double y6 = y2;
			add ( new GLine (x5, y5, x6, y6) );
			
		}
		
		private void addLabels() {
			
			GLabel program = new GLabel ("Program");
			double ascent1 = program.getAscent();
			double width1 = program.getWidth();
			double x1 = ( getWidth() - width1 )/2;
			double y1 = getHeight()/2 - MIDDLE_LINE_LENGTH/2 - RECT_HEIGHT + (RECT_HEIGHT + ascent1)/2;
			add (program, x1, y1);
			
			GLabel graphics = new GLabel ("Graphics Program");
			double ascent2 = ascent1;
			double width2 = graphics.getWidth();
			double x2 = ( getWidth() - 3*RECT_WIDTH - 2*LENGTH_BETWEEN_RECTS )/2 + (RECT_WIDTH - width2)/2;
			double y2 = getHeight()/2 + MIDDLE_LINE_LENGTH/2 + (RECT_HEIGHT + ascent2)/2;
			add (graphics, x2, y2);
			
			GLabel console = new GLabel ("Console Program");
			double width3 = console.getWidth();
			double x3 = ( getWidth() - RECT_WIDTH )/2 + (RECT_WIDTH - width3)/2;
			double y3 = y2;
			add (console, x3, y3);
			
			GLabel dialog = new GLabel ("Dialog Program");
			double width4 = dialog.getWidth();
			double x4 = getWidth()/2 + RECT_WIDTH/2 + LENGTH_BETWEEN_RECTS + (RECT_WIDTH - width4)/2;
			double y4 = y3;
			add (dialog, x4, y4);
			
		}
		
	}