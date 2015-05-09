package socket.v1;

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
		SocketUtils socketUtils = null;
		//�½�һ��Socket Server����
		try (ServerSocket s = new ServerSocket()){
			s.setSoTimeout(0);
			//��ָ���˿�
			s.bind(new InetSocketAddress(SocketUtils.SERVER_LISTEN_PORT));
			//�ȴ��ͻ�������
			Socket socket = s.accept();
			//��װsocket�����ڶ�д���͹ر�
			socketUtils = new SocketUtils(socket);
			//��ȡ�ͻ�������
			System.out.println("client say:" + socketUtils.read());
			//��ͻ���д����
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
		System.out.println("��������");
		
	}
}
