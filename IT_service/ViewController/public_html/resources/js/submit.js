            var request; 

    
            function submit()
                { 
              document.getElementById('submitbtn').style.display="none";
                  setTimeout(function () {
             document.getElementById('submitbtn').style.display="block";  }, 7 * 1000);   
           // alert('submit triggered ');
            var accounts =[]; var classes=[];var hrd =[];
  
             var cid= document.getElementById('company').value;
               var userid=  document.getElementById('idstore').value;
               var brid=  document.getElementById('brid').value;
                 var dtl=  document.getElementById('probdtl').value;
              //var msgtm=  document.getElementById('probdtl').value;
               dtl = dtl.substring(0, 400);
              
                 var comptype=  document.querySelector('input[name="comptype"]:checked').value;
                var accn,clas="";
                var attn="429";
    
                 var boolhrd= (comptype=='2');
                 var boolsft=(comptype=='1');
                   var boolsys=false;
                  var booldtl=!$.trim(dtl);
                   if (!booldtl){
                  if (boolsft)  
                  {
                    var systype=  document.querySelector('input[name="systype"]:checked').value;
   
                     if (systype!='0')      {
                     
                      if ((brid>299 &&brid <399) &&( brid!=359  &&   brid!=372 ) )  {
                       
                     attn="NZMA";  
                        }//if north zone
                       
                        //if s zone
                   else  {
                  var classElements = document.getElementsByClassName('check_class');
               var inputElements = document.getElementsByClassName('check_acc');
               
                for(var i=0; i<inputElements.length; i++){
                  if (inputElements[i].disabled == false){
               
                 if(inputElements[i].checked){
                       accounts.push(inputElements[i].value) ;  
                               accn=inputElements[i].value;}
                 }
                   }
        
            for(i=0; i<classElements.length; i++){
            if (classElements[i].disabled == false){
           
            if(classElements[i].checked){
                   classes.push(classElements[i].value) ; 
                   clas=classElements[i].value;}
                 }
           }
            if (systype=="1"||systype=="9")    //if UW or RENEWALS
                     {
                     if(clas==1||clas==2)
                     {
                   //  attn="509";
                   //  attn="EXTC"; // amendement requested 19/dec/2018
                   attn="UNDC";
                     }
                     if(clas==4||clas==7||clas==9)
                     {
                   //  attn="2064";
                     attn="UNDC";
                     }
                     if(clas==3||clas==5||clas==6 )
                     {
                    // attn="2199";
                      attn="ARSC";
                     }}
                     
                      if (systype=="2") //if Claim
                     {
                    if(clas==3||clas==5||clas==6 )
                     {
                    // attn="2199";
                      attn="ARSC";
                     }
                    else { 
                   // attn="2494";
                     attn="CLMC";
                    }
                     }
                     
                      if (systype=="3") //if Accounting
                     {
                     clas=0;
                        if (accn=="L")
                       { 
                      // attn="2494";
                        attn="CLMC";
                       }
                    else { 
                   // attn="2026";
                     attn="ACCC";
                    } }
                   
                      if (systype=="4")  //if Payroll
                     {
                     
                    // attn="2199";
                    attn="ARSC";
                     }
                      if (systype=="6"||systype=="7"||systype=="13") ////if GL Month-end or others
                     {
                     
                    // attn="429";
                    attn="AAAC";
                     }
                      if (systype=="8")// if co insurance there is no systype=="10"
                     {
                     
                   //  attn="2064";
                    attn="UNDC";
                     }
                       if (systype=="11")//  if service charges there is no systype=="10"
                     {
                     
                   //  attn="2064";
                    attn="UNDC";
                     }
             if (systype=="12")// if bank reconciliation there is no systype=="10"
                     {
                     
                    // attn="2199"; //ARSC 
                   attn="ARSC";
                     }
           hrd.push('0') ;
              
                     } //id south zone 
                  }
                  else{ 
                  alert("Please select a System");
                  accounts.push('0') ;classes.push('0') ; hrd.push('0') ;}  //if no systenm type selected then no compaint
           }
         else  if (boolhrd){
         
        
         var hardwareElements = document.getElementsByClassName('check_hrd');
    for(i=0; i<hardwareElements.length; i++){
  
    if(hardwareElements[i].checked){
           hrd.push (hardwareElements[i].value) ;         } 

           }
           accounts.push('0') ;classes.push('0') ;
          
          
           if ((brid>299 &&brid <399) &&( brid!=359  &&   brid!=372 ) )  {
                       
                     attn="NZMA";  
                        }//if north zone
       else { attn="AADH";
        }//if szone
          
          
             }
           var accJson = JSON.stringify(accounts); 
           var clsJson = JSON.stringify(classes); 
           var hrdJson = JSON.stringify(hrd); 
           
 //  alert(userid+" "+brid+" "+clsJson+" "+accJson+" "+hrdJson+" "+systype+" "+comptype+" "+attn+cid);
   
         if (systype!='0' &&(accn!='0' || clas!='0'))  
      {  if(window.XMLHttpRequest){  $.ajax({
      type: 'POST',
      url: 'liason.jsp', 
      // contentType: 'application/json; charset=utf-8',
       // cache: false,
     //  crossDomain: true,
      data: {userid:userid,brid:brid,cls:clsJson,acc:accJson,hrd:hrdJson,sys:systype,cmp:comptype,dtl:dtl,attn:attn,company:cid, t:"insert"},
      
      success: function(code) {
  
     var resp=code.split("|");
    var temper=resp[1];
       document.getElementById('msgdisp').style.display="block";
       document.getElementById('msgdisp').style.color = '#18137f';
     document.getElementById('msgdisp').innerHTML= "Insertion Successful, Sir. Complain id: "+temper+" Please wait system updating.";

     setTimeout(function () {
            $("#msgdisp").html(''); }, 5000); 
                  
       setTimeout(function () {
               location.reload();  }, 5000); 
          
       
      },
      error: function(xhr, ajaxOptions, thrownError) {
     
            document.getElementById('msgdisp').style.display = 'block';

       document.getElementById('msgdisp').innerHTML= "Server busy, Sir."+xhr+thrownError;

  setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 1000); 
   
      }
   });}
   else if(window.ActiveXObject){
          try  
            { 
        var url="liason.jsp?t=insert&userid="+userid+"&brid="+brid+"&cls="+clsJson+"&acc="+accJson+"&hrd="+hrdJson+"&sys="+systype+"&cmp="+comptype+"&dtl="+dtl+"&attn="+attn+"&company="+cid;  
             //  userid:userid,brid:brid,cls:clsJson,acc:accJson,hrd:hrdJson,sys:systype,cmp:comptype,dtl:dtl,attn:attn,company:cid, t:"insert"
    	  request=new ActiveXObject("Microsoft.XMLHTTP");   
    	  request.onreadystatechange=IEreqsubmit;  
          request.open("GET",url,true);  
          request.send();  
            }
    	  catch(e)  
            {  
            alert("Unable to connect to server");  
            }  
      }  
                   }//if sel;ection not empty
     
   
   }//only do if key elemnts not null
   else { document.getElementById('msgdisp').style.display = 'block';

       document.getElementById('msgdisp').innerHTML= "please select prob. type and enter prob. details, Sir.";

  setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 5 * 1000); }
    }
  
   function IEreqsubmit(){  
        if(request.readyState==4){ 
           var code=request.responseText;
            var resp=code.split("|");
    var temper=resp[1];
       document.getElementById('msgdisp').style.display="block";
       document.getElementById('msgdisp').style.color = '#18137f';
     document.getElementById('msgdisp').innerHTML= "Insertion Successful, Sir. Complain id: "+temper+" Please wait system updating.";

     setTimeout(function () {
            $("#msgdisp").html(''); }, 2 * 1000); 
                  
       setTimeout(function () {
               location.reload();  }, 1 * 1000); 
          
           }
           
           
           }
 
    
