package lab2_3;

import org.junit.Assert;
import edu.iis.mto.similarity.SequenceSearcherDubler;
import edu.iis.mto.similarity.SimilarityFinder;

public class Test {

    private int seq1[] = new int[] {1, 2, 3};
    private int seq2[] = new int[] {1, 2, 3};
    private int seq3[] = new int[] {6, 7, 8};
    private int one[] = new int[] {1};
    private int onetwo[] = new int[] {1, 2};
    private int nullseq[] = new int[] {};
    private int neg1[] = new int[] {-5, -7};
    private int neg2[] = new int[] {-7, -10};
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

    @org.junit.Test
    public void diffSequence() {
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq3);
        Assert.assertEquals(0.0, result, delta);

    }
    
    @org.junit.Test
    public void bothNullSequence() {
        double result = similarityFinder.calculateJackardSimilarity(nullseq, nullseq);
        Assert.assertEquals(1.0, result, delta);

    }
    
    @org.junit.Test
    public void oneOneTwoSeq() {
        double result = similarityFinder.calculateJackardSimilarity(one, onetwo);
        Assert.assertEquals(0.5, result, delta);

    }
    
    @org.junit.Test
    public void oneTwoOneSeq() {
        double result = similarityFinder.calculateJackardSimilarity(onetwo, one);
        Assert.assertEquals(0.5, result, delta);

    }
    
    @org.junit.Test
    public void twoSameLengthCounterTest() {
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        int counter = searcher.getCounter();
        Assert.assertEquals(counter, seq1.length);

    }
    
    @org.junit.Test
    public void bothNullCounterTest() {
        double result = similarityFinder.calculateJackardSimilarity(nullseq, nullseq);
        int counter = searcher.getCounter();
        Assert.assertEquals(counter, nullseq.length);

    }
    
    @org.junit.Test
    public void diffNegativeSequence() {
        double result = similarityFinder.calculateJackardSimilarity(neg1, neg2);
        Assert.assertEquals(1.0/3, result, delta);

    }

}
