package socket.v2;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * �������Ϳͻ���ֻ����򵥵��շ�
 * @author kco1989
 * @email  kco1989@qq.com
 * @date   2015��5��6��
 */
public class Server {
	
	public static void main(String[] args) {
		System.out.println("����socket����");
		//�½�һ��Socket Server����
		try (ServerSocket s = new ServerSocket()){
			s.setSoTimeout(0);
			//��ָ���˿�
			s.bind(new InetSocketAddress(SocketUtils.SERVER_LISTEN_PORT));
			new Thread(new StartServer(s)).start();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("��������");
		
	}
}
