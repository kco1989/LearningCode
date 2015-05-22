package Thread;

/**
 * 测试Thread.join
 * join() 让"主线程"等待"子线程"结束之后才能继续运行
 * @author kco1989
 * @email  kco1989@qq.com
 * @data   2015年5月9日
 */
public class TestJoin {

	static class Father extends Thread{
		@Override
		public void run() {
			System.out.println("father start run");
			Son s = new Son();
			s.start();
			try {
				s.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("father stop run");
			
		}
	}
	static class Son extends Thread{
		@Override
		public void run() {
			System.out.println("Son start run");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Son stop run");
		}
	}
	
//	public static void main(String[] args) {
//		System.out.println("main start run");
//		new Father().start();
//		System.out.println("main stop run");
//	}
	public static void main(String[] args) {
		System.out.println("main start run");
		Father f1 = new Father();
		f1.start();
		try {
			f1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("main stop run");
	}
}

