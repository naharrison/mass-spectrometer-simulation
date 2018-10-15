package edu.ung.phys2212.graphics;

import processing.core.PApplet;

public class GArrow extends PApplet {
	
	public PApplet parent;
	public int xStart, yStart, xEnd, yEnd;
	public int[] colors;
	
	public GArrow(PApplet parent, int[] colors, int xStart, int yStart, int xEnd, int yEnd) {
		this.parent = parent;
		this.colors = colors;
		this.xStart = xStart;
		this.yStart = yStart;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
	}
	
	public void display() {
		double length = Math.sqrt(Math.pow(xEnd - xStart, 2) + Math.pow(yEnd - yStart, 2));
		double angle = Math.atan2(yEnd - yStart, xEnd - xStart);

		parent.stroke(colors[0], colors[1], colors[2]);
		parent.strokeWeight((float) 2.5);
		parent.line(xStart, yStart, xEnd, yEnd);
		
		parent.pushMatrix();
		parent.translate(xEnd, yEnd);
		parent.rotate((float) angle);
		parent.line(0, 0, (int) (-0.09*length), (int) (0.06*length));
		parent.line(0, 0, (int) (-0.09*length), (int) (-0.06*length));
		parent.popMatrix();
	}

}
