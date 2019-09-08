package chatroom.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zzw
 * @date 2019/9/8
 **/
public class ChatServer {

    public static void main(String[] args) throws IOException {
        int port = 9003;
        ChatServer cs=new ChatServer();
        //调用方法，为指定端口创建服务器
        cs.setUpServer(port);
    }

    private void setUpServer(int port) throws IOException {
        ServerSocket server = new ServerSocket(port);
        //打印出当期创建的服务器端口号
        System.out.println("服务器创建成功!端口号："+port);
        ChatServerHandlerExecutePool singleExecutor =
                new ChatServerHandlerExecutePool(50,10000);

        Socket socket;
        while(true) {
            //等待连接进入
            socket=server.accept();
            System.out.println("进入了一个客户机连接："+socket.getRemoteSocketAddress().toString());

            //启动一个线程去处理这个对象
            // 创建一个线程，并加入线程池
            singleExecutor.execute(new ServerThread(socket));
        }
    }
}
