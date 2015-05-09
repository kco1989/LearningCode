package socket.v3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;

/**
 * 读取System.in输入的数据
 * @author kco1989
 * @email  kco1989@qq.com
 * @date   2015年5月7日
 */
public class ReadSystemIn implements Runnable{

	private StartServerListen startServerListen;
	private ExecutorService pool;
	private int port;
	private String ip;
	public ReadSystemIn(StartServerListen startServerListen,ExecutorService pool,String clientIp,int port){
		this.startServerListen = startServerListen;
		this.pool = pool;
		this.port = port;
		this.ip = clientIp;
	}
	@Override
	public void run() {
		try(InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);) {
			String buffer = "";
			while ((buffer = br.readLine()) != null){
				pool.execute(new SendSocketData(buffer,ip,port));
				System.out.println("I said: " + buffer);
				if(buffer.equalsIgnoreCase("quit")){
					//startServerListen.setRunning(false);
					System.exit(0);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
//
//	public static void main(String[] args) {
//		ReadSystemIn rsi = new ReadSystemIn(null, null, 0);
//		rsi.run();
//	}
}
