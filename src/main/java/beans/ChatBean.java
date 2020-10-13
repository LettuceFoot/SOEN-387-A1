package beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatBean implements java.io.Serializable {
    private int id;
    private String msg;
    private String username;
    private String date;
    private String dateRange;
    private String fromDate;
    private String toDate;
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

    public void setDateRange(String date) {
        this.dateRange = date;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setFromDate(String date) {
        fromDate = date;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setToDate(String date) {
        toDate = date;
    }

    public String getToDate() {
        return toDate;
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

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date fromDateFormat = null;
                Date toDateFormat = null;
                Date userDateFormat = null;

                try {
                    if (fromDate.length() != 0)
                        fromDateFormat = simpleDateFormat.parse(getFromDate());
                    if (toDate.length() != 0)
                        toDateFormat = simpleDateFormat.parse(getToDate());
                    userDateFormat = simpleDateFormat.parse(userDate);
                } catch(ParseException e) {
                    System.out.println("help me please");
                }

                if (userMsg.length() > 0) {
                    if (toDate.length() == 0 && fromDate.length() == 0) {
                        out += "<div class=\"card\"><h4>" + userName + "</h4><div class=\"card-body\">" + userMsg + "</div><div class=\"card-date\">" + userDate + "</div><hr></div>";
                    }

                    if (toDate.length() != 0 && fromDate.length() == 0) {
                        if (userDateFormat.before(toDateFormat)){
                            out += "<div class=\"card\"><h4>" + userName + "</h4><div class=\"card-body\">" + userMsg + "</div><div class=\"card-date\">" + userDate + "</div><hr></div>";
                        }
                    }

                    if (toDate.length() == 0 && fromDate.length() != 0) {
                        if (userDateFormat.after(fromDateFormat)){
                            out += "<div class=\"card\"><h4>" + userName + "</h4><div class=\"card-body\">" + userMsg + "</div><div class=\"card-date\">" + userDate + "</div><hr></div>";
                        }
                    }

                    if (toDate.length() != 0 && fromDate.length() != 0){
                        if (userDateFormat.after(fromDateFormat) && userDateFormat.before(toDateFormat)){
                            out += "<div class=\"card\"><h4>" + userName + "</h4><div class=\"card-body\">" + userMsg + "</div><div class=\"card-date\">" + userDate + "</div><hr></div>";
                        }
                    }
                }
            }
        }else {
            db.clear();
        }

        return out;
    }
}
