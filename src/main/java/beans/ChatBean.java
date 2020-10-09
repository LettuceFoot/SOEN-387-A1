package beans;

import java.util.ArrayList;
import java.util.List;

public class ChatBean implements java.io.Serializable {
    private int id;
    private String msg;
    private List<String> db;


    public ChatBean(){
        msg = new String();
        db = new ArrayList<String>();

    }

    public void setId(int id){this.id=id;}
    public int getId(){return id;}

    public void setMsg(String msg){
        this.msg = msg;
        this.db.add(msg);
    }
    public String getMsg(){return msg;}

    public void setdb(List<String> in){this.db = in;}
    public List<String> getDb(){return this.db;}

    public String printDb(){
        String out = "";

        for(int i=0; i<db.size(); i++){
            out += db.get(i) + "<br>" ;
        }
        return out;
    }
}
