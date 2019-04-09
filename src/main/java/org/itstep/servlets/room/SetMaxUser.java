package org.itstep.servlets.room;

import org.itstep.model.Room;
import org.itstep.model.User;
import org.itstep.servlets.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/setMaxUser")
public class SetMaxUser extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        http://localhost:9091/setMaxUser?roomId=1&ownerId=1&maxUser=5

        Room room = getRoomService().getRoomFromCaption(getRoomCaptionRequest(req));
        User owner = getAdminService().getUserById(getOwnerIdFromRequest(req));
        Long maxUser = Long.valueOf(req.getParameter("maxUser"));

        if (getRoomService().setMaxUser(room, owner, maxUser)) {
            resp.getWriter().println(room);
        } else {
            resp.getWriter().println("Max users value wasn't changed.");
        }
    }
}
