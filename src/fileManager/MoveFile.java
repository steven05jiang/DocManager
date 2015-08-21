package fileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MoveFile {
	public boolean checkPath(String src, String dst){
		if(src == null || dst == null) return false;
		File srcDir = new File(src);
		File dstDir = new File(dst);
		if(srcDir.isDirectory() && dstDir.isDirectory()) return true;
		return false;
	}
	
	public boolean copyFile(String src, String dst, String target){
		if(!checkPath(src, dst)) return false;
		File srcDir = new File(src);
		File dstDir = new File(dst);
		String[] docs = srcDir.list();
		int seq = 0;
		for(String s : docs){
			File t = new File(src +"\\" + s);
			if(t.isDirectory() && !dst.equals(t.getAbsolutePath())) 
				copyFile(t.getAbsolutePath(), dst, target);
			else if(t.isFile() && s.equals(target)){
				while(new File(dst+"\\"+ seq + target).isFile()) ++seq;
				transFile(t, dstDir, seq + target);
				++seq;
			}
		}
		return true;
	}
	
	public void transFile(File src, File dst, String name){
		File target = new File(dst+"\\" + name);
//		FileChannel in = null;
//		FileChannel out = null;
//		FileInputStream inStream = null;
//		FileInputStream outStream = null;
//		try {
//			target.createNewFile();
//			inStream = new FileInputStream(src);
//			outStream = new FileInputStream(target); 
//			in = inStream.getChannel();
//			out = outStream.getChannel();
//			in.transferTo(0, in.size(), out);
//			in.close();
//			out.close();
//			inStream.close();
//			outStream.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			FileReader input = new FileReader(src);
			FileWriter output = new FileWriter(target);
			int read = input.read();
			while(read != -1){
				output.write(read);
				read = input.read();
			}
			input.close();
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
