import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Universe {

    private Body[] bodies;

    private int numBodies;

    private double radius;

    public Universe(String fname){
        System.out.println("He entrado EN EL CONSTRUCTOR CLÁSICO");



        try {
            Scanner in = new Scanner(new FileReader(fname));
            numBodies = Integer.parseInt(in.next());
            System.out.println("Number of bodies = " + numBodies);
            // the set scale to draw on the canvas
            radius = Double.parseDouble(in.next());
            System.out.println("Radius = " + radius);
            // read and make the n bodies
            bodies = new Body[numBodies];
            for (int i = 0; i < numBodies; i++) {
                double rx = Double.parseDouble(in.next());
                double ry = Double.parseDouble(in.next());
                double vx = Double.parseDouble(in.next());
                double vy = Double.parseDouble(in.next());
                double mass = Double.parseDouble(in.next());
                double[] position = {rx, ry};
                double[] velocity = {vx, vy};
                Vector r = new Vector(position);
                Vector v = new Vector(velocity);
                bodies[i] = new Body(r, v, mass);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void update(double dt){
        // TODO: first compute the force applied to each body as a result
        //  of the sum of gravitational forces by each other body. Then
        //  tell each body to move according to the force we have computed
        //  for it. Use plus() of Vector and forceFrom(), move() of Body

        for(int bodyIndex=0; bodyIndex < numBodies; bodyIndex++){
            Vector sum = new Vector(2);//vector with lenght of 2 because forceFrom function return 2 elements vector
            for(int bodyIndex2=0;bodyIndex2 < numBodies; bodyIndex2++){
                if(bodyIndex != bodyIndex2)
                {
                    //sumation of all the gravity forces in relation with body from bodyIndex
                    sum=sum.plus(bodies[bodyIndex].forceFrom(bodies[bodyIndex2]));
                }

            }
            bodies[bodyIndex].move(sum, dt);
        }

    }


    public Universe(int n, int tipo)
    {
        if(tipo == 1) {

            numBodies = n;
            radius = 1e11;
            bodies = new Body[numBodies];
            double mass = 1e33;
            double distance = 0.4*radius;
            double gamma = 5e-5; // |z^._j| = \gamma |z_j| \forall j
            double angleVelPos = Math.PI/4.; // angle z^._j z_j \forall j
            double velocityMagnitude = gamma*distance;

            for (int i=0; i<numBodies; i++) {
                double anglePos = (2*Math.PI*i)/numBodies;
                double rx = distance*Math.cos(anglePos);
                double ry = distance*Math.sin(anglePos);
                double vx = velocityMagnitude*Math.cos(anglePos + angleVelPos);
                double vy = velocityMagnitude*Math.sin(anglePos + angleVelPos);
                bodies[i] = new Body(new Vector(new double[]{rx,ry}), new Vector(new double[]{vx,vy}), mass);

            }

        }

        if(tipo == 2){

            final double MAX_VELOCITY = 1e05;
            final double MIN_VELOCITY = 1e04;
            final double MIN_MASS = 1e22;
            final double MAX_MASS = 1e24;
            final double BIGGEST_MASS = 1e39;
            numBodies = n+1;
            radius = 1e12;
            bodies = new Body[numBodies];
            bodies[0] = new Body(new Vector(new double[2]), new Vector(new double[2]), BIGGEST_MASS);

            for (int i = 1; i < numBodies; i++) {
                double angle = (2 * (Math.random() - 0.5))*Math.PI;
                double rho = (radius/4)*(Math.random() + 1);
                double rx = Math.cos(angle)*rho;
                double ry = Math.sin(angle)*rho;
                double vx = -ry/1000. + MIN_VELOCITY + 2 * (Math.random() - 0.5) * (MAX_VELOCITY - MIN_VELOCITY);
                double vy = rx/1000. + MIN_VELOCITY + 2 * (Math.random() - 0.5) * (MAX_VELOCITY - MIN_VELOCITY);
                double mass = MIN_MASS + Math.random() * (MAX_MASS - MIN_MASS);
                double[] position = {rx, ry};
                double[] velocity = {vx, vy};
                Vector r = new Vector(position);
                Vector v = new Vector(velocity);
                bodies[i] = new Body(r, v, mass);



            }

        }
    }
    public double getRadius(){
        return radius;
    }

    public Body[] getBodies(){return bodies;}

    public int getNumberBodies(){return numBodies;}
}