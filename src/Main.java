
public class Main {
	public static void main(String [] args) {
		if(args.length != 1) {
			System.out.println("Program needs 4 files as argument,"
					+ " Terminating!!");
			System.exit(-1);
		}
		try {
		String inputFile   = args[0];
			   //outputFile1 = args[1],
			   //outputFile2 = args[2],
			   //outputFile3 = args[3];
		EightConAlg eightConAlg = new EightConAlg(inputFile);
		eightConAlg.printFunc();
			
		
		} catch(Exception exc) {
			System.out.println(exc);
		}
	}
}
