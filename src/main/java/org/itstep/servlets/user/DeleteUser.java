package org.itstep.servlets.user;


import org.itstep.services.AdminService;
import org.itstep.servlets.AbstractServlet;
import org.itstep.thymeleaf.TemplateEngineUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteUser")
public class DeleteUser extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        шаблон запроса
//        http://localhost:9091/deleteUser?userId=2

        Long userId = getUserIdFromRequest(req);
        if (userId != null) {
            Boolean result = getAdminService().deleteUser(userId);
            resp.getWriter().println(result ? "User was deleted" : "User wasn't deleted");
            req.setAttribute("userId",1L);
            goTo("/adminPage", req, resp);
        }

//        req.getRequestDispatcher("/showAll").forward(req, resp);

    }
}
