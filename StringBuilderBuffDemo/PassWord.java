import java.util.*;
public class PassWord{
	public static void main (String [] args){
		System.out.println("mima="+GetPW.password());
	}
}

class GetPW{
	public static String password(){
		char number [] ={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','T','S','V','U','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'}; 
		Random random = new Random();
		StringBuilder str = new StringBuilder();
		int count =0;
		while(true){
			char newPassword = number[random.nextInt(number.length)]; 
			if(str.indexOf(newPassword+"")== -1){
				str.append(newPassword);
				count++;
				if(count ==4){
					break;
				}
			}
			
		}
		return str.toString();
		
	}
}