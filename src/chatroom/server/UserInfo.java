package chatroom.server;

/**
 * @author zzw
 * @date 2019/9/8
 **/
public class UserInfo {
    /** 用户名 */
    private String name;
    /** 密码 */
    private String password;
    /** 登录时间 */
    private String loignTime;
    /** 客户机端口名 */
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void setPassword(String psw) {
        this.password = psw;
    }

    public String getPassword(){
        return password;
    }
}
