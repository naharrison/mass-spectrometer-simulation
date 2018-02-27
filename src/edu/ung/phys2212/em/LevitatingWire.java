package edu.ung.phys2212.em;

import edu.ung.phys2212.graphics.InOutCurrentWire;
import processing.core.PApplet;

public class LevitatingWire extends PApplet {

	public static void main(String[] args) {
		PApplet.main("edu.ung.phys2212.em.LevitatingWire");
	}
	

	public InOutCurrentWire baseWireLeft, baseWireRight, floatingWire;
	public double vx, vy, mu;

	
	public void settings() {
		size(1000, 600);
	}
	
	
	public void setup() {
		frameRate(30);

		String[] txtFileLines = loadStrings("levitatingWire.txt");
		double x0 = Double.parseDouble(txtFileLines[0]);
		double y0 = Double.parseDouble(txtFileLines[1]);
		double i0 = Double.parseDouble(txtFileLines[2]);
		double xL = Double.parseDouble(txtFileLines[3]);
		double yL = Double.parseDouble(txtFileLines[4]);
		double iL = Double.parseDouble(txtFileLines[5]);
		double xR = Double.parseDouble(txtFileLines[6]);
		double yR = Double.parseDouble(txtFileLines[7]);
		double iR = Double.parseDouble(txtFileLines[8]);
		
		baseWireLeft = new InOutCurrentWire(this, iL, xL, yL, 0.01, 20);
		baseWireRight = new InOutCurrentWire(this, iR, xR, yR, 0.01, 20);
		floatingWire = new InOutCurrentWire(this, i0, x0, y0, 0.01, 20);
		vx = vy = 0.0;
		mu = 4.0*Math.PI*0.0000001;
	}

	
	public void draw() {
		background(240);
		baseWireLeft.display();
		baseWireRight.display();
		floatingWire.display();
		
		double dt = 1.0/30.0;
		
		double dLeft = Math.sqrt(Math.pow(floatingWire.x - baseWireLeft.x, 2) + Math.pow(floatingWire.y - baseWireLeft.y, 2));
		dLeft = dLeft/1000.0;
		double dRight = Math.sqrt(Math.pow(floatingWire.x - baseWireRight.x, 2) + Math.pow(floatingWire.y - baseWireRight.y, 2));
		dRight = dRight/1000.0;

		double thetaLeft = Math.atan2(baseWireLeft.y - floatingWire.y, floatingWire.x - baseWireLeft.x);
		double thetaRight = Math.atan2(baseWireRight.y - floatingWire.y, baseWireRight.x - floatingWire.x);
		
		double fLeft = -1.0*(mu*baseWireLeft.current*floatingWire.current*floatingWire.length)/(2.0*Math.PI*dLeft);
		double fRight = -1.0*(mu*baseWireRight.current*floatingWire.current*floatingWire.length)/(2.0*Math.PI*dRight);

		double fx = fLeft*Math.cos(thetaLeft) - fRight*Math.cos(thetaRight);
		double fy = -1.0* fLeft*Math.sin(thetaLeft) - fRight*Math.sin(thetaRight) + 9.8*floatingWire.mass;
		
		double ax = fx/floatingWire.mass;
		double ay = fy/floatingWire.mass;
		
		floatingWire.update(floatingWire.x + 1000.0*vx*dt, floatingWire.y + 1000.0*vy*dt);
		
		vx += ax*dt;
		vy += ay*dt;

		//System.out.println(dLeft + " " + dRight);
		//System.out.println(fLeft*Math.cos(thetaLeft) + " " + fRight*Math.cos(thetaRight) + " " + fLeft*Math.sin(thetaLeft) + " " + fRight*Math.sin(thetaRight));
		//System.out.println(fx + " " + fy + " " + 9.8*floatingWire.mass);
		//System.out.println(fy + " " + ay + " " + vy + " " + floatingWire.y);
		//System.out.println(thetaLeft + " " + thetaRight);
	}

}


