package quizsite.util.test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

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
