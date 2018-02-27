package edu.ung.phys2212.graphics;

import processing.core.PApplet;

public class InOutCurrentWire extends PApplet {

	public PApplet parent;
	public double current; // out of page > 0; in < 0
	public double x, y, mass, length;
	
	public InOutCurrentWire(PApplet parent, double current, double x, double y, double mass, double length) {
		this.parent = parent;
		this.current = current;
		this.x = x;
		this.y = y;
		this.mass = mass;
		this.length = length;
	}
	
	
	public void update(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void display() {
		parent.strokeWeight((float) 1.5);
		parent.stroke(0);
		parent.fill(255);
		parent.ellipse((int) x, (int) y, 25, 25);
		if(current > 0) {
			parent.fill(0);
			parent.text("X", (int) x-3, (int) y+4);
		}
		else if(current < 0) {
			parent.fill(0);
			parent.ellipse((int) x, (int) y, 6, 6);
		}
	}


}
