
/**
 * Given 3 integers, find if they can be lengths of the sides of a triangle or not ?
 * If they can form a triangle, see if it is equilateral.
 * 
 * Output : 0 --> if given input is not valid or does not form a triangle
 * 			1 --> if given input forms an equilateral triangle
 *          2 --> input forms a non-equilateral triangle
 * 
 * @author rebecca
 */
public class Triangles {
    static int canFormTriangle(int a, int b, int c) {
        if(a <=0 || b <= 0 || c <= 0)
            return 0;
        
        if((a == b) && (b == c))
            return 1;
        
        if(a + b > c && b + c > a && c + a > b)
            return 2;
        else
            return 0;
    }
}
