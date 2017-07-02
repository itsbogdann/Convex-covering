

import java.util.Comparator;

/**
 * Created by user on 03-Jan-16.
 */

public class Punct {
    public static double stanga = 99999999.0, jos = 99999999.0;

    double x;
    double y;

    public Punct(double a, double b) {
        this.x = a;
        this.y = b;
    }
}
//sortare dupa panta
class PunctComparator implements Comparator<Punct> {
    public int compare(Punct a, Punct b) {
        return (int)((a.y - Punct.stanga) * (b.x - Punct.jos) - (a.x - Punct.jos) * (b.y - Punct.stanga));
    }
}
    /*
    public int compareTo(Punct b) {
        return (this.y - stanga) * (b.x - jos) - (this.x - jos) * (b.y - stanga);
    }
}*/

