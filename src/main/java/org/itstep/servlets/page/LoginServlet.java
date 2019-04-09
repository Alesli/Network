package org.itstep.servlets.page;


import org.itstep.servlets.AbstractServlet;
import org.itstep.thymeleaf.TemplateEngineUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (getAdminService().getUserByLoginAndName(login, password) != null) {
            HttpSession session = req.getSession();
            session.setAttribute("isLogin", true);
            Long userId = getAdminService().getUserByLoginAndName(login, password).getId();
            String caption = getRoomService().getDefaultRoomCaption();
            goTo("/refresh?userId=" + userId + "&caption=" + caption, req, resp);

        } else {
            req.setAttribute("userNotFound", "User with login " + login + " not found");
            TemplateEngineUtil.toThymeleaf("/LoginPage.html", req, resp);
        }

    }
}
