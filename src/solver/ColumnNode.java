package solver;

import java.awt.List;
import java.util.ArrayList;

public class ColumnNode extends DancingNode {
	int size;
	String name;

	public ColumnNode(String n) {
		super();
		size = 0;
		name = n;
		column = this;
	}

	public void cover() {
		removeLeftRight();
		for (DancingNode i = down; i != this; i = i.down) {
			for (DancingNode j = i.right; j != i; j = j.right) {
				j.removeTopBottom();
				j.column.size--;
			}
		}
	}

	public void uncover() {
		for (DancingNode i = up; i != this; i = i.up) {
			for (DancingNode j = i.left; j != i; j = j.left) {
				j.column.size++;
				j.reinsertTopBottom();
			}
		}
		reinsertLeftRight();
	}

}
