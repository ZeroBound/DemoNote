package ArraysDemo;
import java.util.*;

// 选择排序
public class SelectSort{
	public static void main (String [] args) {
		int [] array = {1,5,3,2,6,10,8};
		int min;
		/*
		for(int i = 0; i<array.length-1; i++){
			min= i;
			for(int j=i+1;j<array.length;j++){
				if(array[min]>array[j]){
					min =j;
				}
			}
			if(i!=min){
				int temp = array[i];
				array[i] =array[min];
				array[min]=temp;
			}
		}*/
		System.out.println(Arrays.toString(array));
	}
}
