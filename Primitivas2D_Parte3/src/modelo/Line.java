package modelo;


import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Line extends Primitive2D{
	private Point p1;
	private Point p2;
	
	private int DX, DY;
	
	public Line(Point p1, Point p2){
		super();
		this.p1=p1;
		this.p2=p2;
		DX = Math.abs(p2.x-p1.x);
		DY = Math.abs(p2.y-p1.y);
	}
	
	public Line(Point p1, Point p2, String gro, String tipo, Color col){
		super(gro, tipo, col);
		this.p1=p1;
		this.p2=p2;
		DX = Math.abs(p2.x-p1.x);
		DY = Math.abs(p2.y-p1.y);
	}
	
	public String toString(){
		return "Linea";
	}
	
	public void setPoints(Point a, Point b){
		p1 = a;
		p2 = b;
		DX = Math.abs(p2.x-p1.x);
		DY = Math.abs(p2.y-p1.y);
	}

	public ArrayList<Point> ecuacion(){   //(int x0, int y0, int x1, int y1)
        ArrayList<Point> arr = new ArrayList<Point>();    
        int x0= p1.x;  int  y0 = p1.y; int   x1 = p2.x;  int   y1 = p2.y;
        int  ix, iy, x, y;
        double dx, dy;
        dx = x1 - x0;
        dy = y1 - y0;
        x = x0; y = y0;
        
        double m = dy/dx;
        double b = y0 - m*x0;
        
        arr.add(new Point(x, y));
        
        if(dx >= 0) ix = 1;
        else ix = -1;
        if(dy >= 0) iy = 1;
        else iy = -1;
        if (Math.abs(m) <= 1)
            while(x != x1){
                y = (int)Math.round(m*x + b);
                x = x + ix;
                arr.add(new Point(x, y));
            }
        else
            while(y != y1){
                x = (int)Math.round((y - b)/m);
                y = y + iy;
                arr.add(new Point(x, y));
            }
        return thickness(estilo(arr));
    }
	
	public ArrayList<Point> bresenham(){	//(int x0, int y0, int x1, int y1)
	    int  x0= p1.x;   int  y0 = p1.y;  int  x1 = p2.x;  int   y1 = p2.y;
        ArrayList<Point> arr = new ArrayList<Point>();        
        int  ix, iy, x, y, dx, dy, p, deltaA, deltaB;
        dx = Math.abs(x1 - x0);
        dy = Math.abs(y1 - y0);
        x = x0; y = y0;
        
        if (x1 >= x0) ix = 1;
        else ix = -1;
        
        if(y1 >= y0) iy = 1;
        else iy = -1;
        
        /*1er/4to / 5to/8vo octante] */
        if(dx >= dy){                   
            p = 2*dy - dx;            
            deltaA = 2*dy;
            deltaB = 2*(dy-dx);
            while ((x >= x1 && ix== -1 )|| (x <= x1 && ix == 1 ))
            {
                arr.add(new Point(x,y));
                //System.out.println(x+" "+y);
                x = x + ix;
                if (p >= 0){
                    y = y + iy;
                    p = p + deltaB;
                }
                else {
                    p = p + deltaA;
                }
            }
        }
        /*2do/3ro / 6to/7mo octante] */
        else{      
            p = 2*dx - dy;            
            deltaA = 2*dx;
            deltaB = 2*(dx-dy);
            while ((y >= y1 && iy== -1 )|| (y <= y1 && iy == 1 ))
            {
                arr.add(new Point(x,y));
                //System.out.println(x+" "+y);
                y = y + iy;
                if (p >= 0){
                    x = x + ix;
                    p = p + deltaB;
                }
                else {
                    p = p + deltaA;
                }
            }
        }
        return thickness(estilo(arr));
        }
	
	public ArrayList<Point> DDA(){		//(Point p1,Point p2)
		ArrayList<Point> list = new ArrayList<Point>();
		if(p2.x<p1.x){
			Point aux = p2;
			p2 = p1;
			p1 = aux;
		}
		if(p2.y<p1.y){
			Point aux = p2;
			p2 = p1;
			p1 = aux;
		}
		int dx = p2.x-p1.x;
		int dy = p2.y-p1.y;		
		int inc,x,y;
		x = p1.x;
		y = p1.y;
		list.add(p1);
		if(Math.abs(dx)>Math.abs(dy)){
			if(dx>=0){
				inc = 1;
			}else{
				inc = -1;
			}
			int times = 1;
			while(x!=p2.x || y!=p2.y){
				x = x + inc;
				if(dx != 0)
					y = (int) Math.round(p1.y + times*(1.0*dy)/dx);
				else 
					y = p2.y;
				times++;
				list.add(new Point(x,y));
			}
		}else{
			if(dy>=0){
				inc = 1;
			}else{
				inc = -1;
			}
			int times = 1;
			while(x!=p2.x || y!=p2.y){
				y = y + inc;
				if(dy != 0)
					x = (int) Math.round(p1.x + times*(1.0*dx)/dy);
				else
				    x = p2.x;
				times++;
				list.add(new Point(x,y));
			}		
		}
		list.add(p2);
		return thickness(estilo(list));
	}
	
	public ArrayList<Point> estilo(ArrayList<Point> points){
		ArrayList<Point> ans = points;
		if(getTipo().equals("Punteado")){
			for(int i=0;i<ans.size();i++){
					ans.remove(i);
			}
		}else if (getTipo().equals("Segmentado")) {
			int tot = 0;
			int cont = 0;
			while(tot < ans.size()){
				if(cont == 3){
					ans.remove(tot);
					cont = 0;
				}
				tot++;
				cont++;
			}
		}
		return ans;
	}
	
	public ArrayList<Point> thickness(ArrayList<Point> points){
		ArrayList<Point> ans = points;
		int tam = points.size();
		if(getGrosor().equals("Grueso")){
			int x;
			int y;
			for(int i=0;i<tam;i++){
				x = points.get(i).x;
				y = points.get(i).y;
				if(DY >= DX){
					ans.add(new Point(x,y+1));
					ans.add(new Point(x,y-1));
				}else{
					ans.add(new Point(x+1,y));
					ans.add(new Point(x-1,y));
				}
			}
		}else if(getGrosor().equals("Muy Grueso")){
			int x;
			int y;
			for(int i=0;i<tam;i++){
				x = points.get(i).x;
				y = points.get(i).y;
				if(DY >= DX){
					ans.add(new Point(x,y+2));
					ans.add(new Point(x,y+1));
					ans.add(new Point(x,y-1));
					ans.add(new Point(x,y-2));
				}
				else{
				ans.add(new Point(x+1,y));
				ans.add(new Point(x-1,y));
				ans.add(new Point(x+2,y));
				ans.add(new Point(x-2,y));
				}
			}
		}
		
		return ans;
		
	}

	@Override
	public void escalar(double s) {
		p2.x = (int)(p2.x*s);
		p2.y = (int)(p2.y*s);
		
	}

	@Override
	public void rotar(int deg) {
		Point p = p1;
		int x = p2.x-p.x;
		int y = p2.y-p.y;
		double degrees = Math.toRadians(-deg);
		p2.x = (int)(x*Math.cos(degrees)-y*Math.sin(degrees)+p.x);
		p2.y = (int)(y*Math.cos(degrees)+x*Math.sin(degrees)+p.y);
		
	}

	@Override
	public void trasladar(Point a, Point b) {
		int dx = a.x-b.x;
		int dy = a.y-b.y;
		//modificando el punto 1
		p1.x = p1.x +dx;
		p1.y = p1.y +dy;
		//modificando el punto 2
		p2.x = p2.x +dx;
		p2.y = p2.y +dy;
	}

	@Override
	public void trasladar(Point a) {
		int dx = a.x-p1.x;
		int dy = a.y-p1.y;
		//modificando el punto 1
		p1.x = p1.x +dx;
		p1.y = p1.y +dy;
		//modificando el punto 2
		p2.x = p2.x +dx;
		p2.y = p2.y +dy;
	}

	
	
}
