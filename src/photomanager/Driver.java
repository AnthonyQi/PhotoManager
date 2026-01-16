package photomanager;

/**
 * @author UMCP CS Department
 *
 */
public class Driver {

	public static void main(String[] args) {
		PhotoManager photoManager = new PhotoManager();
		String target = "umcp/college1.jpg", answer = "";
		
		/* Adding photos */
		photoManager.addPhoto(target, 300, 400, "09/17/2020-17:10");
		photoManager.addPhoto("umcp/college3.jpg", 200, 200, "11/18/2019-09:00");
		photoManager.addPhoto("umcp/college8.jpg", 200, 200, "10/18/2020-18:30");
		
		answer += "PhotoManager\n";
		answer += photoManager + "\n";
		photoManager.addComment(target, "School Visit");
		photoManager.addComment(target, "Cousins with me");
		answer += "Retrieving comments for " + target + "\n";
		answer += photoManager.getComments(target);
		photoManager.sortPhotosByDate();
		answer += "\nAfter sorting photos by date\n" + photoManager + "\n";
		System.out.println(answer);
		photoManager.createHTMLPage("DriverWebPage1.html");
		System.out.println("Loading Photos");
		PhotoManager photoManager2 = new PhotoManager();
		photoManager2.loadPhotos("photoInfoToLoad.txt");
		photoManager2.createHTMLPage("DriverWebPage2.html");
	}
}
