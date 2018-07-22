
/**
 * You are given a graph where each node represents a city. Each city has a beauty value assigned to it(a non-negative integer).
 * The cities are connected through bi-directional roads. Each road has a distance assigned to it. If two cities are connected through road, 
 * there is only one road connecting them. Assume a person can travel one unit of distance in one unit of time.
 * 
 * You are a traveler starting at origin city( which is given ). You have certain amount of time which you can use for traveling
 * and returning back to the origin city. Find out the maximum beauty that you can cover in this time. Remember that you need to 
 * return back to the origin city at the end.
 * 
 * 						(43)		17	 (0)	10		(26)
 * e.g.					1	|--------- 	 0	---------|	2
 * 										 -
 * 										 -
 * 										 -10
 * 										 -
 * 										 -
 * 										___
 * 										 3 (16)
 * 
 * In the example graph - 0, 1, 2, and 3 are cities with beauty values 0, 43, 26, and 16 respectively.
 * 0 is the origin city and all the other cities are connected to only the origin city. The length of each road is 
 * shown on the road. The length of road connecting cities 0 and 3 is 10 units.
 * 
 * Now, assume that the time you have to travel is 40 units. So, the maximum beauty you can see in that time is 43, because
 * you can visit city 1 and come back to 0 in 34 units of time, visiting 43 units of beauty. You can not visit any other city
 * because the minimum time to visit and come back any other city is 20 units. This is the maximum you can do.
 * 
 * Even if you visit the other two cities only, you'll take 40 units of time and at the end of it, would have seen a collective beauty
 * 42 units only
 * 
 * @author rebecca
 */
public class MaximumBenefitTraversalsInGraph {

}
