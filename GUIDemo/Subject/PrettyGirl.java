import java.util.*;
public class PrettyGirl implements IGirl{
	private List<IBoyFriend> boyList=new ArrayList<IBoyFriend>();
	private String msg="I'm hand";
	
	public void attachBoyFriend(IBoyFriend boyFriend){
		boyList.add(boyFriend);
	};
	
	public void detachBoyFriend(IBoyFriend boyFriend){
		if(boyList.size()>0){
			boyList.remove(boyFriend);
		}
	};
	
	public void notifyAllBoyFriend(IBoyFriend boyFriend){
		for(int i=0;i<boyList.size();i++){
			IBoyFriend boyFriends = boyList.get(i);
			boyFriends.update(msg);
		}
	};
}