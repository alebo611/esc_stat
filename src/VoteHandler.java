import java.util.ArrayList;
import java.util.HashMap;


public class VoteHandler {

	HashMap<String, Country> countryTotalPoints = new HashMap<String,Country>();
	
	public void calculateTotalPoints(ArrayList<Vote> votes){
		for(Vote v  : votes){
	
			Country c = countryTotalPoints.containsKey(v.to) ? countryTotalPoints.get(v.to) : new Country(v.to);  
			c.addVote(v);
			countryTotalPoints.put(v.to,c);
			}
		}
	
}
