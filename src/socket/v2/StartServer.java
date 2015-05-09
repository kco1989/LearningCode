package socket.v2;

import java.net.ServerSocket;
import java.net.Socket;

public class StartServer implements Runnable {

	private ServerSocket serverSocket;
	public StartServer(ServerSocket serverSocket){
		this.serverSocket = serverSocket;
	}
	@Override
	public void run() {
		while (true) {
			SocketUtils socketUtils = null;
			try {
				//等待客户端连接
				Socket socket = serverSocket.accept();
				//封装socket，用于读写，和关闭
				socketUtils = new SocketUtils(socket);
				//读取客户端数据
				System.out.println("client say:" + socketUtils.read());
				//向客户端写数据
				socketUtils.write("hello");
				System.out.println("client say:" + socketUtils.read());
				socketUtils.write("fine");
			} catch (Exception e) {
				// TODO: handle exception
			} finally{
				if(socketUtils != null){
					socketUtils.close();
				}
			}
		}
		
		

	}

}
