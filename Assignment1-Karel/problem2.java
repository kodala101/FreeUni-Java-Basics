import stanford.karel.*;

	public class problem2 extends Karel{
		
		public void run() {
			while (frontIsClear()) {
				fillColumn();
				quadrupleMove();
			}
			fillColumn();
		}
		
		private void quadrupleMove() {
			move();
			move();
			move();
			move();
		}
		
		private void turnAround() {
			turnLeft();
			turnLeft();
		}
		
		//post-condition - column is filled and Karel is on the bottom of the column, facing east.
		private void fillColumn() {
			turnLeft();
			while (frontIsClear()) {
				if (noBeepersPresent()) {
					putBeeper();
				}
				move();
			}
			if (noBeepersPresent()) {
				putBeeper();
			}
			turnAround();
			while (frontIsClear()) {
				move();
			}
			turnLeft();
		}
		
	}
