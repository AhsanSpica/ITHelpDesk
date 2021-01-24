<!DOCTYPE html>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="java.util.ArrayList,view.DBCoding,java.util.HashMap , java.util.Locale, java.util.Map,java.io.*" %>
<%@ page buffer="50000kb"%>   

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
      <link id="favicon" rel="icon" href="resources/Images/favicon.jpg"/>
        <link type="text/css" rel="stylesheet" href="resources/css/locstyle.css"/>
        <link type="text/css" rel="stylesheet" href="resources/css/w3.css"/>
    <link type="text/css" rel="stylesheet" href="resources/css/checkcss.css"/>
    <link type="text/css" rel="stylesheet" href="resources/css/radiocss.css"/>
        <link type="text/css" rel="stylesheet" href="css/compln_css.css"/>
        <link type="text/css" rel="stylesheet" href="resources/css/jquery-style.css"/>
         <link type="text/css" rel="stylesheet" href="resources/css/jquery-ui.css"/>
        <link type="text/css" rel="stylesheet" href="css/fire_fox_css.css"/>
        <link type="text/css" rel="stylesheet" href="css/IE-edge.css"/>
        <link type="text/css" rel="stylesheet" href="css/IE-8.css"/>
        <link type="text/css" rel="stylesheet" href="css/ie_67.css"/>
        <link type="text/css" rel="stylesheet" href="css/Chrome_CSS.css"/>
        <%--<%
String ua = request.getHeader( "User-Agent" );
boolean isFirefox = ( ua != null && ua.indexOf( "Firefox/" ) != -1 );
boolean isMSIE = ( ua != null && ua.indexOf( "MSIE" ) != -1 );
response.setHeader( "Vary", "User-Agent" );


