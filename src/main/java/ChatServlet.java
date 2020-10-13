// import sun.misc.Request;

import beans.ChatBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "ChatServlet")
public class ChatServlet extends HttpServlet {
    private String mymsg;

    public void init() throws ServletException {
        mymsg = "servlet demo";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //if the submit button with name "delete" was submitted, forward to the delete servlet
        if(request.getParameter("delete") != null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("DeleteServlet");
            dispatcher.forward(request, response);
        }else{
            //set response content type
            response.setContentType("text/html");

            //get message that was sent and username
            String userMessage = request.getParameter("message");
            String userName = request.getParameter("username");

            if (userName == null || userName == "") {
                userName = "anonymous";
            }

            System.out.println(userName);

            //getting date of the message
            LocalDateTime dateTimeObj = LocalDateTime.now();
            DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String date = dateTimeObj.format(formatObj);

            //clear or download message
            String chatAction = request.getParameter("chatAction");
            if (chatAction != null) {
                if (chatAction.equals("Download")) {
                    String fileName = "";
                    String fileExtension = ".txt";
                    String fileType = "text/plain";   //set txt by default

                    //we need a way to retreive the filetype the user requested (txt or xml)

                    // You must tell the browser the file type you are going to send
                    // for example application/pdf, text/plain, text/html, image/jpg
                    response.setContentType(fileType);

                    // Make sure to show the download dialog
                    String headerkey = "Content-disposition";
                    String headerVal = "attachment; filename=Chat." + fileExtension;
                    response.setHeader(headerkey, headerVal);

                    OutputStream out = response.getOutputStream();

                    ChatBean temp = new ChatBean();
                    String msg = temp.printDb();

                    out.write(msg.getBytes(Charset.forName("UTF-8")));
                    // Assume file name is retrieved from database
                    // For example D:\\file\\test.pdf

                    /*
                    File my_file = new File(fileName);

                    // This should send the file to browser
                    OutputStream out = response.getOutputStream();
                    FileInputStream in = new FileInputStream(my_file);
                    byte[] buffer = new byte[4096];
                    int length;
                    while ((length = in.read(buffer)) > 0){
                        out.write(buffer, 0, length);
                    }
                    in.close();*/
                    out.flush();
                    out.close();
                }

                if (chatAction.equals("Clear")) {
                    request.setAttribute("clear", "True");
                } else {
                    request.setAttribute("clear", "False");
                }
            }

            //checking status code for errors
            int status_code = response.getStatus();
            //get first digit of the status code
            while (status_code >= 10) {
                status_code /= 10;
            }

            if (status_code == 4 || status_code == 5) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
                request.setAttribute("error_message", "error encountered when attempting to post a message please login again");
                dispatcher.forward(request, response);
            }else {
                //send response to front-end
                RequestDispatcher dispatcher = request.getRequestDispatcher("mainpage.jsp");
                request.setAttribute("msg", userMessage);
                request.setAttribute("username", userName);
                request.setAttribute("date", date);
                dispatcher.forward(request, response);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChatApp</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChatApp at " + request.getContextPath() + "</h1>");
            out.println("<h1>" + mymsg + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException exception){
            exception.getMessage();
        }




    }
}
