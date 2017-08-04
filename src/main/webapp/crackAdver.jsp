<%--
  Created by IntelliJ IDEA.
  User: Singe
  Date: 2017-08-03
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>


<head>
    <base href="http://m.asiael3ab.asiacell.com:80/asiacell/"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>AsiaEl3ab</title>
    <meta http-equiv="Pragma" content="no-cache">
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/line1_style.css">
    <link href="css/newsmsstyle.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/jquery.1.8.3.min.js"></script>
    <style> html{display:none;} </style>
    <script>
        if(self == top) {
            document.documentElement.style.display = 'block';
        } else {
            top.location = self.location;

            //页面被iframe包裹，记录话单日志
            $.get("http://m.asiael3ab.asiacell.com:80/asiacell/servlet/adletangZombieReaper3", { flag: "Iframe"},
                function(data){});
        }
    </script>
    <style>
    </style>
    <script type="text/javascript">
        //var _udataProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
        //document.write(unescape("%3Cscript src='http://edatasdk.huaweiapi.com/EDATA_JS/edata.js?"+Math.random()+"'"+"type='text/javascript'%3E%3C/script%3E"));
    </script>
    <script type="text/javascript">
        var userAccount = "${userAccount}";
        var appKey = "${appKey}";
        var appName = "${appName}";
        var _edata = _edata||[];
        _edata.push(["setUserAccount",userAccount]);
        _edata.push(["setAppkey",appKey]);
        _edata.push(["setAppName",appName]);
    </script>
</head>

<body>
<!--头部banner-->
<header class="header-box">
    <img src="images/zombieReaper3.jpg" alt="banner" class="head-banner"/>

    <form id="form1" name="form1" action="http://m.asiael3ab.asiacell.com:80/asiacell/servlet/handle" method="post">
        <input type="hidden" name="flag" value="Submit">
        <input type="hidden" name="sid" value="${snid}">
    </form>
    <img id="input1" class="head-anniu" src="images/freeDownload_en.gif" alt="download" />
</header>

<!--中部main-->
<div class="main-box">
    <div class="rights-pic">
        <img src="images/newgift_en.png" alt=""/>
        <img src="images/newdiscount_en.png" alt=""/>
        <img src="images/newfree_en.png" alt=""/>
    </div>
    <div class="rights-text">
        Enjoy your first 3 days 100% free and 20% discount on all in-game purchases</br>
        Unsubscribe whenever you want to send "0" to "2180" by SMS</br>
        Charge 250 IQD/day after 3days free Trial</br>
        NoAds pop up during your game play</br>
    </div>
    <div class="rights-bottom">
        For more exciting games</br>
        <a href="http://m.asiael3ab.asiacell.com?sign=7678cc49d9a19e88dbb0aa671c99be1675a72aaa">asiael3ab.asiacell.com</a>
    </div>
</div>
<img alt="logo" src="images/huawei-logo.jpg" style="width: 100%;">
<!--底部footer-->
<footer class="footer-box">
    <div class="footer">
        ?2016 AsiaEl3ab All Rights Reserved
    </div>
</footer>


</body>
<script type="text/javascript">
    window.onload = function(){
        var oBtnSubscribe1 = document.getElementById('input1');
        var oForm2 = document.getElementById('form1');
        //易数统计二次确认页访问次数
        _edata.push(['setEvent','load','load',[['C1','subscribe_page'],['C2','adletangZombieReaper3']]]);
        /* oBtnSubscribe1.onclick = function(){*/
        //易数统计加入VIP次数
        _edata.push(['setEvent','subscribe','subscribe',[['C1','subscribe_page'],['C2','adletangZombieReaper3']]]);
        oForm2.submit();
       /* }*/

    }


    function trim(str){
        return str.replace(/(^\s*)|(\s*$)/g, '');
    }












</script>
