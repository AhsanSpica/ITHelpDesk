     
    
//  function getempnm(elem)
//  {
//// var selemp=  document.getElementById("selempnm");
// var seldev=  document.getElementById("devid");
//  var selval = elem.value;
// 
////Set selected
//   if (elem.id=='devid')
//  {setSelectedValue(selemp, selval);}
//   if (elem.id=='selempnm')
//   {setSelectedValue(seldev, selval);}
//
//    }
//    function setSelectedValue(selectObj, valueToSet) {
//    for (var i = 0; i < selectObj.options.length; i++) {
//        if (selectObj.options[i].value== valueToSet) {
//            selectObj.options[i].selected = true;
//            return;
//        }
//    }
//}
  
  function giveSelection(selValue) {
 
  
  var allsel = document.querySelector('#outselect');
  var opensel = document.querySelector('#openselect');
  var procdrop = document.querySelector('#processdrop');
   var donedrop = document.querySelector('#outselect2');
  var id=document.getElementById("coordid").value;
  var devid=document.getElementById("devid");
 var emptp=document.getElementById("emptph").value;
 var option = document.createElement("option");
 var matchval="";
 emptp=emptp.trim();
 
   
     if (id=="429"&&devid.value!=null)
     {id=devid.value;
     }
 
   
  var resp=""; allsel.innerHTML = '';opensel.innerHTML = ''; procdrop.innerHTML='';donedrop.innerHTML='';
  if (emptp==="AC"||emptp==="AH")
 { $.ajax({
      type: 'POST',
      url: 'liason.jsp', 
   cache:false,
      data: {brid:selValue,emptp:emptp,id:id,t:"getlist"},
    
      success: function(code) {
    
      resp =code.split("|");
       resp = JSON.parse( resp[1] );
      //   alert("get all ajax "+resp);
  $('#outselect')
         .append($("<option></option>")
                    .attr("value",""  )
                    .text( "Select" ) ); 
  for(var j = 0; j < resp.length; j++) {
  
       // option.text = resp[j];
      //  option.value = resp[j] ;
      //  allsel.add(option);
 //matchval=resp[j];
 //  matchval=matchval.substring(0, 3);
   
  //  if(matchval == selValue) {
  
  $('#outselect')
         .append($("<option></option>")
                    .attr("value",resp[j]  )
                    .text( resp[j] ) ); 
                    
                  // }
  }
  
     
      },
      error: function(xhr, ajaxOptions, thrownError) {
     
            document.getElementById('msgdisp').style.display = 'block';

       document.getElementById('msgdisp').innerHTML= "Error, Sir."+xhr+thrownError;

  setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 5 * 1000); 
   
      }
   });
   }// fill all dropdown if adimin login
  
   
   $.ajax({
      type: 'POST',
      url: 'liason.jsp',   cache:false,
      //data: {cls:classes,acc:accounts,hrd:hrd,sys:systype,cmp:comptype, t:"insert"},
      data: {brid:selValue,emptp:emptp,id:id,t:"getopenlist"},
    
      success: function(code) {
    resp="";
      resp =code.split("|");
       resp = JSON.parse( resp[1] );
       //  alert("pending /open "+resp);
   $('#openselect')
         .append($("<option></option>")
                    .attr("value",""  )
                    .text( "Select" ) ); 
  for(var i = 0; i < resp.length; i++) {
  
  // matchval=resp[i];
  //  matchval=matchval.substring(0, 3);
   
  //  option.text = resp[i];
  //      option.value = resp[i] ;
   //     opensel.add(option);
  
   // if(matchval === selValue) {
  
  // alert("pending  "+resp[i]);
   $('#openselect')
         .append($("<option></option>")
                    .attr("value",resp[i]  )
                    .text( resp[i] ) ); 
  //  }
  }

      },
      error: function(xhr, ajaxOptions, thrownError) {
     
            document.getElementById('msgdisp').style.display = 'block';

       document.getElementById('msgdisp').innerHTML= "only open error."+xhr+thrownError;

  setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 5 * 1000); 
   
      }
      
   });
   
   //--process drop ajax call
    $.ajax({
      type: 'POST',
      url: 'liason.jsp',   cache:false,
      //data: {cls:classes,acc:accounts,hrd:hrd,sys:systype,cmp:comptype, t:"insert"},
      data: {brid:selValue,emptp:emptp,id:id,t:"getprocesslist"},
    
      success: function(code) {
    resp="";
      resp =code.split("|");
       resp = JSON.parse( resp[1] );
      //   alert("process  "+resp);
   $('#processdrop')
         .append($("<option></option>")
                    .attr("value",""  )
                    .text( "Select" ) ); 
  for(var i = 0; i < resp.length; i++) {
  

  
 //  alert("process  "+resp[i]);
   $('#processdrop')
         .append($("<option></option>")
                    .attr("value",resp[i]  )
                    .text( resp[i] ) ); 
  //  }
  }

      },
      error: function(xhr, ajaxOptions, thrownError) {
     
            document.getElementById('msgdisp').style.display = 'block';

       document.getElementById('msgdisp').innerHTML= "only open error."+xhr+thrownError;

  setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 5 * 1000); 
   
      }
      
   });
   $.ajax({
      type: 'POST',
      url: 'liason.jsp',   cache:false,
      //data: {cls:classes,acc:accounts,hrd:hrd,sys:systype,cmp:comptype, t:"insert"},
      data: {brid:selValue,emptp:emptp,id:id,t:"getdonelist"},
    
      success: function(code) {
    resp="";
      resp =code.split("|");
       resp = JSON.parse( resp[1] );
      //  alert("done / closed "+resp);
   $('#outselect2')
         .append($("<option></option>")
                    .attr("value",""  )
                    .text( "Select" ) ); 
  for(var i = 0; i < resp.length; i++) {
  

  
   // alert("done / closed "+resp[i]);
   $('#outselect2')
         .append($("<option></option>")
                    .attr("value",resp[i]  )
                    .text( resp[i] ) ); 
  //  }
  }

      },
      error: function(xhr, ajaxOptions, thrownError) {
     
            document.getElementById('msgdisp').style.display = 'block';

       document.getElementById('msgdisp').innerHTML= "only open error."+xhr+thrownError;

  setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 5 * 1000); 
   
      }
      
   });
   
   
    
}

