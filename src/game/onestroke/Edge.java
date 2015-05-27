package game.onestroke;

/**
 * 边
 * @author kco1989
 * @email  kco1989@qq.com
 * @data   2015年5月27日
 */
public class Edge {
	
	private Vertex startVertex;
	private Vertex endVertex;
	private boolean know;
	private int weight;
	
	public Edge(Vertex startVertex, Vertex endVertex){
		this(startVertex, endVertex, 0);
	}
	
	public Edge(Vertex startVertex, Vertex endVertex, int weight) {
		super();
		this.startVertex = startVertex;
		this.endVertex = endVertex;
		this.weight = weight;
	}
	public boolean isKnow() {
		return know;
	}
	public void setKnow(boolean know) {
		this.know = know;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Vertex getStartVertex() {
		return startVertex;
	}
	public Vertex getEndVertex() {
		return endVertex;
	}

	@Override
	public String toString() {
		return "(" + startVertex + "--"+know+"-->" + endVertex + ")";
	}
	
	
}
