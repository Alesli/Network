package org.itstep.servlets.message;


import org.itstep.model.Room;
import org.itstep.model.User;
import org.itstep.servlets.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/addMessageToRoom")
public class AddMessageToRoom  extends AbstractServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Room room = getRoomService().getRoomFromCaption(getRoomCaptionRequest(req));
        User user = getAdminService().getUserById(getUserIdFromRequest(req));
        String message= req.getParameter("message");
        if(!getRoomService().checkExistUserInToRoom(room,user)){
            printToScreen(resp,"User is not exist in the room");
        }else {

            if (message != null && !message.isEmpty() &&
                    getMessageService().sendTextMessage(room, user, message)) {
                goTo("/refresh", req, resp);

            } else {
                printToScreen(resp, "Message must exist");
            }
        }
    }
}
