import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SoluTest2 {
    Solution2 solution;
    @Before
    public void init(){
        solution = new Solution2();
    }

    @Test
    public void hasOneOrFour(){
        Assert.assertTrue(solution.checkArray(new int[]{1,2,3,4,5,4,3,2,1}));
    }

    @Test
    public void hasOneOrFour2(){
        Assert.assertFalse(solution.checkArray(new int[]{2,3,5,6,7}));
    }

    @Test
    public void hasOneOrFour3(){
        Assert.assertFalse(solution.checkArray(new int[]{1,2,3,5,6,7}));
    }

    @Test
    public void hasOneOrFour4(){
        Assert.assertFalse(solution.checkArray(new int[]{4,2,3,5,6,7}));
    }
}
