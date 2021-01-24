<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*,java.util.ArrayList, view.DBCoding" %>
<%@ page import ="java.util.LinkedHashMap, java.io.*,java.util.*,org.json.JSONArray, org.json.JSONObject" %>
<%@ page buffer="50000kb"%>   
<%--<%@ page import ="org.apache.http.HttpStatus,org.apache.http.HttpResponse,org.apache.http.HttpResponseFactory,org.apache.http.HttpVersion,
 org.apache.http.impl.DefaultHttpResponseFactory, org.apache.http.message.BasicStatusLine"%>--%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
 

    </head>
    <body>
      <%
      ArrayList lister = new ArrayList();
      DBCoding db1=new DBCoding();
   //  String hotelResult[]= new String[10];FgetisoFgetiso
   String name="";
  
   int j=0;
   String query="";
  String  repenter=" ";
   String tag ="";
   tag= request.getParameter("t");
   
 


    if (tag.equals("select"))
    {
    String key=request.getParameter("sel");
//String brid=request.getParameter("brid");
 
   repenter=" ";
    repenter=db1.getcompln(key);
    out.println(repenter);
    }

    if (tag.equals("select2"))
    {
    String key=request.getParameter("sel");
//String brid=request.getParameter("brid");

       repenter=" ";
    repenter=db1.getcompln2(key);
    out.println(repenter);
    }
 
    if (tag.equals("insert"))
    {
    DBCoding db2=new DBCoding();
//String cls,acc,hrd;
    ArrayList<String> list1 = new ArrayList<String>();
                    String brid = request.getParameter("brid");
                String dtl = request.getParameter("dtl"); 
                 String attn = request.getParameter("attn");
                  String company = request.getParameter("company");
                    JSONArray cls = new JSONArray(request.getParameter("cls").toString());
                JSONArray acc = new JSONArray(request.getParameter("acc").toString());
                JSONArray hrd = new JSONArray(request.getParameter("hrd").toString());
                String sys=request.getParameter("sys");
                String cmp=request.getParameter("cmp");
                String userid=request.getParameter("userid");
    list1= db2.insertpriority(dtl, attn, brid, company, cls, acc, hrd, sys, cmp, userid);
      out.println("|"+list1+"|");
    }

      if (tag.equals("close"))
    {
    String res="";
     String id=request.getParameter("id").toString();
    String com=request.getParameter("close").toString();
  db1.close(com,id) ;
   res="successfully closed";
   out.println("|"+res+"|");
    }

  if (tag.equals("cancel"))
    {

   String res1="";

  String id=request.getParameter("id").toString();
  db1.cancel(id) ;
  res1="successfully canceled";
    out.println("|"+res1+"|");
    }

   if (tag.equals("getlist"))
    {
    String emptp=request.getParameter("emptp").toString();
    String brid=request.getParameter("brid").toString();
    String eid=request.getParameter("id").toString();
    ArrayList<String>  newres=new ArrayList<String>();
    String open="no";
    newres=(db1.getallajaxcompln(eid, emptp, brid));
    out.println("|"+newres+"|");
    }  
   if (tag.equals("getopenlist"))
    {
    String emptp=request.getParameter("emptp").toString();
    String brid=request.getParameter("brid").toString();
    String eid=request.getParameter("id").toString();
    ArrayList<String>  newres=new ArrayList<String>();
    String open="yes";
     newres=(db1.getonlyopenajax(eid, emptp, brid,open));
    out.println("|"+newres+"|");
    }

   if (tag.equals("getprocesslist"))
    {
    String emptp=request.getParameter("emptp").toString();
    String brid=request.getParameter("brid").toString();
    String eid=request.getParameter("id").toString();
    ArrayList<String>  newres=new ArrayList<String>();
    String open="no";
     newres=(db1.getonlyopenajax(eid, emptp, brid,open));
    out.println("|"+newres+"|");
    }
     if (tag.equals("getdonelist"))
    {
    String emptp=request.getParameter("emptp").toString();
    String brid=request.getParameter("brid").toString();
    String eid=request.getParameter("id").toString();
    ArrayList<String>  newres=new ArrayList<String>();
    String open="no";
     newres=(db1.getalldoneajax(eid, emptp, brid));
    out.println("|"+newres+"|");
    }
    
       if (tag.equals("geticker2"))
    {
     
  
    out.println("|AJAX test|");
    }
      if (tag.equals("geticker"))
    {

    ArrayList<String>  newres=new ArrayList<String>();
     String eid=request.getParameter("str").toString();
     String emptp=request.getParameter("emptp").toString();
    
  //  if (emptp.equals("H")||emptp.equals("AH"))
   //{ 
   newres=(db1.getonlyopen( eid, emptp));
  // }
//  else{
  // newres=  db1.getonlyopen(emp,emptp);}
  
    out.println("|"+newres+"|");
    }
      
      
      
     if (tag.equals("getc"))
    {
    ArrayList<String>  newres=new ArrayList<String>();

     newres=(db1.getcrd());
     out.println("|"+newres+"|");
    }  
     if (tag.equals("getd"))
    {

    ArrayList<String>  newres=new ArrayList<String>();
     newres=(db1.getdev());
    out.println("|"+newres+"|");
    } 
   
   if ( tag.equals("GetIso"))
   { 
  String eid=request.getParameter("id").toString();
  String emptp=request.getParameter("emptp").toString();
   String mon=request.getParameter("mon").toString();
    String yr=request.getParameter("yr").toString();
 ArrayList<String>  repenter2=new ArrayList();
 String resultset ="";
// repenter2.addAll(db1.GetIso( eid));
 resultset=db1.GetIso(eid,mon,yr,emptp);
 out.println(resultset);
   }  
   
