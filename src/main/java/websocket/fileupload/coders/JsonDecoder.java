package websocket.fileupload.coders;

import javax.websocket.DecodeException;
import javax.websocket.Decoder.Text;
import javax.websocket.EndpointConfig;

import websocket.fileupload.files.Operation;

import com.google.gson.Gson;

public class JsonDecoder implements Text<Operation> {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Operation decode(String msg) throws DecodeException {
		Gson gson = new Gson();
		Operation result  = gson.fromJson(msg, Operation.class);
		return result;
	}

	@Override
	public boolean willDecode(String msg) {
		if (msg.contains("op")){
			return true;
		}else{
			return false;
		}
	}

}
