package PrinterDemo;
public class PrinterDemo{
	public static void main(String [] args){
		ColorPrinter cp = new ColorPrinter("Lenovo");
		School s = new School();
		s.setPrinter(cp);
		s.pr("ssssss");
	}
}
class Printer{
	private String brand;
	public Printer(String brand){
		this.brand = brand;
	}
	public void print(String content){
		
	}
	public String getBrand(){
		return brand;
	}
}
class School {
	private Printer p =null;
	public void setPrinter(Printer p){
		this.p = p;
	}
	public void pr(String con){
		p.print(con);
	}
}
class ColorPrinter extends Printer{
	public ColorPrinter(String brand){
		super(brand);
	}
	public void print(String content){
		System.out.println(getBrand()+"color:"+content);
	}
	
}

class BlackPrinter extends Printer{
	public BlackPrinter (String brand){
		super(brand);
	}
	public void print(String content){
		System.out.println(getBrand()+"black:"+content);
	}
}