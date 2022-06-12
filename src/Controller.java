
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

public class Controller {

    // 회원리스트
    static ArrayList<Member> memberlist = new ArrayList<>();
    // 영화리스트
    static ArrayList<Movie> movielist = new ArrayList<>();
    // 티켓리스트
    static ArrayList<Ticket> ticketlist = new ArrayList<>();
    // 관리스트


    Scanner scanner = new Scanner(System.in);
    DB db = new DB();
    //회원 시스템 ////////////////////////////////////////////////////////////////////////

    public int sign (String id, String pw,String name, String phone) {

        Member member = new Member( id, pw, name, phone,false);
        memberlist.add(member);
        db.memberSave();
        return 1;
    }

    public String login(String id,String pw) {


        for(Member temp : memberlist) {
            if(temp!=null) {
                if(id.equals("admin") && temp.getPw().equals(pw)) {
                    return "admin";
                }
                else if(temp.getId().equals(id)&&temp.getPw().equals(pw)) {
                    return temp.getId();
                }

            }

        }
        return "false";
    }


    public String findid(String name, String phone) {//아이디찾기

        for(Member temp : memberlist) {
            if(temp!=null && temp.getName().equals(name) && temp.getPhone().equals(phone)) {
                return temp.getId();
            }
        }
        return "false";
    }

    public String findpw(String id,String phone) {
        for(Member temp : memberlist) {
            if(temp!=null && temp.getId().equals(id) && temp.getPhone().equals(phone)) {
                return temp.getPw();
            }
        }
        return "false";
    }

    public void memberInfo(String id) {
        System.out.println("회원 정보))");
        for(Member temp : memberlist) {
            if(temp.getId().equals(id)) {
                System.out.println("-------------------------------");
                System.out.println("아이디 : " + temp.getId());
                System.out.println("이름  : " + temp.getName());
                System.out.println("번호  : " + temp.getPhone());
                System.out.println("-------------------------------");
                System.out.println("1)비밀번호 변경  2)회원탈퇴  3)뒤로가기");
                String work = scanner.next();

                if(work.equals("1")) {
                    boolean result = changepw(id);
                    if(result) {
                        System.out.println("메세지)) 비밀번호 변경완료");
                    }
                    else {
                        System.out.println("메세지)) 비밀변호 변경실패");
                    }
                }
                else if(work.equals("2")) {
                    singOut(id);
                }
                else if(work.equals("3")) {
                    System.out.println("메세지)) 이전으로 돌아갑니다.");
                }

            }

        }


    }

    public boolean changepw(String id) {

        System.out.println("비밀번호 변경)) ");
        System.out.println("비밀번호 : ");
        String pw = scanner.next();
        String new_pw = null;

        for(Member temp : memberlist) {
            if(temp.getId().equals(id) && temp.getPw().equals(pw)) {
                while(true) {
                    int pass = 0;
                    System.out.println("변경할 비밀번호 입력 : ");
                    new_pw = scanner.next();
                    if(new_pw.length() < 7) {
                        System.out.println("메세지)) 비밀번호의 길이는 8자리 이상이여야 합니다.");
                        pass = 0;
                    }
                    else if(new_pw.equals(temp.getPw())) {
                        System.out.println("메세지)) 이전 비밀번호와 동일합니다.");
                        pass = 0;
                    }
                    else {
                        pass = 1;
                    }
                    if(pass == 1) {
                        break;
                    }
                }
                temp.setPw(new_pw);
                db.memberSave();
                return true;
            }
        }
        return false;
    }

    public void singOut(String id)  {
        System.out.println("회원 탈퇴))");
        System.out.println("비밀번호 입력 : ");
        String pw = scanner.next();

        for(Member temp : memberlist) {

            if(temp.getId().equals(id) && temp.getPw().equals(pw)) {
                System.out.println("메세지)) 정말 탈퇴하시겠습니까?");
                System.out.println("1)네     2)아니요");
                String work = scanner.next();
                if(work.equals("1") || work.equals("네")) {
                    memberlist.remove(temp);
                    break;
                }
                else if (work.equals("2") || work.equals("아니요")) {
                    System.out.println("이전 메뉴로 돌아갑니다.");
                    break;
                }

            }
        }
        db.memberSave();
        return ;
    }

