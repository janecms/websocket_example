package websocket.fileupload.endpoint;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.EncodeException;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import websocket.fileupload.coders.JsonDecoder;
import websocket.fileupload.coders.JsonEncoder;
import websocket.fileupload.files.ListFiles;
import websocket.fileupload.files.Operation;

@ServerEndpoint(value = "/fileUpload", encoders = {JsonEncoder.class}, decoders = {JsonDecoder.class})
public class FileUpload {

    private Session session;
    private FileChannel channel;
    private long size;
    private long length = 0;
    private Path storage;

    @OnOpen
    public void processOnOpen(Session session) {
        System.out.println("open connection id: " + session.getId());

        File dir = new File("uploads");
        if (!dir.exists()) {
            try {
                Files.createDirectory(dir.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.session = session;
        this.storage = dir.toPath();

    }

    @OnMessage
    public void processOperation(Operation operation) {
        System.out.println(operation.toString());
        switch (operation.getOperation()) {
            case ("put"):
                putFile(operation);
                break;
            case ("list"):
                listFiles();
                break;
            default:
                break;
        }
    }

    private void listFiles() {
        ListFiles storageFiles = new ListFiles();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(storage
                .getFileName().normalize())) {
            for (Path entry : stream) {
                Path abs = entry.toAbsolutePath();
                if (!Files.isDirectory(abs))
                    storageFiles.addFile(abs.getFileName().toString(),
                            Files.size(abs));
            }
        } catch (DirectoryIteratorException | IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            session.getBasicRemote().sendObject(storageFiles);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }

    private void putFile(Operation operation) {
        try {
            Path tmp = Paths.get(storage.normalize().toString(),
                    operation.getFileName());

            channel = FileChannel.open(tmp, EnumSet.of(
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE));
            this.size = operation.getFileSize();
            this.length = 0;
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void processBinaryStream(InputStream is) {
        int count = 0;
        byte[] buff = new byte[1024];
        try {
            while (is.available() > 0) {
                count = +is.read(buff);
                ByteBuffer buffer = ByteBuffer.wrap(buff, 0, count);
                int res = channel.write(buffer, length);
                this.length += res;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (length == size) {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            channel = null;
        }
    }

    @OnError
    public void processError(Throwable t) {
        System.out.println(t.getMessage());
    }

}
