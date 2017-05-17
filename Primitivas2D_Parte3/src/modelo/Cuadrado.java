package modelo;

import java.awt.Point;

public class Cuadrado extends Poligono{
	private Point p1,p2;
	private int lado=0;
	
	public Cuadrado(Point p1,Point p2){
		super();
		this.p1 =p1;
		this.p2=p2;
		int dx = p2.x-p1.x;
		int dy = p2.y-p1.y;
		nuevoPunto(p1);
		nuevoPunto(new Point(p1.x+dx,p1.y));
		nuevoPunto(p2);
		nuevoPunto(new Point(p1.x,p1.y+dy));
		generarAristas();
	}
	
	
	public Cuadrado(Point p,int lado){
		super();
		this.p1 =p;
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
