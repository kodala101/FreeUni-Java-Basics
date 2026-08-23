import stanford.karel.*;

	public class problem1 extends Karel {
			
		public void run() {
			goToBeeper();
			pickBeeper();
			goBack();
		}
		
		//post-condition - Karel has taken beeper.
		private void goToBeeper() {
			move();
			move();
			turnRight();
			move();
			turnLeft();
			move();
		}
		
		//post-condition - Karel is on starting point.
		private void goBack() {
			turnLeft();
			turnLeft();
			move();
			turnRight();
			move();
			turnLeft();
			move();
			move();
			turnRight();
			turnRight();
		}
		
		private void turnRight() {
			turnLeft();
			turnLeft();
			turnLeft();
		}
		
	}
