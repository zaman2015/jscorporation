<jsp:include page="header.jsp" />
<jsp:include page="left.jsp" />
    <td style="width: 65%; vertical-align: top " >
        <form action="CreateNewProductServlet" method="post">
            <table style='width: 100%' class='inventory'>
                <tr>
                    <th colspan="2">
                        Insert New Product and Press ENTER
                    </th>
                </tr>
                <tr>
                    <td>
                        Product Name
                    </td>
                    <td>
                        <input id='productName' required type="text" name="productName" size="60">
                    </td>
                </tr>
                <tr>
                    <td>
                        Measurement Unit
                    </td>
                    <td>
                        <Select id='unitMeasurement' required name="unitMeasurement">${measurementlist}</Select>
                    </td>
                </tr>
                <tr>
                    <td>

                    </td>
                    <td>
                        <input id="createPRDBTID" type="submit" value="ENTER">
                    </td>
                </tr>
            </table>
        </form>
        <br>
        <br>
        
        
        <form action="CreateMeasuremntUnitServlet" method="post">
            <table style='width: 100%' class='inventory'>
                <tr>
                    <th colspan="2">
                        Insert Measurement Unit and Press ENTER
                    </th>
                </tr>
                <tr>
                    <td>
                        Measurement Unit Name
                    </td>
                    <td>
                        <input id='measurement' type="text" required name="measurement" size="60">
                    </td>
                </tr>
                
                
                <tr>
                    <td>

                    </td>
                    <td>
                        <input id="createUnitBTID" type="submit" value="ENTER">
                    </td>
                </tr>
            </table>
        </form>
       
    </td>
<jsp:include page="right.jsp" />
<jsp:include page="footer.jsp" />