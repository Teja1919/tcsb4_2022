package com.cts.process.pension.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
/*
 * Have tp update this one for displaying bank charges
 */

@AllArgsConstructor
@Getter
public class PensionDetail {

	private String name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dateOfBirth;
	private String pan;
	private String pensionType;
	private double pensionAmount;
	private double bankCharges;
	
	public PensionDetail(String name2, String string, String pan2, String pensionType2, double pensionAmount2,
			int bankCharges2) {
		// TODO Auto-generated constructor stub
	}
	public PensionDetail(String name2, Date parseDate, String pan2, String pensionType2, int pensionAmount2,
			int bankCharges2) {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getPensionType() {
		return pensionType;
	}
	public void setPensionType(String pensionType) {
		this.pensionType = pensionType;
	}
	public double getPensionAmount() {
		return pensionAmount;
	}
	public void setPensionAmount(double pensionAmount) {
		this.pensionAmount = pensionAmount;
	}
	public double getBankCharges() {
		return bankCharges;
	}
	public void setBankCharges(double bankCharges) {
		this.bankCharges = bankCharges;
	}

}