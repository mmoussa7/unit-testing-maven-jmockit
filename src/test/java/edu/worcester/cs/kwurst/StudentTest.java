package edu.worcester.cs.kwurst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mockit.Expectations;
import mockit.FullVerifications;
import mockit.Mocked;
import mockit.Verifications;
import mockit.VerificationsInOrder;

public class StudentTest {
	@Mocked Transcript transcript;
	Student student;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGpa() {
		new Expectations() {{
			new Transcript();
			transcript.addCourse(
					new Course("CS", 443, "Software Quality Assurance and Testing", 3), 
					Transcript.Semester.FALL, 2015, new Grade("A"));
			transcript.getGpa(); returns(4.0);
		}};
		
		student = new Student("Sue", "Storm");
		student.addCourse(
				new Course("CS", 443, "Software Quality Assurance and Testing", 3), 
				Transcript.Semester.FALL, 2015, new Grade("A"));
		assertEquals(4.0, student.getGpa(), 0.0);
	
		new VerificationsInOrder() {{
			new Transcript();
			transcript.addCourse(
					new Course("CS", 443, "Software Quality Assurance and Testing", 3), 
					Transcript.Semester.FALL, 2015, new Grade("A"));
			transcript.getGpa();
		}};
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
