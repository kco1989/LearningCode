package socket.v3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * socket工具类
 * @author kco1989
 * @email  kco1989@qq.com
 * @date   2015年5月7日
 */
public class SocketUtils {

	/**
	 * 服务端监听的端口
	 */
	public static final int SERVER_LISTEN_PORT = 44444;
	public static final String SERVER_IP = "127.0.0.1";
	public static final String CLIENT_IP = "127.0.0.1";
	/**
	 * 客户端监听的端口
	 */
	public static final int CLIENT_LISTEN_PORT = 55555;
	/**
	 * socket保持时间
	 */
	public static final int SO_TIMEOUT = 3600000;
	
	public static final String MAGIC = "0055";
	public static final String DATA_LEN_ZERO = "000000";
	private static final int BUFFER_SIZE = 1024;
	private Socket socket;

	public SocketUtils(Socket socket) {
		super();
		this.socket = socket;
		try {
			this.socket.setSoTimeout(SO_TIMEOUT);
		} catch (java.net.SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 读取socket数据
	 * 先读取4位的MAGIC
	 * 再读取6位的数据长度
	 * 最后读取数据
	 * @return
	 * @throws SocketException
	 */
	public String read() throws SocketException{
		try {
			InputStream is = socket.getInputStream();
			byte[] magic = new byte[MAGIC.length()];
			is.read(magic);
			if(magic == null || !(new String(magic).equalsIgnoreCase(MAGIC))){
				throw new SocketException("读取socket数据不正确!");
			}
			byte[] lenByte = new byte[DATA_LEN_ZERO.length()];
			is.read(lenByte);
			if (lenByte == null || lenByte.length != DATA_LEN_ZERO.length()) {
				throw new SocketException("读取socket数据不正确!");
			}
			int len = 0;
			try {
				len = Integer.parseInt(new String(lenByte));
			} catch (Exception e) {
				throw new SocketException("读取socket数据不正确!",e);
			}
			byte[] buffer = new byte[len];
			is.read(buffer);
			return new String(buffer);
		} catch (Exception e) {
			throw new SocketException("读取socket错误!",e);
		} 
		
	}
	/**
	 * 向socket写数据
	 * 先写4位的MAGIC
	 * 再写6位的数据长度
	 * 最后写数据
	 * @param data
	 * @throws SocketException
	 */
	public void write(String data) throws SocketException{
		if(data == null || data.length() == 0){
			return;
		}
		int dataLen = data.getBytes().length;
		String strLen = DATA_LEN_ZERO.substring(0,
				DATA_LEN_ZERO.length() - (dataLen + "").length()) 
				+ dataLen;
		try {
			OutputStream os = socket.getOutputStream();
			os.write(MAGIC.getBytes());
			os.write(strLen.getBytes());
			os.write(data.getBytes());
		} catch (Exception e) {
			throw new SocketException("发送socket错误!",e);
		}
	}
	/**
	 * 关闭socket
	 */
	public void close(){
		if(socket != null){
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 开启主线程
	 * @param isServer
	 */
	public static void main(boolean isServer){
		ExecutorService pool = null;
		ServerSocket ss = null;
		try {
			String info = isServer ? "服务器" : "客户端";
			System.out.println(info + "开启监听");
			int serverPort = isServer ? SERVER_LISTEN_PORT : CLIENT_LISTEN_PORT;
			int clientPort = isServer ? CLIENT_LISTEN_PORT : SERVER_LISTEN_PORT;
			System.out.println(info + "监听端口号:" + serverPort);
			String ip = isServer ? CLIENT_IP : SERVER_IP;
			ss = new ServerSocket();
			pool = Executors.newFixedThreadPool(10);
			ss.setSoTimeout(0);
			ss.bind(new InetSocketAddress(serverPort));
			StartServerListen startServerRunnable = new StartServerListen(ss, pool);
			pool.execute(startServerRunnable);
			pool.execute(new ReadSystemIn(startServerRunnable, pool, ip, clientPort));
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
}
