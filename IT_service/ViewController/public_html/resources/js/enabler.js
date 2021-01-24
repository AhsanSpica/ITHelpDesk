   
 
  
   function closeNav() {
       //   alert(" body on load triggerred");

   // document.getElementById("mySidenav").style.width = "0px";
   // document.getElementById("main").style.marginLeft= "2%";
   // document.body.style.backgroundColor = "#bee7f4";
 //  document.body.style.backgroundColor = "#8ce4ff";
 // document.body.style.backgroundColor = "White";
//  enabler();
//  getformDate();
}
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
  
 //compatibility code for IE 6,8,7 
//(function() {
//    var indexOf = [].indexOf || function(prop) {
//        for (var i = 0; i < this.length; i++) {
//            if (this[i] === prop) return i;
//        }
//        return -1;
//    };
//    window.getElementsByClassName = function(className,context) {
//        if (context.getElementsByClassName) return context.getElementsByClassName(className);
//        var elems = document.querySelectorAll ? context.querySelectorAll("." + className) : (function() {
//            var all = context.getElementsByTagName("*"),
//                elements = [],
//                i = 0;
//            for (; i < all.length; i++) {
//                if (all[i].className && (" " + all[i].className + " ").indexOf(" " + className + " ") > -1 && indexOf.call(elements,all[i]) === -1) elements.push(all[i]);
//            }
//            return elements;
//        })();
//        return elems;
//    };
//})();?
  
  
     function enabler()
{  
 //var isIE = /*@cc_on!@*/false || !!document.documentMode;
         
   var disablecomp=document.getElementById("cmpdis").value;
  var disableserv=document.getElementById("serdis").value;
 
//  if(isIE)
//       {
//     //  alert(" disablecomp "+disablecomp);
//   //    alert(" disableserv "+disableserv);
//       }
     
    var outselect=document.getElementById("outselect");
    var loginid=document.getElementById("coordid").value;
    var emptp=document.getElementById("emptph").value;
    emptp=emptp.trim();
   
    var servElements = document.getElementsByClassName("service");
    
     var adminlbl=document.getElementById("admin_lbl");
     var compElements = document.getElementsByClassName("compln");
    document.getElementById("commitbtn").disabled=false;
    document.getElementById("forwardiv").style.display ="none";
    adminlbl.innerHTML = "-";
    // document.getElementById("custresp").style.display ="none";
    
    
     if (disablecomp=="true" )
      { 
      
      for( var i=0; i<servElements.length; i++)   {
     document.getElementById(servElements[i].id).disabled = false; 
     }
   
      }
      //if it personel not C
      if (emptp)
    {  
      if (emptp=="C"||emptp=="H"||emptp=="N"||emptp=="AC"||emptp=="AH"||emptp=="N"||emptp=="D"||emptp=="P")
      { 
    
   for(  i=0; i<servElements.length; i++)   {
     document.getElementById(servElements[i].id).disabled = false; 
     }
     
  document.getElementById("commitbtn").style.display ="block";
   
      if (emptp=="AC"||emptp=="AH"||emptp=="N"||emptp=="AA")
      { 
      outselect.disabled=false;
       document.getElementById("admin_lbl").style.display ="block";
       document.getElementById("devdiv").style.display ="none";
     if (emptp=="AC")  {adminlbl.innerHTML = "B.A Administrative Console";
      document.getElementById('forward_1').checked = true;
      
              
     }
      
      if (emptp=="AH"  )
      { document.getElementById("forwardiv").style.display ="block";
        document.getElementById('forward_2').checked = true;
      
       
        adminlbl.innerHTML = "HARDWARE Administrative Console";}
      if (emptp=="N")  {
      document.getElementById("forwardiv").style.display ="block";
        document.getElementById('forward_2').checked = true;
       
      adminlbl.innerHTML = "Network Console";}
       document.getElementById("tickerbox").style.display ="block";
      
      }
      
     else
      { 
     
      if (emptp=="C")
      {  adminlbl.innerHTML = " ";
      adminlbl.innerHTML = "Help Desk Console "; 
       document.getElementById('forward_1').checked = true;
       outselect.disabled=false; 
       document.getElementById("tickerbox").style.display ="block";
      }
      if (emptp=="D"||emptp=="P")
      {   adminlbl.innerHTML = " ";
     if (emptp=="D" ) { adminlbl.innerHTML = "Developer Console "; }
     else {adminlbl.innerHTML = "-";}
       document.getElementById('forward_1').checked = true;
        outselect.disabled=false; 
       document.getElementById("tickerbox").style.display ="none";
      }
      
      if (emptp=="H")
      { 
       adminlbl.innerHTML = "HARDWARE Console "; 
      document.getElementById("forwardiv").style.display ="block";
        document.getElementById('forward_2').checked = true;
        document.getElementById("tickerbox").style.display ="block";
         document.getElementById("devdiv").style.display ="none";
      
      }
      }
    }

    
}
      if (disableserv=="true")
      {
     
      document.getElementById("submitbtn").style.display ="block";
     for( i=0; i<compElements.length; i++)   {
     
     document.getElementById(compElements[i].id).disabled = false; 
      
     }
   document.getElementById("commitbtn").style.display ="none";
   
   document.getElementById("forwardiv").style.display ="none";
    document.getElementById('forward_1').checked = true;
  document.getElementById("tickerbox").style.display ="none";
    //  document.getElementById("admin_lbl").style.display ="none";
      for(  i=0; i<servElements.length; i++)   {
   document.getElementById(servElements[i].id).disabled = true; 
  }
      
      document.getElementById('coordid').value=" ";
        document.getElementById("crd_dt").value =" ";
          document.getElementById("crd_tm").value =" ";
          
     document.getElementById('devid').value =" ";
       document.getElementById("dev_dt").value =" ";
         document.getElementById("dev_tm").value =" ";
      
      }
 
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
    document.getElementById("acc_sel").style.background="#f0f2cb";
    document.getElementById("clas_sel").style.background="#f0f2cb";
    document.getElementById("sys_sel").style.background="#f0f2cb";
     }
     
     for( i=0; i<hrdelements.length; i++)   {
     
    document.getElementById(hrdelements[i].id).disabled = false; 
    
     document.getElementById("hrd_sel").style.background="#a1bced";
     }
    // #222c3d
       document.getElementById("probdtl").value=" ";
     document.getElementById('remark').value=" ";
     }
     else
      {  
  for( i=0; i<hrdelements.length; i++)   {
  document.getElementById(hrdelements[i].id).checked = false; 
     document.getElementById(hrdelements[i].id).disabled = true; 
   
    document.getElementById("hrd_sel").style.background="#f0f2cb";
     }
     for( i=0; i<classElements.length; i++)   {
    document.getElementById(classElements[i].id).disabled = false; 
    document.getElementById("acc_sel").style.background = "#a1bced";
    document.getElementById("clas_sel").style.background="#a1bced";
    document.getElementById("sys_sel").style.background="#a1bced";
     }
     document.getElementById("probdtl").value=" ";
     document.getElementById('remark').innerHTML=" ";
     }
    
  }

