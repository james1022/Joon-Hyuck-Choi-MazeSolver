import java.io.*;
import java.util.*;

/**
 * The MazeSolver class gives the user a solution to a maze or tells 
 * the user that the maze does not have a solution if it cannot be solved.
 * @author Joon Hyuck Choi
 *
 */
public class MazeSolver {	
		
		/** The array that contains the structure of the maze. */
		private char[][] mazeStructure;
		
		/** The number of rows in the maze. */
		private int row;
		
		/** The number of columns in the maze. */
		private int col;
		
		/** The x position of Mario. */
		private int marioX;
		
		/** The y position of Mario. */
		private int marioY;
		
		/** The x position of the goal X. */
		private int xPositionX;
		
		/** The y position of the goal X. */
		private int yPositionX;
		
		/** The number of valid moves. */
		private int count;
		
		/**
		 * Constructs a maze given the name of text file containing the number of rows and columns of a maze, a Mario, and a goal X. The constructor scans the input file and creates a maze using an array and also scans the the (x,y) positions of Mario and the goal.
		 * @param filename the name of the file that contains the number of rows and columns of a maze and its structure.
		 * @throws IOException if the given file cannot be opened.
		 */
		public MazeSolver(String filename) throws IOException {

			//initialize the instantiable variables.
		    Scanner inputFile = new Scanner(new FileReader(filename));
		    this.row = 0;
		    this.col = 0;
		    this.marioX = 0;
		    this.marioY = 0;
		    this.xPositionX = 0;
		    this.yPositionX = 0;
		    
		    //set up the array for the mazeStructure by getting row and column information from the user's input file.
		    row = inputFile.nextInt();        
		    col = inputFile.nextInt();  
		    mazeStructure = new char[row][col];
		    
		    //fill up the mazeStructure[][] with information from the user's input file.
			inputFile.nextLine();
			for (int r = 0; r < row; r++){
				String oneLine = inputFile.nextLine();
					for (int i = 0; i < oneLine.length(); i++){
						mazeStructure[r][i] = oneLine.charAt(i);
						if(mazeStructure[r][i] == 'M'){
							marioX = i;
							marioY = r;
						} else if(mazeStructure[r][i] == 'X'){
							xPositionX = i;
							xPositionX = r;
						} //end if-else if
					} //end inner for
			} //end outer for

			inputFile.close();
			
		} //end constructor MazeSolver();

		
		/**
		 * Prints out the maze. When it's called, it prints out the most updated version of the maze.
		 */
		private void printMaze(){
			
			for (int r = 0; r < row; r++){
				for (int c = 0; c < col; c++){
					System.out.print(mazeStructure[r][c]);
				} //end inner for
				System.out.println();
			} //end outer for
			
			System.out.println();
			
		} //end method printMaze()
		
		
		/**
		 * Solves the maze using recursion. It first checks if the next move is valid and proceeds. And then it checks if the maze has been solved and returns true in this case. If the maze has not been solved yet, it calls itself recursively and proceeds from thereon. If Mario cannot proceed anymore (either because the next move is a wall or a dot, then it goes back one step.
		 * @param r the row position of Mario.
		 * @param c the column position of Mario.
		 * @return true if the maze is solved, false if the maze is not solved yet (or if it has no solution at all).
		 */
		private boolean solveMaze(int r, int c){
			
			//when the next move is invalid
			if( r >= this.row || c >= this.col || r < 0 || c < 0 || mazeStructure[r][c] == '+' || mazeStructure[r][c] == '.') {
				//back track to a '.'
				//make the current position into a space
				return false;
			} else if(r == this.yPositionX && c == this.xPositionX){
				//when the next move is a the last move. That is, when the maze is solved with the next move.
				return true;
			} else{
				//when the next move is neither invalid nor the last move. That is, when the next move is a valid move in the middle process. 
				mazeStructure[r][c] = '.';
				this.count++;
				
				if(solveMaze(r - 1, c)){
					return true;
				}
				if(solveMaze(r + 1, c)){
					return true;
				}
				if(solveMaze(r, c - 1)){
					return true;
				}
				if(solveMaze(r, c + 1)){
					return true;
				}
			} //end if-else if-if
			
			//Backtracking
			mazeStructure[r][c] = ' ';
			this.count--;
			
			return false;
			
		} //end method solveMaze(int r, int c)
		
		

		/**
		 * Solves the maze using the recursive method solveMaze(int r, int c). This method gets called in the main method of the driver. It first prints the original maze before any of the positions have been updated by the recursive method. It then calls the recursive method solveMaze(int r, int c) and attempts to solve the maze. If the maze is solvable, the recursive method solveMaze(int r, int c) will solve the maze and print out how many moves it took to solve the maze. If the maze is not solvable, the recursive method solveMaze(int r, int c) will return false and the method will print out a message that tells the user that the maze is not solvable.
		 */
		public void solveMaze() {
		
			//print original maze
			printMaze();
			
			if (solveMaze(this.marioY, this.marioX) == true){
				System.out.println("I've solved the maze! The number of moves it took was " + count + ".");
				printMaze();
				System.out.println();
			} else {
				System.out.println("Sorry, this maze has no solution.");
				
			} //end if-else
			
		} //end method solveMaze()
		
} //end class MazeSolver
