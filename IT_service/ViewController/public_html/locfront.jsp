<!DOCTYPE html>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@page import="java.util.ArrayList,view.DBCoding" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
  
    <link type="text/css" rel="stylesheet" href="resources/css/w3.css"/>
  <link type="text/css" rel="stylesheet" href="resources/css/melon.datepicker.css"/>
    <script type="text/javascript" src="resources/js/jquery-3.1.1.js"></script>
            <link type="text/css" rel="stylesheet" href="resources/css/jquery-ui.css"/>
         <!--<link type="text/css" rel="stylesheet" href="resources/css/style.css"/>-->   
      <script src="resources/js/jquery-ui.js"></script>
        <link type="text/css" rel="stylesheet" href="resources/css/boostrap.min.js.css"/>
        <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
      
      <!-- Glaringly new instance at 11:54-->
        <script type="text/javascript">

    function clientchange(evt)
    { 

    var str = evt.value;
    var clid= str.split(":")[1];
    var clnm=str.split(":")[0];
    if(evt.id=='clnm'){
    document.getElementById('clnm').value = clnm;
    document.getElementById('clid').value = clid;}
    else
    {
    document.getElementById('clnm2').value = clnm;
    document.getElementById('clid2').value = clid;
    }

     }
 function submitaction()
  {
        var clid=document.getElementById('clid').value;
        var locadd1=document.getElementById('add1').value;
        var locadd2=document.getElementById('add2').value;
        var locadd3=document.getElementById('add3').value;
        var cityid=document.getElementById('cityid').value;
        var stat=document.getElementById('stat').value;
        var sdate=document.getElementById('stdate').value;
        var endate=document.getElementById('endate').value;
        //var emp='3213';
        //document.getElementById('idemp').value;
      
  if((locadd1=="") || (locadd2=="") ||  (cityid==""))
  { alert("   Haven't specified city and city id or address line 1 and 2.  ");
   return false; 
  }
     //   alert(empid);
   if ((clid!="") )
   {if ((sdate=="")&&(endate=="") ) {
   alert("   Haven't specified 'Start' and 'End' dates.  ");
   return false; 
   }
   else 
   {
     var dates = new Date(sdate);
    var datee = new Date(endate);
        var timeDiff = (datee.getTime() - dates.getTime());
    var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
   
   if (diffDays<=0){
   alert("End date is earlier than risk Start Date");
   return false;
   }//end of if date difference is less than ZERO
   
   }//end of else if date values are not null
   
   }//end of case body if client selected
 
    if (stat=="Active"){stat="A";}
    else {stat="S";}
  
        $.ajax({
      type: 'POST',
      url: 'textmatch.jsp',
      data: {stat:stat, cityid:cityid, locadd3:locadd3, locadd2:locadd2, locadd1:locadd1, clid:clid, sdate:sdate, endate:endate, t:"791"},
      success: function(code) {
   
//         document.getElementById('clid').value='';
//       document.getElementById('add1').value='';
//        document.getElementById('add2').value='';
//        document.getElementById('add3').value='';
//        document.getElementById('cityid').value='';
//        document.getElementById('stat').value='';
//       document.getElementById('stdate').value='';
//       document.getElementById('endate').value='';
         $('#locid').html(code);
         
      }
     
   });
  
  }
    function selectionchange()
    { 
    var e = document.getElementById("provlist");
 
   // var str = e.options[e.selectedIndex].value;
    var str = e.value;
    var cid= str.split(",")[0];
    var termin=str.split(",")[1];
    var city=termin.split("#")[0];
    var kurt=termin.split("#")[1];
    var aid=kurt.split(".")[0];
    var brodie=kurt.split(".")[1];
    var area=brodie.split("+")[0];
    var mantis= brodie.split("+")[1];
    var psid= mantis.split(":")[0];
    var prov=mantis.split(":")[1];
    document.getElementById('cityid').value = cid;
    document.getElementById('city').value = city;
    document.getElementById('area').value =area;
   document.getElementById('provlist').value=prov;
   document.getElementById('provid').value=psid;
    document.getElementById('areaid').value=aid;
 
 }

    $(document).ready(function(){
               
                      $("#area").on('change keyup paste',function() {
                     var value = $(this).val();
                     if((value.length >5)&&(value.length <=10)) {
               $.ajax({
      type: 'POST',
      url: 'textmatch.jsp',
      data: { q: value, t: "683" },
      success: function(response) {
         $('#listar').html(response);
      }
   });}     });
                $("#code").on('change keyup paste',function() {
                     var value = $(this).val();
                     if((value.length >1)) {
               $.ajax({
      type: 'GET',
      url: 'textmatch.jsp',
      data: { q: value, t:"c" },
      success: function(response) {
         $('#footer').html(response);
      }
   });}     });
               
                 $("#add1").on('change keyup paste',function() {
               var prov=document.getElementById('provid').value;
                var cityid=document.getElementById('cityid').value;
                     var value = $(this).val();
                     if((value.length >10)||(value=='godown')||(value=='Godown')) {
               $.ajax({
      type: 'GET',
      url: 'textmatch.jsp',
      data: { q: value, city:cityid,t: "a" },
      success: function(response) {
     // alert("prov : "+prov+" city : "+cityid)
         $('#result').html(response);
      }
   });}     });   });
  
   $(function() {
       
            $( "#stdate" ).datepicker({
              datepicker: true,minDate: new Date(1932,01,01),maxDate: new Date(2032,01,01), changeMonth: true, 
    changeYear: true,  yearRange: "-100:-16",dateFormat:"dd-M-y"});
            
              $( "#endate" ).datepicker({
               datepicker: true,
            changeMonth: true, 
    changeYear: true, 
               dateFormat:"dd-M-y"});  
                $( "#stdate2" ).datepicker({
              datepicker: true,minDate: new Date(1932,01,01),maxDate: new Date(2100,01,01), changeMonth: true, 
    changeYear: true,  yearRange: "-100:-16",dateFormat:"dd-M-y"});
            
              $( "#endate2" ).datepicker({
               datepicker: true,
            changeMonth: true, 
    changeYear: true, 
               dateFormat:"dd-M-y"});  
               
         });
    
  function checkl(el) {
  if ((el.value.length < 2)||isNaN(el.value)) {
    el.value='';
    return false; 
  } else if ((el.value.length === 4)||el.value.length<7){
  return true;}
}
  </script>
 <style type="text/css">
 
 
 body
 { background-image: url("pakistani_industry_chrom.jpg");
  background-repeat: no-repeat;
  background-size: 108.0%;
 }