if( isMSIE ){
  out.println("<script type=\"text/javascript\">");
   out.println("location='test.jsp';");
   out.println("</script>");
}%>--%>
       
       
        <%
        // if (session==null)
      //  {session.setAttribute("session", "True");}
    //  window.location.href ='login.jsp';
 // alert("the empid from request parameter: "+request.getParameter("empid").toString()+" compid: "+request.getParameter("compid").toString()+"brcode "+request.getParameter("brcode").toString()+" );");
 
   String brvalid= "";
         String empl ="";
    
      if (request.getParameter("empid") == null) 
      {
      if (session.getAttribute("empid")==null)
    
      {
       String redirectURL = "http://complain.efuinsurance.com:9003/App_IT_Service/";
    response.sendRedirect(redirectURL);
   
    }
      }
         else {
    //   if (session.getAttribute("empid")==null)
    //  {
       try{
        session.setAttribute("session", "True");   
      
       session.setAttribute("empid", request.getParameter("empid").toString());
       session.setAttribute("company", request.getParameter("compid").toString());
         session.setAttribute("branch",request.getParameter("brcode").toString() );
        
         empl =session.getAttribute("empid").toString();
         brvalid=session.getAttribute("branch").toString();
          
    
       
            String emplnm = "";
           String empltp = "-";
           
     DBCoding db1=new DBCoding();
    
     emplnm=db1.checkempl(empl);
    
    DBCoding db3=new DBCoding();
     String deptnm=db3.checkdeptnm(brvalid);
      
        session.setAttribute("emplnm", emplnm);
        
        session.setAttribute("brnm", deptnm);
         
          if( brvalid.equals("115") )  { 
   
        session.setAttribute("compable","true" ); 
      session.setAttribute("servable","false" ); 
        DBCoding db2=new DBCoding();
         empltp= db2.checktp(empl);
    
            session.setAttribute("empltp", empltp);
           }
     
     else {
     session.setAttribute("compable","false" ); 
      session.setAttribute("servable","true" ); 
     session.setAttribute("empltp", "U");
     }
        }
     catch(Exception ex)
       {
       out.println("<script type=\"text/javascript\">");
       String msg= "the request parameters catch  "+ex.getLocalizedMessage()+ex.getClass()+"session: "+session+" , empid "+request.getParameter("empid").toString()+"compid "+request.getParameter("compid").toString()+"brid "+request.getParameter("brcode").toString();
       out.println("alert("+msg+");");
       out.println("</script>");
       }  }
    %>
    
    
    <script type="text/javascript">
 
   
          $(document).ready(function () {
            //Disable full page
            $("body").on("contextmenu",function(e){
                return false;
            });
            
        
        });


 
    </script>
      
    
     </head>
   <!-- style="  background-color:#8ce4ff;background-image: url('../resources/texture_silver_aluminum.jpg');"-->
  
              <body id="body"    class="bodycss">
              <input id="emptph" hidden="hidden"   value="<%=session.getAttribute("empltp")%>"/>
            <input id="cmpdis" hidden="hidden"     value="<%=session.getAttribute("compable")%>"/>
            <input id="serdis" hidden="hidden"    value="<%=session.getAttribute("servable")%>"  />
        <table style="width:95%;margin-top:-10px;"><tr style="width:100%;"><td style="width:13%;">
       <span style="float:left;color: #0991e5;  margin-left: 40px; font-family: Verdana; font-size:14.0pt;cursor:pointer;" onclick="window.location.href = '/App_IT_Service/login.jsp';" title="Exit">&#8592; Logout</span>
        <!--<img id="headimg" name="headimg" height="76" width="68" src="resources/images/logo_efu.png"
        style="margin-top:23%;margin-left:23%;"/>-->
      </td><td style="width:100%">
        <h2 style=" border-bottom: 0.1px; color: #0991e5;margin-left: 5px; margin-right: 10px;  margin-top: 4%;  font-family: Verdana; font-size:13.0pt;">
        EFU General Insurance LTD 
        <label style="margin-left:30px; color: #0991e5; font-family: Verdana; font-size:13.0pt;">Branch :  <%=session.getAttribute("branch").toString()%> </label>
        <label style="margin-left:30px; color: #0991e5; font-family: Verdana; font-size:13.0pt;"><%=session.getAttribute("brnm").toString()%> </label>
        <label style="margin-left:30px; color: #0991e5; font-family: Verdana; font-size:13.0pt;">Emp.ID :&nbsp;<%=session.getAttribute("empid").toString()%> </label></h2>
                                </td></tr>
                    <audio id="myAudio">
          <source src="../resources/Audio/bell.mp3" type="audio/mpeg"/>
        </audio>            
                        <%-- <%  String branchid = session.getAttribute("branch").toString();
                        if ( branchid.equals(branchid)) { %> <tr style="width:100%;"> <td style="width:100%">
<h2 style=" border-bottom: 0.1px; color: #0991e5;margin-left: 5px; margin-right: 10px;  margin-top: 4%;  font-family: Verdana; font-size:13.0pt;">
This is trial version buy monthly or annual package within 30 days to continue use !</h2>  </td> </tr>
              <%} %> --%> 
                        
                        </table>  
                      <input type ="hidden" id="lochid" name="lochid"/> 
                <!--<div id="mySidenav" class="sidenav" >
                <label style="font-family: Verdana;margin-left:20px;  color: #818181;font-size:10.0pt; float:left;">Menu&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>   
      
                
                 --><!--<input type="button" id="submit" title="Save"  class="navbtn" onfocus="openNav()" 
                       style=" font-weight: bolder; font-size: 11pt; margin-left:10px;  height:35px; width:70px;" tabindex="21" 
                       value="save" name="submit" onclick="submitaction2()"/>--><!--
             <%  String match2 =session.getAttribute("empltp").toString();
                    if (match2.equals("AC")||match2.equals("AA")||match2.equals("AH")||match2.equals("N")) {
               %> <input type="button" style="margin-left:10px;  font-weight: bolder; font-size: 11pt; height:35px; width:70px;"
                class="navbtn" title="Search"  value="Report" id="dialogbtn" name="dialogbtn" onclick="showdialog()"/>
               <% }%>
                   <input type="button" id="refresh" title="Reset Location Capture" name="refresh" onclick=" location.reload();" class="navbtn"
                style=" font-weight: bolder; margin-left:10px;   font-size: 11pt;  height:35px; width:70px;" value="refresh"/>
                <input  type="button" id="retbtn" name="retbtn" onclick="window.location.href = 'login.jsp';" title="Exit" class="navbtn" 
                     style=" font-weight: bolder; margin-left:10px;  font-size: 11pt;  height:35px; width:70px;"
                       value="exit"/>
                <input type="button" style="margin-left:10px; margin-top:53px;  font-weight: bolder; font-size: 16pt;"  class="closebtn" value="&times;"   onclick="closeNav()"/>                             
            </div>-->
                                                           <!-- originally was height width 95%-->
    <form  id="main"  class="form-style-9"  style=" margin-left:4%;margin-top:-1.2%;width: 94%;height: 90%; min-width:20%;min_height:10%;">
     <input type="hidden" id="complnid" name="complnid"  />
   <input type="hidden" id="idstore" name="idstore" value="<%=session.getAttribute("empid").toString()%>" />
   <input type="hidden" id="brid" name="brid" value="<%=session.getAttribute("branch").toString()%>" />
    <input type="hidden" id="company" name="company" value="<%=session.getAttribute("company").toString()%>" />
    
   
     
        <% 
    
   DBCoding db1=new DBCoding();
   String brancher= session.getAttribute("branch").toString();
      String eid= session.getAttribute("empid").toString();
      String emptp=session.getAttribute("empltp").toString();
       emptp=emptp.trim();
    //    eid=eid.trim();
    //    brancher=brancher.trim();
      //  Map<String,String> myMap = new HashMap<String,String>();
    ArrayList<String> list1 = new ArrayList();
   // ArrayList<String> keylist = new ArrayList();
   ArrayList<String> vallist = new ArrayList();
    ArrayList<String> processlist = new ArrayList();
    ArrayList<String> vallist2 = new ArrayList();
   ArrayList<String> openlist = new ArrayList();
   ArrayList<String> devlist = new ArrayList();
   ArrayList<String> crdlist = new ArrayList();
    ArrayList<String> mainlist = new ArrayList();
      ArrayList<String> emplist = new ArrayList();
      if (emptp!="U")
       {  
       //devlist.addAll(db1.getdev());
      //crdlist.addAll(db1.getcrd());
       list1.addAll(db1.checkdept(eid,emptp));
        processlist.addAll(db1.getonlyprocess(eid,emptp ));
       openlist.addAll(db1.getonlyopen(eid,emptp));
      vallist.addAll(db1.getallcompln(eid,emptp));  
      vallist2.addAll(db1.getalldone(eid,emptp)); 
        emplist.addAll(db1.getempnm("all"));
        
        
        }
       // myMap.putAll(db1.getallcompln(brancher));
       // keylist.addAll(myMap.keySet());
       
         mainlist.addAll(db1.getallcomplnmain(brancher,eid ));
     
         %>  
         
               <span style="float:left;color: #0991e5;  margin-left: 2%; font-family: Verdana; font-size:15.0pt;cursor:pointer;" onclick="location.reload(); "> Refresh</span>
               
               <%  String match = session.getAttribute("empltp").toString();
               String empid = session.getAttribute("empid").toString();
                    if (match.equals("AC")||match.equals("AA")||match.equals("AH")||match.equals("N") || empid.equals("3213")) {
               %> <input type="button" style="margin-left:10px;  font-weight: bolder; font-size: 11pt; height:35px; width:70px;"
                class="navbtn" title="Search"  value="Report" id="dialogbtn" name="dialogbtn" onclick="showdialog()"   />
               <!--<span   style="float:left;color: #0991e5;  margin-left: 2%; font-family: Verdana; font-size:15.0pt;cursor:pointer;"
                     id="dialogbtn"   onclick="showdialog()">Report</span>-->
               <% }%>
             
    <!--<span style="float:left;color: #0991e5;  margin-left: 2%; font-family: Verdana; font-size:15.0pt;cursor:pointer;" onclick="openNav()">&#9776; options</span>-->
       <!--<span style="color: #0991e5;  margin-left: 65%; font-family: Verdana; font-size:15.0pt;cursor:pointer;" >-->
      <b id ="msgdisp" style="display:none;" ></b>
      <div style="margin-left:90%; width: 90px; margin-top:-10px;" class="styled-select blue semi-square">
       <select style=" width: 90px; margin-top:4%;" id="inselect" class="compln" name="inselect"  onchange="getinfo()">
      <%
       for(int x=0; x<mainlist.size(); x++)  
    { String val=mainlist.get(x);
   
       String valsub=val.substring(3);
       int id = Integer.parseInt(valsub); 
       String dispid= String.valueOf(id);
       if (id==0){dispid="Select";}
      
       %>
    <option style="text-align: center;text-justify: inter-word;" value= "<%=mainlist.get(x) %>" ><%= dispid  %></option>
    <% }%>
       </select>
       </div>
       
        <!--updated at 10:17 on the 5 22-->  
       <!--<h2 style=" border-bottom: 0.1px;  color: #0991e5;  margin-right: 88.6%; font-family: Verdana; font-size:15.0pt;">Branch Address</h2>-->
   
    <!--<legend>Location</legend>-->
  
         <div  id="service" class="row usercon" >
          <div class="column3" >
          <div id="comp_div"> <label class="container">Software
          <input type="radio"  id="comptype_1" value="1" class="comptype compln"  name="comptype" autocomplete="off" onchange="toggle(this)" />
          <span class="checkmark"></span>
        </label>
        <label class="container">Hardware
          <input type="radio"  class="comptype compln" name="comptype" id="comptype_2" value="2"  autocomplete="off"  onchange="toggle(this)"  />
          <span class="checkmark"></span>
        </label>
        
          <input type="radio" disabled="disabled" style=" visibility:hidden; "     class="comptype compln" name="comptype" id="comptype_0" value="0"    />
          <span class="checkmark"></span>
        
        </div>
          
          <div  id="custresp" style="display:none;"><textarea id="COMPLN_DTL_05" name="COMPLN_DTL_05"  autocomplete="off"  class="compln" style=" height :90px; width:100px;"
                              rows="4" cols="50"></textarea> 
                              
                              <a type="button"   onclick="closing()" class="compln buttonextra" 
        style="margin-left:5px;margin-top:5px;" id="closebtn"      >Close</a>
                              
                              </div></div>
          
           <div class="column3" id="sys_sel"  title="System"  >
            <label style=" margin-bottom:re8px;" >System</label>
          <label class=" container">Under Writing
          <input type="radio" value= "1" id="systype_1" class="compln softinput check"  name="systype" onchange="toggle2(this)"  autocomplete="off" />
         
          <span class="checkmark"></span>
        </label>
        <label class=" container">Claim System
          <input type="radio" value="2" id="systype_2"  class="compln softinput check" name="systype" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Accounting
          <input type="radio" value="3"  id="systype_3"  class="compln softinput check" name="systype" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Payroll
          <input type="radio" value="4"  id="systype_4"  class="compln softinput check" name="systype" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
         <label class=" container">On Line Application
          <input type="radio" value="5"  id="systype_5" class="compln softinput check"  name="systype" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">General Ledger
          <input type="radio" value="6"  id="systype_6" class="compln softinput check"  name="systype" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Month End
          <input type="radio" value="7"  id="systype_7" class="compln softinput check"  name="systype" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Co-Insurance
          <input type="radio" value="8"  id="systype_8" class="compln softinput check"  name="systype" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Renewals
          <input type="radio" value="9"  id="systype_9" class="compln softinput check"  name="systype" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Service Charges
          <input type="radio" value="11"  id="systype_11" class="compln softinput check"  name="systype" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Bank Reconciliation
          <input type="radio" value="12"  id="systype_12" class="compln softinput check"  name="systype" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Others
          <input type="radio" value="13"  id="systype_13"  class="compln softinput check" name="systype" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
          <input type="radio"  value="0"  id="systype_0"  class="compln softinput check" name="systype"  disabled="disabled" style=" visibility:hidden; "   />
           <span class="checkmark"></span>
        </label></div>
        
          <div class="column3"   id="clas_sel"   title="Class" >
           <label style=" margin-bottom:8px;" >Classes</label>
           <label class=" container">Marine Cargo
          <input type="radio"  class="compln check_class softinput check" value='1'  id="classtype_1"  name="classtype" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Marine Hull
          <input type="radio"  class="compln check_class softinput check"   value='2'    id="classtype_2"   name="classtype" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Fire
          <input type="radio"  class="compln check_class softinput check" value='3'   id="classtype_3"   name="classtype" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Motor
          <input type="radio"  class="compln check_class softinput check" value='4'  id="classtype_4"     name="classtype" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
         <label class=" container">Miscellaneous
          <input type="radio"  class="compln check_class softinput check" value='5'   id="classtype_5"   name="classtype" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Engineering
          <input type="radio"  class="compln check_class softinput check" value='6'    id="classtype_6"    name="classtype" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Bond
          <input type="radio"   class="compln check_class softinput check" value='7'   id="classtype_7"  name="classtype" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
         <label class=" container">Live Stock
          <input type="radio"   class="compln check_class softinput check"  value='8'   id="classtype_8"    name="classtype" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Aviation Hull
          <input type="radio"  class="compln check_class softinput check" value='9'   id="classtype_9"  name="classtype" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
          
          
           <input type="radio"  class="compln check_class softinput check" value="0"   id="classtype_0"  name="classtype" disabled="disabled" style=" visibility:hidden; "/>
          <span class="checkmark"></span>
        </label>
          </div>
          <!--  change as of 4:05 pm  23 nov 2017 -->
           <div class="column3"   id="acc_sel"   title="Accounting" >
           <label style=" margin-bottom:8px;" >Accounting</label>
           <label class="container">JV
          <input type="radio"  name="acctype"  class="compln check_acc softinput check"    id="acctype_J"   value='J' onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">BDS
          <input type="radio"  name="acctype"  class="compln check_acc softinput check"   id="acctype_B"  value ='B' onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Claim DV
          <input type="radio"  name="acctype"  class="compln check_acc softinput check"   id="acctype_L"   value ='L' onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Contra
          <input type="radio"  name="acctype"  class="compln check_acc softinput check"   id="acctype_R"   value ='R' onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
         <label class=" container">DCS
          <input type="radio"  name="acctype"  class="compln check_acc softinput check"   id="acctype_E"    value ='E' onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
          <input type="radio"  name="acctype"  class="compln check_acc softinput check"   id="acctype_0"    value ="0"  disabled="disabled" style=" visibility:hidden; "/>
          <span class="checkmark"></span>
        </label>
        
          </div>
            
           <div class="column3"   id="hrd_sel"     title="Hardware" >
            <label style=" margin-bottom:8px;" >Hardware</label>
           <label class=" container">CPU
          <input type="radio"     name="hardtype"  class="compln check_hrd  check"   id="hardtype_1"   value="1" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Thin Client
          <input type="radio"  name="hardtype"  class="compln check_hrd  check"   id="hardtype_2"     value="2" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Display
          <input type="radio"  name="hardtype" class="compln check_hrd  check"   id="hardtype_3"     value="3" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Printer
          <input type="radio"  name="hardtype" class="compln check_hrd  check"   id="hardtype_4"     value="4" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
         <label class=" container">KeyBoard/Mouse
          <input type="radio"  name="hardtype" class="compln check_hrd  check"   id="hardtype_5"     value="5" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Laptop
          <input type="radio"  name="hardtype" class="compln check_hrd  check"   id="hardtype_6"     value="6" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Email
          <input type="radio"  name="hardtype" class="compln check_hrd   check"   id="hardtype_7"     value="7" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Internet
          <input type="radio"  name="hardtype" class="compln check_hrd   check"    id="hardtype_8"    value="8" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
        <label class=" container">Other
          <input type="radio"  name="hardtype" class="compln check_hrd   check"    id="hardtype_9"    value="9" onchange="toggle2(this)" autocomplete="off" />
          <span class="checkmark"></span>
        </label>
         <input type="radio"  name="hardtype" class="compln check_hrd   check"    id="hardtype_0"    value="0"  disabled="disabled" style=" visibility:hidden; " />
          <!--<span class="checkmark"></span>-->
          </div>
  
  <div class="lastcol"  id="prob_detl" >
  
  <div style="100%"> <label style="  margin-left:20px;margin-bottom:20px;" >Problem Details</label>
            <textarea id="probdtl" name="probdtl" class="compln" onkeydown="limitText(this.value)" rows="4" cols="50"></textarea>
                    <!--onchange="limitText(this.value)"-->
            <br>
            <script type="text/javascript">
            function limitText(val)
            {
            var object = document.getElementById("probdtl");
            var countdown = document.getElementById("countdown");
            len=val.length();
             countdown.value=left;
            var left=398-len;
            if (len>397)
            {
            object.value='your message exceeded length Kindly Re-enter msg';
            }
           
            }
            </script>
            
