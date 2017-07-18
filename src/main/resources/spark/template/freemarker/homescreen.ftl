<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Home">

<div class="row">
    <div class="col-xs-11">
        <h3>Welcome to Market Duel! Please <a href="/login">login</a> or <a href="/register">register</a> to continue.</h3>
            <div class="basic-conent">
                Market Duel is a fantasy stock analysis game.  It is meant for entertainment purposes only and no real
                currency or stocks are exchanged.  In Market Duel, matches are set up between users where each user
                selects stocks for their portfolio while staying under a specified spending cap.  Stock prices are based
                on real-world stock indices.  The real-world performance of the stocks in each users portfolio is tracked
                during the match duration and a winner is the user with the highest net gain.
                <br/><br/>
                Market Duel provides various options to customize competitions similar to fantasy sports applications.
                This includes quick play where the entire competition consists of one match over a short period such as
                a day or week, as well as more full featured public and private league play where periodic (e.g. weekly)
                head-to-head matches are held and a record of wins and losses is kept.
                <br/><br/>
                Market Duel provides in-depth stock lookup and analysis tools including filtering of stocks by various
                forms of criteria, a breakdown of a stocks financial statistics, and several tools to track and analyze
                your opponents during competition.
                <br/><br/>
                The main objective of Market Duel is to provide entertainment.  However, users may find the analysis
                tools as well as the lessons learned while playing the game beneficial in real-world investing.
                <br/><br/>
            </div>
    </div>
</div>

</@layout.masterTemplate>