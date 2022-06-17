package com.cts.pensionerDetail.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class BankDetails {

	public BankDetails(String bankName2, long accountNumber2, String bankType2) {
		// TODO Auto-generated constructor stub
	}
	
	private String bankName;
	private long accountNumber;
	private String bankType;
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
}
