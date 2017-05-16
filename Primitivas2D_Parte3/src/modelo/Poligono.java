package modelo;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Poligono implements Grafico{
	private String grosor;
    private String tipo;
    private Color color;
    ArrayList<Point> vertices;
    ArrayList<Line> aristas;
    
    public Poligono(){
    	setDefault();
    	vertices = new ArrayList<Point>();
    	aristas = new ArrayList<Line>();
    }
    
    public void setDefault(){
    	grosor ="Delgado";
        tipo="Continuo";
        color = new Color( 0,76,153); 
    }
    
    public String toString(){
    	return "Poligono";
    }
    
    public void nuevoPunto(Point p){
    	vertices.add(p);
    }
    
    public void generarAristas(){
    	//ArrayList<Line> aristas = new ArrayList<Line>(); 
    	for(int i=0;i<vertices.size()-1;i++){
    		aristas.add(new Line(vertices.get(i),vertices.get(i+1)));
    	}
    	aristas.add(new Line(vertices.get(0),vertices.get(vertices.size()-1)));
    	
    }
    
    public ArrayList<Line> dibujar() {
    	//generarAristas();
    	return aristas;
	}
    	
    public ArrayList<Point> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Point> vertices) {
		this.vertices = vertices;
	}

	@Override
	public void escalar(double s) {
		Point pv = vertices.get(0);
		int a = pv.x;
		int b = pv.y;
		for(Point p: vertices){
			p.x = (int)(((p.x - a)*s)+a);
			p.y = (int)(((p.y - b)*s)+b);
		}
		
	}

	@Override
	public void rotar(int deg) {
		Point p = vertices.get(0);
		for(Point a:vertices){
			if(!a.equals(p)){				
				int x = a.x-p.x;
				int y = a.y-p.y;
				double degrees = Math.toRadians(-deg);
				a.x = (int)(x*Math.cos(degrees)-y*Math.sin(degrees)+p.x);
				a.y = (int)(y*Math.cos(degrees)+x*Math.sin(degrees)+p.y);
			}
		}
		
	}

	@Override
	public void trasladar(Point a,Point b) {
		int dx = a.x-b.x;
		int dy = a.y-b.y;
		for(Point p:vertices){
			p.x = p.x+dx;
			p.y = p.y+dy;
		}
		generarAristas();
	}

	@Override
	public void trasladar(Point a) {
		Point pv = vertices.get(0);
		int dx = a.x-pv.x;
		int dy = a.y-pv.y;
		for(Point p:vertices){
			p.x = p.x+dx;
			p.y = p.y+dy;
		}
		generarAristas();		
	}

	
    
}
