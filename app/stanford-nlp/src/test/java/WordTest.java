import edu.stanford.nlp.io.EncodingPrintWriter;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

public class WordTest {

    @Test
    public void testLemma() {
        final String testInput = "How could you be seeing into my eyes like open doors?";
        final String result = "1 - how\n2 - could\n3 - you\n4 - be\n" +
                "5 - see\n6 - into\n7 - my\n8 - eye\n9 - like\n" +
                "10 - open\n11 - door\n12 - ?\n";
        WordIndex testClass = new WordIndex();
        List<String> lemmas = testClass.lemmatize(testInput);
        String output = testClass.getWordIndex(lemmas);
        Assert.assertTrue(result.equals(output));
    }
}
