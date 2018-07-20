import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Tour {
	
    //Holds our citiesList of cities
    private ArrayList<City> citiesList ;
    private int distance = 0;

    //****************************************************** 
  	//*** Purpose: The constructor of class Tour to initialize the citiesList.
  	//*** Input: None
  	//*** Output: None
  	//******************************************************
    public Tour() {
        citiesList = new ArrayList<City>();
    }
    
    //****************************************************** 
  	//*** Purpose: Constructs a citiesList from another citiesList
  	//*** Input: The tour ArrayList
  	//*** Output: None
  	//******************************************************
    public Tour(ArrayList<City> tour){
        citiesList = new ArrayList<City>();
        for (City city : tour) {
            this.citiesList.add(city);
        }
    }

    //****************************************************** 
  	//*** Purpose: Returns citiesList information
  	//*** Input: None
  	//*** Output: ArrayList<City>
  	//******************************************************
    public ArrayList<City> getCitiesList(){
        return citiesList;
    }

    //****************************************************** 
  	//*** Purpose: Swap two cities randomly
  	//*** Input: The citiesList ArrayList
  	//*** Output: None
  	//******************************************************
    public void swap(ArrayList<City> citiesList) { 
        int ranIndex=0;
        Random random = new Random();
        for (int i = 1; i < citiesList.size()-1; i++) {  
            ranIndex = (int) (3 * Math.random()+1);
            City temp=new City();
            temp=citiesList.get(ranIndex);
            citiesList.set(ranIndex, citiesList.get(i));
            citiesList.set(i, temp);
        }
    }
    
    //****************************************************** 
  	//*** Purpose: Creates a random individual
  	//*** Input: None
  	//*** Output: The instance of Class Tour
  	//******************************************************
    public Tour generateIndividual() {
        // Loop through all our destination cities and add them to our citiesList
        for (int cityIndex = 1; cityIndex < citiesList.size()-1; cityIndex++) {
          setCity(cityIndex, this.getCity(cityIndex));
        }
        // Randomly reorder the citiesList
        Collections.shuffle(citiesList);
        return this;
    }

    //****************************************************** 
  	//*** Purpose: Create new neighbour tour
  	//*** Input: None
  	//*** Output: The instance of Class Tour
  	//******************************************************
    public Tour generateNeighourTour(){
        Tour newSolution = new Tour(this.citiesList);
        // Get a random positions in the tour
        int tourPos1 = (int) (4 * Math.random()+1);
        int tourPos2 = (int) (4 * Math.random()+1);
        
        // Get the cities at selected positions in the tour
        City citySwap1 = newSolution.getCity(tourPos1);
        City citySwap2 = newSolution.getCity(tourPos2);

        // Swap them
        newSolution.setCity(tourPos2, citySwap1);
        newSolution.setCity(tourPos1, citySwap2);
        return newSolution;
    }

    //****************************************************** 
  	//*** Purpose: Gets a city from the citiesList
  	//*** Input: String neighbourName
  	//*** Output: The instance of Class City
  	//******************************************************
    public City getCity(String neighbourName) {
    	for (City nextCity:citiesList)
    		if (nextCity.getCityName().equals(neighbourName))
    			return nextCity;
    	return null;
    }
    
    //****************************************************** 
  	//*** Purpose: Gets a city from the citiesList
  	//*** Input: int tourPosition
  	//*** Output: The instance of Class City
  	//******************************************************
    public City getCity(int tourPosition) {
        return (City)citiesList.get(tourPosition);
    }

    //****************************************************** 
  	//*** Purpose: Sets a city in a certain position within a citiesList
  	//*** Input: int tourPosition, City city
  	//*** Output: None
  	//******************************************************
    public void setCity(int tourPosition, City city) {
        citiesList.set(tourPosition, city);
        // If the tours been altered we need to reset the fitness and distance
        distance = 0;
    }

    //****************************************************** 
  	//*** Purpose: Add a city to citiesList
  	//*** Input: City city
  	//*** Output: The instance of Class Tour
  	//******************************************************
    public Tour addCity(City city) {
        citiesList.add(city);
        return this;
    }

    //****************************************************** 
  	//*** Purpose: Get citiesList
  	//*** Input: None
  	//*** Output: ArrayList<City>
  	//******************************************************
    public ArrayList<City> getAllCities() {
        return citiesList;
    }

    //****************************************************** 
  	//*** Purpose: Get the total distance of the citiesList
  	//*** Input: None
  	//*** Output: distance
  	//******************************************************
    public int getDistance(){
        int tourDistance = 0;
        // Loop through our citiesList's cities
        for (int cityIndex=0; cityIndex < numberOfCities()-1; cityIndex++) {
            // Get city we're traveling from
            City fromCity = getCity(cityIndex);
            // City we're traveling to
            City destinationCity;
            
            destinationCity = getCity(numberOfCities()-1);
            
            // Get the distance between the two cities
            tourDistance += fromCity.getNeighbourDistance(citiesList.get(cityIndex+1).getCityName());
        }
        return tourDistance;
    }

    //****************************************************** 
  	//*** Purpose: Get number of cities on our citiesList
  	//*** Input: None
  	//*** Output: Number of cities
  	//******************************************************
    public int numberOfCities() {
        return citiesList.size();
    }
    
    //****************************************************** 
  	//*** Purpose: Override method to toString
  	//*** Input: None
  	//*** Output: String
  	//******************************************************
    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < numberOfCities(); i++) {
            geneString += getCity(i).getCityName()+"|";
        }
        return geneString;
    }
}