package io.testpiped1;

import java.io.PipedInputStream;

public class Receiver extends Thread{
	
	private PipedInputStream in = new PipedInputStream();
	
	public PipedInputStream getInputStream(){
		return in;
	}
	
	@Override
	public void run() {
		readMessageOnce();
	}

	public void readMessageOnce() {
		byte[] buf = new byte[2048];
		try {
			int len = in.read(buf);
			System.out.println(new String(buf,0,len));
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void readMessageContinued(){
		int total = 0;
		while (true) {
			byte[] buf = new byte[1024];
			try {
				int len = in.read();
				total += len;
				System.out.println(new String(buf,0,len));
				if(total > 1024)
					break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
