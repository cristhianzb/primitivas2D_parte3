package modelo;


import java.util.ArrayList;
import java.util.Comparator;
import java.awt.Color;
import java.awt.Point;

public final class Circumference extends Primitive2D{
	private int h, k ,r;	
	
	public Circumference(){
		super();
                h = 0;
		k = 0;
		r = 0;
	}
	
	public Circumference(int cx, int cy, int radio){
		h = cx;
		k = cy;
		r = radio;
	}
	@Override
	public String toString(){
		return "Circunferencia";
	}
	
	public Circumference(int cx, int cy, int radio, String th, String tipo, Color col){
		super(th, tipo, col);
		h = cx;
		k = cy;
		r = radio;
	}
	
	public void setCenter(int cx, int cy, int radio){
		h = cx;
		k = cy;
		r = radio;
	}
	
	public ArrayList<Point> polar(){       //o polar(int h, int k, int r)
        ArrayList<Point> p = new ArrayList<Point>();
        final double lim = Math.PI/4;
        int x = r, y = 0;
        double ang = 0, inc = 1.0/r;
        p.add(new Point(h+x, k+y)); //1er
        p.add(new Point(h-y, k+x)); //3er
        p.add(new Point(h-x, k-y)); //5to
        p.add(new Point(h+y, k-x)); //7mo
        ang = ang + inc;
        while(ang < lim){
            x = (int)Math.round(r*Math.cos(ang));
            y = (int)Math.round(r*Math.sin(ang));
            p.add(new Point(h+x, k+y)); //1er
            p.add(new Point(h-x, k+y)); //4to
            p.add(new Point(h-x, k-y)); //5to
            p.add(new Point(h+x, k-y)); //8vo
            p.add(new Point(h+y, k+x)); //2do
            p.add(new Point(h-y, k+x)); // 3er
            p.add(new Point(h-y, k-x)); // 6to
            p.add(new Point(h+y, k-x)); // 7mo
            ang = ang + inc;
        }
        return thickness(estilo(p));
    }
	
	public ArrayList<Point> bresenham(){
        ArrayList<Point> ans = new ArrayList<Point>();
        int e = 0;
        int x = r;
        int y = 0;
        while(x>=y){
            ans.add(new Point(h+x,k+y));
            ans.add(new Point(h+y,k+x));
            ans.add(new Point(h-x,k+y));
            ans.add(new Point(h-y,k+x));
            ans.add(new Point(h+x,k-y));
            ans.add(new Point(h+y,k-x));
            ans.add(new Point(h-x,k-y));
            ans.add(new Point(h-y,k-x));
            e = e + 2*y + 1;
            y = y+1;
            if(2*e>(2*x-1)){
                x=x-1;
                e = e - 2*x + 1;
            }
        }  
        return thickness(estilo(ans));
    }  
	
	public ArrayList<Point> estilo(ArrayList<Point> points){
		Point center = new Point(h, k);
		ArrayList<Point> ans = points;
		points.sort(new Comparator<Point>() {
		       public int compare(Point o1, Point o2) {
		    	   double angle1 = Math.atan2(o1.y - center.y, o1.x - center.x);
		           double angle2 = Math.atan2(o2.y - center.y, o2.x - center.x);

		           //For counter-clockwise, just reverse the signs of the return values
		           if(angle1 < angle2) return 1;
		           else if (angle2 < angle1) return -1;
		           return 0;
		       }
		      });
		
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
		
		int dx, dy;
		
		if(getGrosor().equals("Grueso")){
			int x;
			int y;
			for(int i=0;i<tam;i++){
				x = points.get(i).x;
				y = points.get(i).y;
				dx = Math.abs(x - h);
				dy = Math.abs(y - k);
				if(dy >= dx){
					ans.add(new Point(x,y+1));
					ans.add(new Point(x,y-1));
				}
				else{
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
				x = points.get(i).x;
				y = points.get(i).y;
				dx = Math.abs(x - h);
				dy = Math.abs(y - k);
				if(dy >= dx){
					ans.add(new Point(x,y+2));
					ans.add(new Point(x,y+1));
					ans.add(new Point(x,y-1));
					ans.add(new Point(x,y-2));
				}else{
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
		r = (int)(r * s);
		
	}

	
	//no hay rotacion con respecto al centro
	@Override
	public void rotar(int deg) {		
		
	}

	@Override
	public void trasladar(Point a, Point b) {
		int dx = a.x-b.x;
		int dy = a.y-b.y;
		h = h + dx;
		k = k + dy;
	}

	@Override
	public void trasladar(Point a) {
		h = a.x;
		k = a.y;
	}
	
}
