package Lab6;
import genDevs.modeling.*;
import GenCol.*;
import simView.*;

public class receiver extends ViewableAtomic
{
  
	protected packet packet; //Generator로 부터 받은 Job
	protected double processing_time; // Job의 실행시간 

	public receiver()
	{
		this("proc", 20);
	}

	public receiver(String name, double Processing_time)
	{
		super(name);
    
		addInport("in"); // 인풋 포트 생성 
		addOutport("out"); // 아웃풋 포트 생성 
		
		processing_time = Processing_time; 
	}
  
	public void initialize()
	{
		packet = new packet("", 0);
		
		holdIn("passive", INFINITY); 
		// passive상태 INFINITY : Generator로 부터 작업이 전송 될 때 까지 무정 대기  
	}

	public void deltext(double e, message x)
	{
		Continue(e);
		if (phaseIs("passive")) // passive 상태이
		{
			for (int i = 0; i < x.getLength(); i++)
			{
				if (messageOnPort(x, "in", i)) // 메시지 x가 "in" 포트에 존재하면 
				{
					packet = (packet)x.getValOnPort("in", i); // "in" 포트로 부터 생성 되는 메시지를 받아
					
					holdIn("busy", processing_time); // busy: 상태로 변환 후, processing_time만큼 처리한다. 
				}
			}
		}
	}
  
	public void deltint()
	{
		if (phaseIs("busy")) // "busy" 상태이
		{ // phasaIs() -> 모델의 상태를 알아보는 
			holdIn("passive", INFINITY); 
			// 새로운 패킷을 받아 처리하기 위해 초기상태(passive)로 전이 
		}
	}

	public message out()
	{
		message m = new message();
		if (phaseIs("busy")) // "busy" 상태이면 
		{
			m.add(makeContent("out", packet)); // "out"포트로 job을 출력 
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

