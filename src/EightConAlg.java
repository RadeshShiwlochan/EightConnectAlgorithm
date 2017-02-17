import java.util.Scanner;
import java.io.*;

public class EightConAlg {
	private int numRows;
	private int numCols;
	private int minVal;
	private int maxVal;
	private int newMin;
	private int newMax;
	private int newLabel;
	private int zeroFramedAry[][];
	private int NeighborAry[];
	private int EQAry[];
	
	public EightConAlg(String inputFile, String outputFile1,
			              String outputFile2, String outputFile3) {
		try {
		Scanner readInput = new Scanner(new File(inputFile));
		numRows = readInput.nextInt();
		numCols = readInput.nextInt();
		minVal = readInput.nextInt();
		maxVal = readInput.nextInt();
		} catch(IOException exc) {
			System.out.println("Error Reading in the File " + exc);
		}
	}

}
