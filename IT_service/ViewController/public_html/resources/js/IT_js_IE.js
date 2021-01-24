
 window.onload = function() {
 
 var elem = " ";
  var classElements = document.getElementsByClassName('comptype');
     
      for(var i=0; i<classElements.length; i++)   {
     if(document.getElementById(classElements[i].id).checked == true ) {
 // elem= classElements[i].value;
    
      }  }
  toggle(elem);
};
      // bind change event to select


function getformDate(){
   var todaydate = new Date();
   var day = todaydate.getDate();
   var month = todaydate.getMonth() + 1;
   var year = todaydate.getFullYear();
   var datestring = day + "/" + month + "/" + year;
   var hour=todaydate.getHours();
   var min=todaydate.getMinutes();
   var statype="";
   document.getElementById("crd_dt").value = datestring;
   document.getElementById("crd_tm").value = hour+":"+min;
   document.getElementById("dev_dt").value = datestring;
   document.getElementById("dev_tm").value = hour+":"+min;
  }
  //end of getformdate 



  function getinfo(){
   
   var sel=document.getElementById('inselect').value;
    
   
      var classElements = document.getElementsByClassName('check');
     document.getElementById('complnid').value =sel  ;
    
    document.getElementById("submitbtn").style.display = "none";
   
      for(var i=0; i<classElements.length; i++)   {
    document.getElementById(classElements[i].id).checked = false; 
     //document.getElementById(classElements[i].id).disabled = false; 
     }
     
     
    
        $.ajax({
      type: 'GET',
      url: 'liason.jsp',cache: false,
      data: {sel:sel, t:"select"},
      success: function(code) {
      alert(code);
      
      
      var response=code.split("|"); 
    
     var probdetl= response[15] ;
     var remax=response[16];
        if (remax==" "||remax==' ')
               {remax="-";
               }
               if(probdetl==" ")
               {probdetl="-";
               }
   document.getElementById('probdtl').innerHTML =" ";document.getElementById('remark').innerHTML =" ";
   document.getElementById('probdtl').value=probdetl; 
    document.getElementById('remark').value= remax;
    
    
   // document.getElementById('complnid').value =response[1]  ;
    
    document.getElementById('comptype_'+response[2]).checked = true; 
    var comptp=response[2];
   if (response[2]=="1" )
    {
      if (response[4]=="0" ){
   
      } else { document.getElementById('classtype_'+response[4]).checked = true;}
      if (response[3]=="0" )
    { 
    //document.getElementById('systype_0').checked = true;
    } else { document.getElementById('systype_'+response[3]).checked = true;}
      if (response[5]=="0" )
     {  
   //  document.getElementById('acctype_0').checked = true; 
     } else { document.getElementById('acctype_'+response[5]).checked = true;}
    } else{ document.getElementById('hardtype_'+response[6]).checked = true;
    
    
    }
    
     document.getElementById('probtype_'+response[13]).checked = true;
        if (response[10] != ""||(response[10] != null)||(response[10] != "0")) 
        {document.getElementById('devid').value=response[10];}
        else{document.getElementById('devid').value="";}
        if (response[8]=="0"||response[8]===null||response[8]==="0") 
        {   
         document.getElementById('remark').value= "";
        
      // document.getElementById('dev_hr').value="";  
        
        getformDate();   }
        
        else { 
        document.getElementById('cmpl_dt').value=response[7];
        document.getElementById('ass_coordid').value=response[8];
       // alert(response[20]);
        //
        document.getElementById("crd_dt").value =response[9];
        document.getElementById("dev_dt").value = response[11];
        document.getElementById("crd_tm").value = response[17];
          document.getElementById("dev_tm").value = response[18];
     //   if ()
     //   {
        document.getElementById('COMPLN_DTL_05').value=response[19];
     //   document.getElementById('ass_coordnm').value=response[20];
        //}
         
         // document.getElementById('dev_hr').value=response[12];
      
   document.getElementById('stattype_'+response[14]).checked = true;
      statype=response[14];
       
     if (comptp=="2") {  if (statype=="2")
         {
         
          document.getElementById("custresp").style.display ="block";
           document.getElementById("closebtn").style.display = "block";
         }
         document.getElementById("cancelbtn").style.display = "block";}
         else {document.getElementById("custresp").style.display ="none";
           document.getElementById("closebtn").style.display = "none";
           document.getElementById("cancelbtn").style.display = "none";
           }
         
         
          document.getElementById('devid').value=response[10];
          //document.getElementById("selempnm").value=response[10];
         }
     
    
     
  
      },
      error: function(xhr, ajaxOptions, thrownError) {
     
            document.getElementById('msgdisp').style.display = 'block';

       document.getElementById('msgdisp').innerHTML= "Server Busy, Try again, Sir."+xhr+ajaxOptions+thrownError;

  setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 5 * 1000); 
   
      }
   });
}
 

