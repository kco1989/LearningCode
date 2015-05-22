package game.maze;

import java.util.Arrays;

/**
 * 迷宫中的方块,每个方块有4面墙
 * @author kco1989
 * @email  kco1989@qq.com
 * @data   2015年5月22日
 */
public class Box {
	
	public static final int TOP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	
	public static final int[][] DIRCTION = new int[][]{
		{-1,0},{0,1},{1,0},{0,-1}
	};
	
	//方块在迷宫数组中的索引
	private int index;
	//4面墙
	private Wall[] walls;
	
	
	public Box(int index){
		this.index = index;
		walls = new Wall[4];	
		for (int k = 0; k < walls.length; k++) {
			walls[k] = Wall.BLOCK;
		}
	}

	public int getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return "[" +Arrays.toString(walls) + "]";
	}

	public void setWalls(int dirction,Wall wall) {
		this.walls[dirction] = wall;
	}

	public Wall[] getWalls() {
		return walls;
	}
	
	
}
