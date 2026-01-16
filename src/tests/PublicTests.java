package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import photomanager.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class PublicTests {
	/* testName corresponds to the method's name */

	@Test
	public void pub01PhotoConsToString() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String answer = "", photoSource = "umcp/college1.jpg";
		int width = 300, height = 400;
		String date = "10/18/2020-17:10";

		Photo photo = new Photo(photoSource, width, height, date);
		answer += photo;

		assertTrue(TestingSupport.isResultCorrect(testName, answer, true));
	}

	@Test
	public void pub02PhotoGetMethods() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String answer = "", photoSource = "umcp/college2.jpg";
		int width = 350, height = 450;
		String date = "10/18/2020-09:10";

		Photo photo = new Photo(photoSource, width, height, date);
		answer += photo.getPhotoSource() + "\n";
		answer += photo.getWidth() + "\n";
		answer += photo.getHeight() + "\n";
		answer += photo.getDate() + "\n";

		assertTrue(TestingSupport.isResultCorrect(testName, answer, true));
	}

	@Test
	public void pub03PhotoComments() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String answer = "", photoSource = "umcp/college3.jpg";
		int width = 350, height = 450;
		String date = "10/18/2020-10:10";

		Photo photo = new Photo(photoSource, width, height, date);
		answer += photo + "\n";
		answer += "Comments: " + photo.getComments() + "\n";
		photo.addComments("Visiting Campus");
		answer += "Comments: " + photo.getComments() + "\n";
		photo.addComments("With parents");
		answer += "Comments: " + photo.getComments() + "\n";

		assertTrue(TestingSupport.isResultCorrect(testName, answer, true));
	}

	@Test
	public void pub04PhotoCopyCons() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String answer = "", photoSource = "umcp/college1.jpg";
		int width = 300, height = 400;
		String date = "10/18/2020-17:10";

		Photo photo = new Photo(photoSource, width, height, date);
		photo.addComments("Lunch");
		Photo copy = new Photo(photo);
		copy.addComments("Dinner");

		answer += "Original: " + photo + ", " + photo.getComments() + "\n";
		answer += "Copy: " + copy + ", " + copy.getComments() + "\n";

		assertTrue(TestingSupport.isResultCorrect(testName, answer, true));
	}

	@Test
	public void pub05PhotoCompare() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String answer = "";

		Photo photo1 = new Photo("umcp/college1.jpg", 300, 400, "10/18/2020-17:10");
		Photo photo2 = new Photo("umcp/college8.jpg", 200, 200, "10/18/2020-18:10");
		Photo photo3 = new Photo("umcp/college7.jpg", 200, 200, "10/18/2020-19:10");
		Photo photo4 = new Photo("umcp/college2.jpg", 300, 400, "10/18/2020-17:10");

		answer += "Photo1: " + photo1 + "\n";
		answer += "Photo2: " + photo2 + "\n";
		answer += "Photo3: " + photo3 + "\n";
		answer += "Photo4: " + photo4 + "\n";

		answer += "Compare1: photo1 vs photo2 " + (photo1.compareTo(photo2) < 0) + "\n";
		answer += "Compare2: photo3 vs photo2 " + (photo3.compareTo(photo2) > 0) + "\n";
		answer += "Compare3: photo1 vs photo4 " + (photo1.compareTo(photo4) == 0) + "\n";

		assertTrue(TestingSupport.isResultCorrect(testName, answer, true));
	}

	@Test(expected = IllegalArgumentException.class)
	public void pub06Cons() {
		new Photo(null, 300, 400, "10/18/2020-17:10");
	}

	@Test
	public void pub07PhotoManagerAddPhoto() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String answer = "";

		PhotoManager photoManager = new PhotoManager();
		photoManager.addPhoto("umcp/college1.jpg", 300, 400, "09/17/2020-17:10");
		photoManager.addPhoto("umcp/college8.jpg", 200, 200, "10/18/2020-18:10");

		answer += photoManager;

		assertTrue(TestingSupport.isResultCorrect(testName, answer, true));
	}

	@Test
	public void pub08PhotoManagerAddPhoto() {
		PhotoManager photoManager = new PhotoManager();
		boolean added = photoManager.addPhoto(null, 300, 400, "09/17/2020-17:10");
		assertFalse(added);
	}

	@Test
	public void pub09PhotoManagerFindPhoto() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String answer = "";

		PhotoManager photoManager = new PhotoManager();
		String target = "umcp/college7.jpg", notPresent = "umcp/college200.jpg";
		photoManager.addPhoto("umcp/college1.jpg", 300, 400, "10/18/2020-17:10");
		photoManager.addPhoto("umcp/college8.jpg", 200, 200, "10/18/2020-18:10");
		photoManager.addPhoto(target, 200, 200, "10/18/2020-19:10");
		photoManager.addPhoto("umcp/college2.jpg", 300, 400, "10/18/2020-17:10");

		answer += photoManager + "\n";
		int found = photoManager.findPhoto(target);
		answer += "Found " + target + " " + found;
		found = photoManager.findPhoto(notPresent);
		answer += "\nFound " + notPresent + " " + found;

		assertTrue(TestingSupport.isResultCorrect(testName, answer, true));
	}
	
	@Test
	public void pub10PhotoManagerAddComments() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String answer = "";

		PhotoManager photoManager = new PhotoManager();
		String target = "umcp/college1.jpg", comment1 = "Beach", comment2 = "with HS friends";
		photoManager.addPhoto(target, 300, 400, "09/17/2020-17:10");
		photoManager.addPhoto("umcp/college8.jpg", 200, 200, "10/18/2020-18:10");

		answer += photoManager + "\n";
		photoManager.addComment(target, comment1);
		photoManager.addComment(target, comment2);
		answer += "Comments for: " + target + ": " + photoManager.getComments(target);
		
		assertTrue(TestingSupport.isResultCorrect(testName, answer, true));
	}
	
	@Test
	public void pub11PhotoManagerRemovePhoto() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String answer = "";

		PhotoManager photoManager = new PhotoManager();
		String target = "umcp/college7.jpg";
		photoManager.addPhoto("umcp/college1.jpg", 300, 400, "10/18/2020-17:10");
		photoManager.addPhoto("umcp/college8.jpg", 200, 200, "10/18/2020-18:10");
		photoManager.addPhoto(target, 200, 200, "10/18/2020-19:10");
		photoManager.addPhoto("umcp/college2.jpg", 300, 400, "10/18/2020-17:10");

		answer += photoManager + "\n";
		answer += "Removing " + target + "\n";
		boolean removed = photoManager.removePhoto(target);
		answer += photoManager + "\n";
		answer += "Removed: " + removed + "\n";

		assertTrue(TestingSupport.isResultCorrect(testName, answer, true));
	}
	
	@Test
	public void pub12PhotoManagerLoadPhotos() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String answer = "", filename = "photoInfoToLoad.txt";

		PhotoManager photoManager = new PhotoManager();
		photoManager.loadPhotos(filename);
		answer += photoManager + "\n";
		
		assertTrue(TestingSupport.isResultCorrect(testName, answer, true));
	}
	
	@Test
	public void pub13PhotoManagerSortPhotos() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String answer = "";

		PhotoManager photoManager = new PhotoManager();
		photoManager.addPhoto("umcp/college1.jpg", 300, 400, "10/18/2020-17:10");
		photoManager.addPhoto("umcp/college8.jpg", 200, 200, "10/01/2020-04:10");
		photoManager.addPhoto("umcp/college9.jpg", 200, 200, "09/23/2020-09:10");
		photoManager.addPhoto("umcp/college2.jpg", 300, 400, "10/18/2009-16:10");
		photoManager.addPhoto("umcp/college5.jpg", 300, 400, "10/18/2009-10:10");

		answer += photoManager + "\n";
		answer += "After sorting" + "\n";
		photoManager.sortPhotosByDate();
		answer += photoManager + "\n";
		
		assertTrue(TestingSupport.isResultCorrect(testName, answer, true));
	}

}