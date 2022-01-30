
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jdi.Type;

/**
 * 
 * This is the main class with all methods here.
 * 
 */
public class CommandLine {
	// Record such type of play of each team
	List<Pitcher> pitcherListOfMemberA = new ArrayList<Pitcher>();
	List<Pitcher> pitcherListOfMemberB = new ArrayList<Pitcher>();
	List<Pitcher> pitcherListOfMemberC = new ArrayList<Pitcher>();
	List<Pitcher> pitcherListOfMemberD = new ArrayList<Pitcher>();

	// Record such type of play of each team
	List<Hitter> hitterListOfMemberA = new ArrayList<Hitter>();
	List<Hitter> hitterListOfMemberB = new ArrayList<Hitter>();
	List<Hitter> hitterListOfMemberC = new ArrayList<Hitter>();
	List<Hitter> hitterListOfMemberD = new ArrayList<Hitter>();

	// Record the draft order of each team
	List<String> StarA = new ArrayList<String>();
	List<String> StarB = new ArrayList<String>();
	List<String> StarC = new ArrayList<String>();
	List<String> StarD = new ArrayList<String>();

	// A set to check positions has beed drafted
	Set<String> clientDraftPositions = new HashSet<String>();

	DataBase db = new DataBase();
	PitcherDB pdb = new PitcherDB();
	private BufferedReader br;

	public void oDraft(String lastName, String firstInitial, String leagueMember) throws IOException {

		// lastName = "XXXXX,
		// firstInitial = X" , which they need to be trimmed.
		// leagueMember can use directly

		String trimedLName = lastName.substring(1, lastName.length() - 1); // omit the " and ,
		String trimedFirstInitial = firstInitial.substring(0, 1); // omit the " after the first name initial
		String name = trimedLName + ", " + trimedFirstInitial; // the name as the key.

		// make sure the league member is correct
		if (leagueMember.equals("A") || leagueMember.equals("B") || leagueMember.equals("C")
				|| leagueMember.equals("D")) {

			if (DataBase.pitcherHashMap.containsKey(name)) {

				DataBase.pitcherHashMap.get(name).setDrafted(true);
				DataBase.pitcherHashMap.get(name).setDraftedBy(leagueMember);

				System.out.println(name + " has been drafted by " + leagueMember);
				if (leagueMember.equals("A")) {
					pitcherListOfMemberA.add(DataBase.pitcherHashMap.get(name));
					StarA.add("Position: Pitcher; First Name: " + DataBase.pitcherHashMap.get(name).getFirstName()
							+ ", Last Name: " + DataBase.pitcherHashMap.get(name).getLastName());

				} else if (leagueMember.equals("B")) {
					pitcherListOfMemberB.add(DataBase.pitcherHashMap.get(name));
					StarB.add("Position: Pitcher; First Name: " + DataBase.pitcherHashMap.get(name).getFirstName()
							+ ", Last Name: " + DataBase.pitcherHashMap.get(name).getLastName());
				} else if (leagueMember.equals("C")) {
					pitcherListOfMemberC.add(DataBase.pitcherHashMap.get(name));
					StarC.add("Position: Pitcher; First Name: " + DataBase.pitcherHashMap.get(name).getFirstName()
							+ ", Last Name: " + DataBase.pitcherHashMap.get(name).getLastName());
				} else {
					pitcherListOfMemberD.add(DataBase.pitcherHashMap.get(name));
					StarD.add("Position: Pitcher; First Name: " + DataBase.pitcherHashMap.get(name).getFirstName()
							+ ", Last Name: " + DataBase.pitcherHashMap.get(name).getLastName());
				}

				DataBase.pitcherHashMap.remove(name);
				// System.out.println("after remove :" + db.pitcherHashMap);

			} else if (DataBase.hitterHashMap.containsKey(name)) {

				DataBase.hitterHashMap.get(name).setDrafted();
				DataBase.hitterHashMap.get(name).setDraftedBy(leagueMember);

				if (leagueMember.equals("A")) {
					hitterListOfMemberA.add(DataBase.hitterHashMap.get(name));
					StarA.add("Position: " + DataBase.hitterHashMap.get(name).getPosition() + ", First Name: "
							+ DataBase.hitterHashMap.get(name).getFirstName() + ", Last Name: "
							+ DataBase.hitterHashMap.get(name).getLastName());

				} else if (leagueMember.equals("B")) {
					hitterListOfMemberB.add(DataBase.hitterHashMap.get(name));
					StarB.add("Position: " + DataBase.hitterHashMap.get(name).getPosition() + ", First Name: "
							+ DataBase.hitterHashMap.get(name).getFirstName() + ", Last Name: "
							+ DataBase.hitterHashMap.get(name).getLastName());

				} else if (leagueMember.equals("C")) {
					hitterListOfMemberC.add(DataBase.hitterHashMap.get(name));
					StarC.add("Position: " + DataBase.hitterHashMap.get(name).getPosition() + ", First Name: "
							+ DataBase.hitterHashMap.get(name).getFirstName() + ", Last Name: "
							+ DataBase.hitterHashMap.get(name).getLastName());

				} else {
					hitterListOfMemberD.add(DataBase.hitterHashMap.get(name));
					StarD.add("Position: " + DataBase.hitterHashMap.get(name).getPosition() + ", First Name: "
							+ DataBase.hitterHashMap.get(name).getFirstName() + ", Last Name: "
							+ DataBase.hitterHashMap.get(name).getLastName());

				}
				System.out.println(name + " has been drafted by " + leagueMember);

				String position = DataBase.hitterHashMap.get(name).getPosition();
			
				DataBase.positionHashMap.get(position).remove(name);
				DataBase.hitterHashMap.remove(name);

			} else {

				System.out.println("No player was drafted, please check your name input with first initial.");
			}
		} else {
			System.out.println("Please enter the correct LeagueMember: A, B, C, D");
			return;

		}

	}

