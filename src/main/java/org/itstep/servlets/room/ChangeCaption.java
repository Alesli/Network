package org.itstep.servlets.room;

import org.itstep.model.Room;
import org.itstep.model.User;
import org.itstep.servlets.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/changeCaption")
public class ChangeCaption extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//      http://localhost:9091/changeCaption?roomId=1&newCaption=Test2&ownerId=1

        Room room = getRoomService().getRoomFromCaption(getRoomCaptionRequest(req));
        String newCaption = req.getParameter("newCaption");
        User owner = getAdminService().getUserById(getOwnerIdFromRequest(req));

        if (getRoomService().changeCaption(room, newCaption, owner) == null) {
            resp.getWriter().println("Caption wasn't change.");
        } else {
            resp.getWriter().println(room);
        }

    }
}
