var xmlHttp
var tempstr
//function show(str1,str2,str3,str4,str5,str6)

 

function submit()
{ 
	 //  alert('submit triggered ');
    var accounts =[]; var classes=[];var hrd =[];
  
 var cid= document.getElementById('company').value;
   var userid=  document.getElementById('idstore').value;
   var brid=  document.getElementById('brid').value;
     var dtl=  document.getElementById('probdtl').value;
    var comptype=  document.querySelector('input[name="comptype"]:checked').value;
    var accn,clas="";
    var attn="429";
    
 var boolhrd= (comptype=='2');
 var boolsft=(comptype=='1');
   var boolsys=false;
  var booldtl=!$.trim(dtl);
   if (!booldtl){
                  if (boolsft)                  {
 var systype=  document.querySelector('input[name="systype"]:checked').value;
   
                     if (systype!='0')      {
                    
                     
                  var classElements = document.getElementsByClassName('check_class');
   var inputElements = document.getElementsByClassName('check_acc');
   
    for(var i=0; i<inputElements.length; i++){
      if (inputElements[i].disabled == false){
     // alert("if  acc disabled==false" );
     if(inputElements[i].checked){
           accounts.push(inputElements[i].value) ;  
           accn=inputElements[i].value;}
  }
           }

    for(i=0; i<classElements.length; i++){
    if (classElements[i].disabled == false){
   // alert("if  class disabled==false" );
    if(classElements[i].checked){
           classes.push(classElements[i].value) ; 
           clas=classElements[i].value;}
 }
           }
            if (systype=="1"||systype=="9")
                     {
                     if(clas==1||clas==2)
                     {
                     attn="509";
                     }
                     if(clas==4||clas==7||clas==9)
                     {
                     attn="2064";
                     }
                     if(clas==3||clas==5||clas==6 )
                     {
                     attn="2199";
                     }
                     
                     }
                      if (systype=="2")
                     {
                    if(clas==3||clas==5||clas==6 )
                     {
                     attn="2199";
                     }
                    else { attn="2494";}
                     
                     }
                     
                      if (systype=="3")
                     {
                       if (accn=="L")
                       { attn="2494";}
                    else { attn="2026";}
                      
                     }
                     
                      if (systype=="4")
                     {
                     
                     attn="2199";
                   
                     }
                      if (systype=="6"||systype=="7"||systype=="13")
                     {
                     
                     attn="429";
                   
                     }
                      if (systype=="8")//there is no systype=="10"
                     {
                     
                     attn="2064";
                   
                     }
                       if (systype=="11")//there is no systype=="10"
                     {
                     
                     attn="2064";
                   
                     }
             if (systype=="12")//there is no systype=="10"
                     {
                     
                     attn="2199";
                   
                     }
           hrd.push('0') ;
                  }
                  else{ accounts.push('0') ;classes.push('0') ; hrd.push('0') ;}
           }
         else  if (boolhrd){ var hardwareElements = document.getElementsByClassName('check_hrd');
    for(i=0; i<hardwareElements.length; i++){
  // if (hardwareElements[i].disabled == false){
    if(hardwareElements[i].checked){
           hrd.push (hardwareElements[i].value) ;         } 
//}
           }
           accounts.push('0') ;classes.push('0') ;
           attn="1334";
           }
           var accJson = JSON.stringify(accounts); 
           var clsJson = JSON.stringify(classes); 
           var hrdJson = JSON.stringify(hrd); 
 // alert(systype+" "+clsJson+" "+accJson+" "+hrdJson );
 
	xmlHttp=GetXmlHttpObject()
	if (xmlHttp==null)
 	{
 	alert ("Browser does not support HTTP Request")
 	return
 	}
	
	//pag_policyno    =document.getElementById("policyno").value; //Invoice 
	pag_engine      =document.getElementById("engine").value; //Other Exp
	pag_regno       =document.getElementById("regno").value;    // Rate
	pag_chassis     =document.getElementById("chassis").value;	//Markup
        pag_login       =document.getElementById("login").value;	//Markup
        pag_pwd         =document.getElementById("pwd").value;	//Markup
        
       // alert(pag_regno.length);
       // alert(pag_chassis.length());
       // alert(pag_engine.length());
        
        
	var url="liason.jsp"
	//url=url+"?agx_policyno="+pag_policyno
	url=url+"?agx_engine="+pag_engine
	url=url+"&agx_regno="+pag_regno
	url=url+"&agx_chassis="+pag_chassis
        url=url+"&agx_login="+pag_login
        url=url+"&agx_pwd="+pag_pwd
			
	//url=url+"&sid="+Math.random()
	xmlHttp.onreadystatechange = stateChanged
	xmlHttp.open("GET",url,true)
	xmlHttp.send(null)
	}

}


