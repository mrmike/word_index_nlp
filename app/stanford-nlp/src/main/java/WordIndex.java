import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class WordIndex {

    protected StanfordCoreNLP pipeline;

    public WordIndex() {
        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma");
        this.pipeline = new StanfordCoreNLP(props);
    }

    public List<String> lemmatize(String documentText) {
        List<String> lemmas = new LinkedList<String>();

        // Tworzymy dokument z przekazanego stringu
        Annotation document = new Annotation(documentText);
        // i uruchamiamy na nim po kolei wszystkie annonatory
        this.pipeline.annotate(document);
        // Iterujemy po każdym zdaniu
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            // Poddajemy każde zdanie tokenizacji
            for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
                // i dla każdego tokenu tworzymy jego formę podstawową
                lemmas.add(token.get(LemmaAnnotation.class));
            }
        }
        return lemmas;
    }

    public String getWordIndex(List<String> lemmas) {
        final StringBuilder builder = new StringBuilder();
        int mainIndex = 1;
        HashMap<String, Integer> indexes = new HashMap<String, Integer>();
        for (String lemma : lemmas) {
            if (!indexes.containsKey(lemma)) {
                indexes.put(lemma, mainIndex);
                mainIndex += 1;
            }

            int index = indexes.get(lemma);
            builder.append(index + " - " + lemma).append("\n");
        }
        return builder.toString();
    }


    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            builder.append(line).append("\n");
        }

        WordIndex wordIndex = new WordIndex();
        List<String> lemmas = wordIndex.lemmatize(builder.toString());
        String result = wordIndex.getWordIndex(lemmas);
        System.out.println(result);
    }
}