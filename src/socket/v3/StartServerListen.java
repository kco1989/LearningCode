package socket.v3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;


/**
 * socket监听服务
 * @author kco1989
 * @email  kco1989@qq.com
 * @date   2015年5月7日
 */
public class StartServerListen implements Runnable {

	private ServerSocket serverSocket;
	private ExecutorService pool;
	private boolean isRunning;
	public StartServerListen(ServerSocket serverSocket,ExecutorService pool) {
		this.serverSocket = serverSocket;
		this.pool = pool;
		this.isRunning = true;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	@Override
	public void run() {
		while(isRunning){
			try {
				Socket socket = serverSocket.accept();
				//System.out.println("有socket接入"+socket);
				pool.execute(new RevcSocketData(socket));
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}
		close();
	}

	private void close() {
		if(serverSocket != null){
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pool != null){
			pool.shutdown();
		}
	}

}
