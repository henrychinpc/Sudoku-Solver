package solver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import grid.SudokuGrid;

public class DLX {
	private ColumnNode header;
	public List<DancingNode> answer;
	public List<DancingNode> result;

	public DLX(int[][] cover) {
		header = createDLXBoard(cover);
	}

	private ColumnNode createDLXBoard(int[][] grid) {
		final int col = grid[0].length;
		ColumnNode headerNode = new ColumnNode("header");
		List<ColumnNode> columnNode = new ArrayList<>();

		for (int i = 0; i < col; i++) {
			ColumnNode n = new ColumnNode(Integer.toString(i));
			columnNode.add(n);
			headerNode = (ColumnNode) headerNode.linkRight(n);
		}
		headerNode = headerNode.right.column;

		for (int[] aGrid : grid) {
			DancingNode prev = null;

			for (int j = 0; j < col; j++) {
				if (aGrid[j] == 1) {
					ColumnNode cols = columnNode.get(j);
					DancingNode newNode = new DancingNode(cols);

					if (prev == null)
						prev = newNode;

					cols.up.linkDown(newNode);
					prev = prev.linkRight(newNode);
					cols.size++;
				}
			}
		}
		headerNode.size = col;
		return headerNode;
	}

	private void process(int k) {
		if (header.right == header) {
			// End of algorx
			result = new LinkedList<>(answer);
//			handleSolution(answer);
		} else {
			// Choosing column c
			ColumnNode c = selectColumnNodeHeuristic();
			c.cover();

			for (DancingNode r = c.down; r != c; r = r.down) {
				// Adding r line to partial solution
				answer.add(r);
				// Covering columns
				for (DancingNode j = r.right; j != r; j = j.right) {
					j.column.cover();
				}
				// Recursive call
				process(k + 1);
				r = answer.remove(answer.size() - 1);
				c = r.column;
				// Uncover columns
				for (DancingNode j = r.left; j != r; j = j.left) {
					j.column.uncover();
				}
			}
			c.uncover();
		}
	}

	private ColumnNode selectColumnNodeHeuristic() {
		int min = Integer.MAX_VALUE;
		ColumnNode ret = null;

		for (ColumnNode c = (ColumnNode) header.right; c != header; c = (ColumnNode) c.right) {
			if (c.size < min) {
				min = c.size;
				ret = c;
			}
		}
		return ret;
	}

	public void solve() {
		answer = new LinkedList<>();
		process(0);
	}

}
