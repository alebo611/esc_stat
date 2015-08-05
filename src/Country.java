import java.util.HashMap;

public class Country {
	
	String name;
	int totalPoints = 0;
	int televoteRank = 0;
	int juryRank = 0;
	
	HashMap<Integer,Integer> numberOfScores = new HashMap<Integer,Integer>();

	
	public Country(String name){
		this.name=name;
		
		for(int i = 1; i <= 12 ; i++){
			numberOfScores.put(i, 0);
		}
		numberOfScores.remove(11);
		numberOfScores.remove(9);
	}
	
	public void addVote(Vote v){
		this.totalPoints+=v.points;
		try {
			this.televoteRank+=Integer.parseInt(v.televote);
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		
		
		try {
			this.juryRank+= Integer.parseInt(v.combined);
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		
		if(v.points != 0){
			int before = numberOfScores.get(v.points);
			before++;
			numberOfScores.put(v.points, before);
		}
		
	}
	
	void printInfo(){
		System.out.println(this.name);
		System.out.println("total score:\t" + this.totalPoints);
		System.out.println("jury rank:\t" + this.juryRank);
		System.out.println("televote rank:\t" + this.televoteRank);
		printScoreTable();
	}
	
	void printScoreTable(){
		System.out.println("Score table (how many of each point type):");
		for(int i = 1; i <= 12 ; i++){
			if (i == 9 || i == 11) continue;
			
			System.out.println(i +":\t" + numberOfScores.get(i));
		}
	}
	
	
	
}
