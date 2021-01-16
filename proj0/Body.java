import java.util.function.Function;
import java.lang.Math;

public class Body
{
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;
    public static double G = 6.67e-11;
    public Body(double xP, double yP, double xV, double yV, double m, String img)
    {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Body(Body b)
    {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    public double calcDistance(Body other)
    {
        Function<Double, Double> sqr = (Double x) -> x * x;
        return Math.sqrt(sqr.apply(other.xxPos - xxPos) + sqr.apply(other.yyPos - yyPos));
    }
    public double calcForceExertedBy(Body other)
    {
        if(this.equals(other))
        {
            return 0;
        }
        double dist = calcDistance(other);
        return mass * other.mass * G / (dist * dist);
    }
    public double calcForceExertedByX(Body other)
    {
        if(this.equals(other))
        {
            return 0;
        }
        double force = calcForceExertedBy(other);
        double dist = calcDistance(other);
        double distX = other.xxPos - xxPos;
        return force * distX / dist;
    }
    public double calcForceExertedByY(Body other)
    {
        if(this.equals(other))
        {
            return 0;
        }
        double force = calcForceExertedBy(other);
        double dist = calcDistance(other);
        double distY = other.yyPos - yyPos;
        return force * distY / dist;
    }
    public double calcNetForceExertedByX(Body[] others)
    {
        double force = 0;
        for(Body other : others)
        {
            force += calcForceExertedByX(other);
        }
        return force;
    }
    public double calcNetForceExertedByY(Body[] others)
    {
        double force = 0;
        for(Body other : others)
        {
            force += calcForceExertedByY(other);
        }
        return force;
    }
    public void update(double dt, double xxForce, double yyForce)
    {
        double xA = xxForce / mass, yA = yyForce / mass;
        xxVel += dt * xA;
        yyVel += dt * yA;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }
}