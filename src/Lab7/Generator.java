package Lab7;
import simView.*;
import genDevs.modeling.*;
import GenCol.*;

public class Generator extends ViewableAtomic
{
	
	protected double int_arr_time;
	protected int count;
  
	public Generator()
	{
		this("genr", 30);
	}
  
	public Generator(String name, double Int_arr_time)
	{
		super(name);
   
		addOutport("out"); // �븘�썐�뭼 �룷�듃 �깮�꽦 ("�씠由�")
		addInport("in"); // �씤 �룷�듃 �깮�꽦
    
		int_arr_time = Int_arr_time;
	}
  
	public void initialize() // 珥덇린�솕 
	{
		count = 0;
		
		holdIn("active", int_arr_time);
	}
  
	public void deltext(double e, message x)	// �쇅遺��뿉�꽌 硫붿꽭吏�媛� �뱾�뼱�솕�쓣�븣 泥섎━, external function
	{
		Continue(e); 
		if (phaseIs("active")) // active �긽�깭�씠硫� 
		{
			for (int i = 0; i < x.getLength(); i++) //硫붿꽭吏�媛� �룞�떆 �떎諛쒖쟻�쑝濡� �뱾�뼱�삤�뒗 寃쎌슦媛� �엳�쑝 for臾몄쓣 �궗�슜�빀�땲�떎. 
			{
				if (messageOnPort(x, "in", i)) // 硫붿꽭吏� x媛� in �룷�듃濡� �뱾�뼱�솕�쑝硫� 
				{

				}
			}
		}
	}

	public void deltint() // �궡遺��뿉�꽌�쓽 �씠踰ㅽ듃 泥섎━ �븿�닔 
	{
		if (phaseIs("active")) // active �긽�깭�씠硫� 
		{
			count = count + 1; // Job Count 媛쒖닔 利앷� 
			
			// 5媛쒖쓽 Job�쓣 �쟾�넚 �븯���쑝硫� stop
			if(count == 5)
			{
				holdIn("stop", INFINITY);
			}
		}
	}

	public message out() // �떎瑜� 紐⑤뜽濡� 硫붿꽭吏�(�씠踰ㅽ듃)瑜� 蹂대궪 �븣 
	{
		message m = new message(); // 硫붿꽭吏� �깮�꽦 
		m.add(makeContent("out", new entity("job" + count)));
		// makeContent : �깮�꽦�븳 媛쒖껜 �벑濡�, new entity : 媛쒖껜 �깮�꽦 
		// "out"�쓽 �씠由꾩쓣 媛�吏� �룷�듃濡�, 媛쒖껜瑜� 異쒕젰(�쟾�넚) 
		
		return m;
	}
  
	public String getTooltipText() // �뾾�뼱�룄 �긽愿��뾾�뒗 硫붿냼�뱶 (留덉슦�뒪瑜� �삱�졇�쓣�븣 �옄�꽭�븳 �긽�깭瑜� �몴�떆�빐二쇰뒗 �뿭�븷 (�끂�옉諛뺤뒪))
	{
		return
        super.getTooltipText()
        + "\n" + " int_arr_time: " + int_arr_time
        + "\n" + " count: " + count;
	}

}
