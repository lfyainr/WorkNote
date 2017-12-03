package note;

import java.io.File;

public class TestFiles {
	
	public static int currentFileSize=0;
	public static void main(String[] args) {
		
		File file = new File("/Users/liufengyuan/Downloads/1220图标");
		System.out.println(file.length());
		File[] files = file.listFiles();
		loadFilesSize(files);
		System.out.println(files.length);
	}
	
	private static void loadFilesSize(File[] files){
		for(File file : files){
			if(file.exists()){
				if(file.isFile()){
					//过滤
						if(currentFileSize > 500){
							return;
						}
						currentFileSize ++;
				}else{
					loadFilesSize(file.listFiles());
				}
			}
		}
	}
}
