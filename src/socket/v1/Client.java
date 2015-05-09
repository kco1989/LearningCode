package socket.v1;

import java.net.Socket;
/**
 * 客户端
 * @author kco1989
 * @email  kco1989@qq.com
 * @date   2015年5月6日
 */
public class Client {
	
	public static void main(String[] args) {
		
		System.out.println("连接socket服务");
		SocketUtils socketUtils = null;
		try {
			//连接服务端
			Socket socket = new Socket("127.0.0.1",SocketUtils.SERVER_LISTEN_PORT);
			socketUtils = new SocketUtils(socket);
			socketUtils.write("i am client");
			System.out.println("server say:" + socketUtils.read());
			socketUtils.write("how are you?");
			System.out.println("server say:" + socketUtils.read());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(socketUtils != null){
				socketUtils.close();
			}
		}
		System.out.println("断开服务");
	}
}
