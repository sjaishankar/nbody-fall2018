
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
	
	// Calculates distance between two planets
	public double calcDistance(Body b) {
		return Math.sqrt(Math.pow(myXPos - b.myXPos, 2.0) + Math.pow(myYPos - b.myYPos, 2.0));
	}
	
	//Calculates the force exerted by the parameter Body p on the local Body object
	public double calcForceExertedBy(Body p) {
		return (6.67*1e-11)*(p.myMass*myMass)/(calcDistance(p)*calcDistance(p));
	}
	
	//Calculates x-direction force exerted by Body p on local Body object
	public double calcForceExertedByX(Body p) {
		return calcForceExertedBy(p)*(p.myXPos - myXPos)/(calcDistance(p));
	}
	
	//Calculates y-direction force exerted by Body p on local Body object
	public double calcForceExertedByY(Body p) {
		return calcForceExertedBy(p)*(p.myYPos - myYPos)/(calcDistance(p));
	}
	
	//Calculates x-direction net force exerted by all other bodies in system on local Body object
	public double calcNetForceExertedByX(Body [] bodies) {
		double netFX = 0.0;
		for(Body b:bodies) {
			if(! b.equals(this)) {
				netFX += calcForceExertedByX(b);
			}
		}
		return netFX;
	}
	
	//Calculates y-direction net force exerted by all other bodies in system on local Body object
	public double calcNetForceExertedByY(Body [] bodies) {
		double netFY = 0.0;
		for(Body b:bodies) {
			if(! b.equals(this)) {
				netFY += calcForceExertedByY(b);
			}
		}
		return netFY;
	}
	
	/*Changes values of x-position and velocity and y-position and velocity based on dt and calculated 
	 * xforce and yforce in main method that calls calcNetForceExertedBy methods from Body class */
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
	
	//The next 6 methods access the instance field values
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