<font size="1">(Maximum characters: 398)
<!--<br>You have <input readonly="readonly"  type="text" name="countdown" id="countdown" size="4" /> characters left. <br>--> </font>        
                      </div>
            <!--<textarea id="remark" class="service" style=" height :100px; width:600px;"></textarea>-->
    <div  style="100%"> <a type="button"   onclick="submit()" class="compln buttonextra" 
    style="margin-left:180px;margin-top:5px;" id="submitbtn"     >Submit</a>
    <a type="button"   onclick="cancel()" class="compln buttonextra" 
    style="margin-left:180px;margin-top:5px; display:none;" id="cancelbtn"     >Resolved</a>
      
    </div>
                  
                </div>
    </div>
    
    <div id="admin_lbl" style="margin-bottom:-3px;margin-top:6px;margin-left:360px; font: italic 17px Georgia, serif;">-------------Administrative Console-----</div>
    
    <div id="hard_ticker" style="margin-left:800px; max-width:200px; font: italic 17px Georgia, serif;" onclick="fetchdata()">
                <select size="2" id="tickerbox" style="max-width:200px;" name="tickerbox">
                    <option value="Select"/>
                </select>
            </div>
   
    <div  id="agent" class="row agent" >

    <div class="column2" style=" width:32%;" >

 <label style="float:left; margin-left:4px;">Assignee </label>
 <div style=" float:left;" >
 <input type="text" name="ass_coordid" class="service" id="ass_coordid" style="width:50px; margin-left:7px;" readonly="readonly" />
 <input type="text" name="ass_coordnm" class="service" id="ass_coordnm" style="width:150px; margin-left:7px;" readonly="readonly" />
 
 </div>
  <div style=" float:left; margin-top:15px;margin-bottom:15px;" id="forwardiv">
  <label style="float:left; margin-left:4px;">Forward To </label>
  <div style="float:left;">  <label class="container" style="margin-left:10px;width:100px;float:left;">Software
  <input type="radio" id="forward_1"  value="1" name="forward" class="service check"  />
  <span class="checkmark"></span>
