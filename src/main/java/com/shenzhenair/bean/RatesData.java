package com.shenzhenair.bean;

import java.io.Serializable;
/**
 *运价实体
 * @author 胡益博
 *
 */
public class RatesData implements Serializable{
	private static final long serialVersionUID = 1L;
	private String ratesId;
	private String ratesType;
	private Double price;
	private Double totle;
	private Double discount;
	private String discountType;
	private String flight;
	private String notFlight;
	private String notValidBefore;
	private String notValidAfter;
	private String returnClass;
	private String baggage;
	private String returnTicket;
	private String comments;
	private String wayType;
	private String weekLimit;
	private String startDate;
	private String endDate;
	private String officeNo;
	private String pat;
	private String airSdate;
	private String airEdate;
	private String sellLimit;
	public String getRatesId() {
		return ratesId;
	}
	public void setRatesId(String ratesId) {
		this.ratesId = ratesId;
	}
	public String getRatesType() {
		return ratesType;
	}
	public void setRatesType(String ratesType) {
		this.ratesType = ratesType;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getTotle() {
		return totle;
	}
	public void setTotle(Double totle) {
		this.totle = totle;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public String getFlight() {
		return flight;
	}
	public void setFlight(String flight) {
		this.flight = flight;
	}
	public String getNotFlight() {
		return notFlight;
	}
	public void setNotFlight(String notFlight) {
		this.notFlight = notFlight;
	}
	public String getNotValidBefore() {
		return notValidBefore;
	}
	public void setNotValidBefore(String notValidBefore) {
		this.notValidBefore = notValidBefore;
	}
	public String getNotValidAfter() {
		return notValidAfter;
	}
	public void setNotValidAfter(String notValidAfter) {
		this.notValidAfter = notValidAfter;
	}
	public String getReturnClass() {
		return returnClass;
	}
	public void setReturnClass(String returnClass) {
		this.returnClass = returnClass;
	}
	public String getBaggage() {
		return baggage;
	}
	public void setBaggage(String baggage) {
		this.baggage = baggage;
	}
	public String getReturnTicket() {
		return returnTicket;
	}
	public void setReturnTicket(String returnTicket) {
		this.returnTicket = returnTicket;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getWayType() {
		return wayType;
	}
	public void setWayType(String wayType) {
		this.wayType = wayType;
	}
	public String getWeekLimit() {
		return weekLimit;
	}
	public void setWeekLimit(String weekLimit) {
		this.weekLimit = weekLimit;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getOfficeNo() {
		return officeNo;
	}
	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
	}
	public String getPat() {
		return pat;
	}
	public void setPat(String pat) {
		this.pat = pat;
	}
	public String getAirSdate() {
		return airSdate;
	}
	public void setAirSdate(String airSdate) {
		this.airSdate = airSdate;
	}
	public String getAirEdate() {
		return airEdate;
	}
	public void setAirEdate(String airEdate) {
		this.airEdate = airEdate;
	}
	public String getSellLimit() {
		return sellLimit;
	}
	public void setSellLimit(String sellLimit) {
		this.sellLimit = sellLimit;
	}
	@Override
	public String toString() {
		return "RatesData [ratesId=" + ratesId + ", ratesType=" + ratesType
				+ ", price=" + price + ", totle=" + totle + ", discount="
				+ discount + ", discountType=" + discountType + ", flight="
				+ flight + ", notFlight=" + notFlight + ", notValidBefore="
				+ notValidBefore + ", notValidAfter=" + notValidAfter
				+ ", returnClass=" + returnClass + ", baggage=" + baggage
				+ ", returnTicket=" + returnTicket + ", comments=" + comments
				+ ", wayType=" + wayType + ", weekLimit=" + weekLimit
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", officeNo=" + officeNo + ", pat=" + pat + ", airSdate="
				+ airSdate + ", airEdate=" + airEdate + ", sellLimit="
				+ sellLimit + "]";
	}
	
}
