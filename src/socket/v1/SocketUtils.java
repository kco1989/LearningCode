package socket.v1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
/**
 * 封装socket的读写和关闭操作
 * @author kco1989
 * @email  kco1989@qq.com
 * @date   2015年5月6日
 */
public class SocketUtils {

	public static final int SERVER_LISTEN_PORT = 12345;
	private Socket socket;
	public SocketUtils(Socket socket){
		this.socket = socket;
		try {
			socket.setSoTimeout(10000);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	public String read(){
		try {
			InputStream is = socket.getInputStream();
			byte[] buffer = new byte[20];
			is.read(buffer);
			return new String(buffer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Read Error";
	}
	
	public void write(String data){
		try {
			OutputStream os = socket.getOutputStream();
			os.write(data.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void close(){
		if(socket != null){
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
