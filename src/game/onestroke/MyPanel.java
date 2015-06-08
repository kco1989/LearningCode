package game.onestroke;

import game.onestroke.LinkedList.Node;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MyPanel extends JPanel {

	/**
	 * 画
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Vertex vertex : OneStrokeFrame.figure.getVertexSet()) {
			if(!vertex.getEdges().isEmpty()){
				for (Edge edge : vertex.getEdges()) {
					CircularLabel start = OneStrokeFrame.getCircularLabel(edge.getStartVertex().getName());
					CircularLabel end = OneStrokeFrame.getCircularLabel(edge.getEndVertex().getName());
					Color bak = g.getColor();
					g.setColor(Color.blue);
					g.drawLine(start.getCircular().x + start.getRadius(), start.getCircular().y + start.getRadius(),
							end.getCircular().x + end.getRadius(), end.getCircular().y + end.getRadius());
					g.setColor(bak);
				}
			}
			if(OneStrokeFrame.isRun){
				if(OneStrokeFrame.eulerCour != null){
					Node<Vertex> frist = OneStrokeFrame.eulerCour.getFristNode();
					Node<Vertex> second = frist.next;
					do{
						CircularLabel start = OneStrokeFrame.getCircularLabel(frist.e.getName());
						CircularLabel end = OneStrokeFrame.getCircularLabel(second.e.getName());
						Color bak = g.getColor();
						g.setColor(Color.green);
						
						drawLine(g,start.getCircular().x + start.getRadius(), start.getCircular().y + start.getRadius(),
								end.getCircular().x + end.getRadius(), end.getCircular().y + end.getRadius());
						g.setColor(bak);
						frist = second;
						second = second.next;
					}while(second != null);
				}
			}
		}
	}

	private void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
		if(x2 == x1){
			g.drawLine(x1, y1, x2, y2);
			return;
		}
		int k = (y2 - y1)/(x2 - x1);
		
		
	}
}
