package edu.ung.phys2212;

import edu.ung.phys2212.em.UniformMagField;
import edu.ung.phys2212.em.VelocitySelector;
import edu.ung.phys2212.graphics.CircleImage;
import processing.core.PApplet;

public class DrawTry extends PApplet {

	public static void main(String[] args) {
		PApplet.main("edu.ung.phys2212.DrawTry");
	}
	
	
	VelocitySelector v;
	CircleImage ci;
	UniformMagField mf;
	int x, y;
	

	public void settings() {
		size(800, 600);
	}

	
	public void setup() {
		v = new VelocitySelector(this, 0, 0);
		ci = new CircleImage(this, ParticleType.NH.getImgName(), 500, 100, 45);
		mf = new UniformMagField(this, 0.0);
		x = y = 0;
	}

	
	public void draw() {
		background(220);
		
		pushMatrix();
		translate(400, 300);
		scale((float) 0.5, (float) 0.5);
		v.display();
		popMatrix();

		pushMatrix();
		translate(30, 30);
		scale((float) 0.25, (float) 0.25);
		mf.display();
		popMatrix();
		
		x += 2;
		y += 2;
		if(x > width+10 || y > height+10) x = y = 0;
		ci.update(x, y);
		ci.display();
	}

}
