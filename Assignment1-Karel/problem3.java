import stanford.karel.*;

	public class problem3 extends Karel{
		
		public void run() {
			fillTwoRows(); //Karel fills two rows and moves on the first square of the third row. 
			fillLastRow(); //Karel completes last row and fixes each cases.
		}
		
		private void fillTwoRows() {
			
			while (leftIsClear()) {
				putBeeper();
				if (frontIsClear()) {
					move();
					fillBottom();
				}
				if (frontIsClear()) {
					move();
				} else {
					turnAround();
					while (frontIsClear()) {
						move();
					}
					turnRight();
					if (frontIsClear()) {
						move();
					} else {
						turnLeft();
					}
					if (frontIsClear()) {
						move();
					} else {
						turnLeft();
					}
					turnRight();
				}
			}
			
		}
		
		private void fillLastRow() {
			
			while (frontIsClear()) {
				putBeeper();
				if (frontIsClear()) {
					move();
				}
				if (frontIsClear()) {
					move();
				}
			}
			goBack();
			if (beepersPresent()) {
				if (frontIsClear()) {
					move();
				}
			} else {
				move();
				putBeeper();
			}
			
		}
		
		//pre-condition - Karel fills square above and comes down.
		private void fillBottom() {
			turnLeft();
			move();
			putBeeper();
			turnAround();
			move();
			turnLeft();
		}
		
		private void turnAround() {
			turnLeft();
			turnLeft();
		}
		
		private void turnRight() {
			turnLeft();
			turnLeft();
			turnLeft();
		}
		
		private void goBack() {
			turnAround();
			if (frontIsClear()) {
				move();
			} else {
				putBeeper();
			}
			turnAround();
		}
				
	}