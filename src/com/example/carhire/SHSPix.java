package com.example.carhire;

public class SHSPix {
	private String desc;
	private String place;
	private byte image[];
	
	public SHSPix(){
		
	}

	public SHSPix(String desc, String place, byte[] image) {
		super();
		this.desc = desc;
		this.place = place;
		this.image = image;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