	public void iDraft(String lastName, String firstInitial) {
		// lastName = "XXXXX,
		// firstInitial = X" , which they need to be trimmed.

		String trimedLName = lastName.substring(1, lastName.length() - 1); // omit the " and ,
		String trimedFirstInitial = firstInitial.substring(0, 1); // omit the " after the first name initial
		String name = trimedLName + ", " + trimedFirstInitial;

		if (DataBase.pitcherHashMap.containsKey(name)) {

			DataBase.pitcherHashMap.get(name).setDrafted(true);
			DataBase.pitcherHashMap.get(name).setDraftedBy("A");
			pitcherListOfMemberA.add(DataBase.pitcherHashMap.get(name));

			System.out.println(name + " has been drafted by A");
			StarA.add("Position: Pitcher; First Name: " + DataBase.pitcherHashMap.get(name).getFirstName()
					+ ", Last Name: " + DataBase.pitcherHashMap.get(name).getLastName());
			DataBase.pitcherHashMap.remove(name);

			// System.out.println("after remove :" + db.pitcherHashMap);

		} else if (DataBase.hitterHashMap.containsKey(name)) {	//pick the hitter from HitterHashMap 

			DataBase.hitterHashMap.get(name).setDrafted();
			DataBase.hitterHashMap.get(name).setDraftedBy("A");

			System.out.println(name + " has been drafted by A");

			hitterListOfMemberA.add(DataBase.hitterHashMap.get(name));

			String position = DataBase.hitterHashMap.get(name).getPosition();

			StarA.add("Position: " + position + ", First Name: " + DataBase.hitterHashMap.get(name).getFirstName()
					+ ", Last Name: " + DataBase.hitterHashMap.get(name).getLastName());

			//DataBase.positionHashMap.get(position).remove(name);
			
			if (position.equals("C")) {
				DataBase.C.remove(name);
				DataBase.positionHashMap.put(position, DataBase.C);
			}else if(position.equals("1B")) {
				DataBase.B1.remove(name);
				DataBase.positionHashMap.put(position, DataBase.B1);
			}else if(position.equals("2B")) {
				DataBase.B2.remove(name);
				DataBase.positionHashMap.put(position, DataBase.B2);
			}else if(position.equals("3B")) {
				DataBase.B3.remove(name);
				DataBase.positionHashMap.put(position, DataBase.B3);
			}else if(position.equals("SS")) {
				DataBase.SS.remove(name);
				DataBase.positionHashMap.put(position, DataBase.SS);
			}else if(position.equals("LF")) {
				DataBase.LF.remove(name);
				DataBase.positionHashMap.put(position, DataBase.LF);
			}else if(position.equals("CF")) {
				DataBase.CF.remove(name);
				DataBase.positionHashMap.put(position, DataBase.CF);
			}else if(position.equals("RF")) {
				DataBase.RF.remove(name);
				DataBase.positionHashMap.put(position, DataBase.RF);
			}
			DataBase.hitterHashMap.remove(name);
		
			clientDraftPositions.add(position);

		} else {

			System.out.println("No player was drafted, please check your name input with first initial.");
		}
	}
	// method to print all player in all position
	public void overall() { 

		if (!clientDraftPositions.contains("C")) {
			ArrayList<String> l = DataBase.positionHashMap.get("C");
			for (String str : l) {
				System.out.println(DataBase.hitterHashMap.get(str.toUpperCase()));
			}
		}

		if (!clientDraftPositions.contains("1B")) {
			ArrayList<String> l = DataBase.positionHashMap.get("1B");
			for (String str : l) {
				System.out.println(DataBase.hitterHashMap.get(str.toUpperCase()));
			}
		}

		if (!clientDraftPositions.contains("2B")) {
			ArrayList<String> l = DataBase.positionHashMap.get("2B");
			for (String str : l) {
				System.out.println(DataBase.hitterHashMap.get(str.toUpperCase()));
			}
		}

		if (!clientDraftPositions.contains("3B")) {
			ArrayList<String> l = DataBase.positionHashMap.get("3B");
			for (String str : l) {
				System.out.println(DataBase.hitterHashMap.get(str.toUpperCase()));
			}
		}

		if (!clientDraftPositions.contains("SS")) {
			ArrayList<String> l = DataBase.positionHashMap.get("SS");
			for (String str : l) {
				System.out.println(DataBase.hitterHashMap.get(str.toUpperCase()));
			}
		}
		if (!clientDraftPositions.contains("LF")) {
			ArrayList<String> l = DataBase.positionHashMap.get("LF");
			for (String str : l) {
				System.out.println(DataBase.hitterHashMap.get(str.toUpperCase()));
			}
		}
		if (!clientDraftPositions.contains("CF")) {
			ArrayList<String> l = DataBase.positionHashMap.get("CF");
			for (String str : l) {
				System.out.println(DataBase.hitterHashMap.get(str.toUpperCase()));
			}
		}
		if (!clientDraftPositions.contains("RF")) {
			ArrayList<String> l = DataBase.positionHashMap.get("RF");
			for (String str : l) {
				System.out.println(DataBase.hitterHashMap.get(str.toUpperCase()));
			}
		}

	}
	//method to print specific position of all the player
	public void overall(String position) {
		if (clientDraftPositions.contains(position)) {
			System.out.println("You have drafted this postion!");
			return;
		} else {
			ArrayList<String> l = DataBase.positionHashMap.get(position);
			for (String str : l) {
				System.out.println(DataBase.hitterHashMap.get(str.toUpperCase()));
			}

		}
	}
	
