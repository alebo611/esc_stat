
interface IntegerValue{
	String returnValueIfInteger(String value);
}

class Vote {
	String from; // The country that votes
	String to; // The country receiving the vote
	String a,b,c,d,e; //The Jurys
	String rank;
	String televote;
	String combined;
	int points;
	

	
	public Vote(String [] data){
		
		
		this.from = data[0];
		this.to = data[1];
		this.a = returnValueIfInteger(data[2]);
		this.b = returnValueIfInteger(data[3]);
		this.c = returnValueIfInteger(data[4]);
		this.d = returnValueIfInteger(data[5]);
		this.e = returnValueIfInteger(data[6]);
		this.rank = returnValueIfInteger(data[7]);
		this.televote = returnValueIfInteger(data[8]);
		this.combined = returnValueIfInteger(data[9]);
		this.points= getPointAsInteger(data[10]);
		
		
	}
	
	static String returnValueIfInteger(String value){
		try {
			Integer.parseInt(value);
			return value;
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	static int getPointAsInteger(String p){
		try {
			int i = Integer.parseInt(p);
			return i;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	@Override
	public String toString() {
		if (this.points > 0){
			return this.from + " gave " + this.to + " " + this.points + " points";
		}
		else {
			return this.from + " did not give " + this.to + " any points"; 
		}
	}

	
	
	
}