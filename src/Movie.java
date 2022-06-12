
public class Movie {

    private String title;
    private String intime;
    private String runtime;
    private int money;
    private int Th_num;
    private boolean check;
    private String[] theater = {	"[ 0  ]","[ 1  ]","[ 2  ]","[ 3  ]","[ 4  ]","[ 5  ]","[ 6  ]","[ 7  ]","[ 8  ]","[ 9  ]",
            "[ 10 ]","[ 11 ]","[ 12 ]","[ 13 ]","[ 14 ]","[ 15 ]","[ 16 ]","[ 17 ]","[ 18 ]","[ 19 ]",
            "[ 20 ]","[ 21 ]","[ 22 ]","[ 23 ]","[ 24 ]","[ 25 ]","[ 26 ]","[ 27 ]","[ 28 ]","[ 29 ]",
            "[ 30 ]","[ 31 ]","[ 32 ]","[ 33 ]","[ 34 ]","[ 35 ]","[ 36 ]","[ 37 ]","[ 38 ]","[ 39 ]",
            "[ 40 ]","[ 41 ]","[ 42 ]","[ 43 ]","[ 44 ]","[ 45 ]","[ 46 ]","[ 47 ]","[ 48 ]","[ 49 ]"};

    public Movie() {
        // TODO Auto-generated constructor stub
    }

    public Movie(String title, String intime, String runtime, int money, int Th_num, boolean check) {
        this.title = title;
        this.intime = intime;
        this.runtime = runtime;
        this.money = money;
        this.Th_num = Th_num;
        this.check = check;
    }


    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntime() {//
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String outtime) {
        this.runtime = outtime;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


}