</label>
<label class="container" style="width:100px;float:left;">Do Not Switch
  <input type="radio" id="forward_2"  checked="checked" value="2" name="forward" class="service check"   />
  <span class="checkmark"></span>
</label>
<input type="radio" id="forward_0" disabled="disabled" value="0" name="forward" class="service check"   style=" visibility:hidden; " />
  
</div>
 </div>
 </div>
 <div  class="column2" style="width:68%; " >
 <label id="cmpldtlb"  style=" float:left; ">&nbsp;Comp. Date</label>
  <input type="text"  class="service"  id="cmpl_dt" readonly="readonly"  style="width:90px;float:left;margin-left:12px;"/>
  <input type="text"  class="service"  id="cmpl_tm" readonly="readonly"  style="width:80px;float:left;margin-left:12px;"/>
  <input type="text"  class="service"  id="brn_id" readonly="readonly"  style="width:60px;float:left;margin-left:12px;"/>
 <input type="text"  class="service"  id="brn_nm" readonly="readonly"  style="width:110px;float:left;margin-left:12px;"/> 
 <input type="text"  class="service"  id="user_id" readonly="readonly"  style="width:60px;float:left;margin-left:12px;"/>
  
  <input type="text"  class="service"  id="user_nm" readonly="readonly"  style="width:160px;float:left;margin-left:12px;"/>
 
 
 <!--<div style=" float:left; margin-top:15px;margin-bottom:15px;" id="mediv">
  <label style="float:left; margin-left:4px;">ISO Medium</label>
  <div style="float:left;">  <label class="container" style="margin-left:10px;width:100px;float:left;">Visit
  <input type="radio" id="medium_1"  value="1" name="medium" class="service check"  />
  <span class="checkmark"></span>
