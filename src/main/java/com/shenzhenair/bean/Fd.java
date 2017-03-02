package com.shenzhenair.bean;

import java.io.Serializable;

public class Fd implements Serializable{
	/**
	 * FD运价实体类
	 */
	private static final long serialVersionUID = -1506564758129365516L;
	private long id;
	private String depart;//起飞城市
	private String arrive;//到达城市
	private String flightdate;//起飞日期
	private String tpm;//飞行距离
	private double classyprice;//Y舱价格
	private String waytype;//航程类型：GO-去程
	private double afueltax;//成人燃油费
	private double airporttax;//机场建设费
	private String carrier;//航空公司
	private String farebase;//舱位等级
	private String code;//舱位类型
	private double discount;//折扣
	private String notvalidafter;//notValidAfter
	private String notvalidbefore;//notValidBefore
	private double price;//价格
	private String ratestype;//运价类型
	private String returnclass;
	private double totle;//总价
	private int isUse;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public String getArrive() {
		return arrive;
	}
	public void setArrive(String arrive) {
		this.arrive = arrive;
	}
	public String getFlightdate() {
		return flightdate;
	}
	public void setFlightdate(String flightdate) {
		this.flightdate = flightdate;
	}
	public String getTpm() {
		return tpm;
	}
	public void setTpm(String tpm) {
		this.tpm = tpm;
	}
	public double getClassyprice() {
		return classyprice;
	}
	public void setClassyprice(double classyprice) {
		this.classyprice = classyprice;
	}
	public String getWaytype() {
		return waytype;
	}
	public void setWaytype(String waytype) {
		this.waytype = waytype;
	}
	public double getAfueltax() {
		return afueltax;
	}
	public void setAfueltax(double afueltax) {
		this.afueltax = afueltax;
	}
	public double getAirporttax() {
		return airporttax;
	}
	public void setAirporttax(double airporttax) {
		this.airporttax = airporttax;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getFarebase() {
		return farebase;
	}
	public void setFarebase(String farebase) {
		this.farebase = farebase;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getNotvalidafter() {
		return notvalidafter;
	}
	public void setNotvalidafter(String notvalidafter) {
		this.notvalidafter = notvalidafter;
	}
	public String getNotvalidbefore() {
		return notvalidbefore;
	}
	public void setNotvalidbefore(String notvalidbefore) {
		this.notvalidbefore = notvalidbefore;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getRatestype() {
		return ratestype;
	}
	public void setRatestype(String ratestype) {
		this.ratestype = ratestype;
	}
	public String getReturnclass() {
		return returnclass;
	}
	public void setReturnclass(String returnclass) {
		this.returnclass = returnclass;
	}
	public double getTotle() {
		return totle;
	}
	public void setTotle(double totle) {
		this.totle = totle;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int getIsUse() {
		return isUse;
	}
	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}
	@Override
	public String toString() {
		return "Fd [id=" + id + ", depart=" + depart + ", arrive=" + arrive
				+ ", flightdate=" + flightdate + ", tpm=" + tpm
				+ ", classyprice=" + classyprice + ", waytype=" + waytype
				+ ", afueltax=" + afueltax + ", airporttax=" + airporttax
				+ ", carrier=" + carrier + ", farebase=" + farebase + ", code="
				+ code + ", discount=" + discount + ", notvalidafter="
				+ notvalidafter + ", notvalidbefore=" + notvalidbefore
				+ ", price=" + price + ", ratestype=" + ratestype
				+ ", returnclass=" + returnclass + ", totle=" + totle
				+ ", isUse=" + isUse + "]";
	}
	
	
}
