package io.testpiped1;

import java.io.PipedOutputStream;

public class Sender extends Thread{
	
	private PipedOutputStream out = new PipedOutputStream();
	
	public PipedOutputStream getoOutputStream(){
		return out;
	}
	
	@Override
	public void run() {
		writeShortMessage();
		
	}

	private void writeShortMessage() {
		String strInfo = "this is a short message";
		try {
			out.write(strInfo.getBytes());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void writeLongMessage(){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 102; i++) {
			sb.append("0123456789");
		}
		sb.append("abcdefghijklmnopqrstuvwxyz");
		String str = sb.toString();
		try {
			out.write(str.getBytes());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