    //영화시스템///////////////////////////////////////////////////////////////////////////

    public void reserve(String id,String title,String intime,String runtime,int money,int seat,int Th_num) {
        Random random = new Random();
        int ticket_number = random.nextInt(99999999)+10000000;
        DecimalFormat df2 = new DecimalFormat("#,##0원");
        String new_money = df2.format(money);
        System.out.println("-----------예매 정보----------");
        System.out.println("사용자id : " +id);
        System.out.println("영화제목 : "+title);
        System.out.println("상영관  : " + Th_num);
        System.out.println("시작시간 : "+intime);
        System.out.println("러닝타임 : "+runtime);
        System.out.println("금액 : " +new_money);
        System.out.println("자리 : "+seat);
        System.out.println("예매번호 : "+ticket_number);
        System.out.println("----------------------------");
        String[] new_intime = intime.split(":");
        String[] new_runtime = runtime.split(":");
        int intime_hour = Integer.parseInt(new_intime[0]);
        int intime_min = Integer.parseInt(new_intime[1]);
        int runtime_hour = Integer.parseInt(new_runtime[0]);
        int runtime_min = Integer.parseInt(new_runtime[1]);
        int outhour = (intime_hour+runtime_hour);
        int outmin = (intime_min+runtime_min);
        DecimalFormat df = new DecimalFormat("00");
        String out1 = df.format(outhour);
        String out2 = df.format(outmin);
        String outtime = out1+":"+out2;

        Ticket ticket = new Ticket(id, title, intime, outtime, ticket_number,money,Th_num,seat);


        ticketlist.add(ticket);

        db.ticketSave();
    }

    public void myreserve(int num) {

        ticketlist.remove(num);
        db.ticketSave();
        return;
    }
    //관리자 시스템/////////////////////////////////////////////////////////////////////////

    public void moive_register (String title, String intime, String runtime, int num) {

        String [] startTime = intime.split(":");

        int startHour = Integer.parseInt(startTime[0]);

        int money = 0;

        if (startHour <= 10) {
            System.out.println(startHour);
            money = 5000;
        }else {
            money = 7000;
        }
        Movie movie = new Movie(title, intime, runtime , money, num , true);

        movielist.add(movie);
        db.movieSave();
        System.out.println("영화 저장 완료");

    }


    public void movie_remove (int num) {

        System.out.println("메세지)) 정말 삭제하시겠습니까?");
        System.out.println("1)네  2)아니요");
        String work = scanner.next();

        if(work.equals("1") || work.equals("네")) {
            num -= 1;
            movielist.remove(num);
            db.movieSave();
        }
        else if(work.equals("2") || work.equals("아니요")) {

        }

    }

    public void sale() {

        try {
            //매출액 출력
            System.out.println("--------------------매출액----------------------");
            //영화별 매출액 표시
            Hashtable <String, Integer> map = new Hashtable<>();
            DecimalFormat df2 = new DecimalFormat("#,##0원");

            for (int i = 0; i < ticketlist.size(); i++) {
                int ticketfee = 0;
                for (int j = 0; j < ticketlist.size(); j++) {
                    if (ticketlist.get(i).getT_title().equals(ticketlist.get(j).getT_title())) {
                        ticketfee += ticketlist.get(i).getT_money();
                    }
                }
                map.put(ticketlist.get(i).getT_title(), ticketfee);
            }

            for(String temp : map.keySet()) {
                String new_money = df2.format(map.get(temp));
                System.out.println("영화 "+temp+ " " + new_money);
            }
            //총 매출액 표시
            int totalsales = 0;
            for (int i = 0; i < ticketlist.size(); i++) {
                totalsales += ticketlist.get(i).getT_money();
            }
            String new_money = df2.format(totalsales);
            System.out.println("총 매출액 : " + new_money);
            System.out.println("---------------------------------------------");

        }//try end
        catch(Exception e) {

        }
    }

}