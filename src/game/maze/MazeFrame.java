package game.maze;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;

public class MazeFrame extends JFrame {

	public static final int LINE_SIZE = 10;
	public static final int START_X = 50;
	public static final int START_Y = 50;
	private Maze maze;
	public MazeFrame(int row,int column){
		maze = new Maze(row, column);
		setBounds(400, 100, 600 , 600);
		setTitle("迷宫");
		Button btnRun = new Button("run");
		Button btnNext = new Button("next");
		MazePanl mazePanl = new MazePanl(maze);
		mazePanl.add(btnRun);
		mazePanl.add(btnNext);
		getContentPane().add(mazePanl);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		btnRun.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				mazePanl.run();
				System.out.println("run");
			}
			
		});
		btnNext.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("next");
				mazePanl.next();
			}
			
		});
		
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new MazeFrame(50,50);
	}
	/**
	 * 画图
	 * @author kco1989
	 * @email  kco1989@qq.com
	 * @data   2015年5月22日
	 */
	public static class MazePanl extends Panel{
		private Maze maze;
		//private List<Integer> solution;
		private static boolean isDrawOver = false;
		private static boolean isRun;
		
		public MazePanl(Maze maze){
			this.maze = maze;
			maze.buildMazeArea();
			//solution = maze.getSolution();
			System.out.println(maze);
			//isRun = false;
		}
		public void next() {
			maze.buildMazeArea();
			isRun = false;
			repaint();
		}
		public void run() {
			isRun = true;
			repaint();
		}
		/**
		 * 更新界面
		 */
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			for (int i = 0; i < maze.getMazeArea().length; i++) {
				draw(g,maze.getMazeArea()[i]);
			}
			isDrawOver = true;
			
			if(isRun){
				draw(g,maze.getSolution());
			}
		}

		private void draw(Graphics g, List<Integer> solution) {
			g.setColor(Color.red);
			for (int i = 1; i < solution.size(); i++) {
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int preIndex = solution.get(i - 1);
				int currentIndex = solution.get(i);
				draw(g,preIndex,currentIndex);
			}
		}
		
		private void draw(Graphics g, int preIndex, int currentIndex) {
			int preColumn = preIndex / maze.getMaxColumn();
			int preRow = preIndex % maze.getMaxColumn();
			int curColumn = currentIndex / maze.getMaxColumn();
			int curRow = currentIndex % maze.getMaxColumn();
			int x1 = START_X + LINE_SIZE * preRow + LINE_SIZE / 2;
			int y1 = START_Y + LINE_SIZE * preColumn + LINE_SIZE /2;
			int x2 = START_X + LINE_SIZE * curRow + LINE_SIZE / 2;
			int y2 = START_Y + LINE_SIZE * curColumn + LINE_SIZE /2;
			g.drawLine(x1, y1, x2, y2);
			
		}
		/**
		 * 画方块的四条边
		 * @param g
		 * @param box
		 */
		private void draw(Graphics g, Box box) {
			if(box.getIndex() == maze.getStartPoint() || box.getIndex() == maze.getOverPoint()){
				int column = box.getIndex() / maze.getMaxColumn();
				int row = box.getIndex() % maze.getMaxColumn();
				int x = START_X + LINE_SIZE * row ;//+ LINE_SIZE / 2;
				int y = START_Y + LINE_SIZE * column;// + LINE_SIZE / 2;
				Color color = g.getColor();
				if(box.getIndex() == maze.getStartPoint()){
					g.setColor(Color.BLUE);
				}else{
					g.setColor(Color.green);
				}
				g.fillOval(x, y, LINE_SIZE , LINE_SIZE);
				g.setColor(color);
			}
			for (int i = 0; i < box.getWalls().length; i++) {
				draw(g,box,i);
			}
		}

		/**
		 * 画方块的一条边
		 * @param g
		 * @param box
		 * @param dirction
		 */
		private void draw(Graphics g, Box box, int dirction) {
			if(box.getWalls()[dirction] == Wall.ACCESS){
				return;
			}
			int column = box.getIndex() / maze.getMaxColumn();
			int row = box.getIndex() % maze.getMaxColumn();
			int x1 = 0;
			int y1 = 0;
			int x2 = 0;
			int y2 = 0;
			switch (dirction) {
			case Box.TOP:
				x1 = START_X + LINE_SIZE * row;
				y1 = START_Y + LINE_SIZE * column;
				x2 = START_X + LINE_SIZE * (row + 1);
				y2 = START_Y + LINE_SIZE * column;
				break;
			case Box.RIGHT:
				x1 = START_X + LINE_SIZE * (row + 1);
				y1 = START_Y + LINE_SIZE * column;
				x2 = START_X + LINE_SIZE * (row + 1);
				y2 = START_Y + LINE_SIZE * (column + 1);
				break;
			case Box.DOWN:
				x1 = START_X + LINE_SIZE * row;
				y1 = START_Y + LINE_SIZE * (column + 1);
				x2 = START_X + LINE_SIZE * (row + 1);
				y2 = START_Y + LINE_SIZE * (column + 1);
				break;
			case Box.LEFT:
				x1 = START_X + LINE_SIZE * row;
				y1 = START_Y + LINE_SIZE * column;
				x2 = START_X + LINE_SIZE * row ;
				y2 = START_Y + LINE_SIZE * (column + 1);	
				break;
			default:
				break;
			}
			g.drawLine(x1, y1, x2, y2);
		}
	} 
	
	
}
