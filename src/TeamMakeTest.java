import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

public class TeamMakeTest {

	TeamMake sut = new TeamMake();

	@Test
	public void testInput() throws FileNotFoundException {
//		given
		sut.fileIn("./input.txt");

//		when
		String[] arr = sut.getArray();
		
//		then
		assertEquals("1 1 2", arr[0]);
		assertEquals("2 1 3", arr[1]);
		assertEquals("3 0", arr[2]);
		assertEquals("4 2 2 3", arr[3]);
		assertEquals("5 1 4", arr[4]);
		
//		for (String e : arr) {
//			if (e != null) {
//				System.out.println(e);
//			}
//		}
	}
	
	@Test
	public void testGetATeam() throws Exception {
//		given
		sut.fileIn("./input.txt");
		
		
//		when
		sut.buildDislikeMatrix();
		
//		then
		int[] a = sut.getATeam();
		for (int member : a) {
			System.out.println(member);
		}
	}
	
	@Test
	public void testDislikeMatrix() throws Exception {
//		given
		sut.fileIn("./input.txt");
		
		
//		when
		sut.buildDislikeMatrix();
		
//		then
		int[][] info = sut.getDislikeMatrix();
		for (int[] member : info) {
			if (member != null) {
				for (int hater : member) {
					System.out.print(hater + " ");
				}
			}
			System.out.println();
		}
	}

}
