package Lab6;
import genDevs.modeling.*;
import GenCol.*;
import simView.*;

public class router extends ViewableAtomic
{
  
	protected packet packet; //Generator濡� 遺��꽣 諛쏆� Job
	protected double processing_time; // Job�쓽 �떎�뻾�떆媛� 
	
	protected Queue q;

	public router()
	{
		this("proc", 20);
	}

	public router(String name, double Processing_time)
	{
		super(name);
    
		addInport("in"); // �씤�뭼 �룷�듃 �깮�꽦 
		
		for(int i = 1; i <= 5; i++) {
			addOutport("out"+i); // �븘�썐�뭼 �룷�듃 �깮�꽦
		}
		addOutport("out"); // sender�� �뿰寃� �맆 output port
		
		processing_time = Processing_time; 
	}
  
	public void initialize()
	{
		packet = new packet("", 0);
		q = new Queue();
		
		holdIn("passive", INFINITY); 
		// passive�긽�깭 INFINITY : Generator濡� 遺��꽣 �옉�뾽�씠 �쟾�넚 �맆 �븣 源뚯� 臾댁젙 ��湲�  
	}

	public void deltext(double e, message x)
	{
		Continue(e);
		if (phaseIs("passive")) 
		{
		
			for (int i = 0; i < x.getLength(); i++)
			{
				if (messageOnPort(x, "in", i)) {// 硫붿떆吏� x媛� "in" �룷�듃�뿉 議댁옱�븯硫� 
					packet = (packet)x.getValOnPort("in", i); 
					q.add(packet); // �걧�뿉 �뙣�궥 ���옣 
				}
				
				//passive인 상태이면 어떤 조건없이 q의 크기를 확인해 sending 으로 바꿈
				if(q.size() == 5) { 	
					holdIn("sending", processing_time); // sending�긽�깭濡� �쟾
				}
				
			}
			
		}
	}
  
	public void deltint()
	{
	}

	public message out()
	{
		message m = new message();
		if (phaseIs("sending")) //
		{
			if(!q.isEmpty()) {
				packet = (packet)q.removeFirst(); // queue�뿉�꽌 packet�쓣 媛��졇�� 
				int portNum = packet.getArrival(); // 媛��졇�삩 �빐�떦 packet�쓽 紐⑹쟻吏�(receiver) 踰덊샇瑜� �븣怨� 
				m.add(makeContent("out"+ portNum, packet)); // 紐⑹쟻吏�濡� packet�쓣 �쟾�넚 
			}
			else {
				m.add(makeContent("out", new packet("done", 0)));
				// router媛� 5媛쒖쓽 �뙣�궥�쓣 紐⑤몢 receiver�뿉寃� �쟾�넚 �썑 sender�뿉寃� done 硫붿떆吏� �쟾�떖 
				holdIn("passive", processing_time);
				// sender濡� 遺��꽣 �깉濡쒖슫 packet�쓣 諛쏆븘 泥섎━�븯湲� �쐞�빐 珥덇린 �긽�깭濡�(passive)濡� �쟾�씠
			}
		}
		return m;
	}

	public String getTooltipText()
	{
		return
		super.getTooltipText()
		+ "\n" + "job: " + packet.getName();
	}

}

