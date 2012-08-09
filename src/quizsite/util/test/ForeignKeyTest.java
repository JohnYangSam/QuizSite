package quizsite.util.test;

import static org.junit.Assert.*;
import org.junit.*;

import quizsite.util.ForeignKey;

public class ForeignKeyTest {

	private ForeignKey[] fkeys;
	
	@Before
	public void setUp() throws Exception {
		fkeys = new ForeignKey[] { new ForeignKey("quiz_id", "Quiz", "id"), new ForeignKey("user_id", "User", "id")};
	}
	
	@Test
	public void testSerialize() {
		System.out.println(ForeignKey.serialize(fkeys));
		fkeys = new ForeignKey[] {};
		System.out.println(ForeignKey.serialize(fkeys));
	}

}
