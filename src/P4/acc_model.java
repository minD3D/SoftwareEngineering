package P4;
import java.awt.*;
import simView.*;

public class acc_model extends ViewableDigraph{

	public acc_model(){
		super("acc_model");
		
		ViewableAtomic u = new user("user",10);
		ViewableAtomic acc = new tenadder("acc",10);
		
		add(u);
		add(acc);

		addCoupling(u,"out",acc,"in");
		addCoupling(acc,"out",u,"in");
	}
	
	public void layoutForSimView()
    {
        preferredSize = new Dimension(628, 232);
        ((ViewableComponent)withName("accumulator")).setPreferredLocation(new Point(325, 145));
        ((ViewableComponent)withName("user")).setPreferredLocation(new Point(335, 35));
    }
	
}
