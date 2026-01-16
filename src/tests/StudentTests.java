package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import photomanager.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {
    private PhotoManager manager;
    private Photo test;

	//photoManager test
	@Test
    public void testAddPhoto01() {
		manager = new PhotoManager();
        assertTrue(manager.addPhoto("photo1.jpg", 800, 600, "11/11/1111-17:10"));
    }

    @Test
    public void testAddPhoto02() {
    	manager = new PhotoManager();
        manager.addPhoto("photo2.jpg", 800, 600, "12/12/1212-17:10");
        assertFalse(manager.addPhoto("photo2.jpg", 800, 600, "12/12/1212-17:10"));
    }

    @Test
    public void testAddPhoto03() {
    	boolean result = false;
    	manager = new PhotoManager();
        assertEquals(result, manager.addPhoto("photo3.jpg", -800, 600, "11/12/1234-17:10"));
    }

    @Test
    public void testFindPhoto01() {
    	manager = new PhotoManager();
        manager.addPhoto("photo1.jpg", 800, 600, "11/11/1111-17:10");
        assertEquals(0, manager.findPhoto("photo1.jpg"));
    }

    @Test
    public void testFindPhoto02() {
    	manager = new PhotoManager();
        assertEquals(-1, manager.findPhoto("nonexistent.jpg"));
    }

    // Test addComment with valid comment
    @Test
    public void testAddComment01() {
    	manager = new PhotoManager();
        manager.addPhoto("photo1.jpg", 800, 600, "11/11/1111-17:10");
        assertTrue(manager.addComment("photo1.jpg", "WOW"));
        assertEquals("WOW", manager.getComments("photo1.jpg"));
    }

    @Test
    public void testAddComment02() {
    	manager = new PhotoManager();
        assertFalse(manager.addComment(null, ":P"));
    }

    @Test
    public void testGetComments01() {
    	manager = new PhotoManager();
        manager.addPhoto("photo1.jpg", 800, 600, "11/11/1111-17:10");
        manager.addComment("photo1.jpg", "LOL");
        manager.addComment("photo1.jpg", "This is great");
        assertEquals("LOL,This is great", manager.getComments("photo1.jpg"));
    }

    @Test
    public void testGetComments02() {
    	manager = new PhotoManager();
        assertNull(manager.getComments("null.jpg"));
    }

    @Test
    public void testRemoveAllPhotos01() {
    	manager = new PhotoManager();
        manager.addPhoto("photo1.jpg", 800, 600, "11/11/1111-17:10");
        manager.addPhoto("photo2.jpg", 1024, 768, "11/12/1111-17:10");
        manager.removeAllPhotos();
        assertEquals("", manager.toString().trim());
    }

    @Test
    public void testRemovePhoto01() {
    	manager = new PhotoManager();    	
        manager.addPhoto("photo1.jpg", 800, 600, "11/11/1111-17:10");
        assertTrue(manager.removePhoto("photo1.jpg"));
        assertEquals(-1, manager.findPhoto("photo1.jpg"));
    }

    @Test
    public void testRemovePhoto02() {
    	manager = new PhotoManager();
        assertFalse(manager.removePhoto("null.jpg"));
    }

    @Test
    public void testLoadPhotos01() {
    	PhotoManager manager = new PhotoManager();
        manager.addPhoto("photo1.jpg", 800, 600, "11/11/1111-17:10");
        manager.addPhoto("photo2.jpg", 1024, 768, "11/11/1112-17:10");
        assertEquals(2, manager.toString().split("\n").length);
    }

    @Test
    public void testLoadPhotos02() {
    	manager = new PhotoManager();
        assertFalse(manager.loadPhotos("null.txt"));
    }

    @Test
    public void testSortPhotosByDate() {
    	manager = new PhotoManager();
        manager.addPhoto("photo2.jpg", 1024, 768, "11/12/1111-19:10");
        manager.addPhoto("photo1.jpg", 800, 600, "11/11/1111-17:10");
        manager.sortPhotosByDate();
        String[] photos = manager.toString().split("\n");
        assertTrue(photos[0].contains("photo1.jpg"));
        assertTrue(photos[1].contains("photo2.jpg"));
    }
    //photo test
    @Test
    public void testToString() {
    	test = new Photo("photo1.jpg", 800, 600, "11/11/1111-17:10");
    	String result = "photo1.jpg,800,600,11/11/1111-17:10";
    	assertEquals(test.toString(), result);
    }
    @Test
    public void testGetPhotoSource() {
    	test = new Photo("photo1.jpg", 800, 600, "11/11/1111-17:10");
    	String result = "photo1.jpg";
    	assertEquals(test.getPhotoSource(), result);
    }
    @Test
    public void testGetWidth() {
    	test = new Photo("photo1.jpg", 800, 600, "11/11/1111-17:10");
    	int result = 800;
    	assertEquals(test.getWidth(), result);
    }
    @Test
    public void testGetHeight() {
    	test = new Photo("photo1.jpg", 800, 600, "11/11/1111-17:10");
    	int result = 600;
    	assertEquals(test.getHeight(), result);
    }
    @Test
    public void testGetDate() {
    	test = new Photo("photo1.jpg", 800, 600, "11/11/1111-17:10");
    	String result = "11/11/1111-17:10";
    	assertEquals(test.getDate(), result);
    }
    @Test
    public void testComments() {
    	test = new Photo("1.jpg", 1000, 1000, "11/11/1111-17:10");
    	//null test
    	try {
    		test.addComments(null);
    	} catch(IllegalArgumentException e) {
    		assertEquals("Invalid comment", e.getMessage());
    	}
    	//empty test
    	try {
    		test.addComments("");
    	} catch(IllegalArgumentException e) {
    		assertEquals("Invalid comment", e.getMessage());
    	}
    	test.addComments("HIIIII");
    	String result = "HIIIII";
    	assertEquals(test.getComments(), result);
    }
}