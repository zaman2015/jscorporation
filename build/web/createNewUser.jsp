<jsp:include page="header.jsp" />
<jsp:include page="left.jsp" />
    <td style="width: 65%; vertical-align: top " >
        <form action="InsertUserServlet" method="post">
            <table style="background-color: gray">
            <span style="color: red; font-weight: bolder">${message}</span>
            <tr>
                <td colspan="2">Insert Username and Password</td>
            </tr>
            <tr>
                <td colspan=>Username (Maximum 10 letters):</td><td colspan=><input type="text" name="user" size="10"></td>
            </tr>
            <tr>
                <td colspan=>Password (Maximum 10 digits):</td><td colspan=><input type="text" name="password" size="10"></td>
            </tr>
            <tr>
                <td colspan=></td><td colspan=><input type="submit"  value="SAVE"></td>
            </tr>
            
        </table>
        </form>
    </td>
<jsp:include page="right.jsp" />
<jsp:include page="footer.jsp" />