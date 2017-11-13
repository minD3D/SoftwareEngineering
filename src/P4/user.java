package P4;
import simView.*;
import genDevs.modeling.*;
import GenCol.*;

public class user extends ViewableAtomic {

	protected double int_arr_time;
	protected int count;
	
	protected Queue queue;
	
	public user(){
		this("user",30);
	}
	
	public user(String name, double Int_arr_time){
		super(name);
		
		addOutport("out");
		addInport("in");
		
		int_arr_time= Int_arr_time;
	}
	
	public void initialize(){
		count=1;
		queue=new Queue();
		
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);
		queue.add(6);
		queue.add(7);
		queue.add(8);
		queue.add(9);
		queue.add(10);
		
		holdIn("active",int_arr_time);
		
	}
	
	public void deltext(double e, message x){
		Continue(e);
		if(phaseIs("passive")){
			for(int i=0; i<x.getLength(); i++){
				if(messageOnPort(x,"in",i)){
					holdIn("finished",INFINITY);
				}
			}
		}
		
	}
	
	public void deltint(){
		if(phaseIs("active")){
			count+=1;
			holdIn("active",int_arr_time);
		}
	}
	
	public message out(){
		message m= new message();
		
		if(queue.size()>1){
			
			int num= (int)queue.removeFirst();
			m.add(makeContent("out", new job(Integer.toString(num),num)));
		}
		else if(queue.size()==1){
			int num= (int)queue.removeFirst();
			m.add(makeContent("out", new job(Integer.toString(num) + ", last",num,true)));
			holdIn("passive",INFINITY);
			
		}
		return m;
	}
	
	public String getTooltipText(){
		return
				super.getTooltipText()
				+ "\n" + " int_arr_time: " + int_arr_time
			    + "\n" + " count: " + count;
	}
}
