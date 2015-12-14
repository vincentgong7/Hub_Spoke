package com.itp.hubspoke.model;

public class FileProfile {

	private String fileName;
	private String fileID;
	private String fileType;
	private boolean isPrintable = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public boolean isPrintable() {
		return isPrintable;
	}

	public void setPrintable(boolean isPrintable) {
		this.isPrintable = isPrintable;
	}

	public String getFileID() {
		return fileID;
	}

	public void setFileID(String fileID) {
		this.fileID = fileID;
	}
	
	

}
