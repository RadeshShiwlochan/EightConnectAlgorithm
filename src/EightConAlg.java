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
			//class notes says EQAry should be (numRows * numCols)/4
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
		for(int i = 0; i <= numRows + 1; i++) {
			zeroFramedAry[i][0]           = zeroFramedAry[i][1];
			zeroFramedAry[i][numCols + 1] = zeroFramedAry[i][numCols];
		}
		//framing top to bottom
		for(int j = 0; j <= numCols + 1; j++) {
			 zeroFramedAry[0][j]           = zeroFramedAry[1][j];
			 zeroFramedAry[numRows + 1][j] = zeroFramedAry[numRows][j];
		}
	}
	
	public void loadImage(String inputFile ) {
		try {
			Scanner readFile = new Scanner(new File(inputFile));
			int pixel_val = -1;
			
			for(int x = 0; x <= 3; x++)
				pixel_val = readFile.nextInt();
			
			for(int i = 1; i <= numRows; ++i) {
				for(int j = 1; j <= numCols; j++) {
					pixel_val = readFile.nextInt();
					zeroFramedAry[i][j] = pixel_val;
			    }
			}
			readFile.close();
		} catch (IOException exc) {
			System.out.println(exc);
		}
	}
	
	public void EightConCC_Pass1() {
		int pixel_val =-1;
		for(int rowVal = 1; rowVal <= numRows; ++rowVal) {
			for(int colVal = 1; colVal <= numCols; ++colVal) {
				pixel_val = zeroFramedAry[rowVal][colVal];
				if(pixel_val > 0) {
					int index = 0;
					NeighborAry[index] = zeroFramedAry[rowVal - 1][colVal - 1];
					int a = NeighborAry[index++];
					NeighborAry[index] = zeroFramedAry[rowVal - 1][colVal];
					int b = NeighborAry[index++];
					NeighborAry[index] = zeroFramedAry[rowVal - 1][colVal + 1];
					int c = NeighborAry[index++];
					NeighborAry[index] = zeroFramedAry[rowVal][colVal - 1];
					int d = NeighborAry[index++];
					NeighborAry[index] = zeroFramedAry[rowVal][colVal];
					int e = NeighborAry[index++];
					
					int currentCase = getCase(a,b,c,d,e);
					System.out.println("Case # " + currentCase);
					if(currentCase == 1) {
						newLabel++;
						zeroFramedAry[rowVal][colVal] = newLabel;
					}
					else if(currentCase == 2) {
						int nghbrLabel = getLabel(a,b,c,d);
						zeroFramedAry[rowVal][colVal] = nghbrLabel;
						System.out.println("===>" + nghbrLabel);
					}
					else if(currentCase == 3) {
						int minNghbr = getMinInNghbrArr();
						zeroFramedAry[rowVal][colVal] = minNghbr;
						System.out.print(minNghbr + "\n");
						System.out.println("=====================");
						updateEQAry(minNghbr);
					}
				}
			}
		}
	}
	
	public void EightConCC_Pass2() {
		
		for(int rowVal = numRows - 1; rowVal >=1; --rowVal) {
			for(int colVal = numCols - 1; colVal >= 1; --colVal) {
				int index = 0;
				NeighborAry[index] = zeroFramedAry[rowVal + 1][colVal - 1];
				int a = NeighborAry[index++];
				NeighborAry[index] = zeroFramedAry[rowVal - 1][colVal];
				int b = NeighborAry[index++];
				NeighborAry[index] = zeroFramedAry[rowVal - 1][colVal + 1];
				int c = NeighborAry[index++];
				NeighborAry[index] = zeroFramedAry[rowVal][colVal - 1];
				int d = NeighborAry[index++];
				NeighborAry[index] = zeroFramedAry[rowVal][colVal];
				int e = NeighborAry[index++];
			
				int currentCase = getCase(a,b,c,d,e);
				if(currentCase == 1)
					newLabel++;
				else if(currentCase == 2) {}
				else if(currentCase == 3) {}
			}
		}	
	}
	
	public int getCase(int a, int b, int c, int d, int e) {
		
		System.out.print(a + " " + b + " " + c + " " + "\n");
		System.out.print(d + " " + e + "\n");
	
		if(a == 0 && b == 0 && c == 0 && d == 0)
			return 1;
		 
		 else if(a > 0 && b > 0 && c > 0 && d > 0 &&
				 a == b && b == c && c == d          ||
				 a > 0 && b > 0 && c > 0 && d == 0 && 
			     a == b && b == c                    ||
			     a > 0 && b > 0 && c == 0 && d > 0 && 
			     a == b && b == d                    ||
			     a > 0 && b == 0 && c > 0 && d > 0 &&
			     a == c && c == d                    ||
			     a == 0 && b > 0 && c > 0 && d > 0 &&
			     b == c && c == d                    ||
			     a > 0 && b > 0 && c == 0 && d == 0 &&
			     a == b                              ||
			     a > 0 && b == 0 && c > 0 && d == 0 &&
			     a == c                              ||
			     a > 0 && b == 0 && c == 0 && d > 0 &&
			     a == d                              ||
			     a == 0 && b > 0 && c == 0 && d > 0 &&
			     b == d                              ||
			     a == 0 && b == 0 && c > 0 && d > 0 &&
			     c == d                              ||
			     a == 0 && b > 0 && c > 0 && d == 0 &&
			     b == c                              ||
			     a > 0 && b == 0 && c == 0 && d == 0 ||
			     a == 0 && b > 0 && c == 0 && d == 0 ||
			     a == 0 && b == 0 && c > 0 && d == 0 ||
			     a == 0 && b == 0 && c == 0 && d > 0   ) 
			 return 2;	 
		 else
			 return 3;
	}
	
	public int getLabel(int a, int b, int c, int d) {
		if(a > 0)
			return a;
		if(b > 0)
			return b;
		if(c > 0)
			return c;
		else 
			return d;
	}
	
	public int getMinInNghbrArr() {
		int min = 9999;
		for(int i = 0; i < 4;++i ) {
			if(NeighborAry[i] == 0)
				continue;
			else if(NeighborAry[i] < min)
				min = NeighborAry[i];
		}
		return min;
	}
	
	public void updateEQAry(int minNum) {
		EQAry[newLabel] = minNum;
	}
	public void setNghbrsToMinNum(int rowIndex, int colIndex) {
		int min = getMinInNghbrArr();
		zeroFramedAry[rowIndex - 1][colIndex - 1] = min;
		zeroFramedAry[rowIndex - 1][colIndex]     = min;
		zeroFramedAry[rowIndex - 1][colIndex + 1] = min;
		zeroFramedAry[rowIndex][colIndex - 1]     = min;
		//zeroFramedAry[rowIndex][colIndex] = min;
		
	}
	
	public void prettyPrint(String outputFile) {

		try {
		    int pixel_value;
			PrintWriter  printToFile = new PrintWriter(new File(outputFile));
			//read in the input file
			for(int i = 0; i < numRows; i++) {
				for(int j = 0; j < numCols; j++) {
					pixel_value = zeroFramedAry[i][j];
					if(pixel_value > 0) 
						printToFile.println(pixel_value + " ");
					else 
						printToFile.print(" ");
				}
				printToFile.println();
			}		
			printToFile.close();
		}catch(IOException exc) {
			System.out.println(exc);
		}
	}//prettyPrint method
	
	public void printFunc(String outputFile) {
		try {
			PrintWriter printToFile = new PrintWriter(new File(outputFile));
			for (int i = 0; i < numRows + 2; ++i) {
				for(int j = 0; j < numCols + 2; ++j){
					printToFile.print(zeroFramedAry[i][j] + " ");
				}
				printToFile.println();
			}
			printToFile.flush();
			printToFile.close();
		} catch(IOException exc) {
			System.out.println(exc);
		}
	}

}
