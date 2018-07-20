import java.util.HashMap;
import java.util.Map;

public class City {
	private String cityName;
	private Map map=new HashMap();

	public City(){
		
    }
	
	//****************************************************** 
  	//*** Purpose:Constructs a city at chosen x, y location
  	//*** Input: String cityName
  	//*** Output: None
  	//******************************************************
    public City(String cityName){
        this.cityName = cityName;
    }

    //****************************************************** 
  	//*** Purpose: Get the distance between the neighbour
  	//*** Input: String neighbourCityName
  	//*** Output: The distance
  	//******************************************************
    public int getNeighbourDistance(String neighbourCityName){
        return (int) map.get(neighbourCityName);
    }

    //****************************************************** 
  	//*** Purpose: set the distance between the neighbour
  	//*** Input: String neighbourCityName, int distance
  	//*** Output: None
  	//******************************************************
    public void setNeighbourDistance(String neighbourCityName, int distance){
    	map.put(neighbourCityName, distance);
    }
    
    //****************************************************** 
  	//*** Purpose: get city name
  	//*** Input: None
  	//*** Output: City name
  	//******************************************************
    public String getCityName(){
    	return cityName;
    }
}