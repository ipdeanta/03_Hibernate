package com.serbatic.course.datamodel.entities.xmlmapping;

import java.io.Serializable;

public class Provider implements Serializable {

	private int providerId;
  private String cif;
  private String businessName;
  private String address;
  private String phone;
  private String email;

	public Provider() {
		
	}

	public Provider(int providerId, String cif, String businessName, String address, String phone, String email) {
		super();
		this.providerId = providerId;
		this.cif = cif;
		this.businessName = businessName;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	public int getProviderId() {
		return providerId;
	}

	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Provider [providerId=" + providerId + ", cif=" + cif + ", businessName=" + businessName + ", address="
				+ address + ", phone=" + phone + ", email=" + email + "]";
	}

		
	
}
