package org.itstep.servlets.room;


import org.itstep.model.Message;
import org.itstep.model.Room;
import org.itstep.servlets.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(urlPatterns = "/getAllMessagesFromRoom")
public class GetAllMessagesFromRoom extends AbstractServlet{
  private static final Integer MAX_VISIBLE_MESSAGE_COUNT=3;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Room room = getRoomService().getRoomFromCaption(getRoomCaptionRequest(req));
       Collection<Message> messagesFromRoom= getMessageService().getMessagesFromRoom(room,MAX_VISIBLE_MESSAGE_COUNT);
        for (Message m : messagesFromRoom) {
            String outString="(id - "+m.getId()+") "+m.getMessageDate().toString()+" : " +m.getUser().getNickname()+" - "+m.getMessage();
            printToScreen(resp,outString);
        }

    }
}
