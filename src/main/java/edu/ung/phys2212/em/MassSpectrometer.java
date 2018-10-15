package edu.ung.phys2212.em;

import edu.ung.phys2212.Particle;
import edu.ung.phys2212.ParticleType;
import processing.core.PApplet;

/**
 * 1 pixel = 1 mm
 * v in mm/s unless using "scaledv*" variables (then m/s)
 */
public class MassSpectrometer extends PApplet {
	
	public static void main(String[] args) {
		PApplet.main("edu.ung.phys2212.em.MassSpectrometer");
	}
	
	
	VelocitySelector vSelector;
	UniformMagField magField;
	Particle part;
	double iv;
	
	
	public void settings() {
		size(1000, 600);
	}
	
	
	public void setup() {
		frameRate(30);

		String[] txtFileLines = loadStrings("data.txt");
		int pid = Integer.parseInt(txtFileLines[0]);
		double eField = Double.parseDouble(txtFileLines[1]);
		double bField1 = Double.parseDouble(txtFileLines[2]);
		double bField2 = Double.parseDouble(txtFileLines[3]);
		
		if(pid == 2) iv = -100;
		else if(pid == 1) iv = -125;
		else iv = -150;
		vSelector = new VelocitySelector(this, eField, bField1);
		magField = new UniformMagField(this, bField2);
		part = new Particle(this, ParticleType.values()[pid], width/2, height, 0, iv);
	}
	
	
	public void draw() {
		background(240);

		pushMatrix();
		translate(width/3, height/2);
		scale((float) (1.0/3.0), (float) 0.5);
		vSelector.display();
		popMatrix();

		pushMatrix();
		scale((float) 1.0, (float) 0.5);
		magField.display();
		popMatrix();
		
		part.display();
		
		//saveFrame("out/p0_####.png");
		
		update();
	}
	
	
	public void update() {
		double charge = part.ptype.getCharge();
		double mass = part.ptype.getMass();
		double dt = 1.0/30.0;
		double scaledvx = part.vx/1000.0; // vx is mm/s
		double scaledvy = part.vy/1000.0; // vy is mm/s

		double fx, fy;
		fx = fy = 0.0;
		if(part.y > height/2 && part.x > width/3 && part.x < 2*width/3) {
			fx = charge*vSelector.eField + charge*scaledvy*vSelector.bField;
			fy = -1.0*charge*scaledvx*vSelector.bField;
		}
		else if(part.y < height/2) {
			fx = charge*scaledvy*magField.bField;
			fy = -1.0*charge*scaledvx*magField.bField;
		}
		double ax = fx/mass;
		double ay = fy/mass;
		
		part.update(part.x + part.vx*dt, part.y + part.vy*dt, 1000.0*(scaledvx + ax*dt), 1000.0*(scaledvy + ay*dt));
		if(part.x < 0 || part. y < 0 || part.x > width || part.y > height) {
			part.update(width/2, height, 0, iv);
		}
	}

}
