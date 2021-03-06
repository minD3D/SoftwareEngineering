package Lab7;
import java.awt.*;
import simView.*;

public class queue_model extends ViewableDigraph
{

	public queue_model()
	{
		super("gpt");
    	
		ViewableAtomic g = new Generator("Generator", 30); // �뼱�뼡 紐⑤?���쓣 媛��졇�떎 �벝 嫄댁�? �벑濡� 
		ViewableAtomic p = new Processor("Processor", 70); // 
		ViewableAtomic q = new queue("Queue", 10);
    	
		add(g); // �떆裕щ젅�씠�꽣�뿉 紐⑤?�� �벑濡� 
		add(p);
		add(q);
  	
		addCoupling(g, "out", q, "in_1"); // gpt 紐⑤?���쓣 ?�ㅽ뵆留�? �빐以� ?��붾뱶 
		addCoupling(q, "out", p, "in");
		addCoupling(p, "out_2", q, "in_2");
		addCoupling(p, "out_1", g, "in");
	}

    
    /**
     * Automatically generated by the SimView program.
     * Do not edit this manually, as such changes will get overwritten.
     */
    public void layoutForSimView()
    {
        preferredSize = new Dimension(988, 646);
        ((ViewableComponent)withName("Generator")).setPreferredLocation(new Point(61, 124));
        ((ViewableComponent)withName("Processor")).setPreferredLocation(new Point(206, 255));
        ((ViewableComponent)withName("Queue")).setPreferredLocation(new Point(377, 99));
    }
}
