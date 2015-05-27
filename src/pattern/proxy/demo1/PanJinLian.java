package pattern.proxy.demo1;
/**
 * 潘金莲
 * @author kco1989
 * @email  kco1989@qq.com
 * @data   2015年5月24日
 */
public class PanJinLian implements KindWomen {

	@Override
	public void makeEyesWithMan() {
		System.out.println("潘金莲抛媚眼........");

	}

	@Override
	public void happyWithMan() {
		System.out.println("潘金莲和男人在寻乐........");

	}

}
