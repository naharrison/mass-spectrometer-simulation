package edu.ung.phys2212;

public enum ParticleType {
	
	AE(0, -1.5, 1.0, "figs/ae.PNG"), IN(1, 2.0, 0.5, "figs/in.PNG"), NH(2, -1.75, 0.75, "figs/nh2.jpg");
	
	private int id;
	private double charge, mass;
	private String imgName;
	
	ParticleType(int id, double charge, double mass, String imgName) {
		this.id = id;
		this.charge = charge;
		this.mass = mass;
		this.imgName = imgName;
	}

	public int getId() {
		return id;
	}

	public double getCharge() {
		return charge;
	}

	public double getMass() {
		return mass;
	}
	
	public String getImgName() {
		return imgName;
	}

}
