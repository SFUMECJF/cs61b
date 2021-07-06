import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
//https://www.tutorialspoint.com/static-import-the-math-class-methods-in-java
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    // public static final double G = 6.67e-11;
    private static final double G = 6.67e-11;
    public Planet(double xP, double yP, double xV,
    double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p) { // all the data from p is foundmental data
        // so we can copy directly
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
        // this.startDate = new Date(employee.startDate.getTime()); deepcopy
    }

    public double calcDistance(Planet p) {
        return sqrt(pow((this.xxPos - p.xxPos),2) + pow((this.yyPos - p.yyPos), 2));
    }

    public double calcForceExertedBy (Planet p) {
        return G * mass * p.mass / pow(calcDistance(p), 2);
    }

    public double calcForceExertedByX (Planet p) {
        return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
    }
    public double calcForceExertedByY (Planet p) {
        return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double result = 0;
        for (Planet p: allPlanets) {
            if (p.equals(this)) {
                continue;
            }
//            System.out.println(result);
            result += calcForceExertedByX(p);
        }
        return result;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
//        javac Planet.java TestCalcNetForceExertedByXY.java
//        java TestCalcNetForceExertedByXY
        double result = 0;
        for (Planet p: allPlanets) {
            if (p.equals(this)) {
                continue;
            }
            result += calcForceExertedByY(p);
        }
        return result;
    }

    public void update(double dt, double fX, double fY) {
        double xxAcc = fX/mass;
        double yyAcc = fY/mass;
        xxVel = xxVel + dt * xxAcc;
        yyVel = yyVel + dt * yyAcc;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }
    public void draw() {
        String filename = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, filename);
    }
}