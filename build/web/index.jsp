
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
        <title>J. S. Corporation</title>
    </head>
    <body>
        <form action="login.jsp" method="post" id="loginform" class="form-horizontal" role="form">

          <table width="100%">
            <tr>
                <td width="100%" colspan="3" align="center"><font size="3" color="blue">WELCOME</font><font size="8" color="red">.</font><font size="8" color="green">.</font><font size="8" color="yellow">.</font><br></td>
            </tr>
            <tr>
                <td width="15%" height="70%">

                </td>
                <td style="width: 70%; height: 50px; text-align: center; background: goldenrod" > <img src="title.png">130, Hazaribag Tannery Area, Dhaka-1209, Bangladesh.<br>Tel:+88-2-9664301 Mob:+88-01755599270, +88-1712229773 E-mail: sms@dhaka.net<br><br></td>
                <td width="15%" height="70%"></td>
            </tr>
            <tr>
                <td width="15%" height="70%">

                </td>
                
                <td>
                
                
                <div class="container">    
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                    <div class="panel-heading">
                        <div class="panel-title">Sign In</div>
                        
                    </div>     

                    <div style="padding-top:30px" class="panel-body" >

                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                            
                       
                                    
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input id="login-username" type="text" required class="form-control" name="userName" value="" placeholder="username">                                        
                                    </div>
                                
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="login-password" required type="password" class="form-control" name="passWord" placeholder="password">
                                    </div>
                                    

                                
                            <div class="input-group span7 centerrr">
                                      <div class="checkbox">
                                        <label>
                                          <input id="login-remember" type="checkbox" name="remember" value="1"> Remember me
										  
										  
										  
                                        </label>
                                          
                                          <button type="submit" class="btn btn-success">
                                              
                                             Login
                                              
                                          </button>
                                          <div style="color: red">
                                          
                                          ${loginmsg}
                                          </div>
                                      
                                        </div>
                                    </div>


                                <div style="margin-top:10px" class="form-group">
                                    
                                </div>

                        </div>                     
                    </div>  
        </div>
        
                    
                
                </td>
                
                
                
                <td width="15%" height="70%"></td>
            </tr>
            <tr>
                <td style="text-align: center; width: 100%; height: 7%" colspan="3"><img src="moving.gif" ><img src="ourlogo.gif" >
                            </td>
            </tr><tr>
                <td style="text-align: center; width: 100%; height: 8%" colspan="3"><img src="mainlogo.png" ></td>
            </tr>
        </table>
                    
        </form>
    </body>
</html>
