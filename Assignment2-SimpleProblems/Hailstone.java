import acm.program.ConsoleProgram;

	public class Hailstone extends ConsoleProgram {
		
		public void run() {
			int n = readInt ("Enter Number: ");
			while (n != 1) {
				if (n%2==0) {
					n/=2;
					println (2*n + " is even, so I take half: " + n);
				} else {
					n*=3;
					n+=1;
					println ((n-1)/3 + " is odd, so I make 3n+1: " + n);
				}
			}
		}
		
	}