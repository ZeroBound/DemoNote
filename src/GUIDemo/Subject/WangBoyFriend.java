package GUIDemo.Subject;
public class WangBoyFriend implements IBoyFriend{
	public void update(String msg){
		if(msg.equals("I'm hand")){
			System.out.println("I reject");
		}
	}
}