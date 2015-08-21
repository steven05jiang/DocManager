package fileManager;

import java.io.File;

public class MoveFile {
	public boolean checkPath(String src, String dst){
		if(src == null || dst == null) return false;
		File srcDir = new File(src);
		File dstDir = new File(dst);
		if(srcDir.isDirectory() && dstDir.isDirectory()) return true;
		return false;
	}
	
	public void copyFile(String src, String dst, String target){
		if(!checkPath(src, dst)) return;
		File srcDir = new File(src);
		File dstDir = new File(dst);
		String[] docs = srcDir.list();
		for(String s : docs){
			File t = new File(srcDir.getAbsolutePath()+"\\" + s);
			if(t.isDirectory()) 
				copyFile(t.getAbsolutePath(), dst, target);
			else if(s.equals(target))
				System.out.println(t.getAbsolutePath());
		}
	}
}
