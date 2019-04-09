package org.itstep.servlets.room;

import org.itstep.model.Room;
import org.itstep.model.User;
import org.itstep.servlets.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteRoom")
public class DeleteRoom extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        http://localhost:9091/deleteRoom?roomId=1&ownerId=1


        Room room = getRoomService().getRoomFromCaption(getRoomCaptionRequest(req));
        User owner = getAdminService().getUserById(getOwnerIdFromRequest(req));

        if (getRoomService().deleteRoom(room, owner)) {
            goTo("/getAllPublicRooms", req, resp);
        } else {
            resp.getWriter().println("Room " + room.getCaption() + " wasn't deleted");
        }

    }
}
