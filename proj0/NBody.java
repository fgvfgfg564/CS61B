public class NBody
{
    public static double readRadius(String filename)
    {
        In in = new In(filename);
        in.readDouble();
        return in.readDouble();
    }
    public static Body[] readBodies(String filename)
    {
        In in = new In(filename);
        int n = in.readInt();
        in.readDouble();
        Body[] bodies = new Body[n];
        for(int i = 0; i < n; i++)
        {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            bodies[i] = new Body(xP, yP, xV, yV, m, img);
        }
        return bodies;
    }
    public static void main(String[] args)
    {
        double T = Double.valueOf(args[0]), dt = Double.valueOf(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);
        int nBodies = bodies.length;
        String background = new String("images/starfield.jpg");

        PlayMidi player = new PlayMidi("audio/2001.mid");
        player.playMidi();

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);

        double time = 0;
        while(time <= T)
        {
            Double[] xForces = new Double[nBodies];
            Double[] yForces = new Double[nBodies];
            for(int i = 0; i < nBodies; i++)
            {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for(int i = 0; i < nBodies; i++)
            {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, background);
            for(Body body : bodies)
            {
                StdDraw.picture(body.xxPos, body.yyPos, "images/" + body.imgFileName);
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++)
        {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                          bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }

        player.stopMidi();
    }
}