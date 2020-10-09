import sun.misc.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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

            //getting date of the message
            LocalDateTime dateTimeObj = LocalDateTime.now();
            DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String date = dateTimeObj.format(formatObj);

            //clear or download message
            String chatAction = request.getParameter("chatAction");
            if (chatAction.equals("Clear")) {
                request.setAttribute("clear", "True");
            }else {
                request.setAttribute("clear", "False");
            }

            //send response to front-end
            RequestDispatcher dispatcher = request.getRequestDispatcher("testing.jsp");
            request.setAttribute("msg", userMessage);
            request.setAttribute("username", userName);
            request.setAttribute("date", date);
            dispatcher.forward(request, response);
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
