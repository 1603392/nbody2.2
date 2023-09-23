public class Main {
    public static void main(String[] args) {
        int numargs = args.length;
        //assert numargs == 5  : "invalid number of arguments";
        double dt = Double.parseDouble(args[0]);
        int pauseTime = Integer.parseInt(args[1]);
        boolean trace = args[2].toLowerCase().equals("trace");
        String fname = args[3];
        /*
        for (int i = 0; i < numargs; i++)
        {
            System.out.println("Este es el parametro: " + i + "y es: " + args[i]);
        }
                    System.out.println("He entrado aquí central_configuration");

         */
        String constructorIndicator = args[3];
        Universe universe;

        if(constructorIndicator.equals("central_configuration"))
        {
            System.out.println("He entrado aquí central_configuration");

            universe = new Universe(Integer.parseInt(args[4]), 1);
        }
        if(constructorIndicator.equals("nplus1")){
            universe = new Universe(Integer.parseInt(args[4]),2);
            System.out.println("He entrado aquí nplus1");

        }
        else{
            System.out.println("He entrado aquí ");
            universe = new Universe(fname);
        }



        NBodySimulator simulator = new NBodySimulator(universe, dt, pauseTime, trace);
        simulator.simulate();
    }
}