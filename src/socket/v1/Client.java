package socket.v1;

import java.net.Socket;
/**
 * �ͻ���
 * @author kco1989
 * @email  kco1989@qq.com
 * @date   2015��5��6��
 */
public class Client {
	
	public static void main(String[] args) {
		
		System.out.println("����socket����");
		SocketUtils socketUtils = null;
		try {
			//���ӷ����
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
		System.out.println("�Ͽ�����");
	}
}
