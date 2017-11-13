package P5;
import java.awt.*;
import simView.*;

public class handshake extends ViewableDigraph {
	public handshake(){
		super("hs");
		
		ViewableAtomic server = new server("s1",10);
		ViewableAtomic client = new client("c1",20);
	 	
		add(client);
		add(server);
		
		addCoupling(client,"out",server,"in");
		addCoupling(server,"out",client,"in");
		
		
	}
	
	public void layoutForSimView() {
	     	preferredSize = new Dimension(988, 646);
	        ((ViewableComponent)withName("client")).setPreferredLocation(new Point(316, 301));
	        ((ViewableComponent)withName("server")).setPreferredLocation(new Point(322, 430));

	}
}
