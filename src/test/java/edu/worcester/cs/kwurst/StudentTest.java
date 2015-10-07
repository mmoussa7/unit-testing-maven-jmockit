package edu.worcester.cs.kwurst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mockit.Expectations;
import mockit.FullVerifications;
import mockit.Mocked;

public class StudentTest {
	@Mocked Transcript transcript;
	Student student;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGpa() {
		new Expectations() {{
			transcript.getGpa(); returns(4.0);
		}};
		
		student = new Student("Sue", "Storm");
		assertEquals(4.0, student.getGpa(), 0.0);
	}
	
	@Test
	public void testCurrentEarnedCr() {
		new Expectations() {{
			transcript.getCurrentEarnedCr(); returns(100);
		}};
		student = new Student("Sue", "Storm");
		assertEquals(100, student.getCurrentEarnedCr());
	}
	
	@Test
	public void testName() {
		student = new Student("Sue", "Storm");
		assertEquals("Sue", student.getFirstName());
		assertEquals("Storm", student.getLastName());
		student.setFirstName("Johnny");
		assertEquals("Johnny", student.getFirstName());
	}
}
