package Lab7;
import genDevs.modeling.*;
import GenCol.*;
import simView.*;

public class Processor extends ViewableAtomic
{
  
	protected entity job; // Generator濡� 遺��꽣 諛쏆� Job
	protected double processing_time; // Job�쓽 �떎�뻾�떆媛� 
	int cnt;
	public Processor()
	{
		this("proc", 20);
	}

	public Processor(String name, double Processing_time)
	{
		super(name);
    
		addInport("in"); 
		addOutport("out_1"); 
		addOutport("out_2"); 
		
		processing_time = Processing_time;
	}
  
	public void initialize()
	{
		job = new entity("");
		cnt=0;
		holdIn("passive", INFINITY); 
	}

	public void deltext(double e, message x)
	{
		Continue(e);
		if (phaseIs("passive")) 
		{
			for (int i = 0; i < x.getLength(); i++)
			{
				if (messageOnPort(x, "in", i))
				{
					job = x.getValOnPort("in", i);
					
					holdIn("busy", processing_time); 
				
				}
				
			}
		}
	}
  
	public void deltint()
	{
		if (phaseIs("busy"))
		{
			job = new entity(""); 
			
			holdIn("passive", INFINITY);
			cnt++;
		}
		if(cnt==5)
			holdIn("stop", INFINITY);
		
	}

	public message out()
	{
		message m = new message();
		if (phaseIs("busy")) 
		{
			m.add(makeContent("out_1", job));
			m.add(makeContent("out_2", new entity("done"))); 
		}
		return m;
	}

	public String getTooltipText()
	{
		return
		super.getTooltipText()
		+ "\n" + "job: " + job.getName();
	}

}

