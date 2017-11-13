package P3;
import simView.*;
import genDevs.modeling.*;
import GenCol.*;

public class ex_user extends ViewableAtomic{

	protected double int_arr_time;
	protected int count;
	
	public ex_user(){
		this("genr",30);
	}
	
	public ex_user(String name, double Int_arr_time){
		super(name);
		
		addOutport("out number");
		addInport("in_result_will_come");
		
		int_arr_time = Int_arr_time;
	}
	
	public void initialize(){
		count =1;
		holdIn("active", int_arr_time);
	}
	
	public void deltext(double e, message x){
		Continue(e);
		if(phaseIs("active")){
			for(int i=0; i<x.getLength();i++){
				if(messageOnPort(x,"in_result_will_come",i)){
					holdIn("stop", INFINITY);
				}
			}
		}
	}
	
	public void delint(){
		if(phaseIs("active")){
			count+=1;
			//holdIn("stop", INFINITY);
			holdIn("active",int_arr_time);
		}
	}
	
	public message out(){
		message m = new message();
		m.add(makeContent("out number", new addRequest("3+4=?",3,4)));
		return m;
	}
	
	public String getTooltipText(){
		return
				super.getTooltipText()
				+ "\n" + "int_arr_time: "+ int_arr_time
				+ "\n" + "count: "+ count;
	}
}
