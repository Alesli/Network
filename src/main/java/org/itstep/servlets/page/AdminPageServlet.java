package org.itstep.servlets.page;

import org.itstep.dto.AdminPageDTO;
import org.itstep.model.Room;
import org.itstep.model.User;
import org.itstep.servlets.AbstractServlet;
import org.itstep.thymeleaf.TemplateEngineUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/adminPage")
public class AdminPageServlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User activeUser= getAdminService().getUserById(getUserIdFromRequest(req));
        Room room= getRoomService().getRoomFromCaption(getRoomCaptionRequest(req));
        AdminPageDTO adminPageDTO= new AdminPageDTO(getRoomService().getAllRoom(),getAdminService().getAllUsers(),activeUser,room);
        req.setAttribute("adminData",adminPageDTO);
        TemplateEngineUtil.toThymeleaf("/AdminPage.html",req,resp);

    }
}
