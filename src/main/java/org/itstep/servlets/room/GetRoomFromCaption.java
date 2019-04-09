package org.itstep.servlets.room;

import org.itstep.servlets.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/getRoomFromCaption")
public class GetRoomFromCaption extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//      http://localhost:9091/getRoomFromCaption?caption=Test

        String caption = req.getParameter("caption");

        resp.getWriter().println(getRoomService().getRoomFromCaption(caption));
    }
}
