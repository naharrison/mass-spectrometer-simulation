package edu.ung.phys2212;

import processing.core.PApplet;

public class SmallParticle extends PApplet {

	public PApplet parent;
	public double charge, mass, x, y, vx, vy;

	public SmallParticle(PApplet parent, double charge, double mass, double x, double y, double vx, double vy) {
		this.parent = parent;
		this.charge = charge;
		this.mass = mass;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}

	public void update(double x, double y, double vx, double vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}
	
	public void display() {
		parent.fill(0);
		parent.stroke(0);
		parent.ellipse((int) x, (int) y, (int) 8, (int) 8);
	}
}
