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
					new Course(anyString, anyInt, anyString, anyInt), 
					any, anyInt, new Grade("A"));
			transcript.getGpa(); returns(4.0);
		}};
		
		student = new Student("Sue", "Storm");
		student.addCourse(
				new Course("CS", 443, "Software Quality Assurance and Testing", 3), 
				Transcript.Semester.FALL, 2015, new Grade("A"));
		assertEquals(4.0, student.getGpa(), 0.0);
	
		new VerificationsInOrder() {{
			new Transcript(); times = 1;
			transcript.addCourse(
					new Course(anyString, anyInt, anyString, anyInt), 
					any, anyInt, new Grade("A")); times = 1;
			transcript.getGpa(); times = 1;
		}};
	}
	
	@Test
	public void testCurrentEarnedCr() {
		new Expectations() {{
			new Transcript();
			transcript.getCurrentEarnedCr(); returns(100);
		}};
		student = new Student("Sue", "Storm");
		assertEquals(100, student.getCurrentEarnedCr());
	}
	
	@Test
	public void testAnticipatedAdditionalCr() {
		new Expectations() {{
			new Transcript();
			transcript.addCourse(
					new Course(anyString, anyInt, anyString, anyInt), 
					any, anyInt, any); // Course should give 4 credits
			transcript.getAnticipatedAdditionalCr(); returns(4);
		}};
		student = new Student("Sue", "Storm");
		assertEquals(4, student.getAnticipatedAdditionalCr());
	}
	
	@Test
	public void testCurrentRemainingCr() {
		new Expectations() {{
			new Transcript();
			transcript.getCurrentRemainingCr(); returns(20);
		}};
		student = new Student("Sue", "Storm");
		assertEquals(20, student.getCurrentEarnedCr());
	}
	
	@Test
	public void testAnticipatedRemainingCr() {
		new Expectations() {{
			new Transcript();
			transcript.addCourse(
					new Course(anyString, anyInt, anyString, anyInt), 
					any, anyInt, any); // Course should give 4 credits
			transcript.getAnticipatedAdditionalCr(); returns(16);
		}};
		student = new Student("Sue", "Storm");
		assertEquals(16, student.getAnticipatedAdditionalCr());
	}
	
	@Test
	public void testLascComplete() {
		new Expectations() {{
			new Transcript();
			transcript.setLascComplete(anyBoolean);
			transcript.getLascComplete(); returns(anyBoolean);
		}};
		student = new Student("Sue", "Storm");
		student.setLascComplete(true);
		assertTrue(student.getLascComplete());
	}
	
	@Test
	public void testMajorComplete() {
		new Expectations() {{
			new Transcript();
			transcript.setMajorComplete(anyBoolean);
			transcript.getMajorComplete(); returns(anyBoolean);
		}};
		student = new Student("Sue", "Storm");
		student.setMajorComplete(false);
		assertFalse(student.getLascComplete());
	}
	
	@Test
	public void testReadyToGraduate() {
		new Expectations() {{
			new Transcript();
			transcript.readyToGraduate(); returns(false);
		}};
		student = new Student("Sue", "Storm");
		assertFalse(student.readyToGraduate());
	}
	
	@Test
	public void testAddDropCourse() {
		new Expectations() {{
			new Transcript();
			transcript.addCourse(any, any, any, any);
			transcript.dropCourse(any, any, any); returns(true);
		}};
		student = new Student("Sue", "Storm");
		course = new Course("CS", 443, "Software Quality Assurance and Testing", 3);
		student.addCourse(course, Transcript.Semester.FALL, 2015, new Grade("A"));
		assertTrue(student.dropCourse(course, Transcript.Semester.FALL, 2015));
	}
	
	@Test
	public void testGetTranscript() {
		new Expectations() {{
			new Transcript();
			transcript.getTranscript(); returns(anyString);
		}};
		student = new Student("Sue", "Storm");
		assertNotNull(student.getTranscript());
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
