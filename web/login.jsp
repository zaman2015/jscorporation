<%--
    Document   : index
    Created on : Jan 6, 2015, 1:16:29 AM
    Author     : BUDGET003
--%>

<%@page import="bisiness.Inventory"%>
<%@page import="sql.SQLHtml"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="data.Database" %>

        <%
            String userName = request.getParameter("userName");
            String passWord = request.getParameter("passWord");
            String url;
            String loginmsg="";
            boolean loginState= false;


            loginState = Database.login(userName, passWord);
            session = request.getSession();
            if(loginState)
            {
                url = "/home.jsp";
                String accountlist = SQLHtml.getAccountNames();
                String customerList= SQLHtml.getSubAccountNames(Database.selectSubAccountName("Accounts Receivable A/C"));
                String supplierList= SQLHtml.getSubAccountNames(Database.selectSubAccountName("Accounts Payable A/C"));
                String productlist = SQLHtml.getProductNames();
                String measurementUnitTable = SQLHtml.getMeasurementsTable();
                
                ArrayList inventoryList = new ArrayList();
                ArrayList profitAndLossLedger = new ArrayList();
                double total=0.00;
                double totalPrice=0.00;
                double totalPriceAfterVat=0.00;
                double totalVat=0.00;
                String measurementlist = SQLHtml.getMeasurements();
                
                session.setAttribute("userName", userName);
                session.setAttribute("accountlist", accountlist);
                session.setAttribute("customerList", customerList);
                session.setAttribute("supplierList", supplierList);
                session.setAttribute("productlist", productlist);
                session.setAttribute("measurementlist", measurementlist);
                session.setAttribute("measurementUnitTable", measurementUnitTable);
                
                session.setAttribute("inventoryList", inventoryList);
                session.setAttribute("total", total);// total product price for sales or purchase for an order of several Items. this value will be 0.00 after use.
                session.setAttribute("totalPrice", totalPrice);//for grand total after discount. this value will be 0.00 after use.
                session.setAttribute("totalVat", totalVat);
                session.setAttribute("totalPriceAfterVat", totalPriceAfterVat);
                session.setAttribute("profitAndLossLedger", profitAndLossLedger);//this value will be 0.00 after use.
            }
            else
            {
                url = "/index.jsp";
                loginmsg="Invalid Username/Password"; 
                        
            }
            session.setAttribute("loginmsg", loginmsg);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
            
        %>



