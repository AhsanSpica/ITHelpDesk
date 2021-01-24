<!DOCTYPE html>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="java.util.ArrayList,view.DBCoding" %>
<%@ page buffer="50000kb"%>    
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html> <!--<![endif]-->   
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link rel="stylesheet" href="resources/css/style.css"/>
   
    <script language="JavaScript" src="resources/js/login.js"></script>
    <script language="JavaScript" src="resources/js/jquery-1.12.2.js"></script>
     
   
    <link type="text/css" rel="stylesheet" href="resources/css/liststyle.css"/>
    <link type="text/css" rel="stylesheet" href="css/SLIDEW3SCHOOL.css"/>
    <%
    
   // if(session.getAttribute("origin")!= null)
//{
//response.sendRedirect((String)session.getAttribute("origin"));
//}
    
    
    %>
    
    <%--<%if (request.getParameter("empid") == null) 
      {
   session.setAttribute("session", "True");
    session.setAttribute("empid", "3213");
      }
       else 
       {
       session.setAttribute("session", "True");
       session.setAttribute("empid", request.getParameter("empid").toString());
       
       }
        if (request.getParameter("pass") == null) 
      {
   session.setAttribute("pass", "MR509");
      }
       else 
       {
      
      session.setAttribute("branch", request.getParameter("brcode").toString());
       
       }
   if (request.getParameter("brcode") == null) 
      {
   session.setAttribute("branch", "384");
      }
       else 
       {
      
      session.setAttribute("branch", request.getParameter("brcode").toString());
       
       }
    //session.setAttribute("branch", "384");
   
    %>--%>
  <style type="text/css">
  /*Simple reset*/
  body{
   overflow: hidden;
  }

canvas {
	display: block;
}
  
     
  </style>
 
  </head>
<!-- changes made at 11:50 am--->

 
<body id="login_body" onload="relogin() ">
     &nbsp;
    <div id="snow">
        <table>  <tr> <td style=" vertical-align: top; width:100%;">
   
   
</td></tr></table>
     <div>
  <div style="border-bottom: 0.1px; color: #1191d6;margin-left: 5.2%; margin-right: 10%;  margin-top: 8%;  font-family: Verdana; font-size:13.0pt;" id="empdiv">
  </div>
      
      </div>
 
  <div id="formcon" class="form" style=" max-height:800px;max-width: 360px;">
 <div class="slideshow-container">

<div class="mySlides fade" style="display:block;">
 
  <img src="resources/Images/head6.jpg" style="width:100%">
  
</div>

<div class="mySlides fade" style="display:none;">
 
  <img src="resources/Images/head1.jpg" style="width:100%">
  
</div>
<div class="mySlides fade" style="display:none;">
 
  <img src="resources/Images/head2.jpg" style="width:100%">
  
</div>
<div class="mySlides fade" style="display:none;">
 
  <img src="resources/Images/head3.jpg" style="width:100%">
  
</div>
<div class="mySlides fade"  style="display:none;">
 
  <img src="resources/Images/head4.jpg" style="width:100%">
  
</div>
<div class="mySlides fade" style="display:none;">
 
  <img src="resources/Images/head5.jpg" style="width:100%">
  
</div>
</div>

<!--<div style="text-align:center">
  <span class="dot" ></span> 
  <span class="dot" ></span> 
  <span class="dot" ></span> 
</div>-->
   <!--<script type="text/javascript"> 
 var flashyshow=new flashyslideshow({ //create instance of slideshow
	wrapperid: "myslideshow", //unique ID for this slideshow
	wrapperclass: "flashclass", //desired CSS class for this slideshow
	imagearray: [
               ["resources/Images/head6.jpg", 	"http://efuinsurance.com", "_new", 	""],
		["resources/Images/head1.jpg", 	"http://efuinsurance.com", "_new", 	""],
		["resources/Images/head2.jpg",	"http://efuinsurance.com", "_new", 		""],
		["resources/Images/head3.jpg", 	"http://efuinsurance.com", "_new", 		""],
		["resources/Images/head4.jpg", 	"http://efuinsurance.com", "_new", 		""],
                ["resources/Images/head5.jpg", 	"http://efuinsurance.com", "_new", 		""]
	],
	pause: 3000, //pause between content change (millisec)
	transduration: 1000 //duration of transition (affects only IE users)
})
</script>-->
  <!--<img id="headimg"  style="margin-top:0%;margin-bottom:10%;margin-left:75% max-height:120px;max-width: 360px;"  src="resources/Images/head6.jpg"/>-->
  <script>
