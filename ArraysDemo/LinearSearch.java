import java.util.*;

public class LinearSearch{
	public static void main (String [] args){
		int [] array = {10,100,80,50,70,60,90};
		System.out.println("input a number");
		Scanner input = new Scanner(System.in);
		int number = input.nextInt();
		int index = -1;
		for (int i= 0;i<array.length;i++){
			if (array[i]==number){
				index ++;
				break;
			}
		}
		if (index!=-1){
			System.out.println("it's ok");
		}else{
			System.out.println("it's error");
		}
	}
}