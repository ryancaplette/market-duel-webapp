<#macro masterTemplate title="Welcome">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Market Duel</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div class="container">
    <nav class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="/"><img src="images/logo.png" width="20px">Market Duel</a>
        </div>

        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <#if player??>
                    <li><a href="/research">Research</a></li>
                    <li><a href="/account">My Account</a></li>
                    <li><a href="/quickmatch">Quick Match</a></li>
                    <li><a href="/logout">Log Out</a></li>
                <#else>
                    <li><a href="/research">Research</a></li>
                    <li><a href="/register">Register</a></li>
                    <li><a href="/login">Log In</a></li>
                </#if>
            </ul>
        </div>
    </nav>

    <div class="container">
        <#nested />
    </div>

    <footer class="footer">
        <p>Market Duel v1.0.0</p>
    </footer>
</div><!-- /container -->
</body>
</html>
</#macro>
