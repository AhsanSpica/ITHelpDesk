             
             //$("#pass").keyup(function(event) {
             var fileurl;
            // function subkeyup(event){
            //// var input = document.getElementById("pass");
            //
            //  // Cancel the default action, if needed
            //  event.preventDefault();
            //  // Number 13 is the "Enter" key on the keyboard
            //  if (event.keyCode === 13) {
            //    // Trigger the button element with a click
            //    document.getElementById("logbtn").click();
            //  }
            //
            // }
             
            //});

         function form_action()
         {
       //  alert(val);
         document.getElementById("myForm").method = "POST"; 
        document.getElementById("myForm").action = val;
        document.getElementById("myForm").target= '_top';
        document.getElementById("myForm").submit();
        }

             function showpage(val){
            //save previous values
            val=val.split("_");
            val=val[1];
            
            var tempmethod = document.forms[0].method;
            var tempaction = document.forms[0].action;
            var temptarget = document.forms[0].target;
            //change where u want to go
            document.getElementById("myForm").method = "post"; 
            document.getElementById("myForm").action = fileurl[val];
            document.getElementById("myForm").target= '_top';
            document.getElementById("myForm").submit();
            //restore default
            document.forms[0].method = tempmethod;
            document.forms[0].action = tempaction;
            document.forms[0].target = temptarget;
             }


     if(typeof String.prototype.trim !== 'function') {
  String.prototype.trim = function() {
    return this.replace(/^\s+|\s+$/g, ''); 
  }
}
      
     
      function execute()
       {
     document.getElementById('msgdisp').style.display = 'block';
         document.getElementById('msgdisp').style.color = "green";
       document.getElementById('msgdisp').innerHTML=" Loading . . ." ;
       //
        var logid=document.getElementById('logid').value;
       var brcode= ""; var company=""; var dbcompid=""; var emptp="";
        var pass=document.getElementById('pass').value;
       logid=logid.trim();
       pass=pass.trim();
    var xhttp="";
        if (window.XMLHttpRequest) {
   
             xhttp = new XMLHttpRequest();
         } else {
         //  alert("window.XMLHttpRequest exists");
           // code for old IE browsers
             xhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
          var uri="liason.jsp?pass="+pass+"&logid="+logid+"&t=login";
          
          xhttp.open("GET", uri, false);
          xhttp.setRequestHeader('Cache-Control', 'no-cache');  // error of xhr variable insatead of xhttp removed on 6/12/2018 at 2:28 pm
          xhttp.send();
         document.getElementById('msgdisp').innerHTML=" " ;
         var code=xhttp.responseText;
           
        //   alert(code);
           
            var datar = code.split("|");  
           
           var truer=datar[1];
           if(truer!="true"){ 
           $('#msgdisp').append('<ul>');
   
         document.getElementById('msgdisp').style.display = 'block';
         document.getElementById('msgdisp').style.color = "red";
       
       document.getElementById('msgdisp').innerHTML=" Login not Successfull." ;

  setTimeout(function () {
                $("#msgdisp").html('');
            }, 500);
            }
         else { 
          
            logid=datar[2];
            brcode=datar[3];
            company=datar[4];
            dbcompid=datar[5];
            document.getElementById('empid').value=logid;
            document.getElementById('brcode').value=brcode;
            document.getElementById('compid').value=dbcompid;
        //   emptp=datar[6];
            document.getElementById('msgdisp').style.display = 'block';
         document.getElementById('msgdisp').style.color = "green";
                $("#msgdisp").html("Login Successful");
                setTimeout(function () {
                $("#msgdisp").html('');
            }, 500);
              //  window.location.href="compl_main.jsp?empid="+logid+"&brcode="+brcode;
            document.getElementById('tempbr').value=brcode;
      
         if (company=="Both"||dbcompid=="2")
              { 
            
            
        //  loginNav();  //removing login inputs
           document.getElementById("loginputs").style.display = 'none';
           document.getElementById("menuform").style.display = 'block';
        $('#dropdiv').empty(); 
        $('#dropdiv').append('<select id ="selcompid" name="selcompid" onchange="companyselect()"><option value="1">Conventional</option><option value="2">Takaful</option></select>');
        
             $('#menu').empty();
                       $('#menu').append('<ul>');  
                             $('#menu').append('<li><button id="ithdbtn"  type="submit"  >'+"IT HELP DESK" +'</button></li>'); 
                        $('#menu').append('</ul>');  
        } 
        else 
        {
        $('#dropdiv').empty();
        document.getElementById('compid').value='1';
         var myForm=  document.getElementById("myForm");
         myForm.submit() ;
        
          // $('#dropdiv').append('<select id ="selcompid" name="selcompid" onchange="companyselect()"><option value="1">Conventional</option></select>');
         }
                           
                         
                          }//end of if login true
           
                      }//end of execute login func
          
          
     
  
        function gotomain(event)
        {
       var id= event.target.id;
       if ( id=="ithdbtn")
        {
      //  window.location.href = "compl_main.jsp";
      var myForm=  document.getElementById("myForm");
      myForm.action = "/action_page.php";
        myForm.method = "POST";
         myForm.submit() ;
        }
        }
  
       function companyselect()
       {
      // alert("trigerred");
       var tempbr=document.getElementById('tempbr').value;
       var empid=document.getElementById('empid').value;
       var inputcomp=document.getElementById("selcompid").value;
        document.getElementById('compid').value=inputcomp;
         }           
        // $('#menu').empty();
      // $('#menu').append('<ul>');  
       //      $('#menu').append('<li><button  type="submit"  >'+"IT HELP DESK" +'</button></li>'); 
          //          $('#menu').append('</ul>');     
                    
                    
//                    $.ajax({type: 'get',
//      url: 'liason.jsp',
//      data: {id:empid, t: "applist"},
//      success: function(codex) {
//    //  alert(codex);
//  var  respond=codex.split("|");
//    respond=respond[1].split(",");
//    
//       $('#menu').append('<ul  >');
//        var count=0; var acev="";
//        for(var t=2; t<respond.length-2;t++ ){
//     
//      //  alert("LOOPING THROUGH ARRAY FROM T=1" +respond[t]);
//        var box=respond[t].split("+");
//        uri=box[0]+'?empid='+empid+'&brcode='+tempbr+'&compid='+inputcomp;
//       // alert(emp_log);
//      if(box[1]=="IT HELP DESK")
//          {  
//           $('#menu').append('<li><button  type="submit"   >'+box[1]+'</button></li>'); 
//          }
//      else {  $('#menu').append('<li><a  href='+uri+'>'+box[1]+' </a></li>');  }
//        }  
//        $('#menu').append('</ul>');
//      }
//      }); 
      
      
      
               
                  function ammendlink(e)
                {
                var link=e.href;
                link=link+'&brcode='+document.getElementById("brcode").value;
                document.getElementById(e.id).href=link;
                }


              function loginNav() {
             //$("mySidenav").show();
             
              //  alert(" succesful");
                document.getElementById("loginputs").style.display = 'none';
                document.getElementById("menuform").style.display = 'block';
               
            }

                function relogin() {
                        
                   document.getElementById("loginputs").style.display = 'block';
                    
                    document.getElementById("menuform").style.display = 'none';
                }
                function hidNav() {
                    document.getElementById("mySidenav").style.width = "0";
                   
                }


