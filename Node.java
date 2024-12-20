import java.util.ArrayList;
import java.util.List;

/**
 * Node for Graph
 */
class Node {
  public int val;
  public List<Node> neighbors;

  public Node() {
    this.val = 0;
    this.neighbors = new ArrayList<>();
  }

  public Node(int val) {
    this.val = val;
    this.neighbors = new ArrayList<>();
  }

  public Node(int val, ArrayList<Node> neighbor) {
    this.val = val;
    this.neighbors = neighbor;
  }
}
