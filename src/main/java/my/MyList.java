package my;

import java.util.ArrayList;
import java.util.List;

public class MyList {

    public static void main(String[] args) {
        MyList mylist = new MyList(3);
        mylist.append(10);
        mylist.append(20);
        mylist.show();
    }

    private List<Number> alist;
    private int[] rawList;
    public MyList(int maxCnt) {
        this.alist = new ArrayList<>();
        this.rawList = new int[maxCnt];
    }

    public void append(int val) {
        this.rawList[this.alist.size()] = val;
        this.alist.add(val);
    }

    public void show() {
        for(var elm: this.alist) {
            System.out.printf("%d ", elm.intValue());
        }
        System.out.println("");
        for(var elm: this.rawList) {
            System.out.printf("%d ", elm);
        }
        System.out.println("");
    }

}