	//method to print all the pitchers
	public void pOverall() {

		System.out.println(Arrays.asList(DataBase.pitcherHashMap));

	}
	
	//method to print the drafted player by the order of non-hitter to pitcher.
	public void team(String leagueMember) {

		if (leagueMember.equals("A")) {
			for (int i = 0; i < hitterListOfMemberA.size(); i++) {
				System.out.println("Position: " + hitterListOfMemberA.get(i).getPosition() + ", First Name: "
						+ hitterListOfMemberA.get(i).getFirstName() + ", Last Name: "
						+ hitterListOfMemberA.get(i).getLastName());
			}

			for (int j = 0; j < pitcherListOfMemberA.size(); j++) {
				System.out.println("Position: pitcher" + ", First Name: " + pitcherListOfMemberA.get(j).getFirstName()
						+ ", Last Name: " + pitcherListOfMemberA.get(j).getLastName());
			}
		} else if (leagueMember.equals("B")) {
			for (int i = 0; i < hitterListOfMemberB.size(); i++) {
				System.out.println("Position: " + hitterListOfMemberB.get(i).getPosition() + ", First Name: "
						+ hitterListOfMemberB.get(i).getFirstName() + ", Last Name: "
						+ hitterListOfMemberB.get(i).getLastName());
			}

			for (int j = 0; j < pitcherListOfMemberB.size(); j++) {
				System.out.println("Position: pitcher" + ", First Name: " + pitcherListOfMemberB.get(j).getFirstName()
						+ ", Last Name: " + pitcherListOfMemberB.get(j).getLastName());
			}
		} else if (leagueMember.equals("C")) {
			for (int i = 0; i < hitterListOfMemberC.size(); i++) {
				System.out.println("Position: " + hitterListOfMemberC.get(i).getPosition() + ", First Name: "
						+ hitterListOfMemberC.get(i).getFirstName() + ", Last Name: "
						+ hitterListOfMemberC.get(i).getLastName());
			}

			for (int j = 0; j < pitcherListOfMemberC.size(); j++) {
				System.out.println("Position: pitcher" + ", First Name: " + pitcherListOfMemberC.get(j).getFirstName()
						+ ", Last Name: " + pitcherListOfMemberC.get(j).getLastName());
			}
		} else if (leagueMember.equals("D")) {
			for (int i = 0; i < hitterListOfMemberD.size(); i++) {
				System.out.println("Position: " + hitterListOfMemberD.get(i).getPosition() + ", First Name: "
						+ hitterListOfMemberD.get(i).getFirstName() + ", Last Name: "
						+ hitterListOfMemberD.get(i).getLastName());
			}

			for (int j = 0; j < pitcherListOfMemberD.size(); j++) {
				System.out.println("Position: pitcher" + ", First Name: " + pitcherListOfMemberD.get(j).getFirstName()
						+ ", Last Name: " + pitcherListOfMemberD.get(j).getLastName());
			}
		} else {
			System.out.println("PLease enter the correct leagueMember!");
			return;
		}
	}
	