var slideIndex = 0;
//showSlides();

//function showSlides() {
//    var i;
//    var slides = document.getElementsByClassName("mySlides");
//   
//    for (i = 0; i < slides.length; i++) {
//       slides[i].style.display = "none";  
//    }
//    slideIndex++;
//    if (slideIndex > slides.length) {slideIndex = 1}    
// 
//    slides[slideIndex-1].style.display = "block";  
//   
//    setTimeout(showSlides, 2000); // Change image every 2 seconds
//
//
//
////// Execute a function when the user releases a key on the keyboard
////input.addEventListener("keyup", function(event) {
////  // Cancel the default action, if needed
////  event.preventDefault();
////  // Number 13 is the "Enter" key on the keyboard
////  if (event.keyCode === 13) {
//// //  alert("trigerred");
////    // Trigger the button element with a click
////    document.getElementById("logbtn").click();
////  }
////});
//}
                          function passkeypress(event)
                        {
                          var keycode = (event.keyCode ? event.keyCode : event.which);
                                   if(keycode == '13')
                            {
                                execute();
                             }
                              }

</script>
  <div class="login-page"  style=" max-height: 200px;max-width: 360px;">

    <form id="myForm" style=" max-height: 200px;max-width: 360px;" action="compl_main.jsp" method="POST" >
    <div style="float:left" id="msgdisp"></div>
   <div id="loginputs">
   
   <input type="hidden" id="empid" name="empid" /><input type="hidden" id="compid" name="compid" /><input type="hidden" id="brcode" name="brcode" />
    <input type="hidden" id="tempbr" name="tempbr" />
   <h4 align="justify" style=" text-align: center;margin-top:-10px;font-family: 'Bookman Old Style'; color:#aeb8ba;">IT Complaint System</h4>
      <input type="text" placeholder="EMP.ID" id="logid" name="logid" onblur="return checkl(this)" />
      <input type="password" placeholder="password" id="pass" name="pass" onkeypress="passkeypress(event)"/>
        
      <input type="button" onclick="execute()" value="Login "  id="logbtn" name="logbtn"/>
   
    </div>
   
    <div id="menuform" >
     <div id="dropdiv" ></div>
   <div id="menu"  class="list-type4"></div>
   <input type="button" onclick="relogin()" value="GoBack" id="relogbtn" name="relogbtn"/>
    
    </div>
    
    
    
     </form>
  <!--<div>

  
  <a href="https://www.youtube.com/watch?v=eSBKFRZj0sE" style="margin-top:20px;"  target="_blank">Step by Step Guide. </a>
  </div>-->
 
 
 
  </div> 
  <!--<div style="margin-top:10px;">  <button onclick="playVid()" type="button">Play Video</button>
<button onclick="pauseVid()" type="button">Pause Video</button><br> 

<video id="myVideo" style="display:none;" width="320" height="176">
  <source src="/Video/Guide.mp4" type="video/mp4"/>
  
  Your browser does not support HTML5 video.
</video>

<script> 

function playVid() { 
  var vid = document.getElementById("myVideo"); 
vid.style.display="block";
  alert();
  //  vid.play(); 
} 

function pauseVid() { 
    vid.pause(); 
} 
</script></div>-->

    
</div>
 <!--<div id="admin_lbl" style="margin-top:80px;margin-top:6px;width:1000px;margin-left:800px; font: italic 12px Georgia, serif; color: indigo;">Developed By Ahsan Siddiqui / 3213 Assisstant Manager</div>-->
    <script type="text/javascript" src="resources/js/jquery-3.1.1.js"></script>
     
<div style="margin-left:450px;margin-top:-50px;"><p>Kindly Run in updated Chrome or FireFox<br>Kindly refresh Chrome Cache daily/routinely . <br>You may press enter after entering password <br>
Kindly read complaint number from text displayed<br> on top which is on a 4 second timer </p> </div>
</div>  

 
</body>
  
</html>