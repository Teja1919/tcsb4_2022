package com.cts.process.pension.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Entity
public class PensionAmountDetail {

	@Id
	private String aadhaarNumber;

	@Column
	private Double pensionAmount;

	@Column
	private Double bankServiceCharge;

	@Column
	private Double finalAmount;

	public void setAadhaarNumber(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setBankServiceCharge(double d) {
		// TODO Auto-generated method stub
		
	}

	public Short getAadhaarNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	public Short getBankServiceCharge() {
		// TODO Auto-generated method stub
		return null;
	}

	public Short getPensionAmount() {
		// TODO Auto-generated method stub
		return null;
	}

	public Short getFinalAmount() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPensionAmount(double d) {
		// TODO Auto-generated method stub
		
	}

	public void setFinalAmount(double d) {
		// TODO Auto-generated method stub
		
	}
}
