import acm.program.ConsoleProgram;

	public class PythagoreanTheorem extends ConsoleProgram {
		
		public void run() {
			
			int x = readInt ("Enter Adjacent: ");
			int y = readInt ("Enter Opposite: ");
			print ("Hypotenuse is: " + hypotenuse(x, y));
			
		}
		
		private double hypotenuse(int a, int b) {
			return Math.sqrt(a*a + b*b);
		}
		
	}
