package GUIDemo.Subject;
public class Test{
	public static void main(String [] args){
		ZhangBoyFriend z = new ZhangBoyFriend();
		WangBoyFriend w = new WangBoyFriend();
		
		PrettyGirl girl =new PrettyGirl();
		girl.attachBoyFriend(z);
		girl.attachBoyFriend(w);
		
		girl.notifyAllBoyFriend(z);
	}
}