import java.util.Arrays;

public class Solution {


    public int[] getArrayAfterFour (int[] array){
        int index = -1;
        final int cnt = array.length;
        for (int i = 0; i < cnt ; i++) {
            if(array[i] == 4) index = i;
        }

        int newArraySize = cnt - index - 1;
        int[] newArray = new int[newArraySize];
        if (index == -1) throw new RuntimeException();
        else {
            for (int i = 0; i < newArraySize ; i++) {
                index++;
                newArray[i] = array[index];
            }
        }

        return newArray;
    }

}
