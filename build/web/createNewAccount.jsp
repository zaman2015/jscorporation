<jsp:include page="header.jsp" />
<jsp:include page="left.jsp" />
    <td style="width: 65%; vertical-align: top " >
        <form action="CreateNewAccountServlet" method="post">
            <table style="width:100%; text-align: left" class='inventory'>
                <tr>
                    <th colspan='2' style='font-weight: bolder; text-align: center '>
                        Create New Account
                    </th>
                </tr>
                <tr>
                    <td>
                        Account's Name:
                    </td>
                    <td>
                        <input id='accountNameID' required type="text" name="accountName" size="60">
                    </td>
                </tr>
                <tr>
                    <td>
                        Account's Type:
                    </td>
                    <td>
                        <Select id='accountTypeID' required name="accountType">
                            <option value="">--Select One--</option>
                            <option value="Capital">Capital</option>
                            <option value="Revenue">Revenue</option>
                        </Select>
                    </td>
                </tr>
                <tr>
                    <td>

                    </td>
                    <td>
                        <input id="createAcctBTID" type="submit" value="Submit">
                    </td>
                </tr>
            </table>
        </form>
        <br>
        <br>
        <br>
        <br>
        
        
        <form action="CreateSubAccountServlet" method="post">
            <table style="width:100%; text-align: left" class="inventory">
                <tr>
                    <th colspan="2" style="font-weight: bolder; text-align: center">
                        Create New Subsidiary Account
                    </th>
                </tr>
                <tr>
                    <td>
                        Main Account's Name:
                    </td>
                    <td>
                        <select id='mainAcctID' required name="mainAccountName">${accountlist}</select>
                    </td>
                </tr>
                <tr>
                    <td>
                        Sub Account's Name:
                    </td>
                    <td>
                        <input type="text" id="subAcctNameID" required name="subAccountName" size="60">
                    </td>
                </tr>
                <tr>
                    <td>
                        Address:
                    </td>
                    <td>
                        <input type="text" id="address" name="address" size="60" >
                    </td>
                </tr>
                <tr>
                    <td>
                        Phone No:
                    </td>
                    <td>
                        <input type="text" id="phone" name="phone" size="60">
                    </td>
                </tr>
                
                <tr>
                    <td>

                    </td>
                    <td>
                        <input id="createSubAcctBTID" type="submit" value="Submit">
                    </td>
                </tr>
            </table>
        </form>
        
    </td>
<jsp:include page="right.jsp" />
<jsp:include page="footer.jsp" />