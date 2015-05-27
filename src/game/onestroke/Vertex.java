package game.onestroke;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

/**
 * 顶点
 * @author kco1989
 * @email  kco1989@qq.com
 * @data   2015年5月27日
 */
public class Vertex {

	/**
	 * 顶点的名字,唯一
	 */
	private String name;
	/**
	 * 入度
	 */
	private int inDegree;
	
	/**
	 * 权值
	 */
	private int weight;
	/**
	 * 临时变量,用于做一些特殊操作
	 */
	private int dist;
	/**
	 * 这个点时候已经标识过
	 */
	private boolean know;
	/**
	 * 从该点起始的边
	 */
	private Set<Edge> edges;
	/**
	 * 下一个点
	 */
	private Vertex nextVertex;
	/**
	 * 增加一条边
	 * @param v
	 * @return
	 */
	public Vertex add(Vertex v){
		return add(v,1);
	}
	/**
	 * 增加一条边
	 * @param v
	 * @return
	 */
	public Vertex add(Vertex v,int weight){
		if(v == null || v.equals(this)){
			return this;
		}
		edges.add(new Edge(this, v,weight));
		return this;
	}
	/**
	 * 去掉一条边
	 * @param v
	 * @return
	 */
	public Vertex remove(Vertex v){
		if(v == null || v.equals(this)){
			return this;
		}
		Edge result = null;
		for (Edge edge : edges) {
			if(edge.getEndVertex().equals(v)){
				result = edge;
				break;
			}
		}
		if(result != null){
			edges.remove(result);
		}
		return this;
	}
	
	/**
	 * 通过结束点获取这条边
	 * @param end
	 * @return
	 */
	public Edge getEdge(Vertex end){
		if(end == null){
			return null;
		}
		for (Edge edge : edges) {
			if(edge.getEndVertex().equals(end)){
				return edge;
			}
		}
		return null;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Edge edge : edges) {
			sb.append(edge.getEndVertex().name + "," + edge.isKnow() + ",");
		}
		if(sb.length() > 1){
			sb.deleteCharAt(sb.length() - 1);
		}
		return "(" + name + "->" + sb.toString() + ")";
	}
	public Vertex(String name){
		this(name, 0);
	}
	public Vertex(String name, int weight) {
		super();
		this.name = name;
		this.weight = weight;
		this.edges = new HashSet<Edge>();
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getDist() {
		return dist;
	}
	public void setDist(int dist) {
		this.dist = dist;
	}
	public boolean isKnow() {
		return know;
	}
	public void setKnow(boolean know) {
		this.know = know;
	}
	public Vertex getNextVertex() {
		return nextVertex;
	}
	public void setNextVertex(Vertex nextVertex) {
		this.nextVertex = nextVertex;
	}
	public String getName() {
		return name;
	}
	public int getInDegree() {
		return inDegree;
	}
	public Set<Edge> getEdges() {
		return edges;
	}
	/**
	 * 还有未被标记的边
	 * @return
	 */
	public Edge hasNext() {
		if(edges.isEmpty()){
			return null;
		}
		for (Edge edge : edges) {
			if(!edge.isKnow()){
				return edge;
			}
		}
		return null;
	}
	
	
}
