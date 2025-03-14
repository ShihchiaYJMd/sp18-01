public class TestPlanet {
	public static void main(String[] args) {
		// create two planet instances
		Planet earth = new Planet(0, 0, 0, 0, 5.972e24, "earth.png");
		Planet mars = new Planet(1.496e11, 0, 0, 0, 6.417e23, "mars.png");

		// Calculate the gravitational force exerted by Earth on Mars
		double forceEarthToMars = earth.calcForceExertedBy(mars);

		// Calculate the gravitational force exerted by Mars on Earth
		double forceMarsToEarth = mars.calcForceExertedBy(earth);

		// Print the results
		System.out.println("Gravitational force exerted by Earth on Mars: " + forceEarthToMars + " N");
		System.out.println("Gravitational force exerted by Mars on Earth: " + forceMarsToEarth + " N");

		// Verify Newton's Third Law: Action and reaction forces are equal in magnitude
		double difference = Math.abs(forceEarthToMars - forceMarsToEarth);
		if (difference < 1e-6) {
			System.out.println("Newton's Third Law is verfied: Forces are equal in magnitude.");
		} else {
			System.out.println("Error: Newton's Third Law is not verfied! The Error is " + difference + ".");

		}

		// 计算净力
        Planet[] planets = {earth, mars};
        double netForceX = earth.calcNetForceExertedByX(planets);
        double netForceY = earth.calcNetForceExertedByY(planets);

        System.out.println("Net Force in X direction: " + netForceX + " N");
        System.out.println("Net Force in Y direction: " + netForceY + " N");

        // 更新行星状态
        earth.update(0.005, netForceX, netForceY);
        System.out.println("Updated position: (" + earth.xxPos + ", " + earth.yyPos + ")");
        System.out.println("Updated velocity: (" + earth.xxVel + ", " + earth.yyVel + ")");
	}
}




