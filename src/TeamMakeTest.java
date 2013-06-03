import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

public class TeamMakeTest {

	TeamMake sut = new TeamMake();

	@Test
	public void testInput() throws FileNotFoundException {
		// given
		sut.fileIn("./input.txt");

		// when
		String[] arr = sut.getArray();

		// then
		assertEquals("1 1 2", arr[0]);
		assertEquals("2 1 3", arr[1]);
		assertEquals("3 0", arr[2]);
		assertEquals("4 2 2 3", arr[3]);
		assertEquals("5 1 4", arr[4]);

		// for (String e : arr) {
		// if (e != null) {
		// System.out.println(e);
		// }
		// }
	}

	@Test
	public void testGetATeam() throws Exception {
		// given
		sut.fileIn("./input.txt");

		// when
		sut.buildDislikeMatrix();

		// then
		int[] a = sut.getATeam();

		assertEquals(1, a[0]);
		assertEquals(2, a[1]);
		assertEquals(3, a[2]);
		assertEquals(4, a[3]);
		assertEquals(5, a[4]);

		// for (int member : a) {
		// System.out.println(member);
		// }
	}

	@Test
	public void testDislikeMatrix() throws Exception {
		// given
		sut.fileIn("./input.txt");

		// when
		sut.buildDislikeMatrix();

		// then
		int[][] info = sut.getDislikeMatrix();

		assertArrayEquals(new int[] { 1, 2, 0, 0, 0 }, info[0]);
		assertArrayEquals(new int[] { 1, 3, 0, 0, 0 }, info[1]);
		assertArrayEquals(new int[] { 0, 0, 0, 0, 0 }, info[2]);
		assertArrayEquals(new int[] { 2, 2, 3, 0, 0 }, info[3]);
		assertArrayEquals(new int[] { 1, 4, 0, 0, 0 }, info[4]);

		// for (int[] member : info) {
		// if (member != null) {
		// for (int hater : member) {
		// System.out.print(hater + " ");
		// }
		// }
		// System.out.println();
		// }
	}

	@Test
	public void testBuildATeam() throws Exception {
		// given
		sut.fileIn("./input.txt");

		// when
		sut.buildDislikeMatrix();
		sut.buildATeam();

		// then
		int[] a = sut.getATeam();

//		for (int member : a) {
//			System.out.println(member);
//		}

		assertEquals(1, a[0]);
		assertEquals(0, a[1]);
		assertEquals(0, a[2]);
		assertEquals(0, a[3]);
		assertEquals(5, a[4]);
	}
	
	@Test
	public void testBuildBTeam() throws Exception {
		// given
		sut.fileIn("./input.txt");

		// when
		sut.buildDislikeMatrix();
		sut.buildATeam();
		sut.buildBTeam();

		// then
		int[] b = sut.getBTeam();

//		for (int member : b) {
//			System.out.println(member);
//		}

		assertEquals(0, b[0]);
		assertEquals(0, b[1]);
		assertEquals(0, b[2]);
		assertEquals(4, b[3]);
		assertEquals(0, b[4]);
	}

}
