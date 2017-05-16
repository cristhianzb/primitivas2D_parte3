package modelo;

import java.awt.Point;

public class Cuadrado extends Poligono{
	private Point p;
	private int lado;
	
	public Cuadrado(Point p,int lado){
		super();
		this.p =p;
		this.lado = lado;
		nuevoPunto(p);
		nuevoPunto(new Point(p.x+lado,p.y));
		nuevoPunto(new Point(p.x+lado,p.y+lado));
		nuevoPunto(new Point(p.x,p.y+lado));
		generarAristas();
	}
	@Override
	public String toString(){
		return "Cuadrado";
	}
	
		
	
}
