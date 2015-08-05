import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.VolatileCallSite;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class CSVReader {

	final static String filename = "ESC-2015-grand_final-full_results.csv";
	
	ArrayList<Vote> votes = new ArrayList<Vote>();
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CSVReader r = new CSVReader();
		r.readCSVFile(filename);
		VoteHandler v = new VoteHandler();
		v.calculateTotalPoints(r.votes);
		
		
		
		ArrayList<Country> countries = new ArrayList<Country>(v.countryTotalPoints.values());
		
		// with comparator
		
		/*
		Collections.sort(countries, new Comparator<Country>() {
			 public int compare(Country c1, Country c2) {
		            return c2.totalPoints - c1.totalPoints;
		        }
		});
		*/
		
		
		// with lambda
		countries.sort((c1,c2) -> c2.totalPoints - c1.totalPoints);
		
		/*
		for(Country c : countries){
			System.out.format("%10d%20s%12s\n",countries.indexOf(c)+1,c.name, c.totalPoints);
		}
		*/
		
		List<Country> sortedByPoints = countries.stream()
			.sorted((c1,c2) -> c2.totalPoints - c1.totalPoints)
			.limit(10)
			.collect(Collectors.toList());
		
		List<Country> sortedByTelevote = countries.stream()
				.sorted((c1,c2) -> c1.televoteRank - c2.televoteRank)
				.limit(10)
				.collect(Collectors.toList());
		
		// find out all countries that should have made it into top ten if 
		// only televoting was applied
		sortedByTelevote.removeAll(sortedByPoints);
		
		
			
		
		
		
	}
	
	
	public void readCSVFile(String filename){
		String s;
		
		try (BufferedReader br = new BufferedReader(new FileReader(filename))){
			
			s = br.readLine();
			s = br.readLine();
			
			int fields = 11;
				
			String [] data = new String[fields];
			int i = 0;
			while(true){
				
				
				s = br.readLine();
				
				
				if (s == null) break;
				
				for (String retval: s.split(",", fields)){
					data[i] = retval;
					i++;
				}
				
				// if we filled all the fields we add the complete vote. 
				//Otherwise we continue with the values on the next row
				if (i == fields){ 
					i = 0;
					votes.add(new Vote(data));
				}
				else {
					i--; // compensate for the next row;
				}
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
