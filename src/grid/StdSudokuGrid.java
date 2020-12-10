/**
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */
package grid;

import java.io.*;
import java.util.*;

/**
 * Class implementing the grid for standard Sudoku. Extends SudokuGrid (hence
 * implements all abstract methods in that abstract class). You will need to
 * complete the implementation for this for task A and subsequently use it to
 * complete the other classes. See the comments in SudokuGrid to understand what
 * each overriden method is aiming to do (and hence what you should aim for in
 * your implementation).
 */
public class StdSudokuGrid extends SudokuGrid {
	int grid[][];
	int size;
	String input;
	String row;
	String column;
	String string;
	String str;
	boolean valid;
	ArrayList<Integer> values = new ArrayList<>();
	ArrayList<Integer> boxValue = new ArrayList<>();

	public StdSudokuGrid() {
		input = new String();
		str = "";
		valid = true;
	} // end of StdSudokuGrid()

	/* ********************************************************* */

	@Override
	public void initGrid(String filename) throws FileNotFoundException, IOException {
		Scanner scan = new Scanner(new File(filename));
		size = scan.nextInt();
		grid = new int[size][size];
		for (int i = 0; i < size; i++) {
			values.add(scan.nextInt());
		}

		while (scan.hasNext()) {
			input = scan.next();
			if (input.charAt(1) == ',') {
				row = "" + input.charAt(0);
				column = "" + input.charAt(2);
				grid[Integer.parseInt(row)][Integer.parseInt(column)] = scan.nextInt();
			}
		}
	} // end of initBoard()

	@Override
	public void outputGrid(String filename) throws FileNotFoundException, IOException {
		PrintWriter writer = new PrintWriter(filename);
		writer.write(toString());
		writer.close();
	} // end of outputBoard()

	@Override
	public String toString() {
		string = "";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (j < size - 1) {
					string += grid[i][j] + ",";
				} else {
					string += grid[i][j];
				}
			}
			string += "\n";
		}

		// placeholder
		return String.valueOf(string);
	} // end of toString()

	@Override
	public boolean validate() {

		if (valid) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					for (int k = 0; k < size; k++) {
						if (j != k) {
							if (grid[j][i] == grid[k][i]) {
								return false;
							}
						}
					}
				}
			}

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					for (int k = 0; k < size; k++) {
						if (j != k) {
							if (grid[i][j] == grid[i][k]) {
								return false;
							}
						}
					}
				}
			}
		}

		int box = (int) Math.sqrt(size);

		if (valid) {

			boxValue = new ArrayList<>();

			for (int l = 0; l < box; l++) {
				boxValue = new ArrayList<>();
				for (int i = l * box; i < (l * box) + box; i++) {
					for (int j = l * box; j < (l * box) + box; j++) {
						if (grid[i][j] == 0) {
							continue;
						}
						if (boxValue.contains(grid[i][j])) {
							return false;
						} else {
							boxValue.add(grid[i][j]);
						}
					}
				}
			}

			boxValue = new ArrayList<>();

			for (int l = 1; l < box; l++) {
				boxValue = new ArrayList<>();
				for (int i = l * box; i < (l * box) + box; i++) {
					for (int j = 0; j < box; j++) {
						if (grid[i][j] == 0) {
							continue;
						}
						if (boxValue.contains(grid[i][j])) {
							return false;
						} else {
							boxValue.add(grid[i][j]);
						}
					}
				}
			}

			boxValue = new ArrayList<>();

			for (int l = 1; l < box; l++) {
				boxValue = new ArrayList<>();
				for (int i = l * box; i < (l * box) + box; i++) {
					for (int j = 0; j < box; j++) {
						if (grid[j][i] == 0) {
							continue;
						}
						if (boxValue.contains(grid[j][i])) {
							return false;
						} else {
							boxValue.add(grid[j][i]);
						}
					}
				}
			}

			boxValue = new ArrayList<>();
			for (int l = 0; l < box; l++) {
				boxValue = new ArrayList<>();
				for (int i = (l * box) + box - 1; i >= l * box; i--) {
					for (int j = (l * box) - 1; j >= box; j--) {
						if (grid[j][i] == 0) {
							continue;
						}
						if (boxValue.contains(grid[j][i])) {
							return false;
						} else {
							boxValue.add(grid[j][i]);
						}
					}
				}
			}

			boxValue = new ArrayList<>();
			for (int l = 0; l < box; l++) {
				boxValue = new ArrayList<>();
				for (int i = (l * box) + box - 1; i >= l * box; i--) {
					for (int j = (l * box) - 1; j >= box; j--) {
						if (grid[i][j] == 0) {
							continue;
						}
						if (boxValue.contains(grid[i][j])) {
							return false;
						} else {
							boxValue.add(grid[i][j]);
						}
					}
				}
			}
		}
		// placeholder
		return valid;
	} // end of validate()

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	public int[][] getGrid() {
		return grid;
	}

	public ArrayList<Integer> getValues() {
		return values;
	}

} // end of class StdSudokuGrid
