public class Main {
    public static void main(String[] args) {
        int numargs = args.length;
        //assert numargs == 5  : "invalid number of arguments";
        double dt = Double.parseDouble(args[0]);
        int pauseTime = Integer.parseInt(args[1]);
        boolean trace = args[2].toLowerCase().equals("trace");

        String constructorIndicator = args[3];
        Universe universe;

        if(constructorIndicator.equals("central_configuration"))
        {
            universe = new Universe(Integer.parseInt(args[4]), 1);
        }else {
            if (constructorIndicator.equals("nplus1")) {
                universe = new Universe(Integer.parseInt(args[4]), 2);

            } else {

                universe = new Universe(args[4]);
            }
        }


        NBodySimulator simulator = new NBodySimulator(universe, dt, pauseTime, trace);
        simulator.simulate();
    }
}