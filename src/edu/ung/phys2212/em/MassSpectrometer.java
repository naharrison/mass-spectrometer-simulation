package edu.ung.phys2212.em;

import edu.ung.phys2212.Particle;
import edu.ung.phys2212.ParticleType;
import processing.core.PApplet;

public class MassSpectrometer extends PApplet {
	
	public static void main(String[] args) {
		PApplet.main("edu.ung.phys2212.em.MassSpectrometer");
	}
	
	
	VelocitySelector vSelector;
	UniformMagField magField;
	Particle part;
	
	
	public void settings() {
		size(1000, 600);
	}
	
	
	public void setup() {
		// get from command line:
		double eField = -2.3;
		double bField1 = 0.022857;
		double bField2 = 1.75;
		int pid = 0;
		// ^^ get from command line ^^
		
		double initialVelocity = -200;
		vSelector = new VelocitySelector(this, eField, bField1);
		magField = new UniformMagField(this, bField2);
		part = new Particle(this, ParticleType.values()[pid], width/2, height, 0, initialVelocity);
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
		double dt = 1.0/60.0;

		double fx, fy;
		fx = fy = 0.0;
		if(part.y > height/2 && part.x > width/3 && part.x < 2*width/3) {
			fx = charge*vSelector.eField + charge*part.vy*vSelector.bField;
			fy = -1.0*charge*part.vx*vSelector.bField;
		}
		else if(part.y < height/2) {
			fx = charge*part.vy*magField.bField;
			fy = -1.0*charge*part.vx*magField.bField;
		}
		double ax = fx/mass;
		double ay = fy/mass;
		
		part.update(part.x + part.vx*dt, part.y + part.vy*dt, part.vx + ax*dt, part.vy + ay*dt);
	}

}