</label>
<label class="container" style="width:100px;float:left;">Phone
  <input type="radio" id="medium_2"  checked="checked" value="2" name="medium" class="service check"   />
  <span class="checkmark"></span>
</label>
<input type="radio" id="medium_0" disabled="disabled" value="0" name="medium" class="service check"   style=" visibility:hidden; " />
  
</div>
 </div>-->
 
 </div>
  
  <div class="column" style="width:32%;" >
  <div style="float:left;  margin-bottom:10px;"> <label  style="float:left;margin-top:8px; margin-left:4px;">Coordinator</label>
  <input type="text" name="coordid" class="service" id="coordid" style="width:50px; margin-left:7px;" 
  readonly="readonly" value="<%=session.getAttribute("empid").toString()%>"/>
 
  <%
  String brvar=session.getAttribute("branch").toString();
  if (brvar.equals("115"))
  {
  %>
  <input type="text" name="coordname" id="coordname" class="service" readonly="readonly" style="width:170px;" value="<%=session.getAttribute("emplnm").toString()%>"/>
  <% } 
                %></div>
   
 <div style="margin-top:10px;float:left; "> <label style="float:left; margin-left:4px;">Problem </label>
  <div style="float:left; margin-left:40px;">  <label class="container" style="width:120px;">Programming
  <input type="radio" id="probtype_1"  value="1" name="probtype" class="service check" onchange="toggle3(this)" />
  <span class="checkmark"></span>
