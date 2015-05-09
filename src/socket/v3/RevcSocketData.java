package socket.v3;

import java.net.Socket;

/**
 * 接收socket数据
 * @author kco1989
 * @email  kco1989@qq.com
 * @date   2015年5月7日
 */
public class RevcSocketData implements Runnable{

	private SocketUtils socketUtils;
	private Socket socket;
	public RevcSocketData(Socket socket) {
		socketUtils = new SocketUtils(socket);
		this.socket = socket;
//		System.out.println("RevcSocketData:" + socket);
//		System.out.println(socket.getInetAddress());
	}

	@Override
	public void run()  {
		try {
			String data = socketUtils.read();
			System.out.println(socket.getInetAddress().getHostName() + ":" + socket.getPort() + 
					" said:" + data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
