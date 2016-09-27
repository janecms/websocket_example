package websocket.fileupload.coders;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import websocket.fileupload.files.ListFiles;

import com.google.gson.Gson;

public class JsonEncoder implements Encoder.Text<ListFiles> {

	@Override
	public void init(EndpointConfig endpointConfig) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String encode(ListFiles files) throws EncodeException {
		Gson gson = new Gson();
		String result = gson.toJson(files.getFiles());
		return result;
	}

}
