package edu.ung.phys2212.em;

import edu.ung.phys2212.graphics.GArrow;
import processing.core.PApplet;

/**
 * @author naharrison
 * A simple velocity selector.
 * Two vertical charged rods create horizontal E-field.
 * B-field points into page.
 */
public class VelocitySelector extends PApplet {

	public PApplet parent;
	public double eField, bField;
	

	public VelocitySelector(PApplet parent, double eField, double bField) {
		this.parent = parent;
		this.eField = eField;
		this.bField = bField;
	}
	

	public void display() {
		parent.stroke(0);
		parent.strokeWeight((float) 1.0);
		
		int rodThickness = 40;

		parent.fill(205, 99, 71);
		parent.rect(0, 0, rodThickness, parent.height);

		parent.fill(75, 255, 75);
		parent.rect(parent.width - rodThickness, 0, rodThickness, parent.height);
		
		int[] red = {255, 0, 0};
		GArrow a1 = new GArrow(parent, red, rodThickness, 50, parent.width - rodThickness, 50);
		a1.display();
		GArrow a2 = new GArrow(parent, red, rodThickness, (int) (0.28*parent.height), parent.width - rodThickness, (int) (0.28*parent.height));
		a2.display();
		GArrow a3 = new GArrow(parent, red, rodThickness, (int) (0.5*parent.height), parent.width - rodThickness, (int) (0.5*parent.height));
		a3.display();
		GArrow a4 = new GArrow(parent, red, rodThickness, (int) (0.72*parent.height), parent.width - rodThickness, (int) (0.72*parent.height));
		a4.display();
		GArrow a5 = new GArrow(parent, red, rodThickness, parent.height - 50, parent.width - rodThickness, parent.height - 50);
		a5.display();
		
		parent.fill(0, 0, 255);
		parent.textSize(40);
		for(int j = 0; j < 4; j++) {
			int y = j*(parent.height/4) + parent.height/8;
			for(int k = 0; k < 4; k++) {
				int x = k*(parent.width/4) + parent.width/8;
				parent.text("X", x, y);
			}
		}
		
	}

}