//function getisoyr(val){
//       
//                var selyear=val;
//       // alert(selyear);       
// 
//                             $.ajax({                      
//      type: 'POST',
//      url: 'liason.jsp',
//      data: { selyear:selyear, t: "GetIsoyr" },
//     success: function(response) {  
//     alert(response);
//     $('#resultdisp').empty(); 
//   
//        $('#resultdisp').html(response);
//      }
//
//   });
//   
//       } 
        function getiso(){
        
                  var selmon=document.getElementById('selmon').value;
                  var selyear=document.getElementById('selyear').value;
                  var selemp=document.getElementById('empser').value;
                   var emptp=document.getElementById('emptph').value;
            
                 var   data="id:"+selemp+" ,mon:"+selmon+",yr:"+selyear+", t:"+"GetIso"+"";
          
                             $.ajax({                      
                  type: 'POST',
                  url: 'liason.jsp',
                  data: { id:selemp,mon:selmon,yr:selyear, emptp:emptp,t: "GetIso" },
                 success: function(response) {  
               //  alert(response);
                  $('#resultdisp').empty(); 
               
                    $('#resultdisp').html(response);
                  }
            
                     });
   
       } 
     

 function PrintElem()
{
    var selval=document.getElementById('empser').value;
    var skillsSelect=document.getElementById('empser') ;
var selectedText = skillsSelect.options[skillsSelect.selectedIndex].text;
    var mywindow = window.open('', 'PRINT', 'height=400,width=600');
     
    mywindow.document.write('<html><head><title> Complaint Register</title>'); 
    mywindow.document.write('</head><body >');
    mywindow.document.write('<label style="margin-top:10px;margin-bottom:10px; font-family: arial, sans-serif; font-size: 14px;">Team Member - ' + selectedText  + ' ( Help-Desk )</label>');
   
    mywindow.document.write('<h3 >Complaint Register -  </h3>');
     //mywindow.document.write('<h3 >Complaint Register - ' + selval  + '</h3>');
    mywindow.document.write(document.getElementById('resultdisp').innerHTML);
    mywindow.document.write('</body></html>');

    mywindow.document.close(); // necessary for IE >= 10
    mywindow.focus(); // necessary for IE >= 10*/

    mywindow.print();
    mywindow.close();

    return true;
}
    var tableToExcel = (function () {
        var uri = 'data:application/vnd.ms-excel;base64,'
        , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:Column x:height=”80?/><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
        , base64 = function (s) { return window.btoa(unescape(encodeURIComponent(s))) }
        , format = function (s, c) { return s.replace(/{(\w+)}/g, function (m, p) { return c[p]; }) }
        return function (table, name, filename) {
            if (!table.nodeType) table = document.getElementById(table)
            var ctx = { worksheet: name || 'Worksheet', table: table.innerHTML }
var months = new Array(12);
months[0] = "January";
months[1] = "February";
months[2] = "March";
months[3] = "April";
months[4] = "May";
months[5] = "June";
months[6] = "July";
months[7] = "August";
months[8] = "September";
months[9] = "October";
months[10] = "November";
months[11] = "December";
var selectname = skillsSelect.options[skillsSelect.selectedIndex].text;
var current_date = new Date();
month_value = current_date.getMonth();
day_value = current_date.getDate();
year_value = current_date.getFullYear();
 
 
    var filestring=selectname+day_value+months[month_value]+year_value;
          
            document.getElementById("dlink").href = uri + base64(format(template, ctx));
            document.getElementById("dlink").download = filestring;
            document.getElementById("dlink").click();
        }
    })()
    


