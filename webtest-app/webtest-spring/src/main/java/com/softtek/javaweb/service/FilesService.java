package com.softtek.javaweb.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.softtek.javaweb.domain.dto.ResponseStatus;
import com.softtek.javaweb.domain.dto.StoredFiles;

@Service
public class FilesService {	

	private FilesService () {}

	public static final Logger LOGGER = LoggerFactory.getLogger(FilesService.class);

	public List<StoredFiles> getAllFiles (String path) {
		List<StoredFiles> storedFiles = new ArrayList<>();

		LOGGER.info("## Files Path: {}", path);
		
		File file = new File(path);
		for (File myFile: file.listFiles()) {
			storedFiles.add(new StoredFiles(myFile.getName(),StringUtils.replaceAll(myFile.getName()," ","%20"), myFile.getPath(), StringUtils.replaceAll(myFile.getPath()," ","%20"), myFile.length()));
		}

		return storedFiles;
	}

	public ResponseStatus uploadFiles (String destinationPath, CommonsMultipartFile file) {
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setValid(true);

		LOGGER.info("## Upload Path: {}", destinationPath);
		LOGGER.info("## File to upload: {}", file.getOriginalFilename());
		LOGGER.info("## Final Destination: {}{}{}", destinationPath, File.separator, file.getOriginalFilename());

		File newFile = new File(destinationPath + File.separator + file.getOriginalFilename());
		if (file.getOriginalFilename().isEmpty()) {
			responseStatus.setValid(false);
			responseStatus.appendServiceMsg("Empty filename. Please select a file to upload.");			
		} else if (newFile.exists()) {
			responseStatus.setValid(false);
			responseStatus.appendServiceMsg("File already exists. Please select a different file");
		} else {
			byte[] contents = file.getBytes();
			try (
				BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(newFile));
			) {
				output.write(contents);
				output.flush();
			} catch (IOException e) {
				e.printStackTrace();
				responseStatus.setValid(false);
				responseStatus.appendServiceMsg("Error uploading file. Please try again or select a different file");
			}
		}
		return responseStatus;
	}
	public ResponseStatus deleteFiles (String originPath, String filename) {
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setValid(true);

		File file = new File(originPath + File.separator + filename);
		
		LOGGER.info("## elete Path: {}", originPath);
		LOGGER.info("## File to delete: {}", filename);

		try {
			Files.delete(Paths.get(file.toURI()));
		}
		catch (NoSuchFileException e) {
			responseStatus.appendServiceMsg("No such file exists: " + filename);
			responseStatus.setValid(false);
		}
		catch (DirectoryNotEmptyException e) {
			responseStatus.appendServiceMsg("Attempting to delete a non-empty directory: " + filename);
			responseStatus.setValid(false);
		}
		catch (IOException e) {
			responseStatus.appendServiceMsg("Could not delete file: " + filename + ". Check if you have permissions");
			responseStatus.setValid(false);
		}
		
		return responseStatus;
	}

}
