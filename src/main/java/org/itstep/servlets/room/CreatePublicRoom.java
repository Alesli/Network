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

@WebServlet(urlPatterns = "/createPublicRoom")
public class CreatePublicRoom extends AbstractServlet{


    // http://localhost:9091/createPublicRoom?userId=1&caption=Main_boltalka

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User owner= getAdminService().getUserById(getUserIdFromRequest(req));
        String caption = getRoomCaptionRequest(req);
        Long maxUser=req.getParameter("maxUser")==null?999:Long.valueOf(req.getParameter("maxUser"));
        if(maxUser.longValue()== RoomService.PRIVATE_ROOM_MAX_USER){
            printToScreen(resp,"Private room can't created");
        }else {
            Room room = getRoomService().createRoom(caption, "", owner, maxUser);
            if (room == null) {
                printToScreen(resp, "Public room " + caption + " wasn't create");
            } else {
                goTo("/getAllPublicRooms", req, resp);
            }
        }
    }
}
