package org.itstep.servlets.page;

import org.itstep.dto.MainPageDTO;
import org.itstep.model.Room;
import org.itstep.model.User;
import org.itstep.servlets.AbstractServlet;
import org.itstep.thymeleaf.TemplateEngineUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/refresh")
public class RefreshPageServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Room mainRoom = getRoomService().getRoomFromCaption(getRoomCaptionRequest(req));
        User user = getAdminService().getUserById(getUserIdFromRequest(req));
        if (getRoomService().checkExistUserInToRoom(mainRoom, user)) {
            goToMaiPage(req, resp, mainRoom, user);
        } else if (getRoomService().isPublicRoom(mainRoom)) {
            getRoomService().addUserToRoom(mainRoom, user, mainRoom.getOwner());
            goToMaiPage(req, resp, mainRoom, user);
        } else {
            printToScreen(resp, "room is private");
        }

    }

    private void goToMaiPage(HttpServletRequest req, HttpServletResponse resp, Room mainRoom, User user) throws ServletException, IOException {
        MainPageDTO mainPageDTO = new MainPageDTO(getRoomService().getAllPublicRoom(),
                getRoomService().getUsersFromRoom(mainRoom),
                getMessageService().getMessagesFromRoom(mainRoom, 5),
                user,
                mainRoom);
        req.setAttribute("data", mainPageDTO);
        TemplateEngineUtil.toThymeleaf("/MainPage.html", req, resp);
    }
}
