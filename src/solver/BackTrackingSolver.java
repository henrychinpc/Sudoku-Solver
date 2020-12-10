/*
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */

package solver;

import grid.SudokuGrid;

/**
 * Backtracking solver for standard Sudoku.
 */
public class BackTrackingSolver extends StdSudokuSolver {

	public BackTrackingSolver() {
		// TODO: any initialisation you want to implement.
	} // end of BackTrackingSolver()

	@Override
	public boolean solve(SudokuGrid grid) {
		int[][] board = grid.getGrid();
		int n = board.length;
		int row = -1;
		int col = -1;
		boolean empty = true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
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

		for (int num = 1; num <= n; num++) {
			if (checkConstrain(board, row, col, num)) {
				board[row][col] = num;
				if (solve(grid)) {
					return true;
				} else {
					board[row][col] = 0;
				}
			}
		}
		// placeholder
		return false;
	} // end of solve()

	private boolean checkConstrain(int[][] board, int row, int col, int num) {
		for (int k = 0; k < board.length; k++) {
			if (board[row][k] == num) {
				return false;
			}
		}
		for (int l = 0; l < board.length; l++) {
			if (board[l][col] == num) {
				return false;
			}
		}

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

} // end of class BackTrackingSolver()
