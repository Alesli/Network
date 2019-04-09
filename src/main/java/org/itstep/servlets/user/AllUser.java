package org.itstep.servlets.user;

import org.itstep.servlets.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/showAll")
public class AllUser extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getAdminService().getAllUsers().forEach(user -> {
            try {
                resp.getWriter().println(user.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
