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
				//�ȴ��ͻ�������
				Socket socket = serverSocket.accept();
				//��װsocket�����ڶ�д���͹ر�
				socketUtils = new SocketUtils(socket);
				//��ȡ�ͻ�������
				System.out.println("client say:" + socketUtils.read());
				//��ͻ���д����
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
