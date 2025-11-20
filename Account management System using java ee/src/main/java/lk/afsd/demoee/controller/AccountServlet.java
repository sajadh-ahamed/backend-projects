package lk.afsd.demoee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.afsd.demoee.dto.AccountDTO;
import lk.afsd.demoee.service.AccountService;
import lk.afsd.demoee.service.impl.AccountServiceImpl;

import java.io.IOException;
import java.util.Map;

@WebServlet("/accountservlet") // account servlet is the name of the servlet
public class AccountServlet extends HttpServlet {

    final AccountService accountService = new AccountServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();

        AccountDTO accountDTO = objectMapper.readValue(req.getReader(), AccountDTO.class);

        boolean saved = accountService.createAccount(accountDTO);


        if (saved) {
            objectMapper.writeValue(resp.getWriter(), Map.of("status", "success", "message", "Account created."));
        } else {
            objectMapper.writeValue(resp.getWriter(), Map.of("status", "error", "message", "Account creation failed."));
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);

    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
