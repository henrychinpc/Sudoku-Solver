/*
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */
package solver;

import java.util.ArrayList;
import grid.SudokuGrid;


/**
 * Algorithm X solver for standard Sudoku.
 */

public class AlgorXSolver extends StdSudokuSolver {
    // TODO: Add attributes as needed.

    public AlgorXSolver() {
    	
    } // end of AlgorXSolver()


    @Override
    public boolean solve(SudokuGrid grid) {
    	int exactCover[][];
		int row = -1;
		int col = -1;
		int size;
		int constraints = 4;
		boolean empty = true;
		int[][] board = grid.getGrid();
		ArrayList<Integer> index = new ArrayList<>();
		
		size = board.length;
		exactCover = new int[(size * size * size)][size * size * constraints];
		getExactCover(exactCover, board, index);
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] == 0) {
					row = i;
					col = j;
					empty = false;
					break;
				}
			}
			if (!empty) {
				break;
			}
		}
		if (empty) {
			return true;
		}

		for (int num = 1; num <= size; num++) {
			if (checkConstrain(board, row, col, num)) {
				board[row][col] = num;
				if (solve(grid)) {
					optimise(exactCover, board, index, size);
					return true;
				} else {
					board[row][col] = 0;
				}
			}
		}
		return false;
	} // end of solve()

    private boolean checkConstrain(int[][] board, int row, int col, int num) {
		for (int k = 0; k < board.length; k++) {
			//Number duplicates will return false
			if (board[row][k] == num) {
				return false;
			}
		}
		for (int l = 0; l < board.length; l++) {
			//Numnber duplicates will return false
			if (board[l][col] == num) {
				return false;
			}
		}

		//Checking for box unique number using square-root
		int box = (int) Math.sqrt(board.length);
		int boxRow = row - row % box;
		int boxCol = col - col % box;
		for (int l = boxRow; l < boxRow + box; l++) {
			for (int k = boxCol; k < boxCol + box; k++) {
				if (board[l][k] == num) {
					return false;
				}
			}
		}
		return true;
	}

	public static void getExactCover(int exactCoverMatrix[][], int grid[][], ArrayList<Integer> index) {
		int size = grid.length;
		
		for (int k = 0; k < size * size; k++) {
			for (int l = k * size; l < (size * k) + size; l++) {
				exactCoverMatrix[l][k] = 1;
			}
		}

		for (int n = 0; n < size; n++) {
			for (int k = n * size; k < (n * size) + size; k++) {
				for (int l = k * size, m = (n * size) + (size * size); l < k * size + size; l++, m++) {
					exactCoverMatrix[l][m] = 1;
				}
			}
		}

		for (int n = 0; n < size; n++) {
			for (int k = n * size * size, l = size * size * 2; k < (n * size * size) + (size * size); k++, l++) {
				exactCoverMatrix[k][l] = 1;
			}
		}
		
		int l = 0, r = size * size * 3;
		
		for (int x = 0; x < size * size * size; x += (size * size)) {
			if (x == size * size * Math.sqrt(size))
				r += size * Math.sqrt(size);
			for (int n = 0; n < Math.sqrt(size); n++) {
				for (int k = (int) (n * Math.sqrt(size)); k < (n * Math.sqrt(size)) + Math.sqrt(size); k++) {
					for (int m = r + (n * size); l < x + (k * size + size); l++, m++) {
						exactCoverMatrix[l][m] = 1;

					}
				}
			}
		}

		for (int k = 1; k <= size; k++) {
			for (l = 1; l <= size; l++) {
				for (int m = 1; m <= size; m++) {
					index.add((k * 100) + (l * 10) + m);
				}
			}
		}
	}

	public void optimise(int exactCoverMatrix[][], int grid[][], ArrayList<Integer> index, int size) {
		int num = 0;
		int row = 0;
		int col = 0;
		int rem = 0;
		
		for (int y = 0; y < size; y++) {
			for (int z = 0; z < size; z++) {
				if (grid[y][z] != 0) {
					num = ((y + 1) * 100) + ((z + 1) * 10) + grid[y][z];
					row = y + 1;
					col = z + 1;
					if (index.contains(num)) {
						rem = index.indexOf(num);
						for (int l = 0; l < exactCoverMatrix[rem].length; l++) {
							if (exactCoverMatrix[rem][l] != 0) {
								exactCoverMatrix[rem][l] = 0;
							} else
								continue;
						}
						
						for (int p = 0; p < index.size(); p++) {
							num = index.get(p);
							if ((num / 100 == row && (num % 100) / 10 == col) || (num / 100 == row && num % 10 == grid[y][z]) || (num % 10 == grid[y][z] && (num % 100) / 10 == col)) {
								rem = index.indexOf(num);
								for (int l = 0; l < exactCoverMatrix[rem].length; l++) {
									if (exactCoverMatrix[rem][l] != 0) {
										exactCoverMatrix[rem][l] = 0;
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
