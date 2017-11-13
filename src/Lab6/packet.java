package Lab6;
import GenCol.*;

public class packet extends entity{

	int arrival;//紐⑹쟻吏� receiver踰�
	
	public packet(String name, int _arrival) {
		super(name);
		arrival = _arrival;
	}
	
	public int getArrival() { // 媛� �뙣�궥�쓽 receiver 踰덊샇瑜� 媛��졇�삤湲� �쐞
		return arrival;
	}
}
