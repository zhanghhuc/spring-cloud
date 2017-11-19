package test.java.com.powertorque.test;

public class Obj {
	Integer type;
	String personCode;
	String teamCode;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPersonCode() {
		return personCode;
	}
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	@Override
	public String toString() {
		return "Obj [type=" + type + ", personCode=" + personCode + ", teamCode=" + teamCode + "]";
	}
}
