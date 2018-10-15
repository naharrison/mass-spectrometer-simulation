package edu.ung.phys2212.graphics;

import processing.core.PApplet;
import processing.core.PImage;

public class CircleImage extends PApplet {

	public PApplet parent;
	public String imgName;
	public int x, y, r;

	private PImage img;
	
	
	public CircleImage(PApplet parent, String imgName, int x, int y, int r) {
		this.parent = parent;
		this.imgName = imgName;
		this.x = x;
		this.y = y;
		this.r = r;
		this.img = parent.loadImage(imgName);
	}
	
	
	public void update(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void display() {
		parent.stroke(0);
		parent.strokeWeight((float) 1.5);
		parent.fill(255, 255, 255);
		parent.ellipse(x, y, 2*r, 2*r);
		parent.image(img, x - (int) (r/Math.sqrt(2.0)), y - (int) (r/Math.sqrt(2.0)), (int) (r*Math.sqrt(2.0)), (int) (r*Math.sqrt(2.0)));
	}

}
