
            
var request;

function getInfo(str)
{
    var v = str;
    var url = "customerSupplier.jsp?val="+v;

if(window.XMLHttpRequest)
{
    request=new XMLHttpRequest();
    
}
else 
{
    request=new ActiveXObject("Microsoft.XMLHTTP");
    
}
try
{
    request.onreadystatechange=printInfo;
    request.open("GET", url , true);
    request.send();
}
catch(e)
{
    alert("Unable to connect to server");
}
}

function printInfo()
                {
                if(request.readyState===4)
                {
                  var val =request.responseText;
                  document.getElementById('infoTable').innerHTML=val;
                  
                }
                
                }

        
        

function getMeasurementUnit(str)
{
    var v = str;
    var url = "measurementInfo.jsp?val="+v;

if(window.XMLHttpRequest)
{
    request=new XMLHttpRequest();
    
}
else 
{
    request=new ActiveXObject("Microsoft.XMLHTTP");
    
}
try
{
    request.onreadystatechange=showMeasurementInfo;
    request.open("GET", url , true);
    request.send();
}
catch(e)
{
    alert("Unable to connect to server");
}
}

function showMeasurementInfo()
                {
                if(request.readyState===4)
                {
                  var val =request.responseText;
                  document.getElementById('measurementUnit').value=val;
                 
                }
                
                }
                
                
                
                
function insertIntoInventoryTable()
{
    var salesOrPurhase = document.getElementById('salesOrPurchae').value;
    var name = document.getElementById('customerName').value;
    var product= document.getElementById("product").value;
    var quantity=document.getElementById("unit").value;
    var measurement=document.getElementById("measurementUnit").value;
    var rate=document.getElementById("rate").value;
    var memo=document.getElementById("memo").value;
    var invoieceNo = document.getElementById("invoieceNo").value;
    if(rate==="")
    {
        alert("Please Enter Positive Number");
    }
    else
    {
    var url = "inventoryInsert.jsp?name="+name+"&product="+product+"&quantity="+quantity+"&measurement="
            +measurement+"&rate="+rate+"&salesOrPurhase="+salesOrPurhase+"&memo="+memo+"&invoieceNo="+invoieceNo;

if(window.XMLHttpRequest)
{
    request=new XMLHttpRequest();
    
}
else 
{
    request=new ActiveXObject("Microsoft.XMLHTTP");
    
}
try
{
    request.onreadystatechange=showMeasurementInfoTEST;
    request.open("GET", url , true);
    request.send();
    
}
catch(e)
{
    alert("Unable to connect to server");
}
}
}

function showMeasurementInfoTEST()
    {
        if(request.readyState===4)
                
    {
                  
        var val =request.responseText;
                  
        document.getElementById('detailsTable').innerHTML=val;
               
             
}
    }
 
 
 
 
 
 
 function impectTotal()
{
    
    var url = "impectTotal.jsp";

if(window.XMLHttpRequest)
{
    request=new XMLHttpRequest();
    
}
else 
{
    request=new ActiveXObject("Microsoft.XMLHTTP");
    
}
try
{
    request.onreadystatechange=showTotal;
    request.open("GET", url , true);
    request.send();
}
catch(e)
{
    alert("Unable to connect to server");
}
}

function showTotal()
                {
                if(request.readyState===4)
                {
                  var val =request.responseText;
                  document.getElementById('totalID').value=parseFloat(val);
                 
                }
                
                }
                
                
                




function showGrandTotal(str)
{
    var v = str;
    var url = "grandTotal.jsp?val="+v;
    
    if(v==="")
    {
        alert("Please Enter 0 or Positive Number.");
    }
else
{
if(window.XMLHttpRequest)
{
    request=new XMLHttpRequest();
    
}
else 
{
    request=new ActiveXObject("Microsoft.XMLHTTP");
    
}
try
{
    request.onreadystatechange=printGrandTotal;
    request.open("GET", url , true);
    request.send();
}
catch(e)
{
    alert("Unable to connect to server");
}
}
}

function printGrandTotal()
                {
                if(request.readyState===4)
                {
                  var val =request.responseText;
                  document.getElementById('gTotalID').value=parseFloat(val);
                 
                }
                
                }
                
                
                
                
                
function calculateDue(str)
{
    var v = str;
    var url = "calculateDue.jsp?val="+v;
    
    if(v==="")
    {
        alert("Please Enter 0 or Positive Number.");
    }
    else
    {

if(window.XMLHttpRequest)
{
    request=new XMLHttpRequest();
    
}
else 
{
    request=new ActiveXObject("Microsoft.XMLHTTP");
    
}
try
{
    request.onreadystatechange=printDue;
    request.open("GET", url , true);
    request.send();
}
catch(e)
{
    alert("Unable to connect to server");
}
}
}

