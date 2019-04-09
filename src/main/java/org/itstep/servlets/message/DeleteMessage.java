package org.itstep.servlets.message;


import org.itstep.model.Message;
import org.itstep.model.User;
import org.itstep.servlets.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteMessage")
public class DeleteMessage extends AbstractServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long mesasgeId = getMessageIdFromRequest(req);
        Long ownerId= getOwnerIdFromRequest(req);
        User owner= getAdminService().getUserById(ownerId);
        if(!getMessageService().deleteMessage(mesasgeId,owner)){
            printToScreen(resp,"Message wasn't deleted");
        }else{
          //  req.setAttribute("caption","Main_boltalka");
          //  goTo("/getAllMessagesFromRoom",req,resp);
        }


    }
}
