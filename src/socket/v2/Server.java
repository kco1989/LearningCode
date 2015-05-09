package socket.v2;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 服务器和客户端只做最简单的收发
 * @author kco1989
 * @email  kco1989@qq.com
 * @date   2015年5月6日
 */
public class Server {
	
	public static void main(String[] args) {
		System.out.println("启动socket服务");
		//新建一个Socket Server服务
		try (ServerSocket s = new ServerSocket()){
			s.setSoTimeout(0);
			//绑定指定端口
			s.bind(new InetSocketAddress(SocketUtils.SERVER_LISTEN_PORT));
			new Thread(new StartServer(s)).start();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("结束服务");
		
	}
}
