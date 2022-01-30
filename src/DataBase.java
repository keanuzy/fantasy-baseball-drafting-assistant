
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 *	The DataBase Class.
 *	With file scanner from the .cvs file to hashmaps
 *  Update different position for hitter group.
 */
public class DataBase {

	static Map<String, Pitcher> pitcherHashMap = new HashMap<String, Pitcher>();	//store all the pithcer hashmap
	static Map<String, Hitter> hitterHashMap = new HashMap<String, Hitter>();		//store all the hitter hashmap
	//store the hitter with the position
	static Map<String, ArrayList<String>> positionHashMap = new HashMap<String, ArrayList<String>>(); 	
	
	static ArrayList<String> C = new ArrayList<String>(); //Arraylist for each position with every player of this.
	static ArrayList<String> B1 = new ArrayList<String>();
	static ArrayList<String> B2 = new ArrayList<String>();
	static ArrayList<String> B3 = new ArrayList<String>();
	static ArrayList<String> SS = new ArrayList<String>();
	static ArrayList<String> LF = new ArrayList<String>();
	static ArrayList<String> CF = new ArrayList<String>();
	static ArrayList<String> RF = new ArrayList<String>();

	// import the pitcher.csv to the hashmap<name, pitcher> and also add all the
	// pithcer in to the PitcherDataBase
	public void inputPitcherData() throws IOException {

		BufferedReader br = null;
		URL url = getClass().getResource("pitcher_copy.csv");
		String currentLine = ""; // init iterator variable
		String[] valuesTMP;

		try {

			br = new BufferedReader(new FileReader(url.getPath()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while ((currentLine = br.readLine()) != null) {
			valuesTMP = currentLine.split(","); //read file line by line, and split by ","

			String key = valuesTMP[1].trim() + ", " + valuesTMP[0].trim().substring(0, 1);	//make the key as LastName, firstInitial
			// String key = valuesTMP[0].trim();

			Pitcher newP = new Pitcher(valuesTMP[0], valuesTMP[1], Double.parseDouble(valuesTMP[2]),
					Integer.parseInt(valuesTMP[3]), Integer.parseInt(valuesTMP[4]), Double.parseDouble(valuesTMP[5]),
					Integer.parseInt(valuesTMP[6]));	//create new Pithcer objects from file inputs.

			PitcherDB.pitcherDB.add(newP);	//add the all Pitcher objects to PitcherDB

			pitcherHashMap.put(key.toUpperCase(), newP); //create the hashmap by the key of name, value of the Pitcher object
		}
	}

	public void inputHitterData() throws IOException {

		BufferedReader br = null;
		URL url = getClass().getResource("hitter_copy.csv");
		String currentLine = ""; // init iterator variable
		String[] valuesTMP;

		try {
			br = new BufferedReader(new FileReader(url.getPath()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while ((currentLine = br.readLine()) != null) {
			valuesTMP = currentLine.split(","); //read file line by line, and split by ","

			String key = valuesTMP[1].trim() + ", " + valuesTMP[0].trim().substring(0, 1);		//make the key as LastName, firstInitial
			String position = valuesTMP[2].trim();	//get the position of each hitter

			Hitter newH = new Hitter(valuesTMP[0], valuesTMP[1], valuesTMP[2], Integer.parseInt(valuesTMP[3]),
					Integer.parseInt(valuesTMP[4]), Double.parseDouble(valuesTMP[5]), Double.parseDouble(valuesTMP[6]),
					Double.parseDouble(valuesTMP[7])); //create new Hitter objects from file inputs.

			HitterDB.hitterDB.add(newH);	// add new hitter object to the hitterDB class, in case of the evalfun to use.

			hitterHashMap.put(key.toUpperCase(), newH); 	//create the hashmap by the key of name, value of the Hitter object

			
			//update each arraylist after add new player.
			if (position.equals("C")) {
				C.add(key.toUpperCase());
				positionHashMap.put(position, C);
			} else if (position.equals("1B")) {
				B1.add(key.toUpperCase());
				positionHashMap.put(position, B1);
			} else if (position.equals("2B")) {
				B2.add(key.toUpperCase());
				positionHashMap.put(position, B2);
			} else if (position.equals("3B")) {
				B3.add(key.toUpperCase());
				positionHashMap.put(position, B3);
			} else if (position.equals("SS")) {
				SS.add(key.toUpperCase());
				positionHashMap.put(position, SS);
			} else if (position.equals("LF")) {
				LF.add(key.toUpperCase());
				positionHashMap.put(position, LF);
			} else if (position.equals("CF")) {
				CF.add(key.toUpperCase());
				positionHashMap.put(position, CF);
			} else if (position.equals("RF")) {
				RF.add(key.toUpperCase());
				positionHashMap.put(position, RF);
			}
		}
	}
	
	

}
