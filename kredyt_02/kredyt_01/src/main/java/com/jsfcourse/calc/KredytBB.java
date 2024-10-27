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
			double okresKredytowania = Double.parseDouble(this.okresKredytowania);
			double oprocentowanie = Double.parseDouble(this.oprocentowanie);
			double kwota = Double.parseDouble(this.kwota);
			
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
