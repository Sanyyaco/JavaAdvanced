public class Solution2 {

    public boolean checkArray(int[] array){
        final int cnt = array.length;

        boolean hasFour = false;
        boolean hasOne = false;

        for (int i = 0; i < cnt ; i++) {
            if (array[i] == 1) hasOne = true;
            if (array[i] == 4) hasFour = true;
        }

        return hasOne && hasFour;
    }
}
