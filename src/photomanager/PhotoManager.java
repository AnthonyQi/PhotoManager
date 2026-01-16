package photomanager;

import java.io.*;
import java.util.*;

public class PhotoManager {
	private ArrayList<Photo> allPhotos;

	public PhotoManager() {
		allPhotos = new ArrayList<Photo>();
	}

	public boolean addPhoto(String photoSource, int width,
			int height, String date) {
		try {
	        // Check if the photo already exists
	        if(findPhoto(photoSource) != -1) {
	            return false;
	        }
	        Photo newPhoto = new Photo(photoSource, width, height, date);
	        allPhotos.add(newPhoto);
	        return true;
	    } catch (IllegalArgumentException e) {
	        System.err.println("addPhoto: Invalid arguments");
	        return false;
	    }	
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
	    for (Photo photo : allPhotos) {
	        sb.append(photo.toString()).append("\n");
	    }
	    return sb.toString();
	}

	public int findPhoto(String photoSource) {
		if(photoSource == null) {
	        return -1;
	    }
	    for (int i = 0; i < allPhotos.size(); i++) {
	        if(allPhotos.get(i).getPhotoSource().equals(photoSource)) {
	            return i;
	        }
	    }
	    return -1;
	}

	public boolean addComment(String photoSource, String newComment) {
		if(photoSource == null || newComment == null || newComment.isBlank()) {
	        System.err.println("addComment: Invalid parameters.");
	        return false;
	    }

	    int index = findPhoto(photoSource);
	    if(index == -1) {
	        return false;
	    }

	    try {
	        allPhotos.get(index).addComments(newComment);
	        return true;
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	}

	public String getComments(String photoSource) {
		int index = findPhoto(photoSource);
	    if(index == -1 || photoSource == null) {
	        return null;
	    }
	    return allPhotos.get(index).getComments();
	}

	public void removeAllPhotos() {
		allPhotos.clear();
	}

	public boolean removePhoto(String photoSource) {
		int index = findPhoto(photoSource);
	    if(index != -1) {
	        allPhotos.remove(index);
	        return true;
	    }
	    return false;
	}

	public boolean loadPhotos(String filename) {
		if(filename == null) {
	        return false;
	    }
	    try (Scanner scanner = new Scanner(new File(filename))) {
	        while (scanner.hasNextLine()) {
	            String[] parts = scanner.nextLine().split(" ");
	            String photoSource = parts[0];
	            int width = Integer.parseInt(parts[1]);
	            int height = Integer.parseInt(parts[2]);
	            String date = parts[3];
	            addPhoto(photoSource, width, height, date);
	        }
	        return true;
	    } catch (FileNotFoundException e) {
	        System.err.println("loadPhotos: File not found");
	        return false;
	    }
	}

	public void sortPhotosByDate() {
	    Collections.sort(allPhotos);
	}

	public void createHTMLPage(String htmlFilename) {
		String body = "";

		for (Photo photo : allPhotos) {
			body += "<img src=\"" + photo.getPhotoSource() + "\" ";
			body += "width=\"" + photo.getWidth() + "\" ";
			body += "height=\"" + photo.getHeight() + "\" ";
			body += "alt=\"photo image\"><br>\n";
		}
		Utilities.generateHTMLPageWithBody(htmlFilename, body);
	}
}