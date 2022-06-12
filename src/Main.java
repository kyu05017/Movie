
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) { // 최종

        try (Scanner scanner = new Scanner(System.in)) {
            Main main = new Main();
            Controller con = new Controller();


            DB db = new DB();
            db.memberLoad();
            db.movieLoad();
            db.ticketLoad();

            while(true) {
                try {
                    ////상영영화목록////
                    Date date = new Date();
                    SimpleDateFormat nowdate = new SimpleDateFormat("yyyy - MM - dd | HH : mm");
                    String nowInTime = nowdate.format(date);
                    System.out.println("E.zen 영화관 홈페이지))");
                    System.out.println("--------------------------------------------------------");
                    System.out.println("|\t현재 시간 : "+ nowInTime+ "\t\t|");
                    System.out.println("--------------------------------------------------------");
                    System.out.printf("| %s\t%s\t\t| %s | %s | %s\n","관","상영작","시작 시간","종료 시간","가격");
                    System.out.println("--------------------------------------------------------");
                    for(Movie movie : Controller.movielist) {

                        SimpleDateFormat nowtime = new SimpleDateFormat("HH:mm");
                        String nowTime = nowtime.format(date);
                        String[] now_intime = nowTime.split(":");
                        int nowtime_hour = Integer.parseInt(now_intime[0]);
                        int nowtime_min = Integer.parseInt(now_intime[1]);
                        String[] new_intime = movie.getIntime().split(":");
                        String[] new_runtime = movie.getRuntime().split(":");
                        int intime_hour = Integer.parseInt(new_intime[0]);
                        int intime_min = Integer.parseInt(new_intime[1]);
                        int runtime_hour = Integer.parseInt(new_runtime[0]);
                        int runtime_min = Integer.parseInt(new_runtime[1]);
                        int outhour = (intime_hour+runtime_hour);
                        int outmin = (intime_min+runtime_min);
                        if(outmin > 60) {
                            outmin -= 60;
                            outhour += 1;
                        }
                        DecimalFormat df = new DecimalFormat("00");
                        String out1 = df.format(outhour);
                        String out2 = df.format(outmin);
                        String outtime = out1+":"+out2;

                        DecimalFormat df2 = new DecimalFormat("#,##0원");
                        String new_money = df2.format(movie.getMoney());
                        /////if 영화상영시간이 현재시간에 포함되어있으면

                        if(nowtime_hour >= outhour || nowtime_hour == outhour && nowtime_min<outmin) {
                            System.out.printf("| %s\t%s\t\t| %s |\n",movie.getTh_num(),movie.getTitle(),"상영종료");
                            movie.setCheck(false);
                        }
                        else if(nowtime_hour >= intime_hour || nowtime_hour == outhour && nowtime_min<outmin) {
                            System.out.printf("| %s\t%s\t\t| %s | %s | %s |\n",movie.getTh_num(),movie.getTitle(),"상영중",outtime,new_money);
                            movie.setCheck(false);
                        }
                        else {
                            System.out.printf("| %s\t%s\t\t| %s | %s | %s |\n",movie.getTh_num(),movie.getTitle(), movie.getIntime(),outtime,new_money);
                        }

                    }
                    System.out.println("--------------------------------------------------------");
                    System.out.println("메뉴)) ");
                    System.out.println("1.회원가입 2.로그인 3.아이디찾기 4.비밀번호찾기 5.종료");
                    System.out.println("선택 : ");
                    String ch = scanner.next();

                    if(ch.equals("1") || ch.equals("회원가입")) {

                        String id = null;
                        String pw = null;
                        String phone = null;
                        int allpass = 0;

                        while(true) {
                            System.out.println("아이디: ");
                            id = scanner.next();
                            int pass = 1;
                            for(Member temp : Controller.memberlist) {
                                if (temp !=null &&  temp.getId().equals(id)) {
                                    //만약에 공백이 아니면서 배열내 id와 입력받은 id와 동일하면
                                    System.out.println("메세지)) 현재 사용중인 아이디입니다.");
                                    pass = 0;
                                    break; //함수종료 --->아이디중복으로 회원가입 실패
                                }
                            }
                            if(pass == 1) {
                                allpass += 1;
                                break;
                            }
                        }

                        while(true) {
                            int pass = 0;
                            System.out.println("비밀번호: ");
                            pw = scanner.next();
                            if(pw.length() < 8) {
                                System.out.println("메세지)) 비밀번호의 길이는 8자리 이상이여야 합니다.");
                                pass = 0;
                            }
                            else {
                                pass = 1;
                            }
                            if(pass == 1) {
                                allpass += 1;
                                break;
                            }
                        }

                        System.out.println("이름: ");
                        String name = scanner.next();

                        while(true) {
                            int pass = 0;
                            System.out.println("전화번호: ");
                            phone = scanner.next();

                            if(phone.length() >= 12 || phone.length() < 10) {
                                System.out.println("메세지)) 잘못된 형식의 번호입니다.");
                                pass = 0;
                            }
                            else {
                                pass = 1;
                                for(Member temp : Controller.memberlist) {
                                    if (temp !=null &&  temp.getPhone().equals(phone)) {
                                        System.out.println("메세지)) 이미 등록된 전화번호입니다.");
                                        pass = 0;
                                        break;
                                    }
                                }
                            }
                            if(pass == 1) {
                                allpass += 1;
                                break;
                            }
                        }

                        if(allpass == 3) {
                            int result = con.sign(id, pw,name,phone);

                            if(result == 1) {
                                System.out.println("메세지)) 회원가입 성공");
                            }
                        }
                        else {
                            System.out.println("메세지)) 회원가입 실패");
                        }
                    }
                    else if(ch.equals("2") || ch.equals("로그인")) {
                        System.out.println("아이디: ");
                        String id = scanner.next();
                        System.out.println("비밀번호: ");
                        String pw = scanner.next();

                        String result = con.login(id, pw); // 리턴값 String이니깐 String 변수 result생성

                        if(result.equals("admin")) {
                            System.out.println("메세지))관리자 로그인");
                            main.adminmenu();
                        }
                        else if(result.equals("false")) {
                            System.out.println("메세지)) 로그인 실패");
                        }
                        else {
                            System.out.println("메세지)) "+result + "님 환영 합니다.");
                            main.membermenu(result);
                        }
                    }
					/*
					else if(ch.equals("3") || ch.equals("비회원예매")){
						System.out.println("메세지)) 프로그램을 종료 합니다.");
					}
					*/
                    else if(ch.equals("3") || ch.equals("아이디찾기")) {
                        System.out.println("이름: ");
                        String name = scanner.next();
                        System.out.println("전화번호: ");
                        String phone = scanner.next();

                        String result = con.findid(name,phone);

                        if(result.equals("false")) {
                            System.out.println("메세지)) 존재하지 않는 회원입니다.");
                        }
                        else {
                            System.out.println("메세지)) 회원님의 아이디는 "+ result + " 입니다.");
                        }
                    }
                    else if(ch.equals("4") || ch.equals("비밀번호찾기")){
                        System.out.println("아이디: ");
                        String id = scanner.next();
                        System.out.println("전화번호: ");
                        String phone = scanner.next();

                        String result = con.findpw(id,phone);

                        if(result.equals("false")) {
                            System.out.println("메세지)) 존재하지 않는 회원입니다.");
                        }
                        else {
                            System.out.println("메세지)) 회원님의 비밀번호는 "+ result + " 입니다.");
                        }
                    }
                    else if(ch.equals("5") || ch.equals("종료")){
                        System.out.println("메세지)) 프로그램을 종료 합니다.");
                        break;
                    }
                    else {System.out.println("메세지)) 존재하지 않는 메뉴 입니다.");
                    }
                }
                catch(Exception e) {}
            }
        }
    }

    Scanner scanner = new Scanner(System.in);

    public void membermenu(String id) {

        Controller con = new Controller();

        while(true) {
            System.out.println("회원메뉴))");
            System.out.println("1.예매 2.예매확인 3.회원정보 4.로그아웃");
            System.out.println("선택 : ");
            String ch = scanner.next();

            if(ch.equals("1")||ch.equals("예매")) {

                Theater theater = new Theater();
                Theater theater2 = new Hall();


                System.out.println("-------------예매 가능한 영화-----------");
                System.out.println("번호\t영화제목\t\t영화상영시간");
                int i=0;
                for(Movie movie : Controller.movielist) {
                    if(movie.isCheck()) {
                        System.out.printf("%d\t%s\t\t%s \n",(i+1), movie.getTitle(),movie.getIntime());
                    }
                    i++;
                }
                System.out.println("-------------------------------------");
                System.out.println("영화선택: ");
                int index = scanner.nextInt();
                index -= 1;
                if(index > Controller.movielist.size() || index < 0) {
                    System.out.println("메세지)) 존재하지 않는 영화 입니다.");
                }
                else if(Controller.movielist.get(index).isCheck() == false) {
                    System.out.println("메세지)) 예매할수 없는 영화 입니다.");
                }
                else {
                    System.out.println("-------------------------------------");
                    for(Movie temp : Controller.movielist) {
                        if (temp.getTh_num() == 1) {
                            for(Ticket temp2 : Controller.ticketlist) {
                                if(Controller.movielist.get(index).getTitle().equals(temp2.getT_title())) {
                                    if(temp2.getTh_num() == temp.getTh_num()) {
                                        if(Controller.movielist.get(index).getIntime().equals(temp2.getT_intime())) {
                                            theater2.getTheater()[temp2.getT_seat()] = "[ X  ]";
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (Controller.movielist.get(index).getTh_num() == 1) {
                        for(int a=0; a < theater2.getTheater().length; a++) {
                            System.out.print(theater2.getTheater()[a]);
                            if(a%10==9) {
                                if(a==9) {
                                    System.out.println("A열");
                                }
                                if(a==19) {
                                    System.out.println("B열");
                                }
                                if(a==29) {
                                    System.out.println("C열");
                                }
                                if(a==39) {
                                    System.out.println("D열");
                                }
                                if(a==49) {
                                    System.out.println("E열");
                                }
                            }
                        }
                    }


                    System.out.println("-------------------------------------");

                    System.out.println("좌석선택: ");
                    int seat = scanner.nextInt();
                    boolean pass = true;
                    String Tseat = null;
                    if(seat < 50) {
                        for(int p=0; p < theater.getTheater().length;p++) {
                            if(theater2.getTheater()[seat].equals("[ X  ]")) {
                                System.err.println("메세지)) 이미 선택된 좌석입니다.");
                                pass = false;
                                break;
                            }
                            else {
                                theater.getTheater()[seat] = "[ X  ]";
                                if(seat<=9) {
                                    Tseat = "A열"+seat+"번";
                                    System.out.println("메세지)) "+Tseat+" 좌석이 선택되었습니다.");
                                    break;
                                }
                                else if(seat<=19) {
                                    Tseat = "B열"+seat+"번";
                                    System.out.println("메세지)) "+Tseat+" 좌석이 선택되었습니다.");
                                    break;
                                }
                                else if(seat<=29) {
                                    Tseat = "C열"+seat+"번";
                                    System.out.println("메세지)) "+Tseat+" 좌석이 선택되었습니다.");
                                    break;
                                }
                                else if(seat<=39) {
                                    Tseat = "D열"+seat+"번";
                                    System.out.println("메세지)) "+Tseat+" 좌석이 선택되었습니다.");
                                    break;
                                }
                                else if(seat<=49) {
                                    Tseat = "E열"+seat+"번";
                                    System.out.println("메세지)) "+Tseat+" 좌석이 선택되었습니다.");
                                    break;
                                }
                                else {System.err.println("메세지)) 해당 좌석이 없습니다.");
                                }
                            }
                        }
                        if(pass) {
                            DecimalFormat df2 = new DecimalFormat("#,##0원");
                            String new_money = df2.format(Controller.movielist.get(index).getMoney());
                            System.out.println("결제하실 금액: " + new_money);
                            System.out.println("1.결제 2.취소"); String payment = scanner.next();
                            if(payment.equals("1") || payment.equals("결제")) {
                                System.out.println("결제액: "); int money = scanner.nextInt();//// 입금액 입력
                                ///입금액이 요구하는 결제금액보다 적을 경우
                                if(Controller.movielist.get(index).getMoney() > money) {
                                    System.err.println("메세지)) 결제불가(결제액 부족)");
                                }
                                ///입금액이 더 많거나 같을경우 잔돈출력
                                else{
                                    String new_money2 = df2.format(Controller.movielist.get(index).getMoney());
                                    System.err.println(new_money2+ " 결제완료");
                                    String new_money3 = df2.format((money-Controller.movielist.get(index).getMoney()));
                                    System.out.println("잔돈출력: " +  new_money3);
                                    con.reserve(id,Controller.movielist.get(index).getTitle(),Controller.movielist.get(index).getIntime(),
                                            Controller.movielist.get(index).getRuntime(),Controller.movielist.get(index).getMoney(),seat,Controller.movielist.get(index).getTh_num());
                                }
                            }
                            else if(payment.equals("2") || payment.equals("취소")) {
                                theater.getTheater()[seat] = "[ "+seat+" ]";
                            }
                            else {
                                System.out.println("메세지)) 존재하지 않는 메뉴입니다.");
                            }
                        }

                    }
                    else {
                        System.out.println("메세지)) 존재하지 않는 좌석입니다.");
                    }
                }
            }
            else if(ch.equals("2")||ch.equals("예매확인")) {

                System.out.println("-------------예매목록-------------");
                System.out.println("번호\t영화제목\t예매번호\t\t좌석번호\t영화상영시간");
                int i=1;

                for(Ticket ticket : Controller.ticketlist) {
                    if(id.equals(ticket.getT_id())) {
                        String Tseat = null;
                        if(ticket.getT_seat()<=9) {
                            Tseat = "A열"+ticket.getT_seat()+"번";
                        }
                        else if(ticket.getT_seat()>9&&ticket.getT_seat()<=19) {
                            Tseat = "B열"+ticket.getT_seat()+"번";
                        }
                        else if(ticket.getT_seat()>19&&ticket.getT_seat()<=29) {
                            Tseat = "C열"+ticket.getT_seat()+"번";
                        }
                        else if(ticket.getT_seat()>29&&ticket.getT_seat()<=39) {
                            Tseat = "D열"+ticket.getT_seat()+"번";
                        }
                        else if(ticket.getT_seat()>39&&ticket.getT_seat()<=49) {
                            Tseat = "E열"+ticket.getT_seat()+"번";
                        }

                        System.out.printf("%d\t%s\t%d\t%s\t%s \n",(i), ticket.getT_title(),ticket.getT_num(),Tseat,ticket.getT_intime());
                        i++;
                    }
                }
                System.out.println("------------------------------");
                ////////////////
                System.out.println("1.예매취소 2.뒤로가기");
                String confirm = scanner.next();
                ////예매취소
                if(confirm.equals("1") || confirm.equals("예매취소")) {
                    DecimalFormat df2 = new DecimalFormat("#,##0원");
                    System.out.println("취소할 예매선택: ");
                    int index = scanner.nextInt();
                    index -= 1;
                    if(index  > Controller.ticketlist.size() ) {
                        System.out.println("메세지)) 존재하지 않는 영화 입니다.");
                    }
                    else {
                        for(Ticket ticket : Controller.ticketlist) {
                            if(Controller.ticketlist.get(index).getT_title().equals(ticket.getT_title())&&Controller.ticketlist.get(index).getT_intime().equals(ticket.getT_intime())) {
                                String new_money = df2.format(Controller.ticketlist.get(index).getT_money());
                                System.err.println(new_money+" 환불되었습니다.");
                                con.myreserve(index);
                                break;
                            }
                        }
                    }
                }
                ///뒤로가기
                else if(confirm.equals("2") || confirm.equals("뒤로가기")) {

                }
            }
            else if(ch.equals("3")||ch.equals("회원정보")) {
                con.memberInfo(id);
            }
            else if(ch.equals("4")||ch.equals("로그아웃")) {
                System.out.println("메세지)) 로그아웃 했습니다.");
                break;
            }
            else {
                System.out.println("메세지)) 존재하지 않는 메뉴 입니다.");
            }
        }
    }
    public void adminmenu() {

        Controller con = new Controller();

        while(true) {
            System.out.println("관리자 메뉴))");
            System.out.println("1.영화등록 2.영화삭제 3.매출확인 4.회원목록 5.예매목록 6.로그아웃");
            System.out.println("선택 : ");
            String ch =scanner.next();

            if(ch.equals("1")||ch.equals("영화등록")) {

                System.out.println("영화제목 : ");
                scanner.nextLine();
                String title = scanner.nextLine();
                System.out.println("상영할 관을 입력하세요.");
                int num = scanner.nextInt();

                if(num >= 4) {
                    System.out.println("메세지)) 존재하지 않는 상영관 입니다.");
                }
                else {
                    boolean pass1 = true;
                    int hour = 0;
                    while(true) {
                        System.out.println("시작 시간: ");
                        hour = scanner.nextInt();

                        if(hour > 23) {
                            System.out.println("메세지)) 다시 입력하세요");
                            pass1 = false;
                        }
                        else {
                            for(Movie temp : Controller.movielist) {
                                String [] intime = temp.getIntime().split(":");
                                int intime_hour = Integer.parseInt(intime[0]);
                                String [] runtime = temp.getRuntime().split(":");
                                int runtime_hour =Integer.parseInt(runtime[0]);
                                if(temp.getTh_num()==num) {
                                    if(intime_hour <= hour && (intime_hour+runtime_hour) >= hour) {
                                        System.err.println("메세지)) 해당 시간에 상영중인 영화가 있습니다.");
                                        pass1 = false;
                                        break;
                                    }
                                    else {
                                        pass1 = true;
                                    }
                                }
                                else {
                                    pass1 = true;
                                }
                            }
                        }
                        if(pass1) {
                            break;
                        }
                    }
                    DecimalFormat df = new DecimalFormat("00");
                    String start_hour = df.format(hour);

                    int min = 0;
                    boolean pass2 = true;
                    while(true) {
                        System.out.println("시작 분: ");
                        min = scanner.nextInt();
                        if(min > 59) {
                            System.out.println("메세지)) 다시 입력하세요");
                            pass2 = false;
                        }
                        else {
                            for(Movie temp : Controller.movielist) {
                                String [] intime = temp.getIntime().split(":");
                                int intime_hour = Integer.parseInt(intime[0]);
                                int intime_min = Integer.parseInt(intime[1]); ////String 영화시작시간(:)로 나눈후 int로 전환
                                String [] runtime = temp.getRuntime().split(":");
                                int runtime_hour =Integer.parseInt(runtime[0]);
                                int runtime_min =Integer.parseInt(runtime[1]); ///러닝타임 int로 전환 후 60분이면 +1시간
                                ////만약 기존에 있던 영화시작시간~영화시작+러닝시간 안에 상영시간을 등록하면 재입력
                                if(temp.getTh_num()==num) {
                                    if(intime_hour <= hour && (intime_hour + runtime_hour) >= hour && intime_min < min && ( intime_min + runtime_min ) > min) {
                                        System.err.println("메세지)) 해당 시간에 상영중인 영화가 있습니다.");
                                        pass2 = false;
                                        break;
                                    }
                                    else {
                                        pass2 = true;
                                    }
                                }
                                else {
                                    pass2 = true;
                                }
                            }
                            pass2 = true;
                        }
                        if(pass2) {
                            break;
                        }
                    }
                    df = new DecimalFormat("00");
                    String start_min = df.format(min);
                    String intime = start_hour+":"+start_min;


                    System.out.println("러닝타임(분) : ");
                    int time = scanner.nextInt();

                    int runhour = time/60;
                    int runmin = time%60;

                    df = new DecimalFormat("00");
                    String new_runhour = df.format(runhour);
                    String new_runmin = df.format(runmin);
                    String runtime = new_runhour+":"+new_runmin;
                    Boolean movierun_admin = true;
                    while(movierun_admin) {
                        for(Movie temp : Controller.movielist) {
                            String [] intimes = temp.getIntime().split(":");
                            int intime_hour = Integer.parseInt(intimes[0]); ////String 영화시작시간(:)로 나눈후 int로 전환
                            int intime_min = Integer.parseInt(intimes[1]);
                            String [] runtimes = temp.getRuntime().split(":");
                            int runtime_hour =Integer.parseInt(runtimes[0]); ///러닝타임 int로 전환 후 60분이면 +1시간
                            int runtime_min =Integer.parseInt(runtimes[1]);

                            ////만약 기존에 있던 영화시작시간~영화시작+러닝시간 안에 상영시간을 등록하면 재입력
                            if(temp.getTh_num()==num) {
                                if(hour+runhour>=intime_hour&&min+runmin>intime_min&&hour+runhour<=intime_hour+runtime_hour&&min+runmin<intime_min+runtime_min) {
                                    System.err.println("해당 시간에 상영중인 영화가 있습니다.");
                                    System.out.println("다시 입력해주세요.");
                                    System.out.println("러닝타임(분) : ");
                                    time = scanner.nextInt();
                                    runhour = time/60;
                                    runmin = time%60;
                                    new_runhour = df.format(runhour);
                                    new_runmin = df.format(runmin);
                                    runtime = new_runhour+":"+new_runmin;
                                }
                                else {
                                    movierun_admin = false;
                                }
                            }
                            else {
                                movierun_admin = false;
                            }
                        }
                        if (runtime.length() != 5) {
                            System.out.println("다시 입력해주세요.");
                            System.out.println("러닝타임(분) : ");
                            time = scanner.nextInt();

                            runhour = time/60;
                            runmin = time%60;
                            new_runhour = df.format(runhour);

                            new_runmin = df.format(runmin);

                            runtime = new_runhour+":"+new_runmin;
                        }else {
                            movierun_admin = false;
                        }
                    }
                    con.moive_register(title, intime, runtime,num);
                }
            }
            else if(ch.equals("2")||ch.equals("영화삭제")){
                System.out.println("영화 삭제))");
                System.out.println("현재 영화 목록))----------------------------------");
                int i = 0;
                for(Movie temp : Controller.movielist) {
                    System.out.println((i+1)+" "+temp.getTitle());
                    i++;
                }
                System.out.println("----------------------------------------------");
                System.out.println("번호입력 : ");
                int num = 0;
                while(true) {
                    boolean pass = true;
                    try {
                        num = scanner.nextInt();

                    }
                    catch(Exception e) {
                        System.out.println("메세지)) 잘못된 입력");
                        pass = false;
                    }
                    if(pass) {
                        break;
                    }
                }
                con.movie_remove(num);
            }
            else if(ch.equals("3")||ch.equals("매출확인")) {
                con.sale();
            }
            else if(ch.equals("4")||ch.equals("회원목록")) {
                for(Member temp : Controller.memberlist) {
                    System.out.println(temp.getId());
                }
            }
            else if(ch.equals("5")||ch.equals("예매목록")) {


                Theater theater = new Hall();

                System.out.println("-------------현재 상영중인 영화-----------");
                System.out.println("번호\t관\t영화제목\t\t영화상영시간");
                int i=0;
                for(Movie movie : Controller.movielist) {
                    System.out.printf("%d\t%s\t%s\t\t%s \n",(i+1),movie.getTh_num(),movie.getTitle(),movie.getIntime());
                    i++;
                }
                System.out.println("-------------------------------------");
                System.out.println("영화선택: ");

                int index = scanner.nextInt();
                index -= 1;
                if(index > Controller.movielist.size()) {
                    System.out.println("존재 하지않는 영화입니다.");
                }
                else {
                    for(Movie temp : Controller.movielist) {
                        if (temp.getTh_num() == 1) {
                            for(Ticket temp2 : Controller.ticketlist) {
                                if(Controller.movielist.get(index).getTitle().equals(temp2.getT_title())) {
                                    if(temp2.getTh_num() == temp.getTh_num()) {
                                        if(Controller.movielist.get(index).getIntime().equals(temp2.getT_intime())) {
                                            theater.getTheater()[temp2.getT_seat()] = "[ X  ]";
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (Controller.movielist.get(index).getTh_num() == 1) {
                        for(int a=0; a < theater.getTheater().length; a++) {
                            System.out.print(theater.getTheater()[a]);
                            if(a%10==9) {
                                if(a==9) {
                                    System.out.println("A열");
                                }
                                if(a==19) {
                                    System.out.println("B열");
                                }
                                if(a==29) {
                                    System.out.println("C열");
                                }
                                if(a==39) {
                                    System.out.println("D열");
                                }
                                if(a==49) {
                                    System.out.println("E열");
                                }
                            }
                        }
                    }
                }
            }
            else if(ch.equals("6")||ch.equals("로그아웃")) {
                System.out.println("메세지)) 로그아웃 했습니다.");
                break;
            }
            else {
                System.out.println("메세지)) 존재하지 않는 메뉴 입니다.");
            }
        }
    }
}