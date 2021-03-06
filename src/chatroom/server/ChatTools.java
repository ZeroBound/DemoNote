package chatroom.server;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 定义一个管理类，相当于一个中介，处理线程，转发消息
 * 这个只提供方法调用，不需要实例化对象，因此都是静态方法
 *
 * @author zzw
 * @date 2019/9/8
 **/
public class ChatTools {
    /**保存线程处理的对象 **/
    private static ArrayList<ServerThread> stList=new ArrayList<ServerThread>();

    /**
     * 不需要实例化类，因此构造器为私有
     */
    private ChatTools() {}

    /**
     * 将一个客户对应的线程处理对象加入到队列中
     * @param st 服务器线程
     * @throws IOException
     */
    public static void addClient(ServerThread st) throws IOException {
        //将这个线程处理对象加入到队列中
        stList.add(st);
        castMsg(st.getOwnerUser(),"我上线了！目前人数："+stList.size());
    }

    /**
     * 发送消息给其他用户
     * @param sender 发送者
     * @param msg 信息
     * @throws IOException
     */
    public static void castMsg(UserInfo sender,String msg) throws IOException {
        //加上说的对象
        String senderName = sender.getName();
        msg = senderName +"说："+msg;
        for (ServerThread st : stList) {
            //发消息给其他客户机
            if(!senderName.equals(st.getOwnerUser().getName())){
                st.sendMsg2Me(msg);
            }

        }
    }
}