function ammendlink(e)
{
var link=e.href;
link=link+'&brcode='+document.getElementById("brcode").value;
document.getElementById(e.id).href=link;
}

  function execute2()
  {
   $('#menu').empty();

        var logid=document.getElementById('logid').value;
       var brcode= document.getElementById('brcode').value;
        var pass=document.getElementById('pass').value;
       
      document.getElementById('empid').value=logid;
   //alert("submit button fired ");
      $.ajax({
      type: 'POST',
      url: 'textmatch.jsp',
      data: {pass:pass, logid:logid, t:"login"},
      success: function(code) {
      //alert(code);
        var datar = code.split("|");  
     // alert(datar[1]);   
         //  alert(datar[1]);
         if(datar[1]!="true"){ 
          $('#msgdisp').append('<ul>');
       //  alert("Login not Successful");
         document.getElementById('msgdisp').style.display = 'block';
         document.getElementById('msgdisp').style.color = "red";
       document.getElementById('msgdisp').innerHTML=thrownError+":   Login not Successfull." ;

  setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 5 * 1000);}
         else { //alert("array index 1  "+datar[1]);
  //       window.location.href="frontpage.jsp?empid="+logid+"&brcode="+brcode;
                alert("Login Successful");
                 $.ajax({type: 'POST',
      url: 'textmatch.jsp',
      data: {userid:logid,t: "applist"},
      success: function(code) {
   //alert(code);
      
      var respond=code.split("|");
      //alert(respond);
      var emp_log="";
        $('#menu').empty(); 
       $('#menu').append('<ul>');
        for(var t=1; t<=respond.length;t++ ){
        var box=respond[t].split("+");
        emp_log=box[2];
       // alert(emp_log);
           $('#menu').append('<li><a onclick="ammendlink(this)" href='+box[1]+'?empid='+logid+'&brcode='+brcode+'>'+box[0]+'</a></li>'); 
        }  
        $('#menu').append('</ul>');
      //  alert(emp_log);
      // $('#empdiv').innerhtml(emp_log);
      document.getElementById('empdiv').innerHTML = emp_log;

     
      },
      error: function(xhr, ajaxOptions, thrownError) {
             document.getElementById('msgdisp').style.display = 'block';

        document.getElementById('msgdisp').style.color = "red";
       document.getElementById('msgdisp').innerHTML=thrownError+":   Login not Successfull." ;

  setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 5 * 1000);
    
      }
      });   

        // window.location.href="frontpage.jsp?empid="+logid+"&brcode="+brcode;
         }
      },
      error: function(xhr, ajaxOptions, thrownError) {
             document.getElementById('msgdisp').style.display = 'block';

        document.getElementById('msgdisp').style.color = "red";
       document.getElementById('msgdisp').innerHTML=thrownError+":    Login not Successfull." ;

  setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 5 * 1000);
    
      }
      });
  }
  
     function closing()
        {
    var id =document.getElementById('complnid').value;
          var COMPLN_DTL_05 = document.getElementById('COMPLN_DTL_05').value;
       //   alert("id:  "+id+"comments:   "+COMPLN_DTL_05);
    $.ajax({
      type: 'POST',
      url: 'liason.jsp',   cache:false,
       
      data: {id:id,close:COMPLN_DTL_05,t:"close"},
    
      success: function(code) {
      var res=code.split('|');
      
       document.getElementById('msgdisp').style.display = 'block';

       document.getElementById('msgdisp').innerHTML= res;

  setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 5 * 1000); 
       setTimeout(function () {
     location.reload();  }, 2 * 1000); 
      
      },
      error: function(xhr, ajaxOptions, thrownError) {
     
            document.getElementById('msgdisp').style.display = 'block';

       document.getElementById('msgdisp').innerHTML= "closing error."+xhr+thrownError;

  setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 5 * 1000); 
   
      }
      
   });
     }
     //end of close
   
   
  function cancel()
        {
        var id =document.getElementById('complnid').value;
      //  alert(id);
   $.ajax({
      type: 'POST',
      url: 'liason.jsp',   cache:false,
      //data: {cls:classes,acc:accounts,hrd:hrd,sys:systype,cmp:comptype, t:"insert"},
      data: {id:id,t:"cancel"},
    
      success: function(code) {
       
      var res=code.split('|');
      
       document.getElementById('msgdisp').style.display = 'block';

       document.getElementById('msgdisp').innerHTML= res;

  setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 5 * 1000); 
             setTimeout(function () {
     location.reload();  }, 2 * 1000); 
     
      },
      error: function(xhr, ajaxOptions, thrownError) {
     
            document.getElementById('msgdisp').style.display = 'block';

       document.getElementById('msgdisp').innerHTML= "closing error."+xhr+thrownError;

  setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 5 * 1000); 
   
      }
      
   });
   
         }
         //end of cancel function
  
  function commit_btn()
    { 
    
  
       var monthNames = ["JAN", "FEB", "MAR", "APR", "MAY", "JUN",
  "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"
];
  
    var accounts =[]; var classes=[];var hrd =[];
  var forwardto="";
  var complnid=  document.getElementById('complnid').value;
  
     var emptp=document.getElementById("emptph").value;
   
       if ( emptp=="C"||emptp=="AC"||emptp=="D"||emptp=="AD"||emptp=="AA")
       {
       forwardto="1";
       }
      else {
         forwardto=  document.querySelector('input[name="forward"]:checked').value;
       }
      
   var crid=  document.getElementById('coordid').value;
 

   var devid="";
   if ( document.getElementById('devid').value!='')
  {devid =document.getElementById('devid').value;
  //setting crid as logged in coordid not original assignee i.e. changing from original assignee
  crid=devid;
 //
  }
  else
  {devid=crid;}
  //alert("devid "+devid+"crid "+crid);
  
  
  if ( document.getElementById('remark').value)
  { var remark=  document.getElementById('remark').value;}
  else
  {remark="   ";}
  
 // if (  document.getElementById('dev_hr').value!='')
 // {var dev_hr=  document.getElementById('dev_hr').value;}
 // else
  dev_hr="0";
  
  var probtype=  document.querySelector('input[name="probtype"]:checked').value;
 var stattype=  document.querySelector('input[name="stattype"]:checked').value;
      
        
 // alert (emptp+"  :epmtp ,forwardto:   "+forwardto+" ,complnid   "+complnid+",crid   "+crid+", devid    "+devid+", dev_hr  "+dev_hr+", probtype  "+probtype+", stattype  "+stattype+",   remark"+remark);
 
     if (($.trim(complnid))&&($.trim(forwardto))){ 
    
       $.ajax({
      type: 'POST',
      url: 'liason.jsp', 
       
      data: {complnid:complnid,crid:crid,devid:devid,dev_hr:dev_hr,probtype:probtype,stattype:stattype,remark:remark,forw:forwardto, t:"update"},
     
      success: function(code) {
      document.getElementById('msgdisp').style.display="block";
       document.getElementById('msgdisp').style.color = '#db3a15';
     
    document.getElementById('msgdisp').innerHTML= "Complain Committed, Sir. Please wait system updating now.";
  // alert( "Complain Committed, Sir. Please wait system updating now.");
     setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 3 * 1000); 
            
       setTimeout(function () {
     location.reload();  }, 4 * 1000); 
   
      },
      error: function(xhr, ajaxOptions, thrownError) {
     
            document.getElementById('msgdisp').style.display = 'block';

       document.getElementById('msgdisp').innerHTML= "Server busy if Internal Server Error, Sir."+xhr+thrownError;

  setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 10 * 1000); 
   
      }
   });
   
   }
   else {
                document.getElementById('msgdisp').style.display = 'block';

       document.getElementById('msgdisp').innerHTML= "Please Select Some Complaint." ;

  setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 5 * 1000); 
       
   }
    }
  
  
  