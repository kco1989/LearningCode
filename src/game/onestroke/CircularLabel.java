package game.onestroke;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 自定义控件
 * @author kco1989
 * @email  kco1989@qq.com
 * @data   2015年5月27日
 */
public class CircularLabel extends JLabel{
	
	private static final long serialVersionUID = 131504781980241518L;
	//画图起始点,不是圆点
	private Point circular;
	//半径
	private int radius;
	//颜色
	private Color colorCircular;
	//背景颜色
	private Color background;
	private String vertexName;
	public String getVertexName() {
		return vertexName;
	}
	/**
	 * 构造方法
	 * @param name
	 * @param circular
	 * @param radius
	 * @param colorCircular
	 * @param background
	 */
	public CircularLabel(String name,Point circular, int radius, Color colorCircular,
			Color background) {
		this.vertexName = name;
		this.circular = circular;
		this.radius = radius;
		this.colorCircular = colorCircular;
		this.background = background;
		setCustomizeIcon();
		setBounds(circular.x, circular.y, radius*2, radius*2);
		//增加拖拽事件
		addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				JPanel panel = (JPanel)getParent();
				circular.x += e.getX();
				circular.y += e.getY();
				if(circular.x <= 0){
					circular.x = 0;
				}
				if(circular.y <= 0){
					circular.y = 0;
				}
				if(circular.x >= OneStrokeFrame.WIDTH - radius*2 - 20){
					circular.x = OneStrokeFrame.WIDTH - radius*2 - 20;
				}
				if(circular.y >= OneStrokeFrame.HEIGHT - radius*2 - 40){
					circular.y = OneStrokeFrame.HEIGHT - radius*2 - 40;
				}
				setBounds(circular.x, circular.y, radius*2, radius*2);
				panel.repaint();
			}
		});
		//增加双击删除,单击往图里增加点和路径
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JPanel panel = (JPanel)getParent();
				if(e.getClickCount() >= 3){
					if(OneStrokeFrame.start == CircularLabel.this){
						OneStrokeFrame.start = null;
					}
					panel.remove(CircularLabel.this);
					OneStrokeFrame.figure.removeVertex(getVertex());
					OneStrokeFrame.labelSet.remove(CircularLabel.this);
				}else{
					if(OneStrokeFrame.start == null){
						OneStrokeFrame.start = CircularLabel.this;
					}else if(OneStrokeFrame.start != CircularLabel.this){
						
						Vertex startVertex = OneStrokeFrame.figure.getVertex(OneStrokeFrame.start.getVertex());
						if(startVertex.getEdge(CircularLabel.this.getVertex()) == null){
							//这里增加双向路径
							startVertex.add(CircularLabel.this.getVertex());
							OneStrokeFrame.figure.getVertex(CircularLabel.this.getVertex()).add(startVertex);
						}else{
							//这是除去双向路径
							startVertex.remove(CircularLabel.this.getVertex());
							OneStrokeFrame.figure.getVertex(CircularLabel.this.getVertex()).remove(startVertex);
						}
						OneStrokeFrame.start = null;
					}
				}
				panel.repaint();
			}
			
		});
	}
	/**
	 * 自定义控件的显示方式
	 */
	private void setCustomizeIcon() {
		Image image = new BufferedImage(radius*2, radius*2, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(background);
		g.fillRect(0, 0, radius*2, radius*2);
		g.setColor(colorCircular);
		g.fillArc(0, 0, radius*2, radius*2, 0, 360);
		ImageIcon icon = new ImageIcon(image);
		setIcon(icon);
	}

	/**
	 * 通过vertexName获取顶点对象
	 * @return
	 */
	public Vertex getVertex(){
		return new Vertex(vertexName);
	}

	public Point getCircular() {
		return circular;
	}

	public int getRadius() {
		return radius;
	}

	public Color getColorCircular() {
		return colorCircular;
	}

	public Color getBackground() {
		return background;
	}
	
}
