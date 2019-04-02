package lab2_3;

import org.junit.Assert;
import edu.iis.mto.similarity.SequenceSearcherDubler;
import edu.iis.mto.similarity.SimilarityFinder;

public class Test {

    private int seq1[] = new int[] {1, 2, 3};
    private int seq2[] = new int[] {1, 2, 3};
    private int nullseq[] = new int [] {};
    private float delta = (float) 0.01;
    SequenceSearcherDubler searcher = new SequenceSearcherDubler();
    SimilarityFinder similarityFinder = new SimilarityFinder(this.searcher);

    @org.junit.Test
    public void twoSameLengthSameSequence() {
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        Assert.assertEquals(1.0, result, delta);

    }

    @org.junit.Test
    public void nullSequence() {
        double result = similarityFinder.calculateJackardSimilarity(seq1, nullseq);
        Assert.assertEquals(0.0, result, delta);

    }

}
