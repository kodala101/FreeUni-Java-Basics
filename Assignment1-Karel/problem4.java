import stanford.karel.*;

	public class problem4 extends Karel {
		
		public void run() {
			if (leftIsBlocked()) { //only if the world is the size of 1x1. if not, "else" operation is being fulfilled.  
				putBeeper();
			} else {
				findTheMiddle(); 
			    leftOneBeeper();
			}
		}
		
		//pre-condition - the row is fully filled with beepers and on the middle square there are two beepers.
		private void findTheMiddle() {
			putBeeper();
			while (frontIsClear()) {
				move();
			}
			turnAround();
			putBeeper();
			move();
			while (noBeepersPresent()){
				while (noBeepersPresent()) {
					move();
				}
				turnAround();
				move();
				putBeeper();
				move();
			}
			moveBack();
		    putBeeper();
		}
		
		//pre-condition - one beeper in the row, on the middle.
		private void leftOneBeeper() {
			while (frontIsClear()) {
		    	move();
		    }
		    turnAround();
		    while (frontIsClear()) {
		    	pickBeeper();
		    	move();
		    }
		    turnAround();
		    pickBeeper();
		   	while (noBeepersPresent()) {
		    	move();
		   	}
		}
		
		private void turnAround() {
			turnLeft();
			turnLeft();
		}
		
		private void moveBack() {
			turnAround();
			move();
		}
		
	}
