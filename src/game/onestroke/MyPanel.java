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
						g.drawLine(start.getCircular().x + start.getRadius(), start.getCircular().y + start.getRadius(),
								end.getCircular().x + end.getRadius(), end.getCircular().y + end.getRadius());
						g.setColor(bak);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						frist = second;
						second = second.next;
					}while(second != null);
				}
			}
		}
	}
}
