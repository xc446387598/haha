package com.shenzhenair.bean;

import java.io.Serializable;

public class RequestParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String from;
	private String arrive;
	private String fdDate;
	private String carrier;
	private boolean direct;

	public boolean isDirect() {
		return direct;
	}
	public void setDirect(boolean direct) {
		this.direct = direct;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getArrive() {
		return arrive;
	}
	public void setArrive(String arrive) {
		this.arrive = arrive;
	}
	public String getFdDate() {
		return fdDate;
	}
	public void setFdDate(String fdDate) {
		this.fdDate = fdDate;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	@Override
	public String toString() {
		return "RequestParam [from=" + from + ", arrive=" + arrive
				+ ", fdDate=" + fdDate + ", carrier=" + carrier + ", direct="
				+ direct + "]";
	}
	
	
	
}
