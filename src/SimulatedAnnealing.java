public class SimulatedAnnealing {

    //Set initial temp
    private double currentTemperature = 10000;
    //minimal temperature to cool
    private double minTemperature = 0.00001;
    private double internalLoop = 1000;
    //Cooling rate
    private double coolingRate = 0.001;
    //Initialize intial solution
    private Tour currentSolution ;
    
    //For homework
    private int count=0;
    
    //****************************************************** 
  	//*** Purpose: To initial the citiesList
  	//*** Input: None
  	//*** Output: None
  	//******************************************************
    public void initTour() {
        Tour tour = new Tour();
//        tour.addCity(new City("Snell's Farm"));
//        tour.addCity(new City("Planter's Farm"));
//        tour.addCity(new City("School"));
//        tour.addCity(new City("Gym"));
//        tour.addCity(new City("Movies"));
//        tour.addCity(new City("Snell's Farm"));
        
        //Test
        tour.addCity(new City("Snell's Farm"));
        tour.addCity(new City("Planter's Farm"));
        tour.addCity(new City("Gym"));
        tour.addCity(new City("Movies"));
        tour.addCity(new City("School"));
        tour.addCity(new City("Snell's Farm"));
        
        tour.getCity("Gym").setNeighbourDistance("School", 10);
        tour.getCity("Gym").setNeighbourDistance("Planter's Farm", 8);
        tour.getCity("Gym").setNeighbourDistance("Movies", 15);
        tour.getCity("Gym").setNeighbourDistance("Snell's Farm", 10000);
        
        tour.getCity("School").setNeighbourDistance("Gym", 10);
        tour.getCity("School").setNeighbourDistance("Planter's Farm", 14);
        tour.getCity("School").setNeighbourDistance("Movies", 10);
        tour.getCity("School").setNeighbourDistance("Snell's Farm", 10000);
        
        tour.getCity("Movies").setNeighbourDistance("Snell's Farm", 7);
        tour.getCity("Movies").setNeighbourDistance("Planter's Farm", 12);
        tour.getCity("Movies").setNeighbourDistance("School", 10);
        tour.getCity("Movies").setNeighbourDistance("Gym", 15);
        
        tour.getCity("Snell's Farm").setNeighbourDistance("Planter's Farm", 20);
        tour.getCity("Snell's Farm").setNeighbourDistance("Movies", 7);
        tour.getCity("Snell's Farm").setNeighbourDistance("School", 10000);
        tour.getCity("Snell's Farm").setNeighbourDistance("Gym", 10000);
        
        tour.getCity("Planter's Farm").setNeighbourDistance("Gym", 8);
        tour.getCity("Planter's Farm").setNeighbourDistance("School", 14);
        tour.getCity("Planter's Farm").setNeighbourDistance("Movies", 12);
        tour.getCity("Planter's Farm").setNeighbourDistance("Snell's Farm", 20);
        
        currentSolution = tour;
    }

    //****************************************************** 
  	//*** Purpose: Using  getting the best Tour
  	//*** Input: None
  	//*** Output: The best Tour
  	//******************************************************
    public Tour anneal() {
        Tour bestSolution = new Tour(currentSolution.getCitiesList());
        Tour newSolution = null;
        
        //Test
        int temp=0;
        
        // Loop until system has cooled
        while (currentTemperature > minTemperature) {
        	//Let it be stable in the currentTemperature
            for (int i = 0; i < internalLoop; i++) {
            	
                //get a solution from neighbour
                newSolution=currentSolution.generateNeighourTour();
                   
                // Get energy of solutions
                int currentEnergy = currentSolution.getDistance();
                
                //Test
                //temp=currentEnergy;
                
                int neighbourEnergy = newSolution.getDistance();

                //For homework print out
            	if (Math.random()<=0.5 && count<10){
            		for (City nextCity:currentSolution.getCitiesList())
            			System.out.print(nextCity.getCityName()+"->");
            		System.out.print(" | ");
            		for (City nextCity:newSolution.getCitiesList())
            			System.out.print(nextCity.getCityName()+"->");
            		System.out.println(" | "+currentEnergy+" | "+neighbourEnergy+" | "+(currentEnergy-neighbourEnergy));
            		count++;
            	}
                
                // Decide if we should accept the neighbour
                if (acceptanceProbability(currentEnergy, neighbourEnergy, currentTemperature) > Math.random()) {
                    currentSolution = new Tour(newSolution.getCitiesList());
                }
                
                bestSolution=currentSolution;
            }
            // Cool system
            currentTemperature *= 1-coolingRate;
        }
        
        return bestSolution;
    }

    //****************************************************** 
  	//*** Purpose: Calculate the acceptance probability
  	//*** Input: int energy, int newEnergy, double temperature
  	//*** Output: The acceptance probability
  	//******************************************************
    private double acceptanceProbability(int energy, int newEnergy, double temperature) {
        // If the new solution is better, accept it
        if (newEnergy < energy) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((energy - newEnergy) / temperature);
    }
}