function printDue()
                {
                if(request.readyState===4)
                {
                  var val =request.responseText;
                  document.getElementById('dueID').value=parseFloat(val);
                 
                }
                
                }
                




        
function calculateVat(str)
{
    var v = str;
    var url = "calculateVat.jsp?val="+v;
    
    if(v==="")
    {
        alert("Please Enter 0 or Positive Number.");
    }
    else
    {

if(window.XMLHttpRequest)
{
    request=new XMLHttpRequest();
    
}
else 
{
    request=new ActiveXObject("Microsoft.XMLHTTP");
    
}
try
{
    request.onreadystatechange=printVat;
    request.open("GET", url , true);
    request.send();
}
catch(e)
{
    alert("Unable to connect to server");
}
}
}

function printVat()
                {
                if(request.readyState===4)
                {
                  var val =request.responseText;
                  document.getElementById('vatAmountID').value=parseFloat(val);
                 
                }
                
                }




                
                
function getTotalPriceAfterVat(str)
{
    var v = str;
    var url = "getTotalPriceAfterVat.jsp";

if(window.XMLHttpRequest)
{
    request=new XMLHttpRequest();
    
}
else 
{
    request=new ActiveXObject("Microsoft.XMLHTTP");
    
}
try
{
    request.onreadystatechange=printTotalPriceAfterVat;
    request.open("GET", url , true);
    request.send();
}
catch(e)
{
    alert("Unable to connect to server");
}
}

function printTotalPriceAfterVat()
                {
                if(request.readyState===4)
                {
                  var val =request.responseText;
                  document.getElementById('afterDiscInpt').value=parseFloat(val);
                 
                }
                
                }
                
                
                
                


function calculateSalesDue(str)
{
    var v = str;
    var url = "calculateSalesDue.jsp?val="+v;
    
    if(v==="")
    {
        alert("Please Enter 0 or Positive Number.");
    }
    else
    {

if(window.XMLHttpRequest)
{
    request=new XMLHttpRequest();
    
}
else 
{
    request=new ActiveXObject("Microsoft.XMLHTTP");
    
}
try
{
    request.onreadystatechange=printDue;
    request.open("GET", url , true);
    request.send();
}
catch(e)
{
    alert("Unable to connect to server");
}
}
}

function printDue()
                {
                if(request.readyState===4)
                {
                  var val =request.responseText;
                  document.getElementById('dueID').value = parseFloat(val);
                 
                }
                
                }
                
                
                
                
          
function getSaleableQuantity()
{
    
    var productName= document.getElementById('product').value;
    var url = "getClosingQuantity.jsp?productName="+productName;

if(window.XMLHttpRequest)
{
    request=new XMLHttpRequest();
}
else 
{
    request=new ActiveXObject("Microsoft.XMLHTTP");
    
}
try
{
    request.onreadystatechange=showBalane;
    request.open("GET", url , true);
    request.send();
}
catch(e)
{
    alert("Unable to connect to server");
}
}

function showBalane()
                {
                if(request.readyState===4)
                {
                  var val =request.responseText;
                                   
                 document.getElementById('unit').value=parseFloat(val);
                  
                  
                }
                
                }                   





function checkName()
{
    var salesOrPurhase = document.getElementById('salesOrPurchae').value;
    var custmerName = document.getElementById('customerName').value;
    var customerAddress= document.getElementById("customerAddress").value;
    var phone=document.getElementById("phone").value;
    
    if(custmerName==="")
    {
        alert("Please Enter Name, Address and Phone No..");
    }
    else
    {
    var url = "nameCheck.jsp?custmerName="+custmerName+"&customerAddress="+customerAddress+"&phone="+phone+"&salesOrPurhase="+salesOrPurhase+"";

if(window.XMLHttpRequest)
{
    request=new XMLHttpRequest();
    
}
else 
{
    request=new ActiveXObject("Microsoft.XMLHTTP");
    
}
try
{
    request.onreadystatechange=showName;
    request.open("GET", url , true);
    request.send();
    
}
catch(e)
{
    alert("Unable to connect to server");
}
}
}

function showName()
    {
        if(request.readyState===4)
                
    {
                  
        var val =request.responseText;
        alert(val);
               
                
}
    }
    
    
    
    
    
function goToSubLedger(str)
{
    
    var mainAccountName= str;
    var url = "getSubLedgerString.jsp?mainAccountName="+mainAccountName;

if(window.XMLHttpRequest)
{
    request=new XMLHttpRequest();
}
else 
{
    request=new ActiveXObject("Microsoft.XMLHTTP");
    
}
try
{
    request.onreadystatechange=showSubledgerString;
    request.open("GET", url , true);
    request.send();
}
catch(e)
{
    alert("Unable to connect to server");
}
}

