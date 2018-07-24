
/**
 * <pre>
 * Given an array of integers, construct another array as following.
 * 
 * The first element of the second array is the first element of the first array.
 * 
 * From then on second[i] = first[i] = first[i - 1]
 * But, if second[i] < -127 or second[i] > 127, insert another element -129 before this element
 * 
 * So, the second array can have more elements than the first array.
 * 
 * e.g.
 * 
 * input array : 1	130	30	190
 * output array : 1	-129	129	-100	-129	160
 * 
 * </pre>
 * @author rebecca
 */
public class MorphingArray {

}
