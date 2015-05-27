package pattern.proxy.demo1;
/**
 * 王婆太老,男人看不上,所以就做这一类女人的代理
 * @author kco1989
 * @email  kco1989@qq.com
 * @data   2015年5月24日
 */
public class WangPo implements KindWomen{

	private KindWomen kindWomen;
	
	public WangPo(){
		this(new PanJinLian());
	} 
	
	public WangPo(KindWomen kindWomen){
		this.kindWomen = kindWomen;
	} 
	@Override
	public void makeEyesWithMan() {
		kindWomen.makeEyesWithMan();
		
	}

	@Override
	public void happyWithMan() {
		kindWomen.happyWithMan();
		
	}

}