	//Method to print all the player have been drafted by any leaguemember in the drafting order.
	public void stars(String leagueMember) {

		if (leagueMember.equals("A")) {
			for (int i = 0; i < StarA.size(); i++) {
				System.out.println(StarA.get(i));
			}
		} else if (leagueMember.equals("B")) {
			for (int i = 0; i < StarB.size(); i++) {
				System.out.println(StarB.get(i));
			}
		} else if (leagueMember.equals("C")) {
			for (int i = 0; i < StarC.size(); i++) {
				System.out.println(StarC.get(i));
			}
		} else if (leagueMember.equals("D")) {
			for (int i = 0; i < StarD.size(); i++) {
				System.out.println(StarD.get(i));
			}
		} else {
			System.out.println("Please enter correct league member (A, B, C, D)");
		}
	}

	//method to save all the drafted data to a .txt file
	public void save(String fileName) {
		Gson gson = new Gson();
		String jsonPitcherA = gson.toJson(pitcherListOfMemberA);
		String jsonPitcherB = gson.toJson(pitcherListOfMemberB);
		String jsonPitcherC = gson.toJson(pitcherListOfMemberC);
		String jsonPitcherD = gson.toJson(pitcherListOfMemberD);

		String jsonHitterA = gson.toJson(hitterListOfMemberA);
		String jsonHitterB = gson.toJson(hitterListOfMemberB);
		String jsonHitterC = gson.toJson(hitterListOfMemberC);
		String jsonHitterD = gson.toJson(hitterListOfMemberD);

		String c = gson.toJson(db.C);
		String b1 = gson.toJson(db.B1);
		String b2 = gson.toJson(db.B2);
		String b3 = gson.toJson(db.B3);
		String ss = gson.toJson(db.SS);
		String lf = gson.toJson(db.LF);
		String cf = gson.toJson(db.CF);
		String rf = gson.toJson(db.RF);
		

		
		
		String starsA = gson.toJson(StarA);
		String starsB = gson.toJson(StarA);
		String starsC = gson.toJson(StarA);
		String starsD = gson.toJson(StarA);
	
		
		
		String clientDP = gson.toJson(clientDraftPositions);

		String pitcherHM = gson.toJson(DataBase.pitcherHashMap);
		String hitterHM = gson.toJson(DataBase.hitterHashMap);
		String positionHM = gson.toJson(DataBase.positionHashMap);

		String pitcherDB = gson.toJson(PitcherDB.pitcherDB);
		String hitterDB = gson.toJson(HitterDB.hitterDB);

		try (var fwriter = new FileWriter(fileName + ".txt")) {

			fwriter.write(jsonHitterA + "\n"); // writes the contents of the table
			fwriter.write(jsonHitterB + "\n");
			fwriter.write(jsonHitterC + "\n");
			fwriter.write(jsonHitterD + "\n"); // for the hitter teams

			fwriter.write(jsonPitcherA + "\n"); // for the pitcher teams
			fwriter.write(jsonPitcherB + "\n");
			fwriter.write(jsonPitcherC + "\n");
			fwriter.write(jsonPitcherD + "\n");

			fwriter.write(c + "\n");
			fwriter.write(b1 + "\n");
			fwriter.write(b2 + "\n");
			fwriter.write(b3 + "\n");
			fwriter.write(ss + "\n");
			fwriter.write(lf + "\n");
			fwriter.write(cf + "\n");
			fwriter.write(rf + "\n");

			fwriter.write(clientDP + "\n");

			fwriter.write(pitcherHM + "\n");
			fwriter.write(hitterHM + "\n");
			fwriter.write(positionHM + "\n");

			fwriter.write(pitcherDB + "\n");
			fwriter.write(hitterDB + "\n");
			
			fwriter.write(starsA + "\n"); // writes the contents of the table
			fwriter.write(starsB + "\n");
			fwriter.write(starsC + "\n");
			fwriter.write(starsD + "\n");

			System.out.println("The state of the system has been saved in " + fileName + ".txt.");

		} catch (Exception e) {
			// displaying the error message in case of error

			System.out.println("Unable to store the state of the system to file named " + fileName + ".txt");

		}
	}

