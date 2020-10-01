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

@WebServlet(name = "ChatServlet")
public class ChatServlet extends HttpServlet {
    private String mymsg;

    public void init() throws ServletException {
        mymsg = "servlet demo";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //set response content type
        response.setContentType("text/html");

        //get message that was sent
        String userMessage = request.getParameter("message");

        //send response to front-end
        RequestDispatcher dispatcher = request.getRequestDispatcher("testing.jsp");
        request.setAttribute("out", userMessage);
        dispatcher.forward(request, response);
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
