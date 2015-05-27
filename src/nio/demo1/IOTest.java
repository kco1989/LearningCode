package nio.demo1;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class IOTest {
	
	public static void main(String[] args) throws Exception {
		InputStream in = IOTest.class.getResourceAsStream("demo.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		System.out.println(br.readLine());
		System.out.println(br.readLine());
		System.out.println(br.readLine());
		System.out.println(br.readLine());
		br.close();
		Path path = Paths.get("demo.txt");
		File file = path.toFile();
		System.out.println(file.getAbsolutePath());
	}

	private static boolean bufferFull(int bytesRead) {
		// TODO Auto-generated method stub
		return bytesRead == 48;
	}
}
