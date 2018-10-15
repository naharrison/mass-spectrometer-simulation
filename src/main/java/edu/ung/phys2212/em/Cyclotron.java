package edu.ung.phys2212.em;

import edu.ung.phys2212.SmallParticle;
import edu.ung.phys2212.graphics.GArrow;
import processing.core.PApplet;

public class Cyclotron extends PApplet {

	public static void main(String[] args) {
		PApplet.main("edu.ung.phys2212.em.Cyclotron");
	}
	
	UniformMagField magField;
	double eField;
	int eSliderX;
	SmallParticle part;

	public void settings() {
		size(1000, 600);
	}
	

	public void setup() {
		frameRate(120);
		magField = new UniformMagField(this, 3.5);
		eField = 0.0; // initial value
		eSliderX = 150;
		int iv = 50;
		part = new SmallParticle(this, 1.0, 1.0, width/2 - 45, height/2 + 50, 0, iv);
	}
	
	
	public void draw() {
		background(240);
		magField.display();
		fill(25, 25, 225);
		textSize(24);
		text(String.format("B = %3.2f T", magField.bField), 25, 52);
		
		// The gap:
		fill(75);
		stroke(75);
		strokeWeight((float) 1.0);
		rect(470, 0, 10, height);
		rect(530, 0, 10, height);
		for(int j = 0; j < 8; j++) {
			int yVal = j*(height/8) + 15;
			int xStart, xEnd;
			if(eField >= 0) {
				xStart = 480;
				xEnd = 530;
			}
			else {
				xStart = 530;
				xEnd = 480;
			}
			int[] arrColor = {225, 25, 25};
			GArrow arr = new GArrow(this, arrColor, xStart, yVal, xEnd, yVal);
			arr.display();
		}

		// E-field slider:
		if(mouseX > 25 && mouseX < 275 && mouseY > 150 && mouseY < 180) eSliderX = mouseX;
		fill(225, 25, 25);
		stroke(225, 25, 25);
		strokeWeight((float) 1.0);
		rect(eSliderX, 150, 25, 30);
		eField = (eSliderX - 150.0)/400.0; // denominator sets the scale of the eField
		fill(225, 25, 25);
		textSize(22);
		text(String.format("E = %3.2f N/C", eField), 300, 170);
		
		// Particle motion:
		part.display();
		update();
	}
	
	
	public void update() {
		double charge = part.charge;
		double mass = part.mass;
		double dt = 1.0/120.0;
		double scaledvx = part.vx/1000.0; // vx is mm/s
		double scaledvy = part.vy/1000.0; // vy is mm/s

		double fx = charge*scaledvy*magField.bField;
		double fy = -1.0*charge*scaledvx*magField.bField;
		if(part.x > 480 && part.x < 530) {
			fx += charge*eField;
		}
		double ax = fx/mass;
		double ay = fy/mass;
		
		part.update(part.x + part.vx*dt, part.y + part.vy*dt, 1000.0*(scaledvx + ax*dt), 1000.0*(scaledvy + ay*dt));
	}
	
}
