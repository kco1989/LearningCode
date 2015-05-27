package game.onestroke;

import java.util.HashSet;
import java.util.Set;

/**
 * 临接矩阵(图)
 * @author kco1989
 * @email  kco1989@qq.com
 * @data   2015年5月27日
 */
public class Figure {
	
	private Set<Vertex> vertexSet = new HashSet<Vertex>();
	
	/**
	 * 增加一个顶点
	 * @param v
	 * @return
	 */
	public Figure addVertex(Vertex v){
		if(v != null){
			vertexSet.add(v);
		}
		return this;
	}
	
	/**
	 * 移除一个顶点
	 * @param v
	 * @return
	 */
	public Figure removeVertex(Vertex v){
		if(v != null){
			vertexSet.remove(v);
		}
		for (Vertex vertex : vertexSet) {
			vertex.remove(v);
		}
		return this;
	}
	/**
	 * 获取顶点
	 * @param v
	 * @return
	 */
	public Vertex getVertex(Vertex v){
		for (Vertex vertex : vertexSet) {
			if(vertex.equals(v)){
				return vertex;
			}
		}
		return null;
	}

	public Set<Vertex> getVertexSet() {
		return vertexSet;
	}

	@Override
	public String toString() {
		return "[" + vertexSet + "]";
	}

	
	public LinkedList<Vertex> eulerCour(){
		if(vertexSet.isEmpty()){
			return null;
		}
		//初始化所有边都没有被标志
		for (Vertex vertex : vertexSet) {
			if(!vertex.getEdges().isEmpty()){
				for (Edge edge : vertex.getEdges()) {
					edge.setKnow(false);
				}
			}
		}
		System.out.println("this:" + this);
		
		Vertex vertex = (Vertex)vertexSet.toArray()[0];
		LinkedList<Vertex> list = new LinkedList<Vertex>();
		LinkedList<Vertex> temp = null;
		list.add(vertex);
		list = tryRun(list);
		if(list == null){
			return list;
		}
		while ((vertex = hasNext()) != null) {
			temp = new LinkedList<Vertex>();
			temp = tryRun(temp);
			if(temp == null){
				return null;
			}
			list.add(temp);
		}
		return list;
	}
	
	/**
	 * 尝试一笔画
	 * @param list
	 * @return
	 */
	private LinkedList<Vertex> tryRun(LinkedList<Vertex> list) {
		if(list == null){
			return null;
		}
		Vertex lastVertex = list.getLastItem();
		System.out.println("lastVertex:" + lastVertex);
		Edge nextEdge = lastVertex.hasNext();
		System.out.println("nextEdge:" + nextEdge);
		System.out.println("list:"+list);
		if(nextEdge == null){
			if(list.size() <= 1){
				return null;
			}else{
				if(!list.getFristItem().equals(lastVertex)){
					return null;
				}else{
					return list;
				}
			}
		}else{
			list.add(nextEdge.getEndVertex());
			nextEdge.setKnow(true);
			getEdge(nextEdge.getEndVertex(),nextEdge.getStartVertex()).setKnow(true);
		}
		return tryRun(list);
	}

	private Edge getEdge(Vertex startVertex, Vertex endVertex) {
		if(vertexSet.isEmpty()){
			return null;
		}
		for (Vertex vertex : vertexSet) {
			if(vertex.equals(startVertex)){
				for (Edge edge : vertex.getEdges()) {
					if(edge.getEndVertex().equals(endVertex)){
						return edge;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 查找还有没有被标志的边的顶点
	 * @return
	 */
	public Vertex hasNext(){
		for (Vertex vertex : vertexSet) {
			if(!vertex.getEdges().isEmpty()){
				for (Edge edge : vertex.getEdges()) {
					if(!edge.isKnow()){
						return vertex;
					}
				}
			}
		}
		return null;
	}
}
