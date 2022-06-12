
public class Theater {

    private int Th_num;
    private String[] theater = {"[ 0  ]","[ 1  ]","[ 2  ]","[ 3  ]","[ 4  ]","[ 5  ]","[ 6  ]","[ 7  ]","[ 8  ]","[ 9  ]",
            "[ 10 ]","[ 11 ]","[ 12 ]","[ 13 ]","[ 14 ]","[ 15 ]","[ 16 ]","[ 17 ]","[ 18 ]","[ 19 ]",
            "[ 20 ]","[ 21 ]","[ 22 ]","[ 23 ]","[ 24 ]","[ 25 ]","[ 26 ]","[ 27 ]","[ 28 ]","[ 29 ]",
            "[ 30 ]","[ 31 ]","[ 32 ]","[ 33 ]","[ 34 ]","[ 35 ]","[ 36 ]","[ 37 ]","[ 38 ]","[ 39 ]",
            "[ 40 ]","[ 41 ]","[ 42 ]","[ 43 ]","[ 44 ]","[ 45 ]","[ 46 ]","[ 47 ]","[ 48 ]","[ 49 ]"};

    public Theater() {
        // TODO Auto-generated constructor stub
    }

    public Theater(int th_num, String[] theater) {
        Th_num = th_num;
        this.theater = theater;
    }

    public int getTh_num() {
        return Th_num;
    }

    public void setTh_num(int th_num) {
        Th_num = th_num;
    }

    public String[] getTheater() {
        return theater;
    }

    public void setTheater(String[] theater) {
        this.theater = theater;
    }

}