
public class Ticket {

    private String t_id; // Member아이디와 같은지 여부확인하기위해
    private String t_title; // 티켓에 표시될 영화제목
    private String t_intime; // 티켓에 표시될 시작시간
    private String t_outtime; // 티켓에 표시될 끝나는시간
    private int th_num;
    private int t_seat; // 티켓에 표시될 자리
    private int t_num; // 티켓에 표시될 예매번호
    private int t_money; // 티켓에 표시될 금액

    public Ticket() {}

    public Ticket(String t_id, String t_title, String t_intime, String t_outtime,int t_num,
                  int t_money, int th_num,int t_seat) {

        this.t_id = t_id;
        this.t_title = t_title;
        this.t_intime = t_intime;
        this.t_outtime = t_outtime;
        this.th_num = th_num;
        this.t_seat = t_seat;
        this.t_num = t_num;
        this.t_money = t_money;
    }



    public int getTh_num() {
        return th_num;
    }

    public void setTh_num(int th_num) {
        this.th_num = th_num;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getT_title() {
        return t_title;
    }

    public void setT_title(String t_title) {
        this.t_title = t_title;
    }

    public String getT_intime() {
        return t_intime;
    }

    public void setT_intime(String t_intime) {
        this.t_intime = t_intime;
    }

    public String getT_outtime() {
        return t_outtime;
    }

    public void setT_outtime(String t_outtime) {
        this.t_outtime = t_outtime;
    }

    public int getT_seat() {
        return t_seat;
    }

    public void setT_seat(int t_seat) {
        this.t_seat = t_seat;
    }

    public int getT_num() {
        return t_num;
    }

    public void setT_num(int t_num) {
        this.t_num = t_num;
    }

    public int getT_money() {
        return t_money;
    }

    public void setT_money(int t_money) {
        this.t_money = t_money;
    }


}