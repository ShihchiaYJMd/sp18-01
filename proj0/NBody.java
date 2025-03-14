public class NBody {
	public static double readRadius(String filename) {
		In in = new In(filename);
		int N = in.readInt();
		double R = in.readDouble();
		return R;
	}

	public static Planet[] readPlanets(String filename) {
		In in = new In(filename);
		int N = in.readInt(); // 读取行星数量
		double R = in.readDouble(); // 读取宇宙半径
		Planet[] planets_array = new Planet[N];
		for (int i = 0; i < N; i++) {
			double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
			planets_array[i] = new Planet(xPos, yPos, xVel, yVel, mass, img);
		}
		return planets_array;
	}

	public static void main(String[] args) {
		// Adding Audio
        StdAudio.play("audio/2001.mid");

		// Collecting All Needed Input
		double T = Double.parseDouble(args[0]); 
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double universeRadius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		// Drawing the Background
		// Enable double buffering
		StdDraw.enableDoubleBuffering();
		// 设置比例以匹配宇宙的半径
		StdDraw.setXscale(-universeRadius, universeRadius);
		StdDraw.setYscale(-universeRadius, universeRadius);

		// 绘制图像 starfield.jpg 作为背景
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");

		// Drawing One Planet
		for (Planet p : planets) {
			p.draw();
		}

		// Creating an Animation
		double timestep = 0.0;

		while (timestep < T) {
			int length = planets.length;
			double[] xForces = new double[length];
			double[] yForces = new double[length];
			for (int i = 0; i < length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			
			for (int i = 0; i < length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet p : planets) {
			p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			timestep += dt;
		}

		// Printing the Universe
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", universeRadius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
							planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  			planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}

		// Stop Audio
		StdAudio.close();
	}
}