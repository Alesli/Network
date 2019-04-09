package org.itstep.servlets.room;

import org.itstep.model.Room;
import org.itstep.model.User;
import org.itstep.servlets.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/leaveRoom")
public class LeaveRoom extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        http://localhost:9091/leaveRoom?roomId=1&userId=1&ownerId=1


        Room room = getRoomService().getRoomFromCaption(getRoomCaptionRequest(req));
        User user = getAdminService().getUserById(getUserIdFromRequest(req));
        User owner = getAdminService().getUserById(getOwnerIdFromRequest(req));

        if (getRoomService().leaveRoom(room, user, owner)) {
            goTo("/GetAllUsersFromRoom",req,resp);
        } else {
            resp.getWriter().println("User doesn't leave the room " + room.getCaption());
        }
    }
}
