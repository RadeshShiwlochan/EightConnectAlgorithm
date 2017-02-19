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
					int nghbrA = zeroFramedAry[rowVal - 1][colVal - 1];
					int nghbrB = zeroFramedAry[rowVal - 1][colVal];
					int nghbrC = zeroFramedAry[rowVal - 1][colVal + 1];
					int nghbrD = zeroFramedAry[rowVal][colVal - 1];
					int currentCase = getCase(nghbrA, nghbrB,
										nghbrC, nghbrD);
					System.out.print(nghbrA + " " + nghbrB + " " + nghbrC + " " + "\n");
					System.out.print(nghbrD + " " + zeroFramedAry[rowVal][colVal] + "\n");
					System.out.println("Case # " + currentCase);
					if(currentCase == 1) {
						newLabel++;
						zeroFramedAry[rowVal][colVal] = newLabel;
					}
					else if(currentCase == 2) {
						zeroFramedAry[rowVal][colVal] = newLabel;
					}
					else if(currentCase == 3) {}
					}
			}
		}
	}
	
	public void EightConCC_Pass2() {
		for(int rowVal = 1; rowVal <= numRows; ++rowVal) {
			for(int colVal = 1; colVal <= numCols; ++colVal) {
				int neighborA = zeroFramedAry[rowVal - 1][colVal - 1];
				int neighborB = zeroFramedAry[rowVal - 1][colVal];
				int neighborC = zeroFramedAry[rowVal - 1][colVal + 1];
				int neighborD = zeroFramedAry[rowVal][colVal - 1];
				int currentCase = getCase(neighborA, neighborB,
									neighborC, neighborD);
				if(currentCase == 1)
					newLabel++;
				else if(currentCase == 2) {}
				else if(currentCase == 3) {}
			}
		}	
	}
	
	public int getCase(int a, int b, int c, int d) {
	
		if(a == 0 && b == 0 && c == 0 && d == 0)
			return 1;
		 else if(a == b && b == c && c == d )	 
			return 2;
		 else if(a > 0 && b > 0 && c > 0 && d > 0) {
			 if(a == b && a == c && a == d )
				 return 2;
			 else if(a == b && a == c || b == c && b == d )
				 return 3;
		 }
		 else if(a == 0 && b > 0 && c > 0 && d > 0) {
			 if (b == c && c == d)
				 return 2;
			 return 3;
		 } else if(b == 0 && a > 0 && c > 0 && d > 0) {
			 if( a == c && c == d) 
				 return 2;
			 return 3;
		 } else if(c == 0 && a > 0 && b > 0 && d > 0) {
			 if( a == b && b == d)
				 return 2;
			 return 3;
		 } else if(d == 0 && a > 0 && b > 0 && c > 0) {
			 if(a == b && b == c)
				 return 2;
			 return 3;
		 } else if (a == 0 && b == 0 && c > 0 && d > 0) {
			 if(c == d)
				 return 2;
			 return 3;
		 } else if(a == 0 && c == 0 && b > 0 && d > 0) {
			 if(b == d)
				 return 2;
			 return 3;
		 } else if(b == 0 && c == 0 && a > 0 && d > 0) {
			 if(b == c)
				 return 2;
			 return 3;
		 } else if(b == 0 && d == 0 && a > 0 && c > 0) {
			 if(a == c)
				 return 2;
			 return 3;
		 } else if(a == 0 && d == 0 && b > 0 && c > 0) {
			 if(b == c)
				 return 2;
			 return 3;
		 }
		return 2;
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
