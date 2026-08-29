package servlet;

import db.PaymentDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/PaymentInfo")
public class PaymentInfoServlet extends HttpServlet {

    private PaymentDB paymentDB;

    @Override
    public void init() throws ServletException {
        paymentDB = new PaymentDB();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String amount = req.getParameter("amount");
        String method = req.getParameter("paymentMethod");
        String rentingId = req.getParameter("renting_id");

        paymentDB.CreatePaymentDB(amount, method, rentingId);

    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


}
