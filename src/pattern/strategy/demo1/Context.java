package pattern.strategy.demo1;

/**
 * 计谋有了,那还要锦囊
 */
public class Context {
	private IStrategy strategy;
	public Context(IStrategy strategy){
		this.strategy = strategy;
	}
	
	public void operate(){
		strategy.operate();
	}
}
