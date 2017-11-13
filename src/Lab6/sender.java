package Lab6;
import simView.*;
import genDevs.modeling.*;
import GenCol.*;

public class sender extends ViewableAtomic
{
	
	protected double int_arr_time; // 제네레이터의 JOB
	
	protected int count; // 
	private int packet_num;
  
	public sender() // 기본 생성
	{
		this("genr", 30);
	}
  
	public sender(String name, double Int_arr_time)
	{
		super(name);
   
		addOutport("out"); // 아웃풋 포트 생성 
		addInport("in"); //  폰트 생성 
    
		int_arr_time = Int_arr_time;
	}
  
	public void initialize() // 초기
	{
		count = 1; // 전송한 packet의 순서 초기화 
		// packet 1, packet2, packet 3 .... packet n
		packet_num = 0; // 전송한 packet의 개수 초기화 
		// packet_num <= 5;
		
		holdIn("active", int_arr_time); 
		// active 상태로 변환, int_arr_time 이후에 event 발생 하겠다.
	}
  
	public void deltext(double e, message x) //외부에서 메시지가 들어왔을 때 처리, external function
	{
		Continue(e);
		if (phaseIs("wait")) // active 상태이
		{
			for (int i = 0; i < x.getLength(); i++)
			{
				if (messageOnPort(x, "in", i)) // 메시지 x가 in 포트로 들어왔으
				{
					holdIn("active", int_arr_time); // 상태를 stop 상태로 변환, INFINITY = 모델 중
				}
			}
		}
	}

	public void deltint() // 내부에서의 이벤트 처리 함수 
	{
		if (phaseIs("active")) // active 상태이
		{
			count = count + 1; //JOB count 계수 증
		}
		else if(phaseIs("wait")) { 
			// router로 부터 done 메시지(router가 패킷을 receiver에게 모두 전송했다)
			count = 1;
			packet_num = 0;
		}
	}

	public message out() // 다른 모델로 메시지(이벤트)를 보낼 
	{
		message m = new message(); // 메시지 생성 
		
		if(packet_num < 5) {
			m.add(makeContent("out", new packet("packet"+count, (int)(Math.random() * 5) + 1)));
			packet_num++;
			// 패킷의 목적지(receiver)은 랜덤하게
			// packet(name, _arrival)
		}
		if(packet_num == 5) {
			holdIn("wait",INFINITY);
		}
		
		// makeContent : 생성한 개체 등록, new entity : 개체생성
		// out"의 이름을 가진 포트로, 개체를 출력(전송)
		return m;
	}
  
	public String getTooltipText() // 없어도 되는 메소드 지만
	{
		return
        super.getTooltipText()
        + "\n" + " int_arr_time: " + int_arr_time
        + "\n" + " count: " + count;
	}

}
