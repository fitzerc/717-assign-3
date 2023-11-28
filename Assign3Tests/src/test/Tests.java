package test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import main.Main;

import org.junit.Test;

public class Tests {
	private final PrintStream standardOut = System.out;
	  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	  @Before
	  public void setUp() {
	    // Set the custom output stream as the output for System.out
	    System.setOut(new PrintStream(outputStreamCaptor));
	  }

	  @After
	  public void tearDown() {
	    // Restore the original output stream
	    System.setOut(standardOut);
	  }

	@Test
	public void EncryptionTest() {
		String key = "abc";
		String howManyWords = "1";
		String msg = "def";
		String stdIn = key + System.lineSeparator() + howManyWords + System.lineSeparator() + msg;

  	    ByteArrayInputStream testIn = new ByteArrayInputStream(stdIn.getBytes());
		System.setIn(testIn);
		
		Main.main(null);
		
		var output = outputStreamCaptor.toString();
		var splitOutput = output.split(System.lineSeparator());
		
		assertEquals(6, splitOutput.length);
		assertTrue(splitOutput[3].endsWith("def"));
		assertTrue(splitOutput[4].endsWith("ghi"));
	}

	@Test
	public void NonAlph_EncryptionTest() {
		String key = "@bc";
		String howManyWords = "1";
		String msg = "@ef";
		String stdIn = key + System.lineSeparator() + howManyWords + System.lineSeparator() + msg;

  	    ByteArrayInputStream testIn = new ByteArrayInputStream(stdIn.getBytes());
		System.setIn(testIn);
		
		Main.main(null);
		
		var output = outputStreamCaptor.toString();
		var splitOutput = output.split(System.lineSeparator());
		
		assertEquals(6, splitOutput.length);
		assertTrue(splitOutput[3].endsWith("@ef"));
		assertTrue(splitOutput[4].endsWith("@hi"));
	}
}