//   if ( tag.equals("GetIsoyr"))
//   { 
//  String selyear=request.getParameter("selyear").toString();
// 
// ArrayList<String>  repenter2=new ArrayList();
// String resultset ="";
//// repenter2.addAll(db1.GetIso( eid));
// resultset=db1.GetIso(selyear);
// out.println(resultset);
//   }  

 if ( tag.equals("applist"))
   { String  id=request.getParameter("id").toString();
 ArrayList<String>  repenter2=new ArrayList();
 repenter2.addAll(db1.getAllapps(id ));
 out.println("|"+repenter2+"|");
  } 
//  if ( tag.equals("updatelist"))
//   { 
// 
// ArrayList<String>  repenter2=new ArrayList();
// repenter2.addAll(db1.getonlyopen( ));
// out.println(repenter);
//   }   
   
   
  if (tag.equals("gete"))
{
ArrayList<String>  newres=new ArrayList<String>();

 newres=(db1.geteng());
 out.println("|"+newres+"|");
}  
  if (tag.equals("getnames"))
{
String type= request.getParameter("type");
 String   newres="";

 newres=(db1.getempnmonly(type));
 out.println("|"+newres+"|");
} 


    if (tag.equals("update"))
{
//String cls,acc,hrd;

String dump=""; String clssql="";String accsql="";String hrdsql=""; String massql="";
int i=0;
String complnid = request.getParameter("complnid");
String crid = request.getParameter("crid");
 String rmk = request.getParameter("remark");
  String devid = request.getParameter("devid");
 String forwto = request.getParameter("forw");
 String dev_hr = request.getParameter("dev_hr");
 String probtype = request.getParameter("probtype").toString();
 String stattype = request.getParameter("stattype").toString();
 int len4=(rmk.length())/4;
 String rmk1=rmk.substring(0,len4);
 String rmk2=rmk.substring(len4, 2*len4);
 String rmk3=rmk.substring(2*len4, 3*len4);
 String rmk4=rmk.substring(3*len4, rmk.length());

 
 massql="update EFU_GIS.COMPLN_MASTER set ";
 massql=massql+" COMPLN_RMK_01='"+rmk1+"',COMPLAIN_TYPE='"+forwto+"',COMPLN_RMK_02='"+rmk2+"',COMPLN_RMK_03='"+rmk3+"',COMPLN_RMK_04='"+rmk4+"', ";
 massql=massql+" STATUS="+stattype+",DEVLPR_DATE=sysdate ";
 massql=massql+" ,DEVLPR_TIME=CURRENT_TIMESTAMP,PROBLEM_TYPE="+probtype+",DEVLPR_ID='"+devid+"', ";
 massql=massql+" DEVLPR_HRS='"+dev_hr+"' where COMPLN_ID='"+complnid+"' ";


         Connection connection = null;
        try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
    // connection = DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test","efu_gis", "test");
        connection =   
       DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");    
        //   DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");
                 Statement st = connection.createStatement();
                st.execute(massql);  
             //   Statement st2 = connection.createStatement();
             massql="";
               massql="update EFU_GIS.COMPLN_MASTER set CORDNTR_DATE=sysdate,CORDNTR_TIME =CURRENT_TIMESTAMP";  
                 massql=massql+" where COMPLN_ID='"+complnid+"' and CORDNTR_DATE is null";
                 st.execute(massql); 
          
            massql="";
               massql="update EFU_GIS.COMPLN_MASTER set  CORDNTR_ID='"+crid+"' ";  
                 massql=massql+" where COMPLN_ID='"+complnid+"' and CORDNTR_ID is null";
                 st.execute(massql); 
            }
        
            catch(Exception exp)
            {
              repenter=exp.getMessage();
            }
          
       
      //repenter= db1.insertmast(massql, sqldtl);
  //repenter= "test "+massql+"  "+sqldtl;
   
 out.println("|"+repenter+"|");
}
   if (tag.equals("login"))
   {
  
   String login = request.getParameter("logid");
    String pass2 = request.getParameter("pass");
   String truer="false";
  
  truer=db1.login(login, pass2);
  out.println("|"+truer+"|");
 
   }  
   
    
    if (tag.equals("test"))
    
    { String returned="6789";
      out.println("|"+returned+"|");

    }
       
 
     System.gc();   

 // response.setContentType("text/plain");
           // response.getWriter().write( hotelResult[i]);
             //  response.getWriter().write( name);
       %>   
    </body>
</html>