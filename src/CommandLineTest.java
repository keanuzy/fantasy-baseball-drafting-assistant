import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class CommandLineTest {

	@Test
	public void testODraft() {
		//fail("Not yet implemented");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CommandLine cl = new CommandLine();
	
		//cl.oDraft("walse", "j", "A");
		String expected =  "walse has been drafted by A";
		assertEquals(expected, outContent.toString());
		String expected2 = "No player was drafted, please check your name input with first initial.";
		assertEquals(expected2, outContent.toString());
	}

	@Test
	public void testIDraft() {
		//fail("Not yet implemented");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CommandLine cl = new CommandLine();
		//String actual= 
		cl.iDraft("walsh", "j");
		String expected = "walse has been drafted by A";
		assertEquals(expected, outContent.toString());
		String expected2 = "No player was drafted, please check your name input with first initial.";
		assertEquals(expected2, outContent.toString());
	}
	

	@Test
	public void testOverall() {
		
	}

	@Test
	public void testOverallString() {
		CommandLine cl = new CommandLine();
		cl.iDraft("WENDLE","J");
		cl.clientDraftPositions.add("3B");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		
		cl.overall("3B");
		String expectedOutput = "You have drafted this postion!";
		assertEquals(expectedOutput, outContent.toString());
	}
	
	@Test
	public void testOverallString2() {
		CommandLine cl = new CommandLine();
		
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		
		cl.overall("C");
		
		
		assertEquals(DataBase.hitterHashMap.get("Perez, S").toString(), outContent.toString());
	}

	@Test
	public void testPOverall() {
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		List<Map<String, Pitcher>> t = Arrays.asList(DataBase.pitcherHashMap);

		assertEquals(t.toString(), outContent.toString());
	}

	@Test
	public void testTeam() {
		//fail("Not yet implemented");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CommandLine cl = new CommandLine();
		//String actual= 
		cl.team("A");
		String expected = "jared, walsh, 1B";
		assertEquals(expected, outContent.toString());
		
		
		
		
	}

	@Test
	public void testStars() {

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		CommandLine cl = new CommandLine();
		cl.stars("V");

		String expectedOutput = "Please enter correct league member (A, B, C, D)";
		assertEquals(expectedOutput, outContent.toString());

	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testQuit() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		CommandLine cl = new CommandLine();
		 cl.quit();
		String expected = " save successfully. Bye bye!";
		assertEquals(expected, outContent.toString());
		//String actua2 = 
		cl.quit();
		String expected2 = "Bye bye!";
		assertEquals(expected2, outContent.toString());
		
	}

	@Test
	public void testRestore() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEvaulfun() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		CommandLine cl = new CommandLine();
		cl.evalfun("1.05 * OBP + SLG");
		String expected = "EVALFUN runs successfully!";
		assertEquals(expected, outContent.toString());
	}
	
	@Test
	public void testPEvaulfun() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		CommandLine cl = new CommandLine();
		cl.pevalfun("IP + ERA");
		String expected = "PEVALFUN runs successfully!";
		assertEquals(expected, outContent.toString());
	}

}