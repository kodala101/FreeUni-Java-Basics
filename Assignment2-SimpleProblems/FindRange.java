import acm.program.ConsoleProgram;

	public class FindRange extends ConsoleProgram {
		
		private static final int NUMBER = 0;
		private static final double SMALLEST = Math.pow(-2,31);
		
		public void run() {
			
			println ("This program finds the largest and the smallest numbers.");
			double max = SMALLEST;
			double min = SMALLEST;
			
			while (true) {
				int n = readInt ("number: ");
				if (n >= max && n != NUMBER) {
					max = n;
					if (min == SMALLEST) {min = n;}  //because of this "if" statement, only the first input (instead of the all "n>=max") will be assigned to the and "min" variables.
				} else if (n <= min && n != NUMBER) {
					min = n;
				} else if (n == NUMBER && max == SMALLEST && min == SMALLEST) {  // "max==SMALLEST and min==SMALLEST" means that n==NUMBER is the first value to be inputted, so we need to print specific message. 
					println ("shesabamisi mesiji");
					break;
				} else if (n == NUMBER && max != SMALLEST && min != SMALLEST) {
					println ("The largest number is: " + (int) max);
					println ("The smallest number is: " + (int) min);
					break;
				}
			}
			
		}
		
	}