</label>
<label class="container" style="width:120px;">Operational
  <input type="radio" id="probtype_2"  value="2" name="probtype" class="service check" onchange="toggle3(this)" />
  <span class="checkmark"></span>
</label>
<label class="container" style="width:120px;">Hardware/ Network
  <input type="radio" id="probtype_3"  value="3" name="probtype" class="service check" onchange="toggle3(this)" />
  <span class="checkmark"></span>
</label>
<input type="radio" id="probtype_0" disabled="disabled" value="0" name="probtype" class="service check" onchange="toggle3(this)" style=" visibility:hidden; " />
  
</div>

</div>
<div style="float:left;"> <label  style="float:left; margin-left:4px;margin-top:12px;">Status </label>
  <div style="float:left; margin-left:53px;;margin-top:12px;">  <label class="container" style="width:120px;">Closed
  <input type="radio"   id="stattype_1"  value="1" name="stattype" class="service check"/>
  <span class="checkmark"></span>
</label>
<label class="container" style="width:120px;">Process
  <input type="radio"  id="stattype_2"  value="2" name="stattype" class="service check"/>
  <span class="checkmark"></span>
</label>
<label class="container" style="width:120px;">Pending
  <input type="radio"  id="stattype_3"  value="3" name="stattype" class="service check"/>
   <input type="radio"  id="stattype_0" disabled="disabled"  value="0" name="stattype" class="service check" style=" visibility:hidden; "/>
  <span class="checkmark"></span>
</label>
</div>
<div style=" height:34px; width:70%;">
<label style="float:left; margin-left:4px;" id="devlbl" name="devlbl">Resolved By&nbsp;</label> 
<div style="float:left; "> 
<select class="service"  name="devid" id="devid" style="width:190px; margin-left:7px;" onchange="getempnm(this)">
 
 <option> </option>
  <%
       for(int i=0; i<emplist.size(); i++)  
    {
      String row=emplist.get(i);
       String[] temparr;
    temparr  = row.split("#");
        String empval=temparr[0];
        String empnm=temparr[1];
    %>
    <option style="text-align: center;text-justify: inter-word; " id="<%=empval%>" class="devflag" 
    value="<%=empval%>" data-option= "<%=empval %>" ><%=empnm %>
    </option>
    
    <% }%>
 </select></div>
 <!--<div  > 
 <select class="service"   name="selempnm" id="selempnm" style="float:left; width:190px; margin-left:1px;" onchange="getempnm(this)">
 
 <option> </option>
 <%
       for(int i=0; i<emplist.size(); i++)  
    {
      String row=emplist.get(i);
       String[] temparr;
    temparr  = row.split("#");
        String empval=temparr[0];
        String empnm=temparr[1];
    %>
    <option style="text-align: center;text-justify: inter-word; " id="<%=empval%>" class="devflag" 
    value="<%=empval%>" data-option= "<%=empval %>" ><%=empnm %>
    </option>
    
    <% }%>
 </select></div>-->

    </div>
