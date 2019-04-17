package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
import org.junit.Assert;

public class Test {

    private SimilarityFinder similarityFinder;

    private int seq1[] = new int[] {1, 2, 3};
    private int seq2[] = new int[] {1, 2, 3};
    private int seq3[] = new int[] {6, 7, 8};
    private int one[] = new int[] {1};
    private int onetwo[] = new int[] {1, 2};
    private int nullseq[] = new int[] {};
    private int neg1[] = new int[] {-5, -7};
    private int neg2[] = new int[] {-7, -10};
    private float delta = (float) 0.01;

    @org.junit.Test
    public void twoSameLengthSameSequence() {
        similarityFinder = new SimilarityFinder((key, seq) -> {
            if (key == seq[0] || key == seq[1] || key == seq[2] || key == seq[3])
                return SearchResult.builder()
                                   .withFound(true)
                                   .build();
            return SearchResult.builder()
                               .withFound(false)
                               .build();
        });
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        Assert.assertEquals(1.0, result, delta);
    }

    @org.junit.Test
    public void nullSequence() {
        similarityFinder = new SimilarityFinder((key, seq) -> SearchResult.builder()
                                                                          .build());
        double result = similarityFinder.calculateJackardSimilarity(seq1, nullseq);
        Assert.assertEquals(0.0, result, delta);

    }

    @org.junit.Test
    public void diffSequence() {
        similarityFinder = new SimilarityFinder((key, seq) -> SearchResult.builder()
                                                                          .build());
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq3);
        Assert.assertEquals(0.0, result, delta);

    }

    @org.junit.Test
    public void bothNullSequence() {
        similarityFinder = new SimilarityFinder((key, seq) -> SearchResult.builder()
                                                                          .build());
        double result = similarityFinder.calculateJackardSimilarity(nullseq, nullseq);
        Assert.assertEquals(1.0, result, delta);

    }

    @org.junit.Test
    public void oneOneTwoSeq() {
        similarityFinder = new SimilarityFinder((key, seq) -> {
            if (key == seq[0] || key == seq[1] || key == seq[2])
                return SearchResult.builder()
                                   .withFound(true)
                                   .build();
            return SearchResult.builder()
                               .withFound(false)
                               .build();
        });
        double result = similarityFinder.calculateJackardSimilarity(one, onetwo);
        Assert.assertEquals(0.5, result, delta);

    }

    @org.junit.Test
    public void oneTwoOneSeq() {
        similarityFinder = new SimilarityFinder((key, seq) -> {
            if (key == seq[0])
                return SearchResult.builder()
                                   .withFound(true)
                                   .build();
            return SearchResult.builder()
                               .withFound(false)
                               .build();
        });
        double result = similarityFinder.calculateJackardSimilarity(onetwo, one);
        Assert.assertEquals(0.5, result, delta);

    }

    @org.junit.Test
    public void twoSameLengthCounterTest() {
        SequenceSearcherCounterDubler sequenceSearcherCounterDubler = new SequenceSearcherCounterDubler();
        similarityFinder = new SimilarityFinder(sequenceSearcherCounterDubler);
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);
        int counter = sequenceSearcherCounterDubler.getCounter();
        Assert.assertEquals(counter, seq1.length);

    }

    @org.junit.Test
    public void bothNullCounterTest() {
        SequenceSearcherCounterDubler sequenceSearcherCounterDubler = new SequenceSearcherCounterDubler();
        similarityFinder = new SimilarityFinder(sequenceSearcherCounterDubler);
        double result = similarityFinder.calculateJackardSimilarity(nullseq, nullseq);
        int counter = sequenceSearcherCounterDubler.getCounter();
        Assert.assertEquals(counter, nullseq.length);

    }

    @org.junit.Test
    public void diffNegativeSequence() {
        similarityFinder = new SimilarityFinder((key, seq) -> {
            if (key == seq[0] || key == seq[1])
                return SearchResult.builder()
                                   .withFound(true)
                                   .build();
            return SearchResult.builder()
                               .withFound(false)
                               .build();
        });
        double result = similarityFinder.calculateJackardSimilarity(neg1, neg2);
        Assert.assertEquals(1.0 / 3, result, delta);

    }

    private class SequenceSearcherCounterDubler implements SequenceSearcher {

        private int counter = 0;

        @Override
        public SearchResult search(int key, int[] seq) {
            this.counter++;
            return SearchResult.builder()
                               .build();
        }

        int getCounter() {
            return counter;
        }
    }

}
