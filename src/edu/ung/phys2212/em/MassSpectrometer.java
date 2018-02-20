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
	double initialVelocity;
	
	
	public void settings() {
		size(1000, 600);
	}
	
	
	public void setup() {
		String[] txtFileLines = loadStrings("data.txt");
		int pid = Integer.parseInt(txtFileLines[0]);
		double eField = Double.parseDouble(txtFileLines[1]);
		double bField1 = Double.parseDouble(txtFileLines[2]);
		double bField2 = Double.parseDouble(txtFileLines[3]);
		
		if(pid == 2) initialVelocity = -170;
		else if(pid == 1) initialVelocity = -185;
		else initialVelocity = -200;
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
		if(part.x < 0 || part. y < 0 || part.x > width || part.y > height) {
			part.update(width/2, height, 0, initialVelocity);
		}
	}

}
