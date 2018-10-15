package edu.ung.phys2212.em;

import processing.core.PApplet;

/**
 * @author naharrison
 * A const. B-field that points into the page.
 */
public class UniformMagField extends PApplet {
	
	public PApplet parent;
	public double bField;
	
	public UniformMagField(PApplet parent, double bField) {
		this.parent = parent;
		this.bField = bField;
	}
	
	
	public void display() {
		parent.fill(75, 75, 200);
		parent.textSize(30);
		for(int j = 0; j < 10; j++) {
			int y = j*(parent.height/10) + parent.height/20;
			for(int k = 0; k < 10; k++) {
				int x = k*(parent.width/10) + parent.width/20;
				parent.text("X", x, y);
			}
		}
	}

}
