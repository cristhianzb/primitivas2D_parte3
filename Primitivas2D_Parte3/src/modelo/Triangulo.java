package modelo;

import java.awt.Point;

public class Triangulo extends Poligono{
	public Triangulo(Point p1,Point p2,Point p3){
		super();
		nuevoPunto(p1);
		nuevoPunto(p2);
		nuevoPunto(p3);
		generarAristas();
	}
	public String toString(){
    	return "Triangulo";
    }
}
