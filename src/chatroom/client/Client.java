package chatroom.client;

import java.io.*;
import java.net.Socket;

/**
 * @author zzw
 * @date 2019/9/8
 **/
public class Client extends Thread {
    private InputStream input = null;
    private OutputStream output = null;
    private BufferedReader bufferedInput = null;
    private Socket socket = null;

    public Client() {
    }

    public Client(int port) {
        // 初始化时连接服务器
        try {
            socket = new Socket("127.0.0.1", port);
            input = socket.getInputStream();
            output = socket.getOutputStream();
            System.out.println("======client1=====");
            // 验证用户信息
            login();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 9003;
        Client client = new Client(port);
        client.start();
    }


    /** 登录验证 */
    public void login() {
        int login = 0;
        int max = 2;
        try {
            bufferedInput = new BufferedReader(new InputStreamReader(input));
            String line;
            // 登录限制 读取服务器的两次信息(用户名和密码提示信息，输入)
            while (login < max) {
                // 获取消息
                if ((line = bufferedInput.readLine()) != null) {
                    System.out.println(line);
                }

                // 发送消息
                BufferedReader brName = new BufferedReader(new InputStreamReader(System.in));
                // 控制台在读取数据时不会自动换行
                String strName = brName.readLine() + "\r\n";
                output.write(strName.getBytes());
                output.flush();
                login++;
            }
            chat();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 聊天
     */
    public void chat(){
        // 开启线程 读取当前用户的输入
        start();
        bufferedInput = new BufferedReader(new InputStreamReader(input));
        String line;

        try{
            // 获取消息
            while (true){
                if((line=bufferedInput.readLine())!=null){
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        //发送消息
        while(true){
            BufferedReader brName = new BufferedReader(new InputStreamReader(System.in));
            String strName;
            //控制台在读入数据时并不会自动添加换行符号
            try {
                strName = brName.readLine()+"\r\n";
                output.write(strName.getBytes());
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
