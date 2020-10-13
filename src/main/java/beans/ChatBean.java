package beans;

import java.util.ArrayList;
import java.util.List;

public class ChatBean implements java.io.Serializable {
    private int id;
    private String msg;
    private String username;
    private String date;
    private String clear = "False";
    private static List<String> db = new ArrayList<String>();


    public ChatBean(){
        msg = new String();
    }

    public void setId(int id){this.id=id;}
    public int getId(){return id;}

    public void setMsg(String msg){
        this.msg = msg;
        this.db.add("Date: " + date + "Username: " + username + "Message: " + this.msg);
    }
    public String getMsg(){return msg;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setClear(String clear) {
        this.clear = clear;
    }

    public String getClear() {
        return clear;
    }

    public void setdb(List<String> in){this.db = in;}
    public List<String> getDb(){return this.db;}

    public String printDb(){
        String out = "";
        String temp = "";

        if (clear.equals("False")) {
            for(int i=0; i<db.size(); i++){
                temp = db.get(i);

                String userName = temp.substring(temp.indexOf("Username: "), temp.indexOf("Message: "));
                userName = userName.replace("Username: ", "");

                String userMsg = temp.substring(temp.indexOf("Message: "));
                userMsg = userMsg.replace("Message: ", "");

                String userDate = temp.substring(0, temp.indexOf("Username:"));
                userDate = userDate.replace("Date: ", "");

                if (userMsg.length() > 0) {
                    out += "<div class=\"card\"><h4>" + userName + "</h4><div class=\"card-body\">" + userMsg + "</div><div class=\"card-date\">" + userDate + "</div><hr></div>";
                }
            }
        }else {
            db.clear();
        }

        return out;
    }
}
