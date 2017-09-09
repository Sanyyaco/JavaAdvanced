import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SoluTest {
    Solution solution;
    @Before
    public void init(){
        solution = new Solution();
    }

    @Test
    public void testArray(){
        int[] array = solution.getArrayAfterFour(new int[]{1, 2, 2, 1, 4, 5, 6, 4, 7, 8});
        int[] expectedArray = new int[]{7,8};
        Assert.assertArrayEquals(expectedArray,array);
    }

    @Test
    public void testArray1(){
        int[] array = solution.getArrayAfterFour(new int[]{5, 4, 1, 2, 3});
        int[] expectedArray = new int[]{1,2,3};
        Assert.assertArrayEquals(expectedArray,array);
    }

    @Test(expected = RuntimeException.class)
    public void testArray2(){
        int[] array = solution.getArrayAfterFour(new int[]{5, 1, 2, 3});
        int[] expectedArray = new int[]{1,2,3};
        Assert.assertArrayEquals(expectedArray,array);
    }

}
