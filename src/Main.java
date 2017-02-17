
public class Main {
	public static void main(String [] args) {
		if(args.length != 4) {
			System.out.println("Program needs 4 files as argument,"
					+ " Terminating!!");
			System.exit(-1);
		}
		
		String inputFile   = args[0],
			   outputFile1 = args[1],
			   outputFile2 = args[2],
			   outputFile3 = args[3];
		EightConAlg eightConAlg = new EightConAlg(inputFile, outputFile1,
				                       outputFile2, outputFile3);
				                            
	}
}
