package game.maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 迷宫
 * @author kco1989
 * @email  kco1989@qq.com
 * @data   2015年5月22日
 */
public class Maze {

	/** 迷宫的最大行*/
	private int maxRow;
	/** 迷宫的最大列 */
	private int maxColumn;
	/** 迷宫中的方块*/
	private Box[] mazeArea;
	/** 已经生成完成*/
	private boolean isBulidOK ;;
	/**
	 * 不相交集合
	 * value是存放等价的索引
	 * key是等价索引的最小值
	 * 如果map的keySet只剩下一个,说明所有的方块都已经连通了
	 */
	private Map<Integer, Set<Integer>> map;
	/** 迷宫开始点 */
	private int startPoint;
	/** 迷宫结束点*/
	private int overPoint;
	public Maze(int row,int column){
		if(row < 3){
			maxRow = 3;
		}else{
			maxRow = row;
		}
		
		if(column < 3){
			maxColumn = 3;
		}else{
			maxColumn = column;
		}
		
		init();
	}
	
	private void init() {
		isBulidOK = false;
		mazeArea = new Box[maxRow * maxColumn];
		map = new HashMap<Integer, Set<Integer>>();
		for (int i = 0; i < mazeArea.length; i++) {
			mazeArea[i] = new Box(i);
			Set<Integer> set = new HashSet<Integer>();
			set.add(i);
			map.put(i, set);
		}
		startPoint = 0;
		overPoint = mazeArea.length - 1;
		
		mazeArea[startPoint].setWalls(Box.LEFT, Wall.ACCESS);
		mazeArea[overPoint].setWalls(Box.RIGHT, Wall.ACCESS);
		
	}
	/**
	 * 查找与索引x等价的最小索引值
	 * @param x
	 * @return
	 */
	private int find(int x){
		if(map.get(x) != null){
			return x;
		}
		for (Integer key : map.keySet()) {
			if(map.get(key).contains(x)){
				return key;
			}
		}
		return x;
	}
	/**
	 * 让索引x和索引y连通
	 * @param x
	 * @param y
	 */
	private void union(int x,int y){
		int findx = find(x);
		int findy = find(y);
		int min = Math.min(findx, findy);
		int max = Math.max(findx, findy);
		Set<Integer> minSet = map.get(min);
		Set<Integer> maxSet = map.get(max);
		minSet.addAll(maxSet);
		map.remove(max);
	}
	/**
	 * 创建迷宫
	 */
	public void buildMazeArea(){
		init();
		while(map.keySet().size() != 1){
			int currentIndex = getRandomIndex();
			int dirction = getRandomDirection();
			int nextIndex = getNextIndex(currentIndex,dirction);
			if(nextIndex == -1){
				continue;
			}
			if(find(currentIndex) != find(nextIndex)){
				union(currentIndex, nextIndex);
				switch (dirction) {
				case Box.TOP:
					mazeArea[currentIndex].setWalls(Box.TOP, Wall.ACCESS);
					mazeArea[nextIndex].setWalls(Box.DOWN, Wall.ACCESS);
					break;
				case Box.RIGHT:
					mazeArea[currentIndex].setWalls(Box.RIGHT, Wall.ACCESS);
					mazeArea[nextIndex].setWalls(Box.LEFT, Wall.ACCESS);
					break;
				case Box.DOWN:
					mazeArea[currentIndex].setWalls(Box.DOWN, Wall.ACCESS);
					mazeArea[nextIndex].setWalls(Box.TOP, Wall.ACCESS);
					break;
				case Box.LEFT:
					mazeArea[currentIndex].setWalls(Box.LEFT, Wall.ACCESS);
					mazeArea[nextIndex].setWalls(Box.RIGHT, Wall.ACCESS);
					break;

				default:
					break;
				}
			}
		}
		isBulidOK = true;
	}
	
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < maxRow; i++) {
			for (int j = 0; j < maxColumn; j++) {
				sb.append(mazeArea[i * maxColumn + j]+",");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	/**
	 * 根据索引x,已经方向dirction,获取需要连通的方块,
	 * 如果方块超出了迷宫的界限,则返回null
	 * @param currentIndex
	 * @param dirction
	 * @return
	 */
//	private Box getNextBox(int currentIndex, int dirction) {
//		int row = currentIndex / maxColumn;
//		int column = currentIndex % maxColumn;
//		int newRow = row + Box.DIRCTION[dirction][0];
//		int newColumn = column + Box.DIRCTION[dirction][1];
//		if(newRow < 0 || newColumn < 0 || newRow >= maxRow || newColumn >= maxColumn){
//			return null;
//		}
//		return mazeArea[newRow * maxColumn + newColumn];
//	}
	/**
	 * 
	 * @param currentIndex
	 * @param dirction
	 * @return
	 */
	private int getNextIndex(int currentIndex, int dirction) {
		int row = currentIndex / maxColumn;
		int column = currentIndex % maxColumn;
		int newRow = row + Box.DIRCTION[dirction][0];
		int newColumn = column + Box.DIRCTION[dirction][1];
		if(newRow < 0 || newColumn < 0 || newRow >= maxRow || newColumn >= maxColumn){
			return -1;
		}
		return newRow * maxColumn + newColumn;
	}

	/**
	 * 随机索引需要连通的方块索引
	 * @return
	 */
	private int getRandomIndex(){
		List<Integer> keyList = new ArrayList<Integer>();
		keyList.addAll(map.keySet());
		int keyIndex = new Random().nextInt(keyList.size());
		
		List<Integer> valueList = new ArrayList<Integer>();
		valueList.addAll(map.get(keyList.get(keyIndex)));
		int valueIndex = new Random().nextInt(valueList.size());
		return valueList.get(valueIndex);
	}
//	public int getRandomIndex(){
//		int minValueCountIndex = 0;
//		List<Integer> keyList = new ArrayList<Integer>();
//		keyList.addAll(map.keySet());
//		for (int i = 0; i < mazeArea.length; i++) {
//			if(map.get(keyList.get(i)).size() < map.get(keyList.get(minValueCountIndex)).size()){
//				minValueCountIndex = i;
//			}
//			if(map.get(keyList.get(minValueCountIndex)).size() == 1){
//				break;
//			}
//		}
//		
//		List<Integer> valueList = new ArrayList<Integer>();
//		valueList.addAll(map.get(keyList.get(minValueCountIndex)));
//		int valueIndex = new Random().nextInt(valueList.size());
//		return valueList.get(valueIndex);
//	}
	/**
	 * 随机获取方向
	 * @return
	 */
	private int getRandomDirection(){
		return new Random().nextInt(4);
	}
	
//	public static void main(String[] args) {
//		Maze maze = new Maze(3, 3);
//		maze.buildMazeArea();
//		System.out.println(maze);
//	}

	public Box[] getMazeArea() {
		return mazeArea;
	}

	public int getMaxRow() {
		return maxRow;
	}

	public int getMaxColumn() {
		return maxColumn;
	}
	
	/**
	 * 获取迷宫的解法
	 * @return
	 */
	public List<Integer> getSolution(){
		if(!isBulidOK){
			return null;
		}
		List<Integer> solution = new ArrayList<Integer>();
		int[] mazeTag = new int[maxRow * maxColumn];
		for (int i = 0; i < 3; i++) {
			tryRun(mazeTag,startPoint,i,0);
		}
		int minCount = mazeTag[overPoint];
		generateSolution(mazeTag,solution,overPoint,minCount);

		for (int i = 0; i < maxRow; i++) {
			for (int j = 0; j < maxColumn; j++) {
				System.out.print(mazeTag[i * maxColumn + j] + "\t");
			}
			System.out.println();
		}
		System.out.println(solution);
		return solution;
	}
	
	/**
	 * 最短距离算法
	 * @param mazeTag	存放着迷路路径的集合
	 * @param solution	存放最短距离的点的集合
	 * @param currentIndex 当前位置
	 * @param value	当前位置的值
	 */
	private void generateSolution(int[] mazeTag, List<Integer> solution, int currentIndex,
			int value) {
		//如果当前位置是开始点,则返回
		if(currentIndex == startPoint){
			solution.add(currentIndex);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			//如果这个方向是一面墙,则返回
			if(mazeArea[currentIndex].getWalls()[i] == Wall.BLOCK){
				continue;
			}
			//获取下一个位置
			int nextIndex = getNextIndex(currentIndex, i);
			//该位置无效,则返回
			if(nextIndex == -1){
				continue;
			}
			//该位置是最佳解得下一个点,则添加到currentIndex,再对这个点进行探索,探索结束返回
			if(mazeTag[nextIndex] == (value - 1)){
				solution.add(currentIndex);
				generateSolution(mazeTag, solution, nextIndex, value - 1);
				return;
			}
		}
	}
	/**
	 * 尝试走迷宫
	 * @param mazeTag
	 * @param currentIndex
	 * @param dirction
	 */
	private void tryRun(int[] mazeTag, int currentIndex, int dirction,int value) {
		//如果这个方向是一面墙,则返回
		if(mazeArea[currentIndex].getWalls()[dirction] == Wall.BLOCK){
			return;
		}
		//如果已经到最后一块方块,则返回
		if(currentIndex == overPoint){
			return;
		}
		int nextIndex = getNextIndex(currentIndex, dirction);
		if(nextIndex == -1){
			return;
		}
		mazeTag[nextIndex] = value + 1;
		for (int i = 0; i < 4; i++) {
			//不要走回头路 up(0)+down(2) = 2;left(1)+right(3)=4
			if((i ==  Box.DOWN && dirction == Box.TOP) ||
					(i ==  Box.TOP && dirction == Box.DOWN) ||
					(i ==  Box.LEFT && dirction == Box.RIGHT) ||
					(i ==  Box.RIGHT && dirction == Box.LEFT)){
				continue;
			}
			tryRun(mazeTag,nextIndex,i,value + 1);
		}
	}

}
