
public class Body {
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	public Body(Body b) {
		myXPos = b.myXPos;
		myYPos = b.myYPos;
		myXVel = b.myXVel;
		myYVel = b.myYVel;
		myMass = b.myMass;
		myFileName = b.myFileName;
	}
	
	public Body(double xp, double yp, double xv, double yv, double mass, String fileName) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = fileName;
	}
	
	public double calcDistance(Body b) {
		return Math.sqrt(Math.pow(myXPos - b.myXPos, 2.0) + Math.pow(myYPos - b.myYPos, 2.0));
	}
	
	public double calcForceExertedBy(Body p) {
		return (6.67*1e-11)*(p.myMass*myMass)/(calcDistance(p)*calcDistance(p));
	}
	
	public double calcForceExertedByX(Body p) {
		return calcForceExertedBy(p)*(p.myXPos - myXPos)/(calcDistance(p));
	}
	
	public double calcForceExertedByY(Body p) {
		return calcForceExertedBy(p)*(p.myYPos - myYPos)/(calcDistance(p));
	}
	
	public double calcNetForceExertedByX(Body [] bodies) {
		double netFX = 0.0;
		for(Body b:bodies) {
			if(! b.equals(this)) {
				netFX += calcForceExertedByX(b);
			}
		}
		return netFX;
	}
	
	public double calcNetForceExertedByY(Body [] bodies) {
		double netFY = 0.0;
		for(Body b:bodies) {
			if(! b.equals(this)) {
				netFY += calcForceExertedByY(b);
			}
		}
		return netFY;
	}
	
	public void update(double deltaT, double xforce, double yforce) {
		double ax = xforce/myMass;
		double ay = yforce/myMass;
		double nvx = myXVel + ax*deltaT;
		double nvy = myYVel + ay*deltaT;
		double nx = myXPos + nvx*deltaT;
		double ny = myYPos + nvy*deltaT;
		
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
	}
	
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
	
	public double getX() {
		return myXPos;
	}
	
	public double getY() {
		return myYPos;
	}
	
	public double getXVel() {
		return myXVel;
	}
	
	public double getYVel() {
		return myYVel;
	}
	
	public double getMass() {
		return myMass;
	}
	
	public String getName() {
		return myFileName;
	}
}
