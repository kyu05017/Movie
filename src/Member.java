
public class Member {

    private String id;
    private String pw;
    private String name;
    private String phone;
    private boolean check;

    public Member() {
        // TODO Auto-generated constructor stub
    }

    public Member(String id, String pw, String name, String phone, boolean check) {
        super();
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phone = phone;
        this.check = check;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    //
    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }


}