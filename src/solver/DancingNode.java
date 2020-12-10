package solver;

public class DancingNode {
	DancingNode left, right, up, down;
	ColumnNode column;

	public DancingNode() {
		left = right = up = down = this;
	}

	public DancingNode(ColumnNode c) {
		this();
		column = c;
	}

	public DancingNode linkDown(DancingNode node) {
		assert (this.column == node.column);
		node.down = this.down;
		node.down.up = node;
		node.up = this;
		this.down = node;
		return node;
	}

	public DancingNode linkRight(DancingNode node) {
		node.right = this.right;
		node.right.left = node;
		node.left = this;
		this.right = node;
		return node;
	}

	public void removeLeftRight() {
		left.right = right;
		right.left = left;
	}

	public void reinsertLeftRight() {
		left.right = this;
		right.left = this;
	}

	public void removeTopBottom() {
		up.down = down;
		down.up = up;
	}

	public void reinsertTopBottom() {
		up.down = this;
		down.up = this;
	}

}