	public void quit() {
		System.out.println("Save the current draft before terminate the program? Y/N");
		Scanner kbInput = new Scanner(System.in);
		String answer = kbInput.nextLine().toUpperCase();
		if (answer.equals("Y")) {
			System.out.println("Please type SAVE filename");
			answer = kbInput.nextLine();

			save(answer);
			System.out.println("File " + answer + " save successfully. Bye bye!");
			System.exit(0);
		} else {
			System.out.println("Bye bye!");
			System.exit(0);
		}
	}

	public void restore(String fileName) {
		List<String> jsonObjects = new ArrayList<>();
		
		java.lang.reflect.Type pitcherMapType = new TypeToken<Map<String, Pitcher>>() {}.getType();
		java.lang.reflect.Type hitterMapType = new TypeToken<Map<String, Hitter>>() {}.getType();
		java.lang.reflect.Type clientPositionSet = new TypeToken<Set<String>>() {}.getType();
		java.lang.reflect.Type hitterPositionMapType = new TypeToken<Map<String, ArrayList<String>>>() {}.getType();
		java.lang.reflect.Type dbPosition = new TypeToken<ArrayList<String>>() {}.getType();
		java.lang.reflect.Type pitcherDB = new TypeToken<ArrayList<Pitcher>>() {}.getType();
		java.lang.reflect.Type hitterDB = new TypeToken<ArrayList<Hitter>>() {}.getType();
		
		String obj;
		String line;
		try {
			br = new BufferedReader(new FileReader(fileName + ".txt"));

			while ((line = br.readLine()) != null) {
				obj = new JSONParser().parse(line).toString();
				jsonObjects.add(obj);
			}

			hitterListOfMemberA = Arrays.asList(new Gson().fromJson(jsonObjects.get(0), Hitter[].class));
			hitterListOfMemberB = Arrays.asList(new Gson().fromJson(jsonObjects.get(1), Hitter[].class));
			hitterListOfMemberC = Arrays.asList(new Gson().fromJson(jsonObjects.get(2), Hitter[].class));
			hitterListOfMemberD = Arrays.asList(new Gson().fromJson(jsonObjects.get(3), Hitter[].class));

			pitcherListOfMemberA = Arrays.asList(new Gson().fromJson(jsonObjects.get(4), Pitcher[].class));
			pitcherListOfMemberB = Arrays.asList(new Gson().fromJson(jsonObjects.get(5), Pitcher[].class));
			pitcherListOfMemberC = Arrays.asList(new Gson().fromJson(jsonObjects.get(6), Pitcher[].class));
			pitcherListOfMemberD = Arrays.asList(new Gson().fromJson(jsonObjects.get(7), Pitcher[].class));

			DataBase.C = new Gson().fromJson(jsonObjects.get(8), dbPosition);
			DataBase.B1 = new Gson().fromJson(jsonObjects.get(9), dbPosition);
			DataBase.B2 = new Gson().fromJson(jsonObjects.get(10), dbPosition);
			DataBase.B3 = new Gson().fromJson(jsonObjects.get(11), dbPosition);
			DataBase.SS = new Gson().fromJson(jsonObjects.get(12), dbPosition);
			DataBase.LF = new Gson().fromJson(jsonObjects.get(13), dbPosition);
			DataBase.CF = new Gson().fromJson(jsonObjects.get(14), dbPosition);
			DataBase.RF = new Gson().fromJson(jsonObjects.get(15), dbPosition);
			
			

			clientDraftPositions = new Gson().fromJson(jsonObjects.get(16), clientPositionSet);
			DataBase.pitcherHashMap = new Gson().fromJson(jsonObjects.get(17), pitcherMapType);
			DataBase.hitterHashMap = new Gson().fromJson(jsonObjects.get(18), hitterMapType);
			DataBase.positionHashMap = new Gson().fromJson(jsonObjects.get(19), hitterPositionMapType);

			PitcherDB.pitcherDB = new Gson().fromJson(jsonObjects.get(20), pitcherDB);
			HitterDB.hitterDB = new Gson().fromJson(jsonObjects.get(21), hitterDB);
			
			StarA = new Gson().fromJson(jsonObjects.get(22), dbPosition);
			StarB = new Gson().fromJson(jsonObjects.get(23), dbPosition);
			StarC = new Gson().fromJson(jsonObjects.get(24), dbPosition);
			StarD = new Gson().fromJson(jsonObjects.get(25), dbPosition);

			System.out.println("The system has been updated. ");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to restore the state of the system from this file named " + fileName);
		}
	}
	
