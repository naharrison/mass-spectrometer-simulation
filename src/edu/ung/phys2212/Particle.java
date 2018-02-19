package edu.ung.phys2212;

import edu.ung.phys2212.graphics.CircleImage;
import processing.core.PApplet;

public class Particle extends PApplet {
	
	public PApplet parent;
	public ParticleType ptype;
	public double x, y, vx, vy;
	
	private CircleImage cimg;

	public Particle(PApplet parent, ParticleType ptype, double x, double y, double vx, double vy) {
		this.parent = parent;
		this.ptype = ptype;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.cimg = new CircleImage(parent, ptype.getImgName(), (int) x, (int) y, 45);
	}
	
	public void update(double x, double y, double vx, double vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.cimg.update((int) x, (int) y);
	}
	
	public void display() {
		cimg.display();
	}
	
}
