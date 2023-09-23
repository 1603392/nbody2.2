//import com.sun.prism.paint.Color;

public class NBodySimulator {

    private Universe universe;
    private double timeStep;
    private int pauseTime;
    private boolean trace;

    public NBodySimulator(Universe universe, double dt, int pt, boolean doTrace) {
        this.universe = universe;
        timeStep = dt;
        pauseTime = pt;
        trace = doTrace;
    }

    public void simulate() {
        // TODO: to simulate, first create a canvas and then iterate forever :
        //  clear the canvas with StdDraw.clear(), update the universe (which
        //  means the position of its bodies), draw the universe (which means
        //  the position of its bodies), do StdDraw.show(), wait for some time
        //  that can be 0 with StdDraw.pause(pauseTime)

        createCanvas();

        while (true) {
            StdDraw.clear();

            universe.update(timeStep);

            drawUniverse(); // tell each body to draw itself
            StdDraw.show();
            StdDraw.pause(10);
        }

    }

    private void createCanvas() {
        //StdDraw.setCanvasSize(700, 700); // uncomment for a larger window
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenRadius(0.025);
        double radius = universe.getRadius();
        // read from txt file, second line
        StdDraw.setXscale(-radius, +radius);
        StdDraw.setYscale(-radius, +radius);
    }

    private void drawUniverse(){
        // TODO : get the position (a Vector) of each body in universe
        //  and plot it with StdDraw.point(x,y). A Vector v returns its coordinates
        //  with v.cartesian(0) and v.cartesian(1)
        //  For this we need to ask to a Universe its number of bodies n
        //  and the position of the i-th body, i=0...n-1. Also, a Body
        //  must have a getPosition() method


        Body[] planets=universe.getBodies();
        for(int i = 0; i < universe.getNumberBodies();i++)
        {

            Vector pos = planets[i].getPosition();

            StdDraw.point(pos.cartesian(0), pos.cartesian(1));

        }
    }
}
