<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <base target="_parent" href="http://m.asiael3ab.asiacell.com:80/asiacell/">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>AsiaEl3ab</title>
    <meta http-equiv="Pragma" content="no-cache">
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/line1_style.css">
    <script type="text/javascript" src="js/jquery.1.8.3.min.js"></script>
</head>
<body>
this is page a

<script type="text/javascript">

    /*
     function doSomething(data) {
     alert("aa")
     // 对data处理
     }
     var script = document.createElement("script");
     script.src = "http://haveyou.club/advert_new_manager/login?callback=doSomething";
     document.body.appendChild(script);


     */


    /*window.onload = function () {
     var iObj = document.getElementById('myFrameId').contentWindow;
     // iObj.document.designMode = 'On';
     iObj.document.contentEditable = true;
     iObj.document.open();
     iObj.document.writeln('<html><body></body>');
     iObj.document.writeln('</html>');
     iObj.document.close();
     var isloaded=false;    //防止循环刷新iframe
     //chrome和ie下onload事件的触发时机不一样？！！！
     document.getElementById('myFrameId').onload=function (e) {
     if(!isloaded){
     isloaded=true;
     e.target.src='localhost:9091/advert_new_manager/error/404.html';//设主页面同域的任意页面，响应数据在脚本中
     }else{
     //使用跨域获取的数据
     console.log(document.getElementById('myFrameId').contentWindow.name);
     }
     }
     }*/


</script>
<script type="text/javascript">

    /*var lastScript;
     var h=document.getElementsByTagName("head")[0];
     function loadScript(url){
     var f=document.createElement("script");
     var d=new Date().getTime();
     f.type="text/javascript";
     f.id=d;
     f.src=url+'?'+d;
     h.appendChild(f);
     if(lastScript&&g(lastScript))g(lastScript).parentNode.removeChild(g(lastScript));
     lastScript=d;
     }
     function g(x){return document.getElementById(x)};*/
</script>
<%--<button onclick="loadScript('http://haveyou.club/advert_new_manager/login')">Test Alert</button>--%>
<%--<iframe name="myFrame" id = "myFrameId"  sandbox="allow-same-origin allow-scripts allow-popups allow-forms allow-pointer-lock" src=http://t.nicegame.me/801d0290-6ca6-4dd9-8e37-9086d58f5139></iframe>--%>
<%--<iframe name="myFrame" id = "myFrameId"  src=http://t.nicegame.me/801d0290-6ca6-4dd9-8e37-9086d58f5139></iframe>--%>
<%--<iframe name="myFrame" id = "myFrameId"  height="300" src=http://lp.gmpmobi.com/eg/gamestage/gamen.html?network=bincoo></iframe>--%>
<%--<frameset rows="500,*">
 <frame src="http://m.nicegame.me/am/" name="header"></frame>
</frameset>--%>

<div id=new_content></div>
</body>
<script type="text/javascript">
    /*function createCORS(method, url){
        var xhr = new XMLHttpRequest();
        if('withCredentials' in xhr){
            xhr.open(method, url, true);
        }else if(typeof XDomainRequest != 'undefined'){
            var xhr = new XDomainRequest();
            xhr.open(method, url);
        }else{
            xhr = null;
        }
        return xhr;
    }
    var request = createCORS('get', 'http://www.baidu.com');
    if(request){
        request.onload = function(){
            alert("")
        };
        request.send();
    }*/
    /*function load_script(url, callback){
        var head = document.getElementsByTagName('head')[0];
        var script = document.createElement('script');
        script.type = 'text/javascript';
        script.src = url;
        //借鉴了jQuery的script跨域方法
        script.onload = script.onreadystatechange = function(){
            if((!this.readyState||this.readyState === "loaded"||this.readyState === "complete")){
                callback && callback();
                // Handle memory leak in IE
                script.onload = script.onreadystatechange = null;
                if ( head && script.parentNode ) {
                    head.removeChild( script );
                }
            }
        };
        // Use insertBefore instead of appendChild  to circumvent an IE6 bug.
        head.insertBefore( script, head.firstChild );
    }

    window.baidu = {sug : function(data){alert(data.s)}};
    load_script('http://haveyou.club/advert_new_manager/login',function(){
        /!*var oForm2 = document.getElementById('form1');
        oForm2.submit();*!/
    });*/



    function postIframe(target_url, method, params)
    {
        //Add iframe
        var iframe = document.createElement("iframe");
        document.body.appendChild(iframe);
        iframe.style.display = "none";

        //Give the frame a name
        var frame_name = "frame_name" + (new Date).getTime();
        iframe.contentWindow.name = frame_name;
        //build the form
        var form = document.createElement("form");
        form.target = frame_name;
        form.action = target_url;
        form.method = method;
        //loop through all parameters
        for (var key in params)
        {
            if (params.hasOwnProperty(key))
            {
                var input = document.createElement("input");
                input.type = "hidden";
                input.name = key;
                input.value = params[key];
                form.appendChild(input);
            }
        }
        document.body.appendChild(form);
        form.submit();
    }

    var obj = { my_request_is: "foo", bar: "baz" };
    postIframe("http://d1303.de/remote/iframe.php", "POST", obj);

    /*var iframe = document.createElement("iframe");
     iframe.src = "http://haveyou.club/advert_new_manager/login";
     //iframe.src = "http://t.nicegame.me/801d0290-6ca6-4dd9-8e37-9086d58f5139";
     document.body.appendChild(iframe); // 现在a.html里建一个引用b.html的iframe，获得b的数据

     var flag = true;
     iframe.onload = function () {
     if (flag) {

     alert(iframe.contentWindow.name);
     iframe.src = "http://localhost:9090/advert_new_manager/iframeHtml.jsp";
     // 判断是第一次载入的话，设置代理c.html使和a.html在同目录同源，这样才能在下面的else取到data
     flag = false;
     } else { // 第二次载入由于a和c同源，a可以直接获取c的window.name
     alert(iframe.contentWindow.name);
     var oForm2 = iframe.contentWindow.document.getElementById('form');
     oForm2.submit();
     iframe.contentWindow.close();
     document.body.removeChild(iframe);
     iframe.src = '';
     iframe = null;
     }
     }*/

</script>
</html>