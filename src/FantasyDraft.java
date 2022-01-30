import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * This assignment for COSC381 Summer Semester. 
 * To create a draft software with draft methods and update the draft result by each team.
 * Including save and restore methods.
 * Including exp4j.ExpressionBuilder like scriptengine in js.
 *
 */
public class FantasyDraft {

	public static void main(String[] args) throws IOException {
		CommandLine cl = new CommandLine();

		DataBase db = new DataBase();
		db.inputPitcherData();
		db.inputHitterData();

		Scanner kbInput = new Scanner(System.in);
		String userInput = null;
		System.out
				.println("Welcome to fantasy baseball drafting assistant, type \"help\" to get command information. ");

		while (true) {
			userInput = kbInput.nextLine().toUpperCase();

			String[] command = userInput.split(" ");
			int size = command.length;

			switch (command[0]) {
			case "ODRAFT": // 1. for last name, 2. for initial, 3. for leagueMember name
				cl.oDraft(command[1], command[2], command[3].trim());
				break;
			case "IDRAFT":// 1. for last name, 2. for initial,
				cl.iDraft(command[1], command[2]);
				break;
			case "OVERALL":
				if (size > 1)
					cl.overall(command[1]); // for situation: overall with any one postion
				else
					cl.overall(); // for situation: overall without position
				break;
			case "POVERALL":
				cl.pOverall();
				break;
			case "TEAM":// 1.  for leagueMember name
				cl.team(command[1]);
				break;
			case "STARS":// 1.  for leagueMember name
				cl.stars(command[1]);
				break;
			case "SAVE":// 1.  for filename
				cl.save(command[1]);
				break;
			case "RESTORE":// 1.  for filename assue the file in the same directory with java class
				cl.restore(command[1]);
				break;
			case "EVALFUN":
				String input = ""; //create a string of all operation for evalfun
				for (int i = 1; i < command.length; i++) {
					input = input + command[i] + " ";
				}			
				cl.evalfun(input.toUpperCase());
				break;
			case "PEVALFUN":
				String pInput = "";//create a string of all operation for pevalfun
				for (int i = 1; i < command.length; i++) {
					pInput = pInput + command[i] + " ";
				}
				cl.pevalfun(pInput.toUpperCase());		
				break;
			case "HELP":
				cl.help();
				break;
			case "QUIT":
				cl.quit();
				break;
			default:
				System.out.println("Invalid input, please type HELP more command information");
			}
		}	
	}
}
