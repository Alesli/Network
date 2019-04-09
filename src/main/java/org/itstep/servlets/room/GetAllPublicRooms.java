package org.itstep.servlets.room;

import org.itstep.model.Room;
import org.itstep.model.User;
import org.itstep.servlets.AbstractServlet;
import org.itstep.thymeleaf.TemplateEngineUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/getAllPublicRooms")
public class GetAllPublicRooms extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Room> rooms = (List<Room>) getRoomService().getAllPublicRoom();
            req.setAttribute("rooms", rooms);
            TemplateEngineUtil.toThymeleaf("/AllRooms.html",req,resp);

    }
}
