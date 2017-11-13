package P3;
import genDevs.modeling.*;
import GenCol.*;
import simView.*;

public class calc extends ViewableAtomic {
	protected addRequest addNum;
	protected double processing_time;
	
	int result;
	
	public calc(){
		this("proc",20);
	}
	
	public calc(String name, double Processing_time){
		super(name);
		
		addInport("input_added_num");
		addOutport("result_will_out");
		
		processing_time= Processing_time;
		
	}
	
	public void initialize(){
		addNum= new addRequest("",0,0);
		holdIn("passive",INFINITY);
	}
	
	public void deltext(double e, message x){
		Continue(e);
		if(phaseIs("passive")){
			for(int i=0; i<x.getLength();i++){
				if(messageOnPort(x,"input_added_num",i)){
					addNum =(addRequest)x.getValOnPort("input_added_num", i);
					result = addNum.n1+addNum.n2;
					holdIn("calculating",processing_time);
				}
			}
		}
	}
	
	public void deltint(){
		if(phaseIs("calculating")){
			addNum= new addRequest("",0,0);
			holdIn("passive",INFINITY);
			
		}
	}
	
	public message out(){
		message m= new message();
		if(phaseIs("calculating")){
			m.add(makeContent("result_will_out",new entity("result= "+result)));
		}
		return m;
	}
	
	public String getTooltipText()
	{
		return
		super.getTooltipText()
		+ "\n" + "job: " + addNum.getName();
	}

}
