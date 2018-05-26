package MapDemo;
import java.util.*;

public class AccountStringDemo2{
	public static void main(String [] args){
		String [] str ={"chenhao","zhangsan","zhangsan","chenghao","wangwu"};
		AccountUtil2.account(str);
		
	}
}

class AccountUtil2{
	public static void account(String [] strs){
		Map<String,Integer> data =new HashMap<String,Integer>();
		for(int i=0;i<strs.length;i++){
			String str = strs[i];
			if(data.get(str)==null){
				data.put(str,1);
			}else{
				data.put(str,data.get(str)+1);
			}
		}
		Set<Map.Entry<String,Integer>> entrys =data.entrySet();
		for(Map.Entry<String,Integer> entry:entrys){
			System.out.println(entry.getKey()+"----"+entry.getValue());
		}
	}
}