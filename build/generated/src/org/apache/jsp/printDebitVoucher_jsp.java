package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class printDebitVoucher_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div>\n");
      out.write("            <table  style=\"width: 100%\">\n");
      out.write("            <tr>\n");
      out.write("                <td colspan=\"5\" style=\"text-align: center\">\n");
      out.write("                    <font size=\"8\" color=\"red\">M/S J. S. CORPORATION</font><br>130, Hazaribag Tannery Area, Dhaka-1209, Bangladesh.<br>Tel:+88-2-9664301 Mob:+88-01755599270, +88-1712229773 E-mail: sms@dhaka.net\n");
      out.write("                </td>\n");
      out.write("            </tr>\n");
      out.write("            </table>\n");
      out.write("            <table style=\"width: 100%\" class=\"dvHead\">\n");
      out.write("            <tr>\n");
      out.write("                <td style=\" text-align: left; width: 15%\">Transection ID:</td>\n");
      out.write("                <td style=\" text-align: left; width: 10%\">\n");
      out.write("                    <input id=\"trID\" type=\"text\" readonly size=\"15\" name=\"transectionID\">\n");
      out.write("                </td>\n");
      out.write("                <td style=\"width: 55%\">\n");
      out.write("\n");
      out.write("                </td>\n");
      out.write("                <td style=\" text-align: right; width:10%\">Voucher No:</td>\n");
      out.write("                <td style=\"text-align: right; width: 10%\">\n");
      out.write("                    <input id=\"drVrNo\" type=\"text\" readonly size=\"15\" name=\"voucherNo\">\n");
      out.write("                </td>\n");
      out.write("\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td style=\" text-align: left; width: 15%\">Sub Ledger ID:</td>\n");
      out.write("                <td style=\" text-align: left; width: 10%\">\n");
      out.write("                    <input id=\"subLdrID\" type=\"text\" readonly size=\"15\" name=\"subLedgerID\">\n");
      out.write("                </td>\n");
      out.write("                <td style=\"font-size: 125%; color: olivedrab; width: 55%; text-align: center\">\n");
      out.write("                    <span class=\"dv\"><u>DEBIT VOUCHER</u></span>\n");
      out.write("                </td>\n");
      out.write("                <td style=\" text-align: right; width: 10%\">Date:</td>\n");
      out.write("                <td style=\" text-align: right; width: 10%\">\n");
      out.write("                    <input id=\"date\" type=\"text\" readonly size=\"15\" name=\"date\" value='");
      out.print( new SimpleDateFormat("yyy-MM-dd").format(new Date()) );
      out.write("'>\n");
      out.write("                </td>\n");
      out.write("\n");
      out.write("            </tr>\n");
      out.write("\n");
      out.write("        </table>\n");
      out.write("        <br>\n");
      out.write("        <table border=\"1\" style=\"width:100%\" class=\"inventory\">\n");
      out.write("            <tr>\n");
      out.write("                <th style=\" font-weight: bold; width:20%; text-align: left\">\n");
      out.write("                    DEBIT                    \n");
      out.write("                </th>\n");
      out.write("                <th style=\"width: 40%; text-align: left\">\n");
      out.write("                    <select id=\"debitAccountID\" name=\"debitAccount\" required >\n");
      out.write("                          \n");
      out.write("                    </select>                   \n");
      out.write("                </th>\n");
      out.write("                 <th style=\" width: 40%\">\n");
      out.write("                    Sub Ledger:<select id='subAccount' name='subAccountDebit'></select>\n");
      out.write("                </th>\n");
      out.write("             </tr>\n");
      out.write("        </table>\n");
      out.write("        <br>            \n");
      out.write("        <table border=\"1\" style=\"width: 100%\" class=\"inventory\">\n");
      out.write("            <tr>\n");
      out.write("                <th style=\" width: 80%; font-weight: bold; text-align: center\">Particular</th>\n");
      out.write("                <th style=\" width: 20%; font-weight: bold; text-align: center\">Taka</th>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td style=\" width: 80%;\"><textarea id=\"debitTextarea\" rows=\"8\" cols=\"60\" name=\"particular\" required></textarea></td>\n");
      out.write("                <td style=\" width: 20%;vertical-align: top; text-align: center\"><input id=\"debitAmountInDebit\" name=\"debitAmount\" type=\"number\" required min=\"0.00\" step=\"any\"></td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td style=\" width: 80%; font-weight: bold; text-align: center\"></td>\n");
      out.write("                <td style=\" width: 20%; font-weight: bold; text-align: center\"></td>\n");
      out.write("            </tr>\n");
      out.write("\n");
      out.write("        </table>\n");
      out.write("                    <br>\n");
      out.write("                    <br>\n");
      out.write("                    <br>\n");
      out.write("        <table  style=\"width: 100%;\">\n");
      out.write("            <tr>\n");
      out.write("                <td>\n");
      out.write("                    <b><u>Prepared By</u></b>                    \n");
      out.write("                </td>\n");
      out.write("                <td>\n");
      out.write("                    <b><u>Accounts Officer</u></b>                    \n");
      out.write("                </td>\n");
      out.write("                <td>\n");
      out.write("                    <b><u>Proprietor</u></b>                    \n");
      out.write("                </td>\n");
      out.write("             </tr>\n");
      out.write("             <tr>\n");
      out.write("                <td>\n");
      out.write("                    <input id=\"creditAccountIDinDebit\" type=\"hidden\" name=\"creditAccount\" value=\"Cash A/C\">\n");
      out.write("                </td>\n");
      out.write("                <td>\n");
      out.write("                    <input id=\"creditAmountIDinDebit\" type=\"hidden\" name=\"creditAmount\" value=\"0.00\">\n");
      out.write("                </td>\n");
      out.write("                <td>\n");
      out.write("                    <input id=\"debitSaveBT\" type=\"submit\" value=\"SAVE\">                   \n");
      out.write("                </td>\n");
      out.write("             </tr>\n");
      out.write("        </table>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
