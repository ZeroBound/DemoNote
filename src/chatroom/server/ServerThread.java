package chatroom.server;

import java.io.*;
import java.net.Socket;

/**
 * 每当有客户机和服务器连接时，都要定义一个接受对象来进行数据的传输从服务器的角度看，这个类就是客户端
 *
 * @author zzw
 * @date 2019/9/8
 **/

public class ServerThread extends Thread{
    /**
     * 线程中的处理对象
     */
    private Socket client;
    /**
     * 输出流对象
     */
    private OutputStream ous;
    /**
     * 用户信息对象
     */
    private UserInfo user;

    public ServerThread(Socket client) {
        this.client=client;
    }

    public UserInfo getOwnerUser() {
        return this.user;
    }

    @Override
    public void run() {
        try {
            processSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在显示屏中打印信息，例如"用户名"、"密码"等等
     * @param msg 信息
     * @throws IOException
     */
    public void sendMsg2Me(String msg) throws IOException {
        msg+="\r\n";
        ous.write(msg.getBytes());
        ous.flush();
    }

    /**
     * 处理通讯信息
     * @throws IOException
     */
    private void processSocket() throws IOException {
        InputStream ins=client.getInputStream();
        ous=client.getOutputStream();
        BufferedReader brd = new BufferedReader(new InputStreamReader(ins));

        sendMsg2Me("欢迎你来聊天，请输入你的用户名：");
        String userName=brd.readLine();
        System.out.println(userName);
        sendMsg2Me("请输入密码：");
        String pwd=brd.readLine();
        System.out.println(pwd);
        user=new UserInfo();
        user.setName(userName);
        user.setPassword(pwd);
        //调用数据库，验证用户是否存在
        boolean loginState = DaoTools.checkLogin(user);
        if(!loginState) {
            //如果不存在这个账号则关闭
            sendMsg2Me("用户名或密码错误");
            this.closeMe();
            return;
        }
        sendMsg2Me("成功登录系统, 可以开始聊天了");

        //认证成功，把这个用户加入服务器队列
        ChatTools.addClient(this);
        String bye = "bye";
        String input = brd.readLine();
        while(!bye.equals(input)) {
            System.out.println("服务器读到的是:"+input);
            ChatTools.castMsg(this.user, input);
            input=brd.readLine();
        }
        ChatTools.castMsg(this.user, bye);
        this.closeMe();
    }

    /**关闭当前客户机与服务器的连接。*/
    public void closeMe() throws IOException {
        client.close();
    }

}
