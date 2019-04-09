package org.itstep.servlets.room;

import org.itstep.model.Room;
import org.itstep.model.User;
import org.itstep.servlets.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/addUserToRoom")
public class AddUserToRoom extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        http://localhost:9091/addUserToRoom?caption=Main_boltalka&userId=3&ownerId=1

        Room room = getRoomService().getRoomFromCaption(getRoomCaptionRequest(req));
        User user = getAdminService().getUserById(getUserIdFromRequest(req));
        User owner = getAdminService().getUserById(getOwnerIdFromRequest(req));
        if (!getRoomService().addUserToRoom(room, user, owner)) {
            resp.getWriter().println("User wasn't add");
        } else {
            goTo("/getAllUsersFromRoom",req,resp);
        }
    }
}