</div> 
  </div>
  
  
   <div class="column"  style="width:68%;" >
   <label style=" float:left;">&nbsp;Commit Date&nbsp;</label> <input type="text"  class="service"  id="crd_dt"  readonly="readonly"  style="width:100px;float:left;"/>
   <label style=" float:left;">&nbsp;Commit Time&nbsp;</label><input type="text"  class="service"  id="crd_tm" readonly="readonly"  style="width:65px;float:left;"/>
  
  <!--<input type="text"  class="service"  id="cmpl_tm" readonly="readonly"  style="width:80px;float:left; margin-left:20px;"/>-->
  <div style=" width: 80px;float:left;margin-left:2px;margin-top:-5%;" class="styled-select blue semi-square">
          <label style=" margin-bottom:8px;" >Branches</label>

       <select style=" width: 80px;" id="sel1"  class="service"  name="sel1" onchange="giveSelection(this.value)">
       <option>
       Br.id
       </option><%
       String btemp="";
       for(int m=0; m< list1.size(); m++)  
    { String str=list1.get(m);
        if (m!=0)
        {
       btemp= list1.get(m-1).substring(0, 3);
        }
        
        String  brcd=str.substring(0, 3);
        String brnm=str.substring(3);
   if (!brcd.equals(btemp)){ 
   //this condition intends to check duplicate branches while populating branch names
   %>
    <option style="text-align: center;text-justify: inter-word;" value= "<%=brcd %>"  ><%=brnm.trim()%></option>
    <% 
    }}%>
       </select>
       </div>
   
   <div style=" width: 70px;float:left;margin-left:2px;margin-top:-5%;" class="styled-select blue semi-square">
      <label style=" margin-bottom:8px;" >Pending</label>
       <select style=" width: 70px;" id="openselect"  class="service"   title="Only Open"  name="openselect" onchange="getinfo2(this.value)">
       <option>
        
       </option><%
       for(int i=0; i<openlist.size(); i++)  
    { 
    String cmpid=openlist.get(i); 
     String brcode=cmpid.substring(0, 3);
    String valsub=cmpid.substring(3);
    int id = Integer.parseInt(valsub); 
     String dispid= "";
    if (id==0){dispid="Select";}
    else{dispid=String.valueOf(id);}
    
    %>
    <option style="text-align: center;text-justify: inter-word;" value="<%=openlist.get(i) %>" data-option= "<%=openlist.get(i) %>" ><%=brcode+dispid %></option>
    <% }%>
       </select>
       </div>
   <div style=" width: 70px;float:left;margin-left:5px;margin-top:-5%;" class="styled-select blue semi-square">
         <label style=" margin-bottom:8px;" >All</label>
       <select style=" width: 70px;" id="outselect"  title="All" class="service"  name="outselect"  onchange="getinfo2(this.value)">
       <option>
      
       </option><%
       for(int j=0; j<vallist.size(); j++)  
    { String cmpid=vallist.get(j); 
     String brcode=cmpid.substring(0, 3);
    String valsub=cmpid.substring(3);
    int id = Integer.parseInt(valsub); 
     String dispid= "";
    if (id==0){dispid="Select";}
    else{dispid=String.valueOf(id);}
    %>
    <option style="text-align: center;text-justify: inter-word;" value="<%=vallist.get(j) %>" data-option= "<%=vallist.get(j) %>" ><%=brcode+dispid %></option>
    <% }%>
       </select>
       </div>
        <div style=" width: 70px;float:left;margin-left:5px;margin-top:-5%;" class="styled-select blue semi-square">
         <label style=" margin-bottom:8px;" >Process</label>
       <select style=" width:70px;" id="processdrop"  title="In Process" class="service"  name="processdrop"  onchange="getinfo2(this.value)">
       <option>
        
       </option>
       <%
       for(int i=0; i<processlist.size(); i++)  
    { 
    String cmpid=processlist.get(i); 
     String brcode=cmpid.substring(0, 3);
    String valsub=cmpid.substring(3);
    int id = Integer.parseInt(valsub); 
     String dispid= "";
    if (id==0){dispid="Select";}
    else{dispid=String.valueOf(id);}
    
    %>
    <option style="text-align: center;text-justify: inter-word;" value="<%=processlist.get(i) %>" data-option= "<%=processlist.get(i) %>" ><%=brcode+dispid %></option>
    <% }%>
       </select>
       </div>
        <div style=" width: 70px;float:left;margin-left:5px;margin-top:-5%;" class="styled-select blue semi-square">
         <label style=" margin-bottom:8px;" >Done</label>
       <select style=" width:70px;" id="outselect2"  title="Done" class="service"  name="outselect2"  onchange="getinfo2(this.value)">
       <option>
        
       </option><%
       for(int j=0; j<vallist2.size(); j++)  
    { String cmpid2=vallist2.get(j); 
     String brcode2=cmpid2.substring(0, 3);
    String valsub2=cmpid2.substring(3);
    int id2 = Integer.parseInt(valsub2); 
     String dispid2= "";
    if (id2==0){dispid2="Select";}
    else{dispid2=String.valueOf(id2);}
    %>
    <option style="text-align: center;text-justify: inter-word;" value="<%=vallist2.get(j) %>" data-option= "<%=vallist2.get(j) %>" ><%=brcode2+dispid2 %></option>
    <% }%>
       </select>
       </div>
       
                    <div style="float:left; margin-top:40px;"> <label style=" margin-left:220px;margin-bottom:20px;">Remarks</label>
            <div> <textarea id="remark" class="service" style=" height :100px; width:600px;" rows="4" cols="50"></textarea></div>
  </div>
  <div id="devdiv" style="float:left;margin-top:40px;">  
  &nbsp;Dev. Date&nbsp;<input id="dev_dt"  type="text"  style="width:100px;" readonly= "readonly" class="service pickdate" />
  &nbsp;Dev. Time&nbsp;<input class="service" type="text"  id="dev_tm" readonly="readonly" style="width:65px;"/>
                       <!--&nbsp; Working Hours-->
                        <input id="dev_hr" hidden="hidden"  class="service" type="text" readonly="readonly"  style="width:80px;"/>
  
  
  </div><div style="float:left;">
    <input type="button" onclick="commit_btn()"  id="commitbtn"  value="Commit"  class="buttonextra service" style="width:90px;margin-left:180px;margin-top:5px;"/>
   <!--<a  type="button"  onclick="commit_btn()"id="commitbtn" 
  style="margin-left:180px;margin-top:5px;"     ></a>-->
  
  </div>
  </div>
  
