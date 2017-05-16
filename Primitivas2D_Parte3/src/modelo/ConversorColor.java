package modelo;

public class ConversorColor {

	public static int[] CMYtoRGB(double c, double m, double y) {
		int[] rgb = new int[3];
		if (validoCMY(c, m, y)) {
			rgb[0] = (int)((1 - c)*255);
			rgb[1] = (int)((1 - m)*255);
			rgb[2] = (int)((1 - y)*255);			
		}		
		return rgb;
	}

	public static int[] HSLtoRGB(int h, double s, double l) {
		s = s / 100.0;
		l = l / 100.0;
		int[] rgb = new int[3];
		double r = 0;
		double g = 0;
		double b = 0;
		if (validoHSL(h, s, l)) {
			double c = (1 - Math.abs(2 * l - 1)) * s;
			double hp = h / 60.0;
			double x = c * (1 - Math.abs((hp % 2) - 1));
			if(0 <= hp && hp < 1){
				r = c;
				g = x;
				b = 0;
			}
			if(1 <= hp && hp < 2){
				r = x;
				g = c;
				b = 0;
			}
			if(2 <= hp && hp < 3){
				r = 0;
				g = c;
				b = x;
			}
			if(3 <= hp && hp < 4){
				r = 0;
				g = x;
				b = c;
			}
			if(4 <= hp && hp < 5){
				r = x;
				g = 0;
				b = c;
			}
			if(5 <= hp && hp < 6){
				r = c;
				g = 0;
				b = x;
			}
			double m = l - (c / 2);
			rgb[0] = (int)(Math.round(((r+m)*255)));
			rgb[1] = (int)(Math.round(((g+m)*255)));
			rgb[2] = (int)(Math.round(((b+m)*255)));	
		}
		return rgb;
	}

	private static boolean validoHSL(int h, double s, double l) {
		boolean valido = false;
		if (0 <= h && h < 360) {
			if (0 <= s && s <= 1.0) {
				if (0 <= l && l <= 1.0) {
					valido = true;
				}
			}
		}
		return valido;
	}
	
	private static boolean validoCMY(double c, double m, double y) {
		boolean valido = false;
		if (0 <= c && c <= 1) {
			if (0 <= m && m <= 1) {
				if (0 <= y && y <= 1) {
					valido = true;
				}
			}
		}
		return valido;
	}

}
