/*
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */

package solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import grid.SudokuGrid;

/**
 * Dancing links solver for standard Sudoku.
 */
public class DancingLinksSolver extends StdSudokuSolver {

	public DancingLinksSolver() {
		// TODO: any initialisation you want to implement.
	}

	@Override
	public boolean solve(SudokuGrid grid) {
		int[][] answer;
		int size = grid.getGrid().length;
		int[][] cover = initializeExactCover(grid);
		DLX dlx = new DLX(cover);
		dlx.solve();
		answer = parseBoard(dlx.result, size);
		grid.setGrid(answer);
		return true;
	}

	private int[][] parseBoard(List<DancingNode> answer, int size) {
		int[][] result = new int[size][size];
		for (DancingNode n : answer) {
			DancingNode rcNode = n;
			int min = Integer.parseInt(rcNode.column.name);
			for (DancingNode temp = n.right; temp != n; temp = temp.right) {
				int val = Integer.parseInt(temp.column.name);
				if (val < min) {
					min = val;
					rcNode = temp;
				}
			}
			int ans1 = Integer.parseInt(rcNode.column.name);
			int ans2 = Integer.parseInt(rcNode.right.column.name);
			int r = ans1 / size;
			int c = ans1 % size;
			int num = (ans2 % size) + 1;
			result[r][c] = num;
		}
		return result;
	}

	private int[][] initializeExactCover(SudokuGrid grid) {
		int[][] R = getExactCover(grid);
		int[][] board = grid.getGrid();
		int S = board.length;
		for (int i = 1; i <= S; i++) {
			for (int j = 1; j <= S; j++) {
				int n = board[i - 1][j - 1];
				if (n != 0) { // zero out in the constraint board
					for (int num = 1; num <= S; num++) {
						if (num != n) {
							Arrays.fill(R[getIndex(i, j, num, S)], 0);
						}
					}
				}
			}
		}
		return R;
	}

	private int[][] getExactCover(SudokuGrid grid) {
		int[][] board = grid.getGrid();
		int S = board.length;
		int side = (int) Math.sqrt(S);
		int[][] R = new int[S * S * S][S * S * 4];
		int hBase = 0;

		//Row-column constraints
		for (int r = 1; r <= S; r++) {
			for (int c = 1; c <= S; c++, hBase++) {
				for (int n = 1; n <= S; n++) {
					R[getIndex(r, c, n, S)][hBase] = 1;
				}
			}
		}

		//Row-number constraints
		for (int r = 1; r <= S; r++) {
			for (int n = 1; n <= S; n++, hBase++) {
				for (int c1 = 1; c1 <= S; c1++) {
					R[getIndex(r, c1, n, S)][hBase] = 1;
				}
			}
		}

		//Column-number constraints
		for (int c = 1; c <= S; c++) {
			for (int n = 1; n <= S; n++, hBase++) {
				for (int r1 = 1; r1 <= S; r1++) {
					R[getIndex(r1, c, n, S)][hBase] = 1;
				}
			}
		}

		//Box-number constraints
		for (int br = 1; br <= S; br += side) {
			for (int bc = 1; bc <= S; bc += side) {
				for (int n = 1; n <= S; n++, hBase++) {
					for (int rDelta = 0; rDelta < side; rDelta++) {
						for (int cDelta = 0; cDelta < side; cDelta++) {
							R[getIndex(br + rDelta, bc + cDelta, n, S)][hBase] = 1;
						}
					}
				}
			}
		}

		return R;
	}

	//Index in the cover matrix
	private int getIndex(int row, int col, int num, int size) {
		return (row - 1) * size * size + (col - 1) * size + (num - 1);
	}
}
