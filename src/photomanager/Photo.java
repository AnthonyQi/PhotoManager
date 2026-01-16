package photomanager;

public class Photo implements Comparable<Photo> {
	private String photoSource;
	private int width, height;
	private String date;
	private StringBuffer comments;
	
	public Photo(String photoSource, int width, int height, String date) {
		if(photoSource == null || photoSource.isBlank() || date == null ||
				date.isBlank() || width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Invalid arguments");
        }
		this.photoSource = photoSource;
		this.width = width;
		this.height = height;
		this.date = date;
		comments = new StringBuffer();
		
	}
	public Photo(Photo photo) {
		if(photo == null) {
            throw new 
            	IllegalArgumentException("Invalid arguments");
        }
        //Deep copy just in case for max security
        this.photoSource = photo.photoSource;
        this.width = photo.width;
        this.height = photo.height;
        this.date = photo.date;
        this.comments = new StringBuffer(photo.comments);
	}

	public String toString() {
		String s = photoSource + "," + width + "," + height + "," + date;
		return s;
	}
	//not sure if i need to use copy for these get methods
	public String getPhotoSource() {
		String ps = this.photoSource;
		return ps;
	}

	public int getWidth() {
		int w = this.width;
		return w;
	}
	
	public int getHeight() {
		int h = this.height;
		return h;
	}

	public String getDate() {
		String copyDate = this.date;
		return copyDate;
	}

	public Photo addComments(String newComment) {
		if(newComment == null || newComment.isBlank()) {
			throw new IllegalArgumentException("Invalid comment");
		}
		if (comments.length() > 0) {
	        comments.append(",");
	    }
		comments.append(newComment);
		return this;
		
	}

	public String getComments() {
		StringBuffer copyComment = this.comments;
		return copyComment.toString();
	}

	public int compareTo(Photo photo) {
		if (photo == null) {
	        throw new IllegalArgumentException("Cannot compare to a null Photo.");
	    }
	    long thisDate = Utilities.getDate(this.date);
	    long otherDate = Utilities.getDate(photo.date);
	    return Long.compare(thisDate, otherDate);
	}
}