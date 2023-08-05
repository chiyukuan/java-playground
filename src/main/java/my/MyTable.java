package my;

import java.util.ArrayList;
import java.util.List;

public class MyTable {

    public static void main(String[] args) {
        var table = new MyTable(3, 4);
        table.append(0, 10);
        table.append(0, 20);
        table.append(0, 30);
        table.append(2, 10);
        table.append(1, 10);
        table.append(0, 40);
        table.show();
    }

    private final List<List<Number>> table;
    private final int[][] rawTable;

    public MyTable(int rowCnt, int colCnt) {
        this.table = new ArrayList<>();
        this.rawTable = new int[rowCnt][colCnt];
    }

    public void append(int ii, int val) {
        while (this.table.size() < ii + 1) {
            this.table.add(new ArrayList<>());
        }
        var row = this.table.get(ii);
        this.rawTable[ii][row.size()] = val;
        row.add(val);
    }

    public void show() {
        for(var row: this.table) {
            for(var cell: row) {
                System.out.printf("%3d ", cell.intValue());
            }
            System.out.println("");
        }
        System.out.println("");
        for(var row: this.rawTable) {
            for(var cell: row) {
                System.out.printf("%3d ", cell);
            }
            System.out.println("");
        }
    }

}
