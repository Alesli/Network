package org.itstep.servlets.room;

import org.itstep.model.Room;
import org.itstep.model.User;
import org.itstep.services.RoomService;
import org.itstep.servlets.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/createPrivateRoom")
public class CreatePrivateRoom extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User owner= getAdminService().getUserById(getUserIdFromRequest(req));
        String caption = getRoomCaptionRequest(req);
        String password = req.getParameter("password");
        Room room=getRoomService().createRoom(caption,password,owner, RoomService.PRIVATE_ROOM_MAX_USER);
        if(room==null){
            printToScreen(resp,"Private room "+caption+" wasn't create");
        }else{
            printToScreen(resp,"Private room "+caption+" create");
        }

    }
}
