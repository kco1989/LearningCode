package game.maze;

/**
 * 迷宫的墙
 * @author kco1989
 * @email  kco1989@qq.com
 * @data   2015年5月22日
 */
public enum Wall {
	//access表示连通,block表示有墙
	BLOCK(0),ACCESS(1);

	private int value;
	Wall(int value){
		this.value = value;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return value + "";
	}
	
	
}
