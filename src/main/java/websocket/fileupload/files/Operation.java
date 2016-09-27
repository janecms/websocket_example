package websocket.fileupload.files;

public class Operation {

	private String op;
	private String fname;
	private long fsize;
	
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder = builder.append("operation: "+this.op).append("\n");
		builder = builder.append("file name: "+this.fname).append("\n");
		builder = builder.append("file size: "+this.fsize).append("\n");
		return builder.toString();
	}


	public String getOperation() {
		return op;
	}
	
	public String getFileName() {
		return fname;
	}

	public long getFileSize() {
		return fsize;
	}
	
}
