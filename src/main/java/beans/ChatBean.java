package beans;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatBean implements java.io.Serializable {
    private int id;
    private String msg;
    private String username;
    private String date;
    private static String clear = "False";
    private String dateRange;
    private static String fromDate;
    private static String toDate;

    private static List<String> db = new ArrayList<String>();
    //private static List<Date> dateDB = new ArrayList<Date>();


    public ChatBean(){
        msg = new String();
    }

    public void setId(int id){this.id=id;}
    public int getId(){return id;}

    public void setMsg(String msg){
        if (msg.length() > 0) {
            this.msg = msg;
            this.db.add("Date: " + date + " Username: " + username + " Message: " + this.msg);
            DateFormat format = new SimpleDateFormat("MMMM d, yyyy");
            Date Tdate = null;
        }
/*
        try {
            Tdate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.dateDB.add(Tdate);*/
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

    public static String getFromDate() {
        return fromDate;
    }

    public void setToDate(String date) {
        toDate = date;
    }

    public static String getToDate() {
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

    //public void setDateDB(List<Date> in){this.dateDB = in;}
    //public List<Date> getDateDB(){return this.dateDB;}

    public static String printDb(){
        String out = "";
        String temp = "";

        if (clear.equalsIgnoreCase("False")) {
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

    public static String printTxt(String from, String To){
        String out = "";
        if(from == null || from.isEmpty()) from = "00-00-0000 00:00:00";
        if(To == null || To.isEmpty()) To = "00-00-2050 00:00:00";
        int Sindex = getDateIndex(from,true,db);
        int Eindex = getDateIndex(To,false,db);
        if(Sindex == -1 || Eindex == -1) return out;

        for(int i = Sindex ; i <= Eindex; i++){
            out += db.get(i) + "\n";
        }
        return out;
    }

    //fromTo = true gives the starting index, fromTo = false give the ending index
    private static int getDateIndex(String Date ,boolean fromTo, List<String> db){

        String msg;
        String[] sUserDate = Date.split("(-)|(\\ )");
        String[] sUserTime = sUserDate[3].split(":");
        String[] sMsgDate;
        String[] sMsgTime;

        Date userDate =null;
        Date userTime = null;
        Date msgDate = null;
        Date msgTime = null;

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");

        try {
            userDate = dateFormatter.parse(sUserDate[0] + '-' + sUserDate[1] + '-' + sUserDate[2] + ' ' + sUserDate[3]);
            //userTime = timeFormatter.parse(sUserTime[0] + ':' + sUserTime[1] + ':' + sUserTime[2] );
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for(int i =0; i < db.size(); i++){
            msg = db.get(i);

            // [0]=day [1]=month [2]=year [3]=time
            sMsgDate = msg.split("Username")[0].substring(6).split("(-)|(\\ )");

            // [0]=hour/24 [1]=min [2]=sec
           // sMsgTime = sMsgDate[3].split(":");

            try {
                msgDate = dateFormatter.parse(sMsgDate[0] + '-' + sMsgDate[1] + '-' + sMsgDate[2] + ' ' + sMsgDate[3]);
               // msgTime = timeFormatter.parse(sMsgTime[0] + ':' + sMsgTime[1] + ':' + sMsgTime[2] );
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //if we're looking for the TO range, and we hit the end of the list, return the last index
            if(i == db.size() -1 && !fromTo) return i;

            // if userDate > msgDate
            if(userDate.compareTo(msgDate) > 0){
                continue;
            }else{
               if(fromTo){
                   return i;
               }else{
                   return i-1;
               }
            }



          /* if(fromTo){
               if(userDate.compareTo(msgDate) > 0){
                   continue;
               }else{
                    return i ;
               }
           }else{
               if(userDate.compareTo(msgDate) > 0){
                   continue;
               }else{
                   return i -1;
               }
           }*/
        }
        return -1;
    }
}
