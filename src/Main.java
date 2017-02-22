import java.io.File;
import java.io.PrintWriter;

public class Main {
	public static void main(String [] args) {
		if(args.length != 3) {
			System.out.println("Program needs 3 files as argument,"
					+ " Terminating!!");
			System.exit(-1);
		}
		try {
			
			String inputFile   = args[0],
				   outputFile1 = args[1],
				   outputFile2 = args[2];
			PrintWriter printToFile = new PrintWriter(new File(outputFile1));
			EightConAlg eightConAlg = new EightConAlg(inputFile);
			eightConAlg.zeroFramed();
			eightConAlg.loadImage(inputFile);
			eightConAlg.EightConCC_Pass1();
			eightConAlg.prettyPrint(printToFile, 1);

			eightConAlg.EightConCC_Pass2();
			eightConAlg.prettyPrint(printToFile, 2);
	
			eightConAlg.manageEQAry();
	
			printToFile.flush();
			printToFile.close();
		
		} catch(Exception exc) {
			System.out.println(exc);
		}
	}
}
