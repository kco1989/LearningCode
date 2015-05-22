package io.testpiped1;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedStreamTest {
	
	public static void main(String[] args){
		Sender t1 = new Sender();
		Receiver t2 = new Receiver();
		
		PipedOutputStream out = t1.getoOutputStream();
		PipedInputStream in = t2.getInputStream();
		try {
			in.connect(out);
			t1.start();
			t2.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
