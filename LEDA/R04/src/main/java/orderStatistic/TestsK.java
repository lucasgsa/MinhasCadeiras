package orderStatistic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestsK {

	@Test
	public void test() {
		KLargestOrderStatisticsImpl<Integer> kl = new KLargestOrderStatisticsImpl<Integer>();
		Integer[] a1 = {9,8,7,6,5};
		Integer[] saidaEsperada = {9,8};
		Integer[] saidaReal = kl.getKLargest(a1, 2);
		assertArrayEquals(saidaEsperada, saidaReal);
	}

}
