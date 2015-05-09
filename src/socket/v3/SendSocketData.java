package socket.v3;

import java.net.Socket;

/**
 * 发送socket数据
 * @author kco1989
 * @email  kco1989@qq.com
 * @date   2015年5月7日
 */
public class SendSocketData implements Runnable {

	private String buffer;
	private String ip;
	private int port;

	public SendSocketData(String buffer, String ip, int port) {
		this.buffer = buffer;
		this.ip = ip;
		this.port = port;
	}

	@Override
	public void run() {
		try {
			Socket socket = new Socket(ip,port);
			SocketUtils socketUtils = new SocketUtils(socket);
			socketUtils.write(buffer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