function showSubledgerString()
                {
                if(request.readyState===4)
                {
                  var val =request.responseText;
                                   
                 document.getElementById('subAccount').innerHTML=val;
                  
                  
                }
                
                } 
                
                
             



function goToSubLedgerCredit(str)
{
    
    var mainAccountName= str;
    var url = "getSubLedgerString.jsp?mainAccountName="+mainAccountName;

if(window.XMLHttpRequest)
{
    request=new XMLHttpRequest();
}
else 
{
    request=new ActiveXObject("Microsoft.XMLHTTP");
    
}
try
{
    request.onreadystatechange=showSubledgerStringCr;
    request.open("GET", url , true);
    request.send();
}
catch(e)
{
    alert("Unable to connect to server");
}
}

function showSubledgerStringCr()
                {
                if(request.readyState===4)
                {
                  var val =request.responseText;
                                   
                 document.getElementById('subAccountCredit').innerHTML=val;
                  
                  
                }
                
                } 
                
                
                
                
                
                
function savePaymentData()
{
    var type = document.getElementById('typeId').value;
    var debitAccount = document.getElementById('debitAccount').value;
    var particular = document.getElementById('particular').value;
    var amount= document.getElementById("amount").value;
    var cashOrCheque=document.getElementById("cashOrCheque").value;
    var subAccount=document.getElementById("subAccount").value;
    var subParticular=document.getElementById("subParticular").value;
    var subAmount=document.getElementById("subAmount").value;
    var subCashOrCheque=document.getElementById("subCashOrCheque").value;
    
    
    var url = "paymentDataInsert.jsp?debitAccount="+debitAccount+"&particular="+particular+"&amount="+amount+"&cashOrCheque="
            +cashOrCheque+"&subAccount="+subAccount+"&subParticular="+subParticular+"&subAmount="+subAmount+"&subCashOrCheque="+subCashOrCheque+"&type="+type;

if(window.XMLHttpRequest)
{
    request=new XMLHttpRequest();
    
}
else 
{
    request=new ActiveXObject("Microsoft.XMLHTTP");
    
}
try
{
    request.onreadystatechange=showSuccessReport;
    request.open("GET", url , true);
    request.send();
    
}
catch(e)
{
    alert("Unable to connect to server");
}
}


function showSuccessReport()
    {
        if(request.readyState===4)
                
    {
                  
        var val =request.responseText;
                  
        alert("Saved Successfully!");
               
                
}
    }
    
    
    
function cancel()
{
    
    var url = "cancel.jsp";

if(window.XMLHttpRequest)
{
    request=new XMLHttpRequest();
}
else 
{
    request=new ActiveXObject("Microsoft.XMLHTTP");
    
}
try
{
    request.onreadystatechange=showCancelMsg;
    request.open("GET", url , true);
    request.send();
}
catch(e)
{
    alert("Unable to connect to server");
}
}

function showCancelMsg()
                {
                if(request.readyState===4)
                {
                  var val =request.responseText;
                  
                 window.location.reload();
                 document.getElementById("afterDiscInpt").value="";               
                 document.getElementById("customerListID").value=""; 
                 document.getElementById("product").value="";
                 document.getElementById("unit").value="";
                 document.getElementById("measurementUnit").value="";
                 document.getElementById("rate").value="";
                 document.getElementById("totalID").value="";
                 document.getElementById("discountlID").value="";
                 document.getElementById("gTotalID").value="";
                 document.getElementById("vatRateID").value="";
                 document.getElementById("vatAmountID").value="";
                 document.getElementById("paidID").value="";
                 
                 
                }
                
                } 
                
                
function cancelPurchase()
{
    
    var url = "cancel.jsp";

if(window.XMLHttpRequest)
{
    request=new XMLHttpRequest();
}
else 
{
    request=new ActiveXObject("Microsoft.XMLHTTP");
    
}
try
{
    request.onreadystatechange=showCancePurchlMsg;
    request.open("GET", url , true);
    request.send();
}
catch(e)
{
    alert("Unable to connect to server");
}
}

function showCancePurchlMsg()
                {
                if(request.readyState===4)
                {
                  var val =request.responseText;
                  
                 window.location.reload();
                 document.getElementById("customerListID").value="";               
                 document.getElementById("product").value=""; 
                 document.getElementById("unit").value="";
                 document.getElementById("measurementUnit").value="";
                 document.getElementById("rate").value="";
                 document.getElementById("totalID").value="";
                 document.getElementById("discountlID").value="";
                 document.getElementById("gTotalID").value="";
                 document.getElementById("paidID").value="";
                 document.getElementById("dueID").value="";
                 
                 
                }
                
                }