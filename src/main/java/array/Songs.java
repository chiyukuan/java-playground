package array;

public class Songs {

    public static void main(String[] args) {
        // var songs = new int[]{10, 20, 30, 40, 50};
        var songs = new int[]{90,90,90};

        var result = songPairCount(songs);
        System.out.printf("Result %d\n", result);
    }

    public static int songPairCount(int[] songs) {
        int result = 0;
        var slots = new int[60];
        for (int song: songs) {
            slots[song % 60] ++;
        }
        for (int ii = 1; ii < 30; ii++) {
            result += slots[ii] * slots[60-ii];
        }

        for (int idx: new int[] {0, 30}) {
            if (slots[idx] == 0) {
                continue;
            }
            result += (slots[idx] * slots[idx]) - slots[idx] >> 1;
        }
        return result;
    }
}
