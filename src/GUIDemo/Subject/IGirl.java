package GUIDemo.Subject;
public interface IGirl{
	
	public void attachBoyFriend(IBoyFriend boyFriend);
	
	public void detachBoyFriend(IBoyFriend boyFriend);
	
	public void notifyAllBoyFriend(IBoyFriend boyFriend);
}