.form-style-9{
    max-width: 820px;
    background:#0991e5
;
    padding: 30px;
    margin: 50px auto;
    box-shadow: 1px 1px 25px rgba(0, 0, 0, 0.35);
  
}

.field-style2{
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    padding: 8px;
    outline: none;
    border: 1px solid #B0CFE0;
    -webkit-transition: all 0.30s ease-in-out;
    -moz-transition: all 0.30s ease-in-out;
    -ms-transition: all 0.30s ease-in-out;
    -o-transition: all 0.30s ease-in-out;

}

.showtray:hover+.tray
{display: block;
}

 input[type='button'] {
 background-color: #140038;
	border: 0;
	color: #ffffff;
	cursor: pointer;
	min-width:3%;max-width:45%;
	padding: .000001em;}
.sp{
  content: "\21B4";
}  
legend{
  display:none;
}
h2{
  border-bottom:2px solid gray;
  margin:1em 0;
}



</style>
    </head>
    <body class="w3-light-grey">
   <form class="form-style-9">
  
    <!--<input type="hidden" id="idemp" name="idemp" value="3213" />-->
   <!--<input type="hidden" id="idemp" name="idemp" value="<%=session.getAttribute("emp").toString()%>" />-->
    <h2 style=" border-bottom: 0.1px; color: White; margin-right: 65%;   font-family: Verdana; font-size:13.0pt;">EFU General Insurance LTD</h2>
    <div class="tray" style="height:37px; width:319px;">
      <input type="button" id="retbtn" name="retbtn" onclick="window.location.href = 'login.jsp';"
                       style=" font-weight: bolder;   font-size: 21pt;  height:35px; width:70px;"
                       value="&#8592;"/>
                <input type="button" id="refresh" name="refresh" onclick=" location.reload();"
                       style=" font-weight: bolder;   font-size: 21pt;  height:35px; width:70px;"
                       value="&#8634;"/>
                 <input type="button" id="submit"
                       style=" font-weight: bolder; font-size: 21pt;  height:35px; width:70px;"
                       value="&#8628;" name="submit" onclick="submitaction()"/>
                <input type="button" style=" font-weight: bolder; font-size: 21pt; height:35px; width:70px;"
                                                        value="&#8981;"     data-toggle="modal" data-target="#myModal"/>
            </div>
    <%
    session.setAttribute("branch", "384");
    String brid=session.getAttribute("branch").toString();
    DBCoding db1=new DBCoding();  
    ArrayList<String> list1 = new ArrayList();
    ArrayList<String> list2 = new ArrayList();
    ArrayList<String> list3 = new ArrayList();
        list1.addAll(db1.getAll());
        // list2.addAll(db1.getarea());
         list3.addAll(db1.getAllclient(brid));
         %>
          
         <h2 style=" border-bottom: 0.1px;  color: White;  margin-left: 88.6%; font-family: Verdana; font-size:15.0pt;">Location</h2>
    <fieldset style=" background-color:White;color: navy;"><legend>Location</legend>
    <table  width="611"><tr><td  width="109">
    <input type="text" class="field-style2"  name="provid" id="provid" style="  width: 100%;min-width:5%;" readonly="readonly" placeholder="Prov. ID" /> </td><td >
    <input type="text" class="field-style2"  id="provlist" name="provlist"  list="listprov"  style=" width:100% " onchange="selectionchange()"   placeholder="Province" />

 <datalist id="listprov"  >
    <option selected="selected" value= "Please Select">Please Select</option>
    <%
       for(int i=0; i<list1.size(); i++)  
    { %>
    <option value= "<%=list1.get(i) %>" ><%=list1.get(i)  %></option>
    <% }%></datalist> 
    </td><td  style="width:70px;"><div class="field-style2"  style="width:70px;"  id="locid"  ></div> </td></tr> 
    <tr><td  width="109"> <input type="text" style="  width: 100%;min-width:5%;" class="field-style2"  name="cityid" id="cityid" readonly="readonly" placeholder="City ID" /> </td>
    <td width="410"> <input type="text" name="city" id="city"  style="  width: 100%;min-width:5%;" class="field-style2"   placeholder="City" /></td></tr>
    <tr><td  width="109"> <input type="text"  style="  width: 100%;min-width:5%;" class="field-style2"   name="areaid" id="areaid"  placeholder="Area. ID"   readonly="readonly" />   </td>
    <td width="410"> <input type="text" name="area" id="area" list="arlist"  style="  width: 100%;min-width:5%;" class="field-style2"   placeholder="Area" />
 <div id="listar"> </div>
    </td>

    </tr>
    <tr><td width="109">&nbsp;&nbsp;&nbsp;&nbsp;Location </td><td width="410"><input type="text" name="add1" id="add1" style="  width: 100%;min-width:5%;" class="field-style2"  placeholder="Location Address" /></td></tr>
    <tr><td width="109">&nbsp;</td><td width="410"> <input type="text" name="add2" id="add2" style="  width: 100%;min-width:5%;" class="field-style2"  placeholder="Location Address" /></td></tr>
    <tr><td width="109">&nbsp;</td><td width="410"> <input type="text" name="add3" id="add3" style="  width: 100%;min-width:5%;" class="field-style2"  placeholder="Location Address" /></td></tr>
    <tr><td width="109">&nbsp;&nbsp;&nbsp;&nbsp;Geographical &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Area </td><td width="410"> <input type="text" name="geo" id="geo" style="  width: 100%;min-width:5%;" class="field-style2"  placeholder="Geographical Area" /></td></tr>
    <tr><td width="109">&nbsp;&nbsp;&nbsp;&nbsp;Status </td><td width="410"> <select  name="stat" id="stat" style="  width: 100%;min-width:5%;" class="field-style2"   >
    <option>Active</option><option>InActive</option></select></td></tr></table>

    </fieldset>

 <!--<div class=" showtray" align="left" style="float:left; height:35px; width:45px;"></div>-->
    <h2 style=" border-bottom: 0.1px;  color: White;  margin-left: 80%; font-family: Verdana; font-size:15.0pt;">Location Client</h2>
    <fieldset  id="locl" style=" background-color:#5d0bea; "><legend style=" color: Silver; font-effect: outline;">Location Client</legend>

  
    <table width="659"><tr><td   colspan="1">
    <input type="text" style="width:80px;" class="field-style2"  name="clid" id="clid" placeholder="Cl.ID"     readonly="readonly" /> </td><td colspan="10">
    <input type="text" style="width:257px;" class="field-style2"  onchange="clientchange(this)" name="clnm" id="clnm" placeholder="Client Name" list="listcl"  />
    <datalist id="listcl"  >
    <option selected="selected" value= "Please Select"  >Please Select</option>
    <%
       for(int k=0; k<list3.size(); k++)  
    { %>
    <option color="blue" value= "<%=list3.get(k)  %>" ><%=list3.get(k)  %></option>
    <% }%></datalist> 
    </td><td  colspan="4"><input id="stdate" name="stdate" style="width:190px;"  onfocus="hideprev()"  class="field-style2"   placeholder="29-MAR-17"  maxlength="9" readonly='readonly'/></td>
    <td  colspan="4"><input id="endate" name="endate" style="width:190px;"  onfocus="hideprev()"  class="field-style2"   placeholder="29-MAR-17"  maxlength="9" readonly='readonly'/></td>
    </tr>
    <tr><td   colspan="1">
    <input type="text" style="width:80px;" class="field-style2"  name="clid2" id="clid2"  placeholder="Cl.ID"   readonly="readonly" /> </td><td colspan="10">
    <input type="text" style="width:257px;" class="field-style2"  name="clnm2" id="clnm2"  placeholder="Client Name"  list="listcl"    onchange="clientchange(this)" />
 
    </td><td  colspan="4"><input id="stdate2" name="stdate2" style="width:190px;"  onfocus="hideprev()"   class="field-style2"  placeholder="29-MAR-17"  maxlength="9" readonly='readonly'/></td>
    <td  colspan="4"><input id="endate2" name="endate2" style="width:190px;"  onfocus="hideprev()"  class="field-style2"   placeholder="29-MAR-17"  maxlength="9" readonly='readonly'/></td>

    </tr> 
    </table></fieldset>

           
            <h2 style=" border-bottom: 0.1px;  color: White;  margin-left: 80.5%; font-family: Verdana; font-size:12.0pt;">Existing Locations</h2>
            <fieldset id="exloc" style=" background-color:#5d0bea; "><legend style=" color: Silver; font-effect: outline;">Existing Locations</legend>
<div id="result">
<!--<b>Name will be displayed here</b>-->
</div>

</fieldset>


            <!-- Trigger the modal with a button -->


<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Search With Location ID </h4>
      </div>
      <div class="modal-body">
        <input id="code" name="code"  onblur="return checkl(this)"/>
      </div>
      <div id="footer" class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
        </form> 
    </body>
</html>