function getinfo2(sel){

    
     var probtype,stattype="";
     sel=sel ;
    
      document.getElementById("submitbtn").style.display = "none";
       var classElements = document.getElementsByClassName("check");
      var ddl = document.getElementById("devid");
     // var ddlnm=document.getElementById("selempnm");
      var statype="";
        var opts = ddl.options.length;
    // var optsnm = ddlnm.options.length;
       var compElements = document.getElementsByClassName("compln");
        
      for(var i=0; i<compElements.length; i++)   {
     document.getElementById(compElements[i].id).disabled = true; 
     }
     
      for( i=0; i<classElements.length; i++)   {
    document.getElementById(classElements[i].id).checked = false; 
     document.getElementById(classElements[i].id).disabled = false; 
     }
      if ($.trim(sel) )
      {
   
      $.ajax({
      type: 'GET',
      url: 'liason.jsp',cache: false,
      data: {sel:sel, t:"select2"},
      success: function(code) {
   alert(code);
      var response=code.split("|");
   
  var probdetl= response[8];
     
               if(probdetl==" ")
               {
               probdetl="-";
               }
              
   document.getElementById('probdtl').value =probdetl;
   
  
   
     document.getElementById('complnid').value =sel  ;
   document.getElementById('comptype_'+response[2]).checked = true;
   if (response[2]=="1" )
    {
      if (response[4]=="0" ){ 
     
      } else { document.getElementById('classtype_'+response[4]).checked = true;}
      if (response[3]=="0" )
    { 
    
    } else { document.getElementById('systype_'+response[3]).checked = true;}
    
      if (response[5]=="0" )
     {  
    // document.getElementById('acctype_0').checked = true;
     } else { document.getElementById('acctype_'+response[5]).checked = true;}

    } else{document.getElementById('hardtype_'+response[6]).checked = true;}
    document.getElementById('cmpl_dt').value=response[7];
   document.getElementById('cmpl_tm').value=response[19];
    document.getElementById('user_id').value=response[20];
    document.getElementById('brn_id').value=response[21];
    document.getElementById('user_nm').value=response[22];
    document.getElementById('COMPLN_DTL_05').value=response[23];
        if (response[10]=="0"||response[10]===null||response[10]==="0") 
        { 
         document.getElementById('remark').value= "";
        document.getElementById('devid').value="";
      // document.getElementById('dev_hr').value="";   
       
        if ( response[11]==''  ||response[11]==" " ||response[11]=="" 
        ||response[11]==null     )
    {
    getformDate();}
       }
        else 
        {  document.getElementById('remark').value= response[9];
       document.getElementById('ass_coordid').value=response[10];
    //  document.getElementById('ass_coordnm').value=response[24];
        document.getElementById("crd_dt").value =response[11];
        document.getElementById("crd_tm").value = response[12];
        
        document.getElementById("dev_dt").value = response[14];
          document.getElementById("dev_tm").value = response[15];
     //  document.getElementById('dev_hr').value=response[16];  
     
    document.getElementById("probtype_"+response[17]).checked = true;
    document.getElementById("stattype_"+response[18] ).checked = true;
     statype=response[18];
    if (statype=="2"||statype=="1")
         {
          document.getElementById("custresp").style.display ="block";
         }
         if ( statype=="1")
      { document.getElementById("stattype_1" ).disabled=true;
       document.getElementById("stattype_2"  ).disabled=true;
        document.getElementById("stattype_3"  ).disabled=true;
        }
      if ( response[11]==''  ||response[11]==" " ||response[11]=="" 
        ||response[11]==null     )
    {
    getformDate();}
      }
       
        if (response[13] != ""||(response[13] != null)||(response[13] != "0")) 
        {
        
        for ( i=0; i<opts; i++){
        if (ddl.options[i].value === response[13]){
           ddl.options[i].selected = true;
           break;
         }
         else{document.getElementById('devid').value=response[13];}
       }
//       for ( i=0; i<optsnm; i++){
//        if (ddlnm.options[i].value == response[13]){
//           ddlnm.options[i].selected = true;
//           break;
//         } 
//       }
        }
        
        else{document.getElementById('devid').value="";} 
       
     
     
      },
      error: function(xhr, ajaxOptions, thrownError) {
     
            document.getElementById('msgdisp').style.display = 'block';

       document.getElementById('msgdisp').innerHTML= "Server Busy, Try again, Sir."+xhr+ajaxOptions+thrownError;

  setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 5 * 1000); 
   
      }
   });
   
   }  //EMD OF IF NOT DUMY ENTRY
    for( i=0; i<compElements.length; i++)   {
   document.getElementById(compElements[i].id).disabled = true; 
     }
     document.getElementById('forward_2').checked = true;
}
  
  
    function toggle(switchElement) {
    
    
    document.getElementById("submitbtn").style.display ="";
    
      var hrdelements = document.getElementsByClassName('check_hrd');    
  var classElements = document.getElementsByClassName('softinput');
    
        if (switchElement.value == '2')
     {  
  for(var i=0; i<classElements.length; i++)   {
  document.getElementById(classElements[i].id).checked = false; 
    document.getElementById(classElements[i].id).disabled = true; 
     }
     
     for( i=0; i<hrdelements.length; i++)   {
     
    document.getElementById(hrdelements[i].id).disabled = false; 
     }
       document.getElementById("probdtl").value=" ";
     document.getElementById('remark').value=" ";
     }
     else
      {  
  for( i=0; i<hrdelements.length; i++)   {
  document.getElementById(hrdelements[i].id).checked = false; 
     document.getElementById(hrdelements[i].id).disabled = true; 
     }
     for( i=0; i<classElements.length; i++)   {
    document.getElementById(classElements[i].id).disabled = false; 
     }
     document.getElementById("probdtl").value=" ";
     document.getElementById('remark').innerHTML=" ";
     }
    
  }
  
  
  
  function toggle3(switchElement)

