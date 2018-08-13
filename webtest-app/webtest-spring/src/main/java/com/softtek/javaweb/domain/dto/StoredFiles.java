package com.softtek.javaweb.domain.dto;

public class StoredFiles {
	String filename;
	String filenameEncoded;
	String fullFilename;
	String fullFilenameEncoded;
	Long filesize;
	
	public StoredFiles() {}
	public StoredFiles(String filename, String filenameEncoded, String fullFilename, String fullFilenameEncoded, Long filesize) {
		super();
		this.filename = filename;
		this.filenameEncoded = filenameEncoded;
		this.fullFilename = fullFilename;
		this.fullFilenameEncoded = fullFilenameEncoded;
		this.filesize = filesize;
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilenameEncoded() {
		return filenameEncoded;
	}
	public void setFilenameEncoded(String filenameEncoded) {
		this.filenameEncoded = filenameEncoded;
	}
	public String getFullFilename() {
		return fullFilename;
	}
	public void setFullFilename(String fullFilename) {
		this.fullFilename = fullFilename;
	}
	public String getFullFilenameEncoded() {
		return fullFilenameEncoded;
	}
	public void setFullFilenameEncoded(String fullFilenameEncoded) {
		this.fullFilenameEncoded = fullFilenameEncoded;
	}
	public Long getFilesize() {
		return filesize;
	}
	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}
	@Override
	public String toString() {
		return "StoredFiles [filename=" + filename + ", filenameEncoded=" + filenameEncoded + ", fullFilename="
				+ fullFilename + ", fullFilenameEncoded=" + fullFilenameEncoded + ", filesize=" + filesize + "]";
	}

	
}
