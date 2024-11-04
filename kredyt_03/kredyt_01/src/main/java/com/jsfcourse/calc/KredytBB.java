package com.jsfcourse.calc;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class KredytBB {
	private String kwota;
	private String okresKredytowania;
	private String oprocentowanie;
	private Double wynik;

	@Inject
	FacesContext ctx;
	


	public String getKwota() {
		return kwota;
	}

	public void setKwota(String kwota) {
		this.kwota = kwota;
	}

	public String getOkresKredytowania() {
		return okresKredytowania;
	}

	public void setOkresKredytowania(String okresKredytowania) {
		this.okresKredytowania = okresKredytowania;
	}

	public String getOprocentowanie() {
		return oprocentowanie;
	}

	public void setOprocentowanie(String oprocentowanie) {
		this.oprocentowanie = oprocentowanie;
	}

	public Double getWynik() {
		return wynik;
	}

	public String calc() {
		try {
			if (kwota == null || kwota.isEmpty() || okresKredytowania == null || okresKredytowania.isEmpty() || oprocentowanie == null || oprocentowanie.isEmpty()) {
				ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wszystkie pola są wymagane", null));
				return null;
			}
			
			double okresKredytowania = Double.parseDouble(this.okresKredytowania);
			double oprocentowanie = Double.parseDouble(this.oprocentowanie);
			double kwota = Double.parseDouble(this.kwota);
			
			if (kwota < 30000) {
				ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Kwota musi być większa niż 30 000", null));
				return null;
			}
			
			if (okresKredytowania < 5 && okresKredytowania > 40) {
				ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Okres kredytowania musi być pomiędzy 5 a 40 lat", null));
				return null;
			}
			
			if (oprocentowanie < 0.5 && oprocentowanie > 40) {
				ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Oprocentowanie powinno znajdować się w przedziale od 0.5 do 40 procent", null));
				return null;
			}
			
			wynik = kwota * oprocentowanie / (okresKredytowania * 12);
			
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return "showresult"; 
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return null; 
		}
				
	}
	public String info() {
		return "info"; 
	}
}
