package StringBuilderBuffDemo;

class StringBuilder1{
	public static void main(String [] args){
		//default capacity 16
		StringBuilder sb= new StringBuilder();
		sb.append("hello");
		System.out.println(sb.length());
	}
}