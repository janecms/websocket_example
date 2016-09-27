package websocket.fileupload.files;


import java.util.ArrayList;
import java.util.List;

public class ListFiles {

	private List<MyFile> files = new ArrayList<>(0);

	public void addFile(String name, long size) {
		files.add(new MyFile(name,size));
	}
	
	public List<MyFile> getFiles(){
		return this.files;
	}
	
	

}
