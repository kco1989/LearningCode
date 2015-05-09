package socket.v1;

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
		SocketUtils socketUtils = null;
		//新建一个Socket Server服务
		try (ServerSocket s = new ServerSocket()){
			s.setSoTimeout(0);
			//绑定指定端口
			s.bind(new InetSocketAddress(SocketUtils.SERVER_LISTEN_PORT));
			//等待客户端连接
			Socket socket = s.accept();
			//封装socket，用于读写，和关闭
			socketUtils = new SocketUtils(socket);
			//读取客户端数据
			System.out.println("client say:" + socketUtils.read());
			//向客户端写数据
			socketUtils.write("hello");
			System.out.println("client say:" + socketUtils.read());
			socketUtils.write("fine");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(socketUtils != null){
				socketUtils.close();
			}
		}
		System.out.println("结束服务");
		
	}
}
