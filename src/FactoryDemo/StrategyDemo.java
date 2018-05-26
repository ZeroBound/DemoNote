package FactoryDemo;
import java.util.*;
public class StrategyDemo{
	public static void main(String [] args){
		int [] array={1,3,6,9,2,7,5,8,4};
		ISort bubbleSort =new BubbleSort();
		Context context = new Context(bubbleSort);
		context.sort(array);
		context.printArray(array);
	}
}
class Context{
	private ISort iSort =null;
	Context(ISort iSort){
		this.iSort =iSort;
	}
	void sort(int [] array){
		iSort.sort(array);
	}
	void printArray(int [] array){
		System.out.println(Arrays.toString(array));
	}
}
interface ISort{
	public void sort(int [] array);
}
class BubbleSort implements ISort{
	public void sort(int [] array){
		System.out.println("----------");
        mpSort(array);
    }

    private static void mpSort(int[] array) {
        for(int i=0 ;i<array.length-1;i++){
            for(int j=0;j<array.length-1-i;j++){
                if(array[j]>array[j+1]){
                    int temp =array[j];
                    array[j] =array[j+1];
                    array[j+1] =temp;
                }
            }
        }
    }
}
class SelectSort implements ISort{
	public void sort (int [] array){
		System.out.println("===========");
        slSort(array);
    }

    private static void slSort(int[] array) {
        int min;
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
        }
    }
}