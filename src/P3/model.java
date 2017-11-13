package P3;
import java.awt.*;
import simView.*;

public class model extends ViewableDigraph{

	public model(){
		super("model");
		
		ViewableAtomic user1 = new ex_user("user1",10);
		ViewableAtomic calculater = new calc("add_calculater",10);
		
		add(user1);
		add(calculater);
		
		addCoupling(user1,"out number", calculater,"input_added_num");
		addCoupling(calculater,"result_will_out", user1,"in_result_will_come");
		
	}
	
	public void layoutForSimView() {
        preferredSize = new Dimension(988, 646);
        ((ViewableComponent)withName("user")).setPreferredLocation(new Point(316, 301));
        ((ViewableComponent)withName("add")).setPreferredLocation(new Point(322, 430));
    }
}
