package FactoryDemo;

public class SoldierDemo{
	public static void main(String [] args){
		Officer officer = new Officer("officer");
		officer.callToAttack();
		System.out.println("-----------");
		officer.callToAttack(officer.getSoldiers()[0]);
	}
}

abstract class Soldier{
	private String name;
	public Soldier(String name){
		this.name =name;
	}
	public String getName(){
		return name;
	}
	public void start(){
		System.out.println("---start---");
	}
	public void end(){
		System.out.println("---end---");
	}
	public abstract void attack();
	
	public void action(){
		start();
		attack();
		end();
	}
}
class LandSoldier extends Soldier{
	public LandSoldier(String name){
		super(name);
	}
	public void attack(){
		System.out.println(getName()+"use gan");
	}
}
class OceanSoldier extends Soldier{
	public OceanSoldier(String name){
		super(name);
	}
	public void attack(){
		System.out.println(getName()+"use ship");
	}
}
class Officer extends Soldier{
	private Soldier [] soldiers = new Soldier[2];
	public Officer(String name){
		super(name);
		soldiers[0] = new LandSoldier("zhang");
		soldiers[1] = new OceanSoldier("li");
	}
	public Soldier [] getSoldiers(){
		return soldiers ;
	}
	public void attack(){
		System.out.println(getName()+"use hend");
	}
	public void callToAttack(){
		for(int i=0;i<soldiers.length;i++){
			soldiers[i].action();
		}
		this.action();
	}
	public void callToAttack(Soldier soldier){
		soldier.action();
	}
}