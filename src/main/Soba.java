package main;

public class Soba {
	private String tip;
	private int cena;
	private boolean kupatiloUSobi;
	
	public Soba(String tip, int cena, boolean kupatiloUSobi) {
		this.tip = tip;
		this.cena = cena;
		this.kupatiloUSobi = kupatiloUSobi;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public boolean isKupatiloUSobi() {
		return kupatiloUSobi;
	}

	public void setKupatiloUSobi(boolean kupatiloUSobi) {
		this.kupatiloUSobi = kupatiloUSobi;
	}
	
	
	
	
}
