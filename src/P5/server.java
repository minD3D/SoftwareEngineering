package P5;
import simView.*;
import genDevs.modeling.*;
import GenCol.*;

public class server extends ViewableAtomic{
	protected double processing_time;
	protected packet Packet;
	
	public server(){
		this("serv",30);
	
	}
	
	public server(String name, double Processing_time){
		super(name);
		
		addOutport("out");
		addInport("in");
		
		processing_time=Processing_time;
	}
	
	public void initialize(){
		
		Packet = new packet("");
		holdIn("Wait",INFINITY);
	}
	
	public void deltext(double e, message x){
		Continue(e);
		
		if(phaseIs("Wait")){
		
			for(int i=0; i<x.getLength();i++){
			
				if(messageOnPort(x,"in",i)){
					
					Packet=(packet)x.getValOnPort("in", i);
					
					holdIn("Wait", processing_time);
				}
			}
		}
		else if(phaseIs("SYN-received")){

			for(int i=0; i<x.getLength();i++){
			
				if(messageOnPort(x,"in",i)){
					
					Packet=(packet)x.getValOnPort("in", i);
					
					holdIn("Established", INFINITY);
				}
			}
		}
	}
	
	public void deltint(){
		
	}
	
	public message out(){
		message m= new message();
		
		if(phaseIs("Wait")){
			holdIn("Established",INFINITY);
			m.add(makeContent("out", new packet("SYN-ACK")));
			
			holdIn("SYN-received",processing_time);
		}
		
		return m;
	}
	
	public String getToolText(){
		return 
				super.getTooltipText()
				+"\n"+ "Processing_time: "+processing_time;
	}
}
