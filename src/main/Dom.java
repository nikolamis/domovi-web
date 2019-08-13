package main;

import java.util.ArrayList;

public class Dom {
	private int id;
	private String imeDoma;
	private String adresa;
	private int brojSoba;
	private ArrayList<Soba> tipSobe;
	
	
	
	public Dom(int id, String imeDoma, String adresa, int brojSoba, ArrayList<Soba> tipSobe) {
		this.id = id;
		this.imeDoma = imeDoma;
		this.adresa = adresa;
		this.brojSoba = brojSoba;
		this.tipSobe = tipSobe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImeDoma() {
		return imeDoma;
	}

	public void setImeDoma(String imeDoma) {
		this.imeDoma = imeDoma;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public int getBrojSoba() {
		return brojSoba;
	}

	public void setBrojSoba(int brojSoba) {
		this.brojSoba = brojSoba;
	}

	public ArrayList<Soba> getTipSobe() {
		return tipSobe;
	}

	public void setTipSobe(ArrayList<Soba> tipSobe) {
		this.tipSobe = tipSobe;
	}
	
	
	
	

}
