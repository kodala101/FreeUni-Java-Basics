/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

	public class NameSurferGraph extends GCanvas
		implements NameSurferConstants, ComponentListener {
	
		ArrayList<NameSurferEntry> obj = new ArrayList<NameSurferEntry>();
		
		/**
		* Creates a new NameSurferGraph object that displays the data.
		*/
		public NameSurferGraph() {
			addComponentListener(this);
		}
		
		/**
		* Clears the list of name surfer entries stored inside this class.
		*/
		public void clear() {
			obj.clear();
		}
		
		/**
		* Adds a new NameSurferEntry to the list of entries on the display.
		* Note that this method does not actually draw the graph, but
		* simply stores the entry; the graph is drawn by calling update.
		*/
		public void addEntry(NameSurferEntry entry) {
			if (entry != null) obj.add(entry);
		}
		
		/**
		* Updates the display image by deleting all the graphical objects
		* from the canvas and then reassembling the display according to
		* the list of entries. Your application must call update after
		* calling either clear or addEntry; update is also called whenever
		* the size of the canvas changes.
		*/
		public void update() {
			removeAll();
			drawBackground();
			drawGraphs();
		}
		
		/* Implementation of the ComponentListener interface */
		public void componentHidden(ComponentEvent e) { }
		public void componentMoved(ComponentEvent e) { }
		public void componentResized(ComponentEvent e) { update(); }
		public void componentShown(ComponentEvent e) { }
		
		private void drawBackground() {
			horizontalLines();
			verticalLines();
			addLabels();
		}
		
		private void horizontalLines() {
			for (int i = 1; i < NDECADES; i++) {
				double x1 = i * getWidth()/11;    //APLICATION_WIDTH???
				double y1 = 0;
				double x2 = x1;
				double y2 = getHeight();
				GLine line = new GLine(x1, y1, x2, y2);
				add(line);
			}
		}
		
		private void verticalLines() {
			double x1 = 0;
			double y1 = GRAPH_MARGIN_SIZE;
			double x2 = getWidth();
			double y2 = y1;
			GLine line1 = new GLine(x1, y1, x2, y2);
			add(line1);
			
			double a1 = x1;
			double b1 = getHeight() - y1;
			double a2 = x2;
			double b2 = b1;
			GLine line2 = new GLine(a1, b1, a2, b2);
			add(line2);
		}
		
		private void addLabels() {
			for (int i = 0; i < NDECADES; i++) {
				double x1 = i * getWidth()/11 + 2;  // +2unda???
				double y1 = getHeight() - GRAPH_MARGIN_SIZE/4;
				GLabel lbl = new GLabel(Integer.toString(START_DECADE + i * 10));
				add(lbl, x1, y1);
			}
		}
		
		private void drawGraphs() {
			Color cl = null;
			for (int i = 0; i < obj.size(); i++) {
				if (i % 4 == 0) cl = Color.BLACK;
				if (i % 4 == 1) cl = Color.RED;
				if (i % 4 == 2) cl = Color.BLUE;
				if (i % 4 == 3) cl = Color.MAGENTA;
				draw(obj.get(i), cl);
			}
		}
		
		private void draw(NameSurferEntry entry, Color cl) {
			if (entry != null) {
				for (int i = 1; i < NDECADES; i++) {
					double x1 = (i - 1) * getWidth()/11;
					double y1 = entry.getRank(i) * (getHeight() - 2 * GRAPH_MARGIN_SIZE)/MAX_RANK + GRAPH_MARGIN_SIZE;
					if (entry.getRank(i) == 0) y1 += getHeight() - 2 * GRAPH_MARGIN_SIZE;
					double x2 = (i) * getWidth()/11;
					double y2 = entry.getRank(i + 1) * (getHeight() - 2 * GRAPH_MARGIN_SIZE)/MAX_RANK + GRAPH_MARGIN_SIZE;
					if (entry.getRank(i + 1) == 0) y2 += getHeight() - 2 * GRAPH_MARGIN_SIZE;
					GLine ln = new GLine(x1, y1, x2, y2);
					ln.setColor(cl);
					add(ln);
					
					if (entry.getRank(i) != 0) {
						GLabel lb = new GLabel(entry.getName() + " " + entry.getRank(i));
						lb.setColor(cl);
						add(lb, x1 + 2, y1 - 2);
					} else {
						GLabel lb = new GLabel(entry.getName() + "*");
						lb.setColor(cl);
						add(lb, x1 + 2, y1 - 2);
					}
					if (i == NDECADES - 1) {
						GLabel lb = new GLabel(entry.getName() + " " + entry.getRank(i + 1));
						lb.setColor(cl);
						add(lb, x2 + 2, y2 - 2);
					}
				}
			}
		}
		
	}