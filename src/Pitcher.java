import net.objecthunter.exp4j.ExpressionBuilder;
/**
 * 
 * 
 *	The Pitcher Object Class.
 *	With the exp4j.ExpressionBuilder package in the setPEvafun.
 */
public class Pitcher {
	private String firstName;
	private String lastName;
	private int G;
	private int GS;
	private double ERA;
	private double IP;
	private int BB;
	private boolean drafted = false;
	private String draftedBy;
	private boolean eval = false;
	private double pEvalue;

	public Pitcher() {

	}
	
	//constructor
	public Pitcher(String fN, String lN, double era, int g, int gs, double ip, int bb) {
		this.firstName = fN;
		this.lastName = lN;
		this.G = g;
		this.GS = gs;
		this.ERA = era;
		this.IP = ip;
		this.BB = bb;

	}
	//method to convert string of math expression to the double value
	public void setPEvafun(String str) {	
		double result = new ExpressionBuilder(str)
				.variables("G","GS","ERA","IP","BB")
				.build()
				.setVariable("G", getG())
				.setVariable("GS", getGS())
				.setVariable("ERA", getERA())
				.setVariable("IP", getIP())
				.setVariable("BB", getBB())
				.evaluate();
		this.pEvalue = result;
	
	}

	public double getPEvalue() {
		return pEvalue;
	}

	public boolean evalDefault() { // switch function. No eval == false then the default is the value of IP.
		return eval;
	}

	public boolean setEval(boolean eva) {
		return eval = eva;
	}

	/**
	 * @return the drafted
	 */
	public boolean isDrafted() {
		return drafted;
	}

	/**
	 * @param drafted the drafted to set
	 */
	public void setDrafted(boolean drafted) {
		this.drafted = drafted;
	}

	public void setDraftedBy(String t) {
		this.draftedBy = t;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lasgName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the g
	 */
	public int getG() {
		return G;
	}

	/**
	 * @return the gS
	 */
	public int getGS() {
		return GS;
	}

	/**
	 * @return the eRA
	 */
	public double getERA() {
		return ERA;
	}

	/**
	 * @return the iP
	 */
	public double getIP() {
		return IP;
	}

	/**
	 * @return the bB
	 */
	public int getBB() {
		return BB;
	}

	@Override
	public String toString() {

		return evalDefault()
				? " first name:  " + getFirstName() + " , last name: " + getLastName() + " PEVALFUN: "
						+ getPEvalue() +"\n"
				: " first name:  " + getFirstName() + " , last name: " + getLastName() + " , IP: " + getIP() +"\n";

	}

}
