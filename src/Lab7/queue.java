package Lab7;
import genDevs.modeling.*;
import GenCol.*;
import simView.*;

public class queue extends ViewableAtomic
{
	
	protected Queue q;
	protected entity job;
	protected double processing_time;
	
	public queue()
	{
		this("procQ", 20);
	}

	public queue(String name, double Processing_time)
	{
		super(name);
    
		addInport("in_2");	// processor�� �뿰寃� 
		addInport("in_1");	// generator�� �뿰寃� 
		addOutport("out");
		
		processing_time = Processing_time;
	}
	
	public void initialize()
	{
		q = new Queue();
		job = new entity("");
		
		holdIn("passive", INFINITY);	// 珥덇린�긽�깭 passive
	}

	public void deltext(double e, message x)
	{
		Continue(e);
		if (phaseIs("passive"))
		{
			for (int i = 0; i < x.size(); i++)
			{
				if (messageOnPort(x, "in_1", i))	// in_1 : Generator�� �뿰寃� 
				{
					job = x.getValOnPort("in_1", i);
					// forwarding : Processor濡� Job�쓣 �쟾�넚 媛��뒫�븳 �긽�깭 
					holdIn("forwarding", 0);
				}
			}
		}
		else if (phaseIs("queuing"))		// queuing : Processor濡� Job�쓽 �쟾�넚�씠 遺덇��뒫�븳 �긽�깭 
		{
			for (int i = 0; i < x.size(); i++)
			{
				if (messageOnPort(x, "in_1", i))
				{
					entity temp = x.getValOnPort("in_1", i);
					q.add(temp);
				}
				if(messageOnPort(x, "in_2", i))	// in_2 : processor�� �뿰寃� 
				{
					if(q.isEmpty())	// queue�븞�뿉 �뜑�씠�긽 �쟾�넚�븷 Job�씠 �뾾�쑝硫� 
						holdIn("stop", INFINITY);	// stop �긽�깭濡� �쟾�씠�븯�뿬 紐⑤뜽 以묒� 
					else
						holdIn("forwarding", 0);		// 洹몃젃吏� �븡�떎硫� 蹂대궦�떎 
				}
			}
		}
	}
	
	public void deltint()
	{
		if (phaseIs("forwarding"))	// processor�뿉寃� Job�쓣 �쟾�넚�븳 �썑 �옄�룞�쑝濡� queuing �긽�깭濡� �쟾�씠  
		{
			holdIn("queuing", INFINITY);
		}
	}

	public message out()
	{
		message m = new message();
		
		if (phaseIs("forwarding"))	// forwarding �긽�깭�뿉�꽌 
		{
			if(!q.isEmpty()) // queue媛� 鍮꾩뼱�엳吏� �븡�쑝硫� 
				job = (entity) q.removeFirst();	// queue�뿉�꽌 Job�쓣 爰쇰궡 
			m.add(makeContent("out", job));	// Processor濡� Job �쟾�넚 
		}
		
		//if(q.isEmpty()) 
			//m.add(makeContent("out", new entity("end"))); 
		return m;
	}	
	
	public String getTooltipText()
	{
		return
        super.getTooltipText()
        + "\n" + "queue length: " + q.size()
        + "\n" + "queue itself: " + q.toString();
	}

}