function stateChanged() 
{ 
	if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
 	{
            var showdata = xmlHttp.responseText; 
           // alert(showdata); 
            //showdata ="data:test";
            //var strar = showdata; 
            var strar = showdata.split("|"); 
            if(strar.length==1) 
            { 
if ((pag_login.length!=0) && (pag_pwd.length!=0 )) {
alert("Wrong Data try again!"); 
//alert("try again!"); 
}
//document.write(strname); 
                document.getElementById("insured").value     		=  ''; 
                document.getElementById("keepercell").value     	=  ''; 
                document.getElementById("clientcode").value     	=  ''; 								
                //document.getElementById("city").value     		=  strar[7]; 												
                document.getElementById("br").value     		=  ''; 																
                document.getElementById("vehicle").value     		=  ''; 																				
                document.getElementById("regno").value     		=  ''; 	
                document.getElementById("engine").value     		=  ''; 	
                document.getElementById("chassis").value     		=  ''; 	
                document.getElementById("policyno").value     		=  ''; 	
                document.getElementById("brid").value     		=  ''; 	
                document.getElementById("uw_doc_id").value     		=  ''; 	               
               
                document.getElementById('detail').style.visibility="hidden";
                document.getElementById("saveForm").disabled = true;
                document.getElementById('saveForm').setAttribute("disabled","disabled");         
            } 
            else if(strar.length>1) 
            { 
            //alert(strar[14]); 
            //var btn = document.getElementById("saveForm");
            //if (strar[14]!=0) {
            //var  policy_exp_yesNo =strar[16].trim();
            var  policy_exp_yesNo =strar[16];
            if  ( strar[14]!=0)     //login_sucess            
                {
					       document.getElementById('detail').style.visibility="visible";
			                       document.getElementById("saveForm").disabled=false;          
                                               document.getElementById('saveForm').removeAttribute("disabled");								
                                        //document.write(strname); 
              				       document.getElementById("insured").value     		=  strar[1]; 
					       document.getElementById("keepercell").value     	        =  strar[2]; 
					       document.getElementById("clientcode").value     	        =  strar[6]; 								
					//document.getElementById("city").value     		=  strar[7]; 												
					       document.getElementById("br").value     			=  strar[8]; 																
					       document.getElementById("vehicle").value     		=  strar[9]; 																				
					       document.getElementById("regno").value     		=  strar[10]; 	
					       document.getElementById("engine").value     		=  strar[11]; 	
					       document.getElementById("chassis").value     		=  strar[12]; 	
					       document.getElementById("policyno").value     		=  strar[13]; 	
					       document.getElementById("brid").value     		=  strar[15]; 	
					       document.getElementById("uw_doc_id").value     		=  strar[17]; 	              
                }else {
              //  alert('asas');
                document.getElementById('detail').style.visibility="hidden";
                document.getElementById("saveForm").disabled = true;
		document.getElementById('saveForm').setAttribute("disabled","disabled");
                       
                }
            
             } 
        }
} 


function GetXmlHttpObject()
{
var xmlHttp=null;
try
 {
 // Firefox, Opera 8.0+, Safari
 xmlHttp=new XMLHttpRequest();
 }
catch (e)
 {
 //Internet Explorer
 try
  {
  xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
  }
 catch (e)
  {
  xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
 }
return xmlHttp;
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
     setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 3 * 1000); 
            
       setTimeout(function () {
     location.reload();  }, 1 * 1000); 
   
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
    
