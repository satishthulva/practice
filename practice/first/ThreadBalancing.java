package practice.interview.rivigo;

/**
 * Let us define a system where there are N buckets initially. Each bucket has certain number of balls in it.
 * e.g. 3 buckets are there initially with 3 balls in first bucket, 5 balls in second bucket, and 9 balls in third bucket.
 * Each bucket is attached to a machine arm which can pick one ball per minute from the bucket and throw.
 * 
 * All machines are synchronized to the same clock. At the start of every minute, a supervisor can make a call
 * to stop all the machines and move balls from EXACTLY one bucket to EXACTLY one another bucket or add a new bucket and put them
 * in that new bucket. The supervisor will take a full minute to do that.
 * 
 * From the next minute onwards, the machine arms resume operating(including the new one added in previous step, if any).
 * Assume there is an infinite supply of buckets.
 * 
 * Now, given an initial configuration of buckets and balls - find out the minimum number of minutes in which
 * all the balls can be thrown.
 * 
 * e.g. 1 bucket with 4 balls
 * 
 * end of minute 1 --> rearrange into 2, 2 i.e. new bucket added and 2 balls moved from bucket 1 to bucket 2
 * end of minute 2 --> 1, 1 i.e. both buckets 1 ball gone
 * end of minute 3 --> 0, 0 i.e. both buckets 1 ball gone
 * 
 * i.e. 3 minutes to finish all balls
 * 
 * e.g. 2 
 * buckets with balls 2 and 6
 * end of minute 1 --> rearrange 6 into 3, 3 --> 2, 3, 3
 * end of minute 2 --> 1, 2, 2
 * end of minute 3 --> 0, 1, 1
 * end of minute 4 --> 0, 0, 0
 * 
 * @author rebecca
 */
public class ThreadBalancing {

}
