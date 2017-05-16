package modelo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class ScanLine {
	public ScanLine(){
		
	}
	public void fill(Poligono pol,Canvas lienzo,Color color){
		ArrayList<Point> vertices = pol.getVertices();
		//recorriendo los scanline
		ArrayList<Point[]> aristas = obtenerAristas(vertices);
		for(int i=0;i<lienzo.getHeight();i++){  //scanline
			PriorityQueue pq = new PriorityQueue();
			for(Point[] a:aristas){				//aristas
				if(hayInterseccion(new Point(0,i),new Point((int)lienzo.getWidth(),i),a[0],a[1])){
					int x=0;
					int y=i;
					//scanExtrema
					int dx = a[0].x-a[1].x;
					int dy = a[0].y-a[1].y;
					int m = 0;
					if(dy!=0){
						m = (int)(dx/dy*1.0);
					}
					y = y-1;
					x = a[0].x + m*(y-a[0].y);	
					pq.add(x);
				}
			}			
			//pintar entre cada par de interseccines
			while(!pq.isEmpty()){
				int x1 =  (int) pq.poll();				
				int x2 =  (int) pq.poll();
				System.out.println(""+x1+" , "+x2);
				
				lineaHor(lienzo, i, x1, x2, color);				
			}
		}
		
	}
	
	private void scanExtrema(int x, int y, Point p1,Point p2){
		int dx = p1.x-p2.x;
		int dy = p1.y-p2.y;
		int m = 0;
		if(dy!=0){
			m = (int)(dx/dy*1.0);
		}
		y = y-1;
		x = p1.x + m*(y-p1.y);		
	}
	
	private ArrayList<Point[]> obtenerAristas(ArrayList<Point> vertices){
		ArrayList<Point[]> aristas = new ArrayList<Point[]>();
		for(int i=0;i<vertices.size()-1;i++){
			Point[] arista = new Point[2];
			arista[0]=vertices.get(i);
			arista[1]=vertices.get(i+1);
			aristas.add(arista);
		}
		Point[] arista = new Point[2];
		arista[0]=vertices.get(vertices.size()-1);
		arista[1]=vertices.get(0);
		aristas.add(arista);
		return aristas;
	}
	
	public class ComparadorPunto implements Comparator<Point>{

		@Override
		public int compare(Point p1, Point p2) {
			return p1.x-p2.x;
		}
		
	}
	
	//OK pinta una linea de pixeles
	public void lineaHor(Canvas lienzo,int y ,int oeste, int este,Color color){
		for(int i=oeste;i<=este;i++){
			lienzo.getGraphicsContext2D().getPixelWriter().setColor(i, y, color);
		}
		
	}
	
	//Direccion de un Punto con respecto a una recta
    private double direccion(Point p, Point v, Point w){
        return cruz(diferencia(w,p),diferencia(v,p)); 
    }
    
    private Point diferencia(Point a,Point b){
        return new Point(a.x-b.x,a.y-b.y);
    }
    
    public int cruz(Point a,Point b){
        return a.x*b.y-b.x*a.y;    
    }
    
  //Indica se dos segmentos o aristas se intersectan
    public boolean hayInterseccion(Point a, Point b, Point c, Point d){
        boolean resp;
        double d1 = direccion(c,d,a);
        double d2 = direccion(c,d,b);
        double d3 = direccion(a,b,c);
        double d4 = direccion(a,b,d);
        boolean cond1 = d1 > 0 && d2 < 0;
        boolean cond2 = d1 < 0 && d2 > 0;
        boolean cond3 = d3 > 0 && d4 < 0;
        boolean cond4 = d3 < 0 && d4 > 0;
        if((cond1||cond2) && (cond3||cond4)){
            resp = true;
        }else{
            if(d1==0 && sobreSegmento(c,d,a)){
                resp = true;
            }else{
                if(d2==0 && sobreSegmento(c,d,b)){
                    resp = true;
                }else{
                    if(d3==0 && sobreSegmento(a,b,c)){
                        resp = true;
                    }else{
                        if(d4==0 && sobreSegmento(a,b,d)){
                            resp = true;
                        }else{
                            resp = false;
                        }
                    }
                }
            }
        }
        return resp;
    }
    
  //Indica si un punto esta sobre un segmento
    private boolean sobreSegmento(Point p, Point v, Point w){
        boolean resp;
        if(Math.min(p.getX(),v.getX())<=w.getX()&& w.getX()<= Math.max(p.getX(),v.getX())&&
        Math.min(p.getY(),v.getY())<=w.getY()&& w.getY()<= Math.max(p.getY(),v.getY())){
            resp = true;
        }else{
            resp = false;
        }
        return false;
    }
	
}
