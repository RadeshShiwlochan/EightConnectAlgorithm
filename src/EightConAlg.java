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
	
	public EightConAlg(String inputFile) {
		try {
			Scanner readFile = new Scanner(new File(inputFile));
			numRows = readFile.nextInt();
			numCols = readFile.nextInt();
			minVal = readFile.nextInt();
			maxVal = readFile.nextInt();
		
			newLabel = 0;
			int rowSize = numRows + 2, colSize = numCols + 2,
					      EQArySize = (numRows * numCols)/2;
			zeroFramedAry = new int[rowSize][colSize];
			NeighborAry = new int[5];
			EQAry = new int[EQArySize];
			for(int rowIndex = 0; rowIndex < EQArySize; ++rowIndex )
				EQAry[rowIndex] = rowIndex;
			readFile.close();
		} catch(Exception exc) {
			System.out.println("Error Reading in the File " + exc);
		}
	}
	
	public void zeroFramed() {
		//framing left to right
		for(int i = 0; i <= numRows + 3; i++) {
			zeroFramedAry[i][0]           = zeroFramedAry[i][2];
			zeroFramedAry[i][1]           = zeroFramedAry[i][2];
			zeroFramedAry[i][numCols + 2] = zeroFramedAry[i][numCols + 1];
			zeroFramedAry[i][numCols + 3] = zeroFramedAry[i][numCols + 1];
		}
		//framing top to bottom
		for(int j = 0; j <= numCols + 3; j++) {
			 zeroFramedAry[0][j]           = zeroFramedAry[2][j];
			 zeroFramedAry[1][j]           = zeroFramedAry[2][j];
			 zeroFramedAry[numRows + 2][j] = zeroFramedAry[numRows + 1][j];
			 zeroFramedAry[numRows + 3][j] = zeroFramedAry[numRows + 1 ][j];
		}
	}
	
	public void printFunc() {
		for (int i = 0; i < numRows + 2; ++i) {
			for(int j = 0; j < numCols + 2; ++j){
				System.out.print(zeroFramedAry[i][j] + " ");
			}
			System.out.println();
		}
	}

}
