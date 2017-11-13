package SimpArc;
import java.awt.*;
import simView.*;

public class efp extends ViewableDigraph 
{

	public efp()
	{
		super("efp");
		
		ViewableAtomic sp = new proc("proc", 25);
		ViewableDigraph  expf = new ef("ExpFrame", 10, 100);

		add(expf);
		add(sp);

		addCoupling(expf, "out", sp, "in");

		addCoupling(sp, "out", expf, "in");
	}
	
    /**
     * Automatically generated by the SimView program.
     * Do not edit this manually, as such changes will get overwritten.
     */
    public void layoutForSimView()
    {
        preferredSize = new Dimension(980, 644);
        ((ViewableComponent)withName("ExpFrame")).setPreferredLocation(new Point(363, 149));
        ((ViewableComponent)withName("proc")).setPreferredLocation(new Point(87, 201));
    }
 }