function toggle2(switchElement) {
    
      var accElements = document.getElementsByClassName('check_acc');    
  var classElements = document.getElementsByClassName('check_class');
  var hrdelements = document.getElementsByClassName('check_hrd');    
  var comptype = document.getElementsByClassName('comptype'); 
 
    
    if(switchElement.name == 'systype') 
    
    {document.getElementById('comptype_1').checked = true; 
    for( i=0; i<hrdelements.length; i++)   {
  document.getElementById(hrdelements[i].id).checked = false; 
     document.getElementById(hrdelements[i].id).disabled = true; 
     }
    
    if (switchElement.value == '1')
     {
     document.getElementById("clas_sel").style.background =  "#a1bced";
   document.getElementById("clas_sel").style.opacity = "1";
  for( i=0; i<classElements.length; i++)   {
  document.getElementById(classElements[i].id).checked = false; 
    document.getElementById(classElements[i].id).disabled = false; 
     }
     document.getElementById("acc_sel").style.background = "#a8635f";
     document.getElementById("acc_sel").style.opacity = "0.5"; 
     for( i=0; i<accElements.length; i++)   {
     document.getElementById(accElements[i].id).checked = false; 
    document.getElementById(accElements[i].id).disabled = true; 
    
      
     }
     
     }
     else if (switchElement.value == '2')
      {  
      document.getElementById("clas_sel").style.background =  "#a1bced";
   document.getElementById("clas_sel").style.opacity = "1";
   for( i=0; i<classElements.length; i++)   {
  document.getElementById(classElements[i].id).checked = false; 
    document.getElementById(classElements[i].id).disabled = false; 
     }
     document.getElementById("acc_sel").style.background = "#a8635f";
     document.getElementById("acc_sel").style.opacity = "0.5";
     for( i=0; i<accElements.length; i++)   {
     document.getElementById(accElements[i].id).checked = false; 
    document.getElementById(accElements[i].id).disabled = true; 
     
     }
     }
      else if (switchElement.value == '3')
      {  
      
   for( i=0; i<classElements.length; i++)   {
  document.getElementById(classElements[i].id).checked = false; 
    document.getElementById(classElements[i].id).disabled = true; 
     }
     document.getElementById("clas_sel").style.background="#a8635f";
   document.getElementById("clas_sel").style.opacity = "0.5";
    document.getElementById("acc_sel").style.background =  "#a1bced";
     document.getElementById("acc_sel").style.opacity = "1";
     for( i=0; i<accElements.length; i++)   {
     document.getElementById(accElements[i].id).checked = false; 
    document.getElementById(accElements[i].id).disabled = false; 
    
     }
     }
      else if (switchElement.value == '4')
      {  
      document.getElementById("clas_sel").style.background =  "#a1bced";
   document.getElementById("clas_sel").style.opacity = "1";
 for( i=0; i<classElements.length; i++)   {
  document.getElementById(classElements[i].id).checked = false; 
    document.getElementById(classElements[i].id).disabled = true; 
     }
     document.getElementById("acc_sel").style.background = "#a8635f";
   document.getElementById("acc_sel").style.opacity = "0.5";
     for( i=0; i<accElements.length; i++)   {
     document.getElementById(accElements[i].id).checked = false; 
    document.getElementById(accElements[i].id).disabled = true; 
     
     }
     }
      else if (switchElement.value == '5')
      {  
      document.getElementById("clas_sel").style.background =  "#a1bced";
   document.getElementById("clas_sel").style.opacity = "1";
 for( i=0; i<classElements.length; i++)   {
  document.getElementById(classElements[i].id).checked = false; 
    document.getElementById(classElements[i].id).disabled = false; 
     }
     document.getElementById("acc_sel").style.background = "#a8635f";
   document.getElementById("acc_sel").style.opacity = "0.5";
     for( i=0; i<accElements.length; i++)   {
     document.getElementById(accElements[i].id).checked = false; 
    document.getElementById(accElements[i].id).disabled = true; 
     }
     }
        else if (switchElement.value == '6')
      {
      document.getElementById("clas_sel").style.background =  "#a1bced";
   document.getElementById("clas_sel").style.opacity = "1";
 for( i=0; i<classElements.length; i++)   {
  document.getElementById(classElements[i].id).checked = false; 
    document.getElementById(classElements[i].id).disabled = true; 
     }
     document.getElementById("acc_sel").style.background = "#a8635f";
   document.getElementById("acc_sel").style.opacity = "0.5";
     for( i=0; i<accElements.length; i++)   {
     document.getElementById(accElements[i].id).checked = false; 
    document.getElementById(accElements[i].id).disabled = true; 
     }
     }
     
        else if (switchElement.value == '7')
      {
      document.getElementById("clas_sel").style.background =  "#a1bced";
   document.getElementById("clas_sel").style.opacity = "1";
 for( i=0; i<classElements.length; i++)   {
  document.getElementById(classElements[i].id).checked = false; 
    document.getElementById(classElements[i].id).disabled = true; 
     }
     document.getElementById("acc_sel").style.background = "#a8635f";
   document.getElementById("acc_sel").style.opacity = "0.5";
     for( i=0; i<accElements.length; i++)   {
     document.getElementById(accElements[i].id).checked = false; 
    document.getElementById(accElements[i].id).disabled = true; 
     }
     }
        else if (switchElement.value == '8')
      {
      document.getElementById("clas_sel").style.background =  "#a1bced";
   document.getElementById("clas_sel").style.opacity = "1";
 for( i=0; i<classElements.length; i++)   {
  document.getElementById(classElements[i].id).checked = false; 
    document.getElementById(classElements[i].id).disabled = true; 
     }
     document.getElementById("acc_sel").style.background = "#a8635f";
   document.getElementById("acc_sel").style.opacity = "0.5";
     for( i=0; i<accElements.length; i++)   {
     document.getElementById(accElements[i].id).checked = false; 
    document.getElementById(accElements[i].id).disabled = true; 
     }
     }
     
        else if (switchElement.value == '9')
      { 
      document.getElementById("clas_sel").style.background =  "#a1bced";
   document.getElementById("clas_sel").style.opacity = "1";
 for( i=0; i<classElements.length; i++)   {
  document.getElementById(classElements[i].id).checked = false; 
    document.getElementById(classElements[i].id).disabled = false; 
     }
    document.getElementById("acc_sel").style.background = "#a8635f";
   document.getElementById("acc_sel").style.opacity = "0.5";
     for( i=0; i<accElements.length; i++)   {
     document.getElementById(accElements[i].id).checked = false; 
    document.getElementById(accElements[i].id).disabled = true; 
     }
     }
     
        else if (switchElement.value == '11')
      { 
      document.getElementById("clas_sel").style.background =  "#a1bced";
   document.getElementById("clas_sel").style.opacity = "1";
 for( i=0; i<classElements.length; i++)   {
  document.getElementById(classElements[i].id).checked = false; 
    document.getElementById(classElements[i].id).disabled = true; 
     }
   document.getElementById("acc_sel").style.background = "#a8635f";
   document.getElementById("acc_sel").style.opacity = "0.5";
     for( i=0; i<accElements.length; i++)   {
     document.getElementById(accElements[i].id).checked = false; 
    document.getElementById(accElements[i].id).disabled = true; 
     }
     }
      else if (switchElement.value == '12')
      {
      document.getElementById("clas_sel").style.background =  "#a1bced";
   document.getElementById("clas_sel").style.opacity = "1";
  for( i=0; i<classElements.length; i++)   {
  document.getElementById(classElements[i].id).checked = false; 
    document.getElementById(classElements[i].id).disabled = true; 
     }
     document.getElementById("acc_sel").style.background = "#a8635f";
   document.getElementById("acc_sel").style.opacity = "0.5";
     for( i=0; i<accElements.length; i++)   {
     document.getElementById(accElements[i].id).checked = false; 
    document.getElementById(accElements[i].id).disabled = true; 
     }
     }
     
      else if (switchElement.value == '13')
      {  
      document.getElementById("clas_sel").style.background =  "#a1bced";
   document.getElementById("clas_sel").style.opacity = "1";
  for( i=0; i<classElements.length; i++)   {
  document.getElementById(classElements[i].id).checked = false; 
    document.getElementById(classElements[i].id).disabled = true; 
     }
   document.getElementById("acc_sel").style.background = "#a8635f";
   document.getElementById("acc_sel").style.opacity = "0.5";
     for( i=0; i<accElements.length; i++)   {
     document.getElementById(accElements[i].id).checked = false; 
    document.getElementById(accElements[i].id).disabled = true; 
     }
     }
     
     }
      if(switchElement.name == 'hardtype') 
    
    {
    
     document.getElementById('comptype_2').checked = true; 
    }
     if(switchElement.name == 'acctype'||switchElement.name == 'classtype '  ) 
    
    {
     
     if (!document.getElementById('comptype_2').checked&&!document.getElementById('comptype_1').checked)
    {
    for( i=0; i<accElements.length; i++)   {
     document.getElementById(accElements[i].id).checked = false; 
    
     } //end of acc for loop
     for( i=0; i<classElements.length; i++)   {
  document.getElementById(classElements[i].id).checked = false; 
     
     }// end of class forloop
     }//if comp type not checked
    
    }
            
  }
  
  
  //setInterval(ajaxCall, 30000); //300000 MS == 5 minutes // only when not self loading function
  function fetchdata()
     {
   var id =document.getElementById("tickerbox").value;
     
  getinfo2(id);
   
     }
         function changeTitle(count) {
  
    var newTitle = '(' + count + ') ';
// alert(tpemp+" "+str);
 //alert(document.title);
    document.title = newTitle;
}  
 
 function changeFavicon(len) {
   
   if (len>0){
    document.getElementById('favicon').href = 'resources/Images/favicon-dot.jpg';
   }
   else 
   { document.getElementById('favicon').href = 'resources/Images/favicon.jpg' ;
   }
}
  
          
     $( function dostuff ()   {
       
       var str=document.getElementById("coordid").value;
          var tpemp=document.getElementById("emptph").value;
     //  alert(str +" "+tpemp);
     
     // alert(str+"  "+tpemp);
        if(tpemp!="U"&&tpemp!="P")
     {$.ajax({
      type: 'POST',
      url: 'liason.jsp', 
      
      data: {str:str,emptp:tpemp,t:"geticker"},
     // data: {t:"geticker"},
      success: function(code) {
  
  
      var resp="";
      resp =code.split("|");
       resp = JSON.parse( resp[1] );
       
 //empty the ticker listbox
   $('#tickerbox').empty(); 
 
   
 var temp=resp[0];
  for(var i = 0; i < resp.length; i++) { 
 if( temp!=" ")   
 { 
     $('#tickerbox')
         .append($('<option></option>')
                    .attr("value",resp[i]  )
                    .text( resp[i] ) ); 
 
 }
//              changeFavicon(resp.length); 
 }
 
 if (temp==" "){
var opt  ="none in last 5 minutes";
  $('#tickerbox')
         .append($('<option></option>')
                    .attr("value",opt  )
                    .text( opt ) );
              changeFavicon(resp.length); 
                    }
changeTitle(resp.length);changeFavicon(resp.length); 
      }
      //, complete: function() {
      // Schedule the next request when the current one's complete
      
//                    }

      ,error: function(xhr, ajaxOptions, thrownError) {
     
            document.getElementById('msgdisp').style.display = 'block';
            setTimeout(function () {
             $("#msgdisp").html('');
           }, 5 * 1000); 
      }  
  
   });  }
setTimeout(dostuff, 5000);


}   )();

 // );

      function showdialog(){
         
         
    	  window.scrollBy(0, 300);
    	 var x = document.getElementById("reportbox");
       //    alert("trigerred "+x.id);
       

    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
  
    }  
//$("#dialogbtn").click(function() {
  
          
           
//                $( "#reportbox" ).dialog({
//         position: 'center',
//        maxWidth:1000,
//                    maxHeight: 1000,
//                    width: 650,
//                    height: 800,
//        overflow:'scroll',
//        show: {
//        effect: "fade",
//        duration: 360
//        },
//        hide: {
//        effect: "fade",
//        duration: 400
//        },
//        buttons: [
//       
//        {
//            text: "Close",
//            click: function() {
//                $( this ).dialog( "close" );
//                 $( "#reportbox" ).hide();
//            }
//        }
//    ]
//});
  //  $( "#reportbox" ).dialog();
       
//});

    $("#closebtn2").click(function (){
     document.getElementById("reportbox").style.display='none';
   } );
    