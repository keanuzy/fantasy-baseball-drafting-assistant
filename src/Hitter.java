import net.objecthunter.exp4j.ExpressionBuilder;
/**
 * 
 * 
 *	The Hitter Object Class.
 *	With the exp4j.ExpressionBuilder package in the setPEvafun.
 */
public class Hitter {
	private String firstName;
	private String lastName;
	private String position;
	private double AVG;
	private double OBP;
	private int AB;
	private double SLG;
	private int SB;
	private boolean drafted;
	private String draftedBy;
	private boolean eval = false;
	private double evalue ;

	//constructor
	public Hitter(String firstName, String lastName, String position,int aB,int sB, double aVG, double oBP, double sLG) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		AVG = aVG;
		OBP = oBP;
		AB = aB;
		SLG = sLG;
		SB = sB;
	}
	
	
	//method to convert string of math expression to the double value
	public void setEvafun(String str) {
		
		
		double result = new ExpressionBuilder(str)
				.variables("AVG","OBP","AB","SLG","SB")
				.build()
				.setVariable("AVG", getAVG())
				.setVariable("OBP", getOBP())
				.setVariable("AB", getAB())
				.setVariable("SLG", getSLG())
				.setVariable("SB", getSB())
				.evaluate();
		this.evalue = result;
	
	}

	
	public double getEvalue() {
		return evalue;
	}

	public boolean evalDefault() { // switch function. No eval value then the default is the value of IP.
		return eval;
	}
	
	public boolean setEval(boolean eva) {
		return eval = eva;
	}
	
	/**
	 * @return the draftedBy
	 */
	public String getDraftedBy() {
		return draftedBy;
	}


	/**
	 * @param draftedBy the draftedBy to set
	 */
	public void setDraftedBy(String draftedBy) {
		this.draftedBy = draftedBy;
	}


	/**
	 * @param drafted the drafted to set
	 */
	public boolean setDrafted() {
		return !drafted;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @return the aVG
	 */
	public double getAVG() {
		return AVG;
	}

	/**
	 * @return the oBP
	 */
	public double getOBP() {
		return OBP;
	}

	/**
	 * @return the aB
	 */
	public int getAB() {
		return AB;
	}

	/**
	 * @return the sLG
	 */
	public double getSLG() {
		return SLG;
	}

	/**
	 * @return the sB
	 */
	public int getSB() {
		return SB;
	}


	@Override
	public String toString() {
		return evalDefault()? " first name:  " + getFirstName() + " , last name: " + getLastName() +  " , position: "+ getPosition() + " ,  EVALFUN: "
				+ getEvalue() +"\n"
		: " first name:  " + getFirstName() + " , last name: " + getLastName() + ",  position: "+ getPosition() + " AVG: " + getAVG() +"\n";
	}
	
	

}