{
    var devoptions = document.getElementsByClassName('devflag');
    var crdoptions = document.getElementsByClassName('crdflag');
      var sel2 = document.querySelector('#devid');
    //  var sel3 = document.querySelector('#selempnm');
    var resp="";
  sel2.innerHTML = '';
 
    var names=""; var id=""; var array="";
    // document.getElementById('devid' ).onclick = "getempnm(this)";
  //  document.getElementById('selempnm' ).onclick = "getempnm(this)";
  
  
   if(switchElement.id == 'probtype_2') 
    
    { 
    
    document.getElementById('devlbl').innerHTML='Coordinator';
   var temp="";

   
        $.ajax({
      type: 'GET',
      url: 'liason.jsp', 
     content: 'application/json',
      data: {type:"C",t:"getnames"},   
      success: function(code) {  
      names =code.split("|"); 
     
        names =   names[1].split("#");  ;
        //     alert(names+"    "+ names.length);  
    for( i = 1; i < names.length-1; i++) { 
    temp=names[i].split("&");
   

                    $('#devid')
        .append($("<option></option>")
                  .attr("value",temp[1] )  .text(temp[0]  ) ); 
     
  }
      },
      error: function(xhr, ajaxOptions, thrownError) {
      }
   });
    
    
    }
    
    else if(switchElement.id == 'probtype_3') 
    
    {
    document.getElementById('devlbl').innerHTML='Engineer';
   
     $.ajax({
      type: 'GET',
      url: 'liason.jsp',   content: 'application/json',
      data: {type:"H",t:"getnames"},   
      success: function(code) {  
      names =code.split("|"); 
    
     
           names =   names[1].split("#");  ;
        //     alert(names+"    "+ names.length);  
    for( i = 1; i < names.length-1; i++) { 
    temp=names[i].split("&");
 
                    $('#devid')
        .append($("<option></option>")
                  .attr("value",temp[1] )  .text(temp[0]  ) ); 
     
  }
      },
      error: function(xhr, ajaxOptions, thrownError) {
      }
   });
    
    }
    
    else if (switchElement.id == 'probtype_1') 
    {
    document.getElementById('devlbl').innerHTML='Developer';
    
    $.ajax({
      type: 'GET',
      url: 'liason.jsp', 
     content: 'application/json',
      data: {type:"D",t:"getnames"},   
      success: function(code) {  
      names =code.split("|"); 
    
    
            names =   names[1].split("#");  ;
        //     alert(names+"    "+ names.length);  
    for( i = 1; i < names.length-1; i++) { 
    temp=names[i].split("&");
  
          $('#devid')
        .append($("<option></option>")
                  .attr("value",temp[1] )  .text(temp[0]  ) ); 
     
  }
      },
      error: function(xhr, ajaxOptions, thrownError) {
      }
   });
  
     
    }


}



