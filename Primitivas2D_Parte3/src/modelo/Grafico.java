package modelo;

import java.awt.Point;

public interface Grafico{

	void escalar(double s);
	void rotar(int deg);
	void trasladar(Point a,Point b);
	void trasladar(Point a);
	
	
	
}
