public class MaxMinDemo{
	public static void main(String [] args){
		int [] array = {10,100,80,50,70,60,90};
		int max = array[0];
		int min = array[0];
		for (int i=1;i<array.length;i++) {
			if(array[i]>max){
				max = array[i];
			}
			if(array[i]<min){
				min = array[i];
			}
		}
		System.out.println("max:"+max+",min:"+min);
	}
}