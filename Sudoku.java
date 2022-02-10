import java.util.*;



public class Sudoku {

	String[][] board;

	//I'm providing this array just in case you want to test the code without entering all the numbers for a sudoku
	// = {
	// 			{"5","3",".",".","7",".",".",".","."},
 //                {"6",".",".","1","9","5",".",".","."},
 //                {".","9","8",".",".",".",".","6","."},
 //                {"8",".",".",".","6",".",".",".","3"},
 //                {"4",".",".","8",".","3",".",".","1"},
 //                {"7",".",".",".","2",".",".",".","6"},
 //                {".","6",".",".",".",".","2","8","."},
 //                {".",".",".","4","1","9",".",".","5"},
 //                {".",".",".",".","8",".",".","7","9"}
 //     };


	/**
	 * Default constructor -- construct an empty Sudoku puzzle
	 */
	public Sudoku () {
		//Initializes all variables inside board to "."
		this.board = new String[9][9];

		for(int i=0; i<=8; i++){
			for(int j=0; j<=8; j++){
				this.board[i][j] = ".";
			}
		}


	}


	/**
	 * Input an initial starting board for Sudoku. Consider using one online, such as
	 * https://www.sudokuoftheday.com/
	 * @param board
	 * @throws Exception
	 */
	public void inputBoard(String [][] board){
		Scanner obj = new Scanner(System.in);
		
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				System.out.println("Please enter the number for coordinate row " + i + " column " + j + "\n" + "or enter '0' for an empty square");
				String answer = obj.nextLine();
				if(answer.equals("0")){
					board[i][j] = ".";
				}

				else{
					board[i][j] = answer;
				}


				for(int x=0; x<=8; x++){
			for(int z=0; z<=8; z++){
				System.out.print("[" + board[x][z] + "]");
			}
			System.out.println();
		}

			}
		}


	}


	public boolean safe(int row, int column, String num, String[][] board){
		
		//Checks below num
		for(int i=row+1; i<=8; i++){
			if((board[i][column].equals(num))){
				return false;
			}
		}

		//Checks above num
		for(int j = row-1; j>=0; j--){
			if((board[j][column].equals(num))){
				return false;
			}
		}

		//Checks right of num
		for(int h=column+1; h<=8; h++){
			if((board[row][h].equals(num))){
				return false;
			}
		}

		//Checks left of num
		for(int k = column-1; k>=0; k--){
			if((board[row][k].equals(num))){
				return false;
			}
		}

		//Checks box of num
		int cornerColumn = column - (column%3);
		int cornerRow = row - (row%3);

		for(int a = cornerRow; a<cornerRow+3; a++){
			for(int b = cornerColumn; b<cornerColumn+3; b++){
				if(a == row && b == column){
				}

				else if(board[a][b].equals(num)){
					return false;
				}
			}
		}
		
		//If all the above pass, then it must be a safe spot, so return true
		return true;
	}


	/**
	 * Solves the Sudoku puzzle from the starting position, if possible.
	 * @return true if the puzzle is solved; false if it cannot be solved.
	 */
	public boolean solveSudoku (String[][] board, int row, int column) {

		//If we are at the end of the row and the column, we have solved the puzzle
		if(row  == 8 && column == 8){
			return true;
		}

		//If we are at the end of the row go to next one
		if(column == 8){
			row++;
			column = 0;
		}

		//Otherwise just increment the column
		else{
			column++;
		}

		//If the square already has a num, go to the next one
		if(board[row][column].equals(".") == false){
			return solveSudoku(board, row, column);
		}

		//Otherwise...
		else if(board[row][column]. equals(".")){
			//Tests nums
			for(int i=1; i<=9; i++){
				//If the square is safe with the number, then sets the num at the index
				if(safe(row, column, "" + i, board)){
					board[row][column] = "" + i;

					//Backtracking/Recursion part
					if(solveSudoku(board, row, column)){
						return true;
					}

					else{
						board[row][column] = ".";
					}
				}
			}

		}



		//Otherwise it doesn't work lol
		return false;
	}

	/**
	 * Prints the Sudoku board to the console.
	 */
	public void printBoard(String[][] board) {

		for(int i=0; i<=8; i++){
			for(int j=0; j<=8; j++){
				System.out.print("[" + board[i][j] + "]");
			}
			System.out.println();
		}





	}
	
	public static void main(String[] args) {
		//Initializes an empty board, prints it out
		Sudoku obj = new Sudoku();
		String[][] board = obj.board;
        obj.printBoard(board);
        System.out.println();

        //If you want to input the board, it goes through and lets you do that
		obj.inputBoard(board);

		//If the sudoku is solveable, it prints the board, otherwise it says it is unsolveable.
		if(obj.solveSudoku(board, 0, 0) == true){
			obj.printBoard(board);
		}

		else{
			System.out.println("Board is not solveable");
		}

		
	}
	
}
