public class NBody {
    /**
     *  Read radius from file
     *  @param String file name
     *  @return a double corresponding to the radius of the universe in that file
     */
    public static double readRadius (String fileName) {
        In in = new In(fileName);
        int nouse = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int n = in.readInt();
        double radius = in.readDouble();

        Planet[] result = new Planet[n];
        for (int i = 0; i < n; i++) {
            result[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),in.readString());
        }
        return result;
    }

    public static void main (String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.clear(); //Clears the screen to the default color (white).
        StdDraw.picture(0, 0, "images/starfield.jpg");

        /* Animation */
        StdDraw.enableDoubleBuffering();
        double time = 0.0;
        while(time < T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i=0; i<planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i=0; i<planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }


        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}