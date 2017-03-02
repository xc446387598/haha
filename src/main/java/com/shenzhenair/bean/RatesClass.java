package com.shenzhenair.bean;

import java.io.Serializable;
import java.util.List;
/**
 * 调用易思凯返回的FD舱位
 * @author 胡益博
 *
 */
public class RatesClass implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer elementNo;
	private String carrier;
	private String farebase;
	private String code;
	private String ext;
	private List<RatesData> ratesDataList;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getElementNo() {
		return elementNo;
	}
	public void setElementNo(Integer elementNo) {
		this.elementNo = elementNo;
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
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public List<RatesData> getRatesDataList() {
		return ratesDataList;
	}
	public void setRatesDataList(List<RatesData> ratesDataList) {
		this.ratesDataList = ratesDataList;
	}
	@Override
	public String toString() {
		return "RatesClass [id=" + id + ", elementNo=" + elementNo
				+ ", carrier=" + carrier + ", farebase=" + farebase + ", code="
				+ code + ", ext=" + ext + ", ratesDataList=" + ratesDataList
				+ "]";
	}


}
