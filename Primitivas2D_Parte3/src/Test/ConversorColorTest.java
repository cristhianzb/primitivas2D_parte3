package Test;

import static org.junit.Assert.*;
import java.util.Arrays;

import org.junit.Test;

import modelo.ConversorColor;

public class ConversorColorTest {

	@Test
	public void test1() {
		ConversorColor cc = new ConversorColor();
		int[] resul = cc.HSLtoRGB(0, 0, 0.4);	
		int[] resp = {102,102,102};				
		assertArrayEquals(resp,resul);
	}
	
	@Test
	public void test2() {
		ConversorColor cc = new ConversorColor();
		int[] resul = cc.HSLtoRGB(153, 0.36, 0.63);
		int[] resp = {127,195,164};			
		assertArrayEquals(resp,resul);
	}
	
	@Test
	public void test3() {
		ConversorColor cc = new ConversorColor();
		int[] resul = cc.HSLtoRGB(300, 1, 0.25);
		int[] resp = {128,0,128};		
		assertArrayEquals(resp,resul);
	}
	
	@Test
	public void test4() {
		ConversorColor cc = new ConversorColor();
		int[] resul = cc.HSLtoRGB(0, 0.75, 0.85);
		int[] resp = {245,188,188};
		assertArrayEquals(resp,resul);
	}

}