</div>

 <div id="reportbox" title="ISO report Search" style="display:none" >
 <div> 
   <select style=" width:100px;" id="selmon"  title="Search with Coord"  class="service"  name="selmon"   >
      <optgroup label = "Month">  <option>
        
       </option>
       <option> JAN </option>
  <option> FEB </option>
     <option> MAR  </option> 
        <option> APR </option>
         <option> MAY </option>
          <option> JUN </option>
           <option> JUL </option>
            <option> AUG </option>
             <option> SEP </option>
              <option> OCT </option>
               <option> NOV </option><option> DEC </option></optgroup>
       </select> 
        <select style=" width:100px;" id="selyear"  title="Search with Year"  class="service"  name="selyear"   >
  
        <optgroup label = "Year">   <option>
        
       </option>
       <option>2014</option>
  <option>2015</option>
     <option>2016 </option>
        <option>2017</option><option>2018</option><option>2019</option></optgroup>
       </select> 
       
   <select style=" width:100px;" id="empser"  title="Search with Coord"   class="service"  name="empser"   >
      <optgroup label = "Resolved By">   <option>
        
       </option>
      
     <%   ArrayList<String> clist = new ArrayList();
      String emptp2=session.getAttribute("empltp").toString();
      if (emptp2.equals("AH")){emptp2="H";}
      if (emptp2.equals("AC")){emptp2="C";}
    //  if (!session.getAttribute("empltp").toString().equals("U")) { 
    
     clist.addAll(db1.getempnm(emptp2));
       for(int i=0; i<clist.size(); i++)  
    {
      String row1=clist.get(i);
       String[] temparr1;
    temparr1  = row1.split("#");
        String empva1=temparr1[0];
        String empnm1=temparr1[1];
    %>
    <option style="text-align: center;text-justify: inter-word; " id="<%=empva1%>" 
    value="<%=empva1%>"  ><%=empnm1 %>
    </option>
    
    <% }
  //  }
    %>
    </optgroup>
       </select> 
     <input type="button" id="repbtn" name="repbtn" value="Find" onclick="getiso()"  class="buttonextra service" />
       <input type="button" id="printbtn" name="printbtn" value="Print" onclick="PrintElem()"  class="buttonextra service" />
       <!--<input type="button" id="orrepbtn" name="orrepbtn"  onclick="location.href('Oracle_Report.jsp');" value="Oracle_Report "  class="buttonextra service" />-->
       </div>
  
       <div id="resultdisp" class="container" > 
      
       </div>
    </div>   <!--endof dialog-->
            
          
<%System.gc();  %>

       <!--<script  type="text/javascript"   src="resources/js/jquery-ui.js"></script>-->
       
        <script type="text/javascript" src="resources/js/jquery-3.1.1.js"></script>
         <script type="text/javascript" src="resources/js/dropdownevent.js"></script>
           <!--<script type="text/javascript" src="resources/js/IT_js.js"></script>-->
      
        <script type="text/javascript"  src="resources/js/enabler.js"></script>
        <script type="text/javascript"  src="resources/js/submit.js"></script>
 
        </form> 
         <!--<input type="button" onclick="tableToExcel('resultdisp', 'name', 'myfile.xls')" value="Excel" class="buttonextra service" >-->
       <!--<a id="dlink" style="display:none;" ></a>-->
   
   
    </body>
</html>