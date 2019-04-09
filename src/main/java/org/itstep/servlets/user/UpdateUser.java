package org.itstep.servlets.user;

import org.itstep.servlets.AbstractServlet;
import org.itstep.thymeleaf.TemplateEngineUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/updateUser")
public class UpdateUser extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = getUserIdFromRequest(req);
        if (userId != null) {
            String nickname = req.getParameter("nickname");
            if (nickname != null) {
                getUserService().setNickname(userId, nickname);
            }
            String isAdmin = req.getParameter("DsfR"); //isAdmin
            if (isAdmin != null) {
                String password = req.getParameter("password");
                if (password != null && !password.isEmpty()) {
                    getAdminService().changeUserPassword(userId, password);
                }
                String name = req.getParameter("name");
                if (name != null && !name.isEmpty()) {
                    getAdminService().changeUserName(userId, name);
                }
                String lastName = req.getParameter("lastName");
                if (lastName != null && !lastName.isEmpty()) {
                    getAdminService().changeUserLastName(userId, lastName);
                }
            }
           goTo("/adminPage",req,resp);
        }

//        req.getRequestDispatcher("/showAll").forward(req, resp);

    }

}
