<#macro masterTemplate title="Welcome">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Market Duel</title>

    <link rel="stylesheet" href="css/datetimepicker.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <script src="images/bootstrap-datetimepicker.js" type="text/javascript"></script>


    
    <link rel="stylesheet" type="text/css" href="/css/style.css">

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript">
    $(window).load(function() {
        $(".loader").fadeOut("slow");
    })
    </script>


</head>
<body style="padding-top: 70px; background-color: #e3e7ed;">

<div class="loader"></div>

<div class="container">
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
             <a class="navbar-left" href="/" style="font-size: x-large; padding-top: 5px; text-decoration: none;"><img src="images/logo.png" style="max-width:50px; max-height:50px; vertical-align: bottom;"><strong> Market Duel</strong></a>
        </div>

        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <#if player??>
                    <li><a href="/research">Research</a></li>

                    <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">My Account <strong>(${player.username})</strong> <span class="caret"></span></a>
                      <ul class="dropdown-menu">
                        <li><a href="/account">Account Dashboard</a></li>
                        <li><a href="/games">Games</a></li>
                        <li><a href="/portfolios">Portfolios</a></li>
                        <li><a href="/players">Players</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/alerts">Alerts</a></li>
                      </ul>
                    </li>

                    <li><a href="/logout">Log Out</a></li>
                <#else>
                    <li><a href="/research">Research</a></li>
                    <li><a href="/login">Log In</a></li>
                    <li><button type="button" class="btn btn-default navbar-btn"><a href="/register">Register</a></button></li>
                </#if>
            </ul>
        </div>
        </div>
    </nav>



    <div class="container">
        <#nested />
    </div>

    <footer class="footer">
        <p><a href="#">Help</a> | <a href="#">Contact</a></p>
        <p>Market Duel v1.0.2 - <b>${.now?long?number_to_datetime}</b></p>
    </footer>
</div><!-- /container -->
</body>
</html>
</#macro>