	//method to add some math expression and convert to the double value
	public void evalfun(String str) {
		String key;
		
		for (int i = 0; i < HitterDB.hitterDB.size(); i++) {
			
			key = getHitterKeys(DataBase.hitterHashMap, HitterDB.hitterDB.get(i)); // get the hashMap key
			
			HitterDB.hitterDB.get(i).setEvafun(str); // update the eval value in the object class
			HitterDB.hitterDB.get(i).setEval(true); // set the flag to true to invoke the other toString

		//System.out.println(key);
	
			if(key != null) {
			DataBase.hitterHashMap.put(key, HitterDB.hitterDB.get(i));} 
			
		// update the hashmap
		}
		System.out.println("EVALFUN runs successfully!");
	}
	
	//method to add some math expression and convert to the double value for pitcher class
	public void pevalfun(String str) {
		String key; //
		for (int i = 0; i < PitcherDB.pitcherDB.size(); i++) {
			PitcherDB.pitcherDB.get(i).setPEvafun(str);// update the eval value in the object class
			PitcherDB.pitcherDB.get(i).setEval(true);// set the flag to true to invoke the other toString

			key = getPitcherKeys(DataBase.pitcherHashMap, PitcherDB.pitcherDB.get(i)); // get the hashMap key

			DataBase.pitcherHashMap.put(key, PitcherDB.pitcherDB.get(i));// update the hashmap

		}
		System.out.println("PEVALFUN runs successfully!");

	}
	
//	public static <K, V> K getKey(Map<K, V> map, V value)
//    {
//        for (Map.Entry<K, V> entry: map.entrySet())
//        {
//            if (value.equals(entry.getValue())) {
//                return entry.getKey();
//            }
//        }
//        return null;
//    }

	// A method from the value to get the key from the hitterHashMap
	public static String getHitterKeys(Map<String, Hitter> hitterHashMap, Object value) {

		String result = null;
		if (hitterHashMap.containsValue(value)) {
			for (Entry<String, Hitter> entry : hitterHashMap.entrySet()) {
				if (Objects.equals(entry.getValue(), value)) {
					result = entry.getKey();
				}
			}
		}
		return result;

	}

	// A method from the value to get the key from the pitcherHashMap
	public static String getPitcherKeys(Map<String, Pitcher> pitcherHashMap, Object value) {

		String result = null;
		if (pitcherHashMap.containsValue(value)) {
			for (Entry<String, Pitcher> entry : pitcherHashMap.entrySet()) {
				if (Objects.equals(entry.getValue(), value)) {
					result = entry.getKey();
				}

			}

		}
		return result;

	}

	//print some tips of how to control.
	public void help() {

		System.out.println("Hints: \n"
				+ "ODRAFT \"LastName, FisrtNameInitial\" leagueMember; For all the member to draft, example: ODRAFT \"Frazier, A\" B \n"
				+ "IDRAFT \"LastName, FisrtNameInitial\"; Only for member A to draft, example: IDRAFT \"Mize, C\" \n"
				+ "OVERALL; show all the non pitcher player of position A not drafted. \n"
				+ "OVERALL [position]; show all the non pitcher player of the specific posistion  \n"
				+ "POVERALL; show all the pithcer \n"
				+ "TEAM leagueMember; show the current roast of the league Member by position order\n"
				+ "STARS leagueMember; show the current players match the order of draft\n" + "SAVE filename;\n"
				+ "RESTORE filename;\n" + "EVALFUN expression including AVG, OBP, AB, SLG, SB. \n"
				+ "PEVALFUN expression including G, GS, ERA, IP, BB; \n" + "QUIT");

	}

}
