package com.shenzhenair.bean;

import java.io.Serializable;

import java.util.List;

/**
 * 航线类
 * 
 * @author wsf
 * 
 */
public class Route implements Serializable {

	private static final long serialVersionUID = 1L;
	private String from;// 出发地
	private String arrive;// 到达地
	private String date;// 起飞日期
	private String tpm;// 飞行距离
	private Double classYPrice;// Y舱价格
	private String wayType;// 航程类型 去:GO 、回:BACK
	private Double afuelTax;// 成人燃油税
	private Double cfuelTax;// 儿童燃油税
	private Double ifuelTax;// 婴儿燃油税
	private Double airportTax;// 机场建设费
	private List<RatesClass> ratesClasss;// 舱位列表

	public Route() {
		super();
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTpm() {
		return tpm;
	}

	public void setTpm(String tpm) {
		this.tpm = tpm;
	}

	public Double getClassYPrice() {
		return classYPrice;
	}

	public void setClassYPrice(Double classYPrice) {
		this.classYPrice = classYPrice;
	}

	public String getWayType() {
		return wayType;
	}

	public void setWayType(String wayType) {
		this.wayType = wayType;
	}

	public Double getAfuelTax() {
		return afuelTax;
	}

	public void setAfuelTax(Double afuelTax) {
		this.afuelTax = afuelTax;
	}

	public Double getCfuelTax() {
		return cfuelTax;
	}

	public void setCfuelTax(Double cfuelTax) {
		this.cfuelTax = cfuelTax;
	}

	public Double getIfuelTax() {
		return ifuelTax;
	}

	public void setIfuelTax(Double ifuelTax) {
		this.ifuelTax = ifuelTax;
	}

	public Double getAirportTax() {
		return airportTax;
	}

	public void setAirportTax(Double airportTax) {
		this.airportTax = airportTax;
	}

	public List<RatesClass> getRatesClasss() {
		return ratesClasss;
	}

	public void setRatesClasss(List<RatesClass> ratesClasss) {
		this.ratesClasss = ratesClasss;
	}

	@Override
	public String toString() {
		return "Route [from=" + from + ", arrive=" + arrive + ", date=" + date
				+ ", tpm=" + tpm + ", classYPrice=" + classYPrice
				+ ", wayType=" + wayType + ", afuelTax=" + afuelTax
				+ ", cfuelTax=" + cfuelTax + ", ifuelTax=" + ifuelTax
				+ ", airportTax=" + airportTax + ", ratesClasss=" + ratesClasss
				+ "]";
	}
	
}
