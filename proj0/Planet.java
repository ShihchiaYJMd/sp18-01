public class Planet{
	// 实例变量
	public double xxPos;

	public double yyPos;

	public double xxVel;

	public double yyVel;

	public double mass;

	public String imgFileName;

	// 定义常量：万有引力常数 G
	public static final double GRAVITATIONAL_CONSTANT = 6.67e-11;

	// 构造函数
	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
		this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
	}

	// 复制构造函数
	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double dX;
		double dY;
		dX = (this.xxPos - p.xxPos);
		dY = (this.yyPos - p.yyPos);
		return Math.pow((dX * dX + dY * dY), 0.5);
	}

	public double calcForceExertedBy(Planet p) {
		double dR = this.calcDistance(p);
		return GRAVITATIONAL_CONSTANT * this.mass * p.mass / (dR * dR);
	}

	public double calcForceExertedByX(Planet p) {
		double dX = p.xxPos - this.xxPos;
		double distance = this.calcDistance(p);
		return this.calcForceExertedBy(p) * dX / distance;
	}

	public double calcForceExertedByY(Planet p) {
		double dY = p.yyPos - this.yyPos;
		double distance = this.calcDistance(p);
		return this.calcForceExertedBy(p) * dY / distance;
	}

	public double calcNetForceExertedByX(Planet[] planets) {
		double netForceX = 0.0;
		for (Planet p : planets) {
			if (!this.equals(p)) {
				netForceX += this.calcForceExertedByX(p);
			}
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Planet[] planets) {
		double netForceY = 0.0;
		for (Planet p : planets) {
			if (!this.equals(p)) {
				netForceY += this.calcForceExertedByY(p);
			}
		}
		return netForceY;
	}

	public void update(double dt, double fX, double fY) {
		double accX = fX / this.mass;
		double accY = fY / this.mass;
		this.xxVel += dt * accX;
		this.yyVel += dt * accY;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}
















