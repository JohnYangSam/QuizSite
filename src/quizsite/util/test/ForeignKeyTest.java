package quizsite.util.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import quizsite.util.ForeignKey;

public class ForeignKeyTest {

	private List<ForeignKey> fkeys;
	
	@Before
	public void setUp() throws Exception {
		fkeys = Arrays.asList( new ForeignKey("quiz_id", "Quiz", "id"), new ForeignKey("user_id", "User", "id"));
	}
	
	@Test
	public void testSerialize() {
		System.out.println(ForeignKey.serialize(fkeys));
		fkeys = new ArrayList<ForeignKey>();
		System.out.println(ForeignKey.serialize(fkeys));
	}

}
