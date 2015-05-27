package game.onestroke;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OneStrokeFrame extends JFrame {
	
	public static final int WIDTH = 300;
	public static final int HEIGHT = 300;
	public static CircularLabel start;
	public static Figure figure = new Figure();
	public static int count = 0;
	private JPanel panel;
	public static Set<CircularLabel> labelSet = new HashSet<CircularLabel>();
	public static boolean isRun = false;
	public static LinkedList<Vertex> eulerCour;
	public OneStrokeFrame(){
		panel = new MyPanel();
		JButton run = new JButton("run");
		panel.add(run);
		run.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				isRun = true;
				eulerCour = figure.eulerCour();
				System.out.println(figure);
				System.out.println(eulerCour);
				panel.repaint();
			}
			
		});
		setContentPane(panel);
		//双击增加控件
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() >= 2){
					isRun = false;
					Point p = new Point(e.getX() - 20, e.getY() - 40);
					CircularLabel label = new CircularLabel((count++) + "",p, 10, Color.red, getBackground());
					panel.add(label);
					figure.addVertex(label.getVertex());
					labelSet.add(label);
					repaint();
				}
			}
			
		});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, WIDTH, HEIGHT);
		setVisible(true);
	}
	public static void main(String[] args) {
		new OneStrokeFrame();
	}
	
	public static CircularLabel getCircularLabel(String name){
		for (CircularLabel circularLabel : labelSet) {
			if(circularLabel.getVertexName().equals(name)){
				return circularLabel;
			}
		}
		return null;
	}
}
