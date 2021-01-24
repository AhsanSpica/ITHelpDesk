package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import  java.io.*;
import java.util.*;

import javax.management.Query;

import javax.sql.DataSource;

import org.json.JSONArray;


public class DBCoding {
    public DBCoding() {
        super();
    }
     
    private static Connection conner2=null;
    


 
    public  ArrayList<String>  insertpriority (String dtl,String attn,String brid,String company,
                                JSONArray cls,JSONArray acc,JSONArray hrd,String sys,String cmp,String userid)
                                throws ClassNotFoundException, SQLException
    {
    Connection connection = null;
    ArrayList<String> list1 = new ArrayList<String>();
    
    String dump="";  String accsql="";String hrdsql=""; String massql="";
    int i=0;
          dtl = dtl.replace("'", " "); 
        dtl = dtl.replace(",", " ");
       
    int lenth=dtl.length();
    int len4=(dtl.length())/4;
    String dtl1,dtl2,dtl3,dtl4="";
    dtl1=dtl.substring(0,len4);
    
    dtl2=dtl.substring(len4, 2*len4);
    
    dtl3=dtl.substring(2*len4, 3*len4);
    dtl4=dtl.substring(3*len4);

    
    
    String getq=" Select EMPID from COMPLN_EMP where EMP_ROLE='"+attn+"'";
    String e_id="";
    
    try{
              Class.forName("oracle.jdbc.driver.OracleDriver");
    
              connection=
              //DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test","efu_gis", "test");
            DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis"); //oversight of changing back string from adg on 19th of feb at 12: correction amde on 20 feb
               //       DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
               Statement st_t = connection.createStatement();
               ResultSet rs_1=  st_t.executeQuery(getq);
               while (rs_1.next()){
               e_id=rs_1.getString(1) ;
               }   }
               catch(Exception exp)
               {list1.add( "this is getting empid for IT "+exp.getMessage());}
    
     
    
    
    massql=" insert into COMPLN_MASTER  ( user_id,BRANCH_ID,COMPLAIN_TYPE,COMPLN_SYS ,COMPLN_DTL_01,COMPLN_DTL_02,COMPLN_DTL_03";
    massql=massql+" ,COMPLN_DTL_04,CORDNTR_ID,COMP_ID) ";
    massql=massql+" values ('"+userid+"','"+brid+"',"+cmp+","+sys+", '"+dtl1+"', '"+dtl2+"', '"+dtl3+"', '"+dtl4+"',"+e_id+","+company+") ";
    String sqldtl="";
       try{
    
    
    
    }
    catch(Exception exp)
    {
    list1.add("error in query creation json exception catch "+exp.getMessage());
    }
    
    String key[] = {"COMPLN_ID"};
    try{
    
          Statement stm=connection.createStatement();
        // stm.executeUpdate(massql,key);
           stm.executeUpdate(massql);
    
       }              catch(Exception exp)
    {
    list1.add("error in compln_master insertion:   "+dtl);
    }
   
    String complid="";
           try{
           String sqlmax="  select max(COMPLN_ID) as complid from compln_master  where BRANCH_ID="+brid; 
               Statement st3 = connection.createStatement();
            ResultSet rs=  st3.executeQuery(sqlmax);
         
               while (rs.next()){
           complid =rs.getString("complid");     
       list1.add(rs.getString("complid")) ;
       } 
       }
          catch(Exception exp)
          {
            list1.add("error in master's max compl: "+exp.getMessage());
          }
    //insert details          
   Statement stdtl = null;
   
   boolean acctrue=false;
    try{
    String acctype="";
    //for COMPLN_ACT the act_type is varchar
    for ( i = 0; i < acc.length(); i++) {
    acctype = acc.getString(i);
    
    }
    if (!acctype.equals(null)) {
    acctrue=true;
    accsql=accsql+"insert into  COMPLN_ACT  ( ACT_TYPE,COMPLN_ACT_FK) values('"+acctype+"',' "+complid+  "'  )";
    }
    else{
    accsql=accsql+"insert into  COMPLN_ACT  ( ACT_TYPE,COMPLN_ACT_FK) values('0',' "+complid+  "'  )";
    }
    stdtl = connection.createStatement();
    stdtl.executeUpdate(accsql);
    
    
    }
    
    catch(Exception exp)
    {
       // acctrue=false;
    list1.add("error in COMPLN_ACT insertion: "+exp.getMessage());
    }
          
          
    
        String clssql=""; int clstyp=0;
        if (acctrue==false)
        {
            try{ 
             
              for ( i = 0; i < cls.length(); i++) {
              
              dump = cls.getString(i);
                 } 
             if (dump.equals(null)) {clstyp=0;   }
             else {clstyp=Integer.parseInt(dump) ;}
            
                clssql = "insert into COMPLN_CLASS(CLASS_ID,COMPLN_CLASS_FK) values("+clstyp+",'"+complid+ "')";
            //  sqldtl="insert ALL "+clssql+accsql+hrdsql;
          Statement      stdtl5 = connection.createStatement();
               stdtl5.executeUpdate(clssql);
             
          }
      
          catch(Exception exp)
          {
            list1.add("error in COMPLN_CLASS insertion:   "+clssql+"   "+clstyp+"  "+complid);
          }
        }
    try{
    int hrdtype=0;
    for ( i = 0; i < hrd.length(); i++)
    {
    dump = hrd.getString(i);
    }
    if (dump.equals(null)) {hrdtype=0; }
    else { hrdtype= Integer.parseInt(dump);}
    hrdsql=hrdsql+"insert into COMPLN_HRD (COMPLN_HRD_FK,HRD_CODE) values('"+complid+ "'  ,"+hrdtype+") ";
     stdtl = connection.createStatement();
    stdtl.executeUpdate(hrdsql);
    
    }
    
    catch(Exception exp)
    {
    list1.add("error in COMPLN_HRD insertion: "+exp.getMessage());
    }
    // repenter="true";
    
    
    
    connection.close();
    return list1;
    }
    
    
    
                public ArrayList<String> getallcomplnmain (String brid,String empid) throws ClassNotFoundException, SQLException
                // public ArrayList<String> getallcompln(String brid, String tp ) throws ClassNotFoundException, SQLException
                // public Map<String,String> getallcompln(String brid) throws ClassNotFoundException, SQLException
                {
        LinkedHashMap<String, String> jon =
            new LinkedHashMap<String, String>();
        Connection connection = null;
                ArrayList<String> list1 = new ArrayList<String>();
        ResultSet rs2;
        String Query = "";
        String date = "";
        Map<String, String> myMap = new HashMap<String, String>();
                
                try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection =
                   // DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test",      "efu_gis", "test");
            DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
        //  DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
           // connection= getConner();
            Statement statement = connection.createStatement();
                
            Query =
                    Query + "select cm.COMPLN_ID from COMPLN_MASTER cm where cm.BRANCH_ID=" +
                    brid + " and (cm.USER_ID)="+empid+"  order  by  cm.COMPLN_ID ASC";
                
            rs2 = statement.executeQuery(Query);
            list1.add("1110");
            while (rs2.next()) {
                //jon.put( rs2.getMetaData().getColumnName(1),rs2.getString(1));
                // if(jon.containsKey("COMPLN_ID")){
                list1.add(rs2.getString("COMPLN_ID"));
                //    }
                
                  //myMap.put(rs2.getString(1),rs2.getString(2));
                  }
            // for (int i=0; i<jon.size();i++){
            // if(jon.containsKey("COMPLN_ID")){
            //  list1.add(jon.get("COMPLN_ID"));
            //    }
            //    }
        } catch (Exception exp) {
            list1.add("error in getallcomplnmain(String brid) : " + exp.getMessage());
            // myMap.put("1", exp.getMessage());
                }
                
//        try{
//        connection.close();
//        }
//        catch(Exception ex)
//        {
//            list1.add(ex.getMessage());
//            }
        connection.close();
                return list1;
                }
                public String getcompln(String text) throws ClassNotFoundException,
                                               SQLException
            
                {
                Connection connection = null;
                String list1 = "";
        ResultSet resultset, r2, r3, r4;
                String Query = "";
        String date = "";
                
                try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection =
                 //   DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test",
                    //                            "efu_gis", "test");
                
                      DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
                //    DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
            Statement statement = connection.createStatement();
            // Query=Query+"select  unique COMPLN_ID,COMPLAIN_TYPE, nvl(COMPLN_SYS,'0 ') COMPLN_SYS,nvl(cc.CLASS_ID,'0 ') CLASS_ID, nvl(ca.ACT_TYPE,' ' ) ACT_TYPE,nvl(ch.HRD_CODE,'0 ') HRD_CODE, COMPLN_DATE ";
                Query =
                       Query + "select   COMPLN_ID,COMPLAIN_TYPE,  nvl(COMPLN_SYS,'0') COMPLN_SYS, nvl(cc.CLASS_ID,'0') CLASS_ID, ";
                Query =
                       Query + " nvl(ca.ACT_TYPE,'0') ACT_TYPE,nvl(ch.HRD_CODE,'0') HRD_CODE, to_char(COMPLN_DATE,'dd-MON-yy') COMPLN_DATE, ";
                Query =
                       Query + " nvl(CORDNTR_ID,'0') CORDNTR_ID , to_char(CORDNTR_DATE,'dd-MON-yy') CORDNTR_DATE , ";
                Query =
                       Query + "DEVLPR_ID , to_char(DEVLPR_DATE,'dd-MON-yy') DEVLPR_DATE , DEVLPR_HRS , nvl(PROBLEM_TYPE,'0') PROBLEM_TYPE , nvl(STATUS,'0') STATUS , ";
                Query =
                       Query + "(COMPLN_DTL_01||COMPLN_DTL_02||COMPLN_DTL_03||COMPLN_DTL_04) COMPLN_DTL,  ";
                Query =
                       Query + " (nvl(COMPLN_RMK_01,' ') ||nvl(COMPLN_RMK_02,' ') ||nvl(COMPLN_RMK_03,' ' ) || nvl(COMPLN_RMK_04,' ')) COMPLN_RMK,";
                Query =
                       Query + " TO_CHAR(CORDNTR_TIME,'HH24:MI') CORDNTR_TIME, TO_CHAR(DEVLPR_TIME,'HH24:MI') DEVLPR_TIME";
            
                Query =
                       Query + " , COMPLN_DTL_05";
                Query =
                       Query + " , TRIM(au.USER_NAME)";
                Query =
                       Query + " from compln_master cm full join COMPLN_ACT ca on   cm.COMPLN_ID=ca.COMPLN_ACT_FK";
                Query =
                       Query + " full join COMPLN_CLASS cc on cm.COMPLN_ID=cc.COMPLN_CLASS_FK ";
                Query =
                       Query + " full join COMPLN_HRD ch on cm.COMPLN_ID=ch.COMPLN_HRD_FK";
                Query =
                       Query + " full join app_users  au    on cm.CORDNTR_ID=au.USER_ID";
                Query = Query + " where  cm.COMPLN_ID=" + text + "  ";
            resultset = statement.executeQuery(Query);
                
                while (resultset.next()) {
                 list1 =
                         "|" + resultset.getString("COMPLN_ID") + "|" + resultset.getString("COMPLAIN_TYPE") +
                         "|" + resultset.getString("COMPLN_SYS");
                 list1 =
                         list1 + "|" + resultset.getString("CLASS_ID") + "|" + resultset.getString("ACT_TYPE") +
                         "|" + resultset.getString("HRD_CODE");
                 list1 = list1 + "|" + resultset.getString("COMPLN_DATE");
            
                 list1 =
                         list1 + "|" + resultset.getString(8) + "|" + resultset.getString(9) +
                         "|" + resultset.getString(10);
                 list1 =
                         list1 + "|" + resultset.getString(11) + "|" + resultset.getString(12) +
                         "|" + resultset.getString(13);
                 list1 = list1 + "|" + resultset.getString(14);
                 list1 =
                         list1 + "|" + resultset.getString(15) + "|" + resultset.getString(16);
                 list1 =
                         list1 + "|" + resultset.getString(17) + "|" + resultset.getString(18) ;
                 list1 = list1 + "|" + resultset.getString(19) + "|" + resultset.getString(20) + "|";
                
                }
            // code for multiple selections query from database
            //             Statement s2 = connection.createStatement();
            //             Query=" select  cc.CLASS_ID,ec.CLASS_DESC from compln_master cm ";
            //                      Query=Query+"  join COMPLN_CLASS cc on cm.COMPLN_ID=cc.COMPLN_CLASS_FK ";
            //             Query=Query+"  join EFU_MAST.CLASSES ec on cc.CLASS_ID=ec.CLASS_ID  ";
            //
            //             Query=Query+" where  cm.COMPLN_ID="+text;
            //             r2 =s2.executeQuery(Query);
            //
            //             while (r2.next()){
            //             list1=list1+"*"+r2.getString(1);
            //
            //             }
            //             Statement s3 = connection.createStatement();
            //                    Query="  select ca.ACT_TYPE, ec.ACC_SYS";
            //                        Query=Query+"   from compln_master cm "; Query=Query+" join COMPLN_ACT ca on   cm.COMPLN_ID=ca.COMPLN_ACT_FK";
            //                Query=Query+"  join COMPLN_ACC_DTL ec on ca.ACT_TYPE=ec.ACC_SYS_ID  ";
            //             Query=Query+" where  cm.COMPLN_ID="+text;
            //             r3 =s3.executeQuery(Query);
            //
            //             while (r3.next())
            //             {
            //              list1=list1+"#"+r3.getString(1);}
            //
            //             Statement s4 = connection.createStatement();
            //                     Query="    select   ch.HRD_CODE, ec.DEVICE_DESC ";
            //                         Query=Query+"   from compln_master cm ";
            //                         Query=Query+"   join COMPLN_HRD ch on cm.COMPLN_ID=ch.COMPLN_HRD_FK";
            //             Query=Query+" join  COMPLN_HRD_DTL ec on ch.HRD_CODE=ec.DEVICE_ID";
            //             Query=Query+" where  cm.COMPLN_ID="+text;
            //             r4 =s4.executeQuery(Query);
            //             while (r4.next())
            //             {
            //                 list1=list1+"&"+r4.getString(1);}
                
                }
                
                catch (Exception exp) {
                list1 = "error in getcompln() for fetching complaint info when slected by user : " + exp.getMessage();
                }
                connection.close();
                return list1;
                }
            
                public String getcompln2(String text) throws ClassNotFoundException,
                                                SQLException
            
                {
        Connection connection = null;
                String list1 = "";
        ResultSet resultset;
                String Query = "";
        String date = "";
                
                try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection =
                 //   DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test",
                 //                               "efu_gis", "test");
                 DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
                //    DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
            Statement statement = connection.createStatement();
                Query =
                       Query + "select   COMPLN_ID,COMPLAIN_TYPE,  nvl(COMPLN_SYS,'0') COMPLN_SYS, nvl(cc.CLASS_ID,'0') CLASS_ID";
                Query =
                       Query + ", nvl(ca.ACT_TYPE,'0') ACT_TYPE,nvl(ch.HRD_CODE,'0') HRD_CODE, to_char(COMPLN_DATE,'dd-MON-yy') COMPLN_DATE";
                Query =
                       Query + ",(COMPLN_DTL_01||COMPLN_DTL_02||COMPLN_DTL_03||COMPLN_DTL_04) COMPLN_DTL  ";
                Query =
                       Query + ", (nvl(COMPLN_RMK_01,' ') ||nvl(COMPLN_RMK_02,' ') ||nvl(COMPLN_RMK_03,' ' ) || nvl(COMPLN_RMK_04,' ')) COMPLN_RMK ";
                Query =
                       Query + ",nvl(CORDNTR_ID,'0') CORDNTR_ID,  to_char(CORDNTR_DATE,'dd-MON-yy') CORDNTR_DATE,TO_CHAR(CORDNTR_TIME,'HH24:MI') CORDNTR_TIME,";
                Query =
                       Query + " DEVLPR_ID,  to_char(DEVLPR_DATE,'dd-MON-yy')  DEVLPR_DATE,TO_CHAR(DEVLPR_TIME,'HH24:MI') DEVLPR_TIME ";
                Query =
                       Query + ", DEVLPR_HRS, nvl(cm.PROBLEM_TYPE,'0' )  PROBLEM_TYPE, nvl(cm.STATUS,'0') STATUS ";
                Query = Query + " ,TO_CHAR(cm.COMPLN_TIME,'HH24:MI') COMPLN_TIME";
                Query = Query + " ,cm.USER_ID,cm.BRANCH_ID,TRIM(emp.USER_NAME)";
                Query =Query + " , COMPLN_DTL_05";
                Query =Query + " , TRIM(au.USER_NAME) ";
                Query =Query + "   ,TRIM(br.BRANCH_NM) BRANCH_NM ";
                
                Query =Query + " from compln_master cm  full  join COMPLN_ACT ca on   cm.COMPLN_ID=ca.COMPLN_ACT_FK";
                
                Query =Query + "   full join  app_users emp on cm.USER_ID=emp.USER_ID";
                
                Query =Query + " full join COMPLN_CLASS cc on cm.COMPLN_ID=cc.COMPLN_CLASS_FK ";
                
                Query =Query + " full join COMPLN_HRD ch on cm.COMPLN_ID=ch.COMPLN_HRD_FK";
                
                Query =Query + " full join app_users  au    on cm.CORDNTR_ID=au.USER_ID";
                Query =Query + "    left join  EFU_MAST.BRANCHES  br    on  cm.BRANCH_ID=br.BRANCH_ID ";
                
                Query = Query + " where  cm.COMPLN_ID=" + text;
            resultset = statement.executeQuery(Query);
                
                while (resultset.next()) {
                 list1 =
                         "|" + resultset.getString("COMPLN_ID") + "|" + resultset.getString("COMPLAIN_TYPE") +
                         "|" + resultset.getString("COMPLN_SYS");
                 list1 =
                         list1 + "|" + resultset.getString("CLASS_ID") + "|" + resultset.getString("ACT_TYPE") +
                         "|" + resultset.getString("HRD_CODE");
                 list1 = list1 + "|" + resultset.getString("COMPLN_DATE");
                 list1 =
                         list1 + "|" + resultset.getString("COMPLN_DTL") + "|" + resultset.getString("COMPLN_RMK");
                 list1 =
                         list1 + "|" + resultset.getString(10) + "|" + resultset.getString(11) +
                         "|" + resultset.getString(12) + "|" +
                         resultset.getString(13);
                 list1 =
                         list1 + "|" + resultset.getString(14) + "|" + resultset.getString(15) +
                         "|" + resultset.getString(16) + "|" +
                         resultset.getString(17);
                 list1 =
                         list1 + "|" + resultset.getString(18) + "|" + resultset.getString(19) +
                         "|" + resultset.getString(20) + "|" +
                         resultset.getString(21);
                 list1 = list1 + "|" + resultset.getString(22) ;
                 list1 = list1 + "|" + resultset.getString(23) + "|"+ resultset.getString(24) + "|"+ resultset.getString(25) + "|";
                }
                } catch(Exception exp) {
            list1 = "error in getcompln2() for fetching complaint info when slected by user : " + exp.getMessage();
                }
        connection.close();
                return list1;
                }
            
                
                
                public ArrayList<String> getalldoneajax(String eid,
                                         String emptp,String brid) throws ClassNotFoundException,
                                                              SQLException
                
                {
        Connection connection = null;
                ArrayList<String> list1 = new ArrayList<String>();
        ResultSet resultset;
                String Query = "";
        String date = "";
        Map<String, String> myMap = new HashMap<String, String>();
                
                try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection =
              //      DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test",  "efu_gis", "test");
                
           DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
          //last temp     DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");             
           // connection= getConner();
            Statement statement = connection.createStatement();
                if (emptp.equals("C") || emptp.equals("AC")||emptp.equals("D")) {
                   if (!emptp.equals("AC")) {
                       Query = Query + "select COMPLN_ID from COMPLN_MASTER   where (DEVLPR_ID=" +eid + "  or CORDNTR_ID=" + eid +") and COMPLAIN_TYPE='1'  " ;
                         Query = Query +   " AND  STATUS  in (1)  and BRANCH_ID=  " +brid + "     order  by   COMPLN_ID ASC ";
                   } 

                   else {
                       Query =
                               Query + "select COMPLN_ID from COMPLN_MASTER  WHERE   COMPLAIN_TYPE='1' AND  STATUS  in (1)  and BRANCH_ID= ' " +brid + "'  order  by   COMPLN_ID ASC ";
                   }
                } else if (emptp.equals("H") || emptp.equals("AH") ||
                          emptp.equals("N")) {
                   //                     if(!eid.equals("1344"))
                   //                     {
                   //                         Query=Query+"select COMPLN_ID from COMPLN_MASTER   where DEVLPR_ID="+eid+" AND  COMPLAIN_TYPE='2'   order  by   COMPLN_ID ASC " ;
                   //                         }
                   //
                   //                     else
                   {
                       Query =
                               Query + "select COMPLN_ID from COMPLN_MASTER   where (DEVLPR_ID=" +
                               eid + " and COMPLAIN_TYPE='2'   AND  STATUS  in (1,2)  and BRANCH_ID=  " +brid + "  ) or( CORDNTR_ID=" + eid +
                               " and COMPLAIN_TYPE='2'   AND  STATUS  in (1,2)  and BRANCH_ID=  " +brid + ")   order  by   COMPLN_ID ASC ";
                   }
            
                }
            else if(emptp.equals("U")||emptp.equals("P") ) {                    Query =
                            Query + "select COMPLN_ID from COMPLN_MASTER  where COMPLAIN_TYPE='1' AND  STATUS   in (1 )   and BRANCH_ID=  " +brid + "  order  by   COMPLN_ID ASC ";}
                
            resultset = statement.executeQuery(Query);
            //  list1.add("1110");
                while (resultset.next()) {
                 list1.add(resultset.getString("COMPLN_ID"));     
                //myMap.put(resultset.getString(1),resultset.getString(2));
                }
                } catch(Exception exp) {
                list1.add("Error in getalldone( ) for it service side dropdown: " + exp.getMessage());
            // myMap.put("1", exp.getMessage());
                }
                
    //        try{
        connection.close();
    //        }
    //        catch(Exception ex)
    //        {
    //            list1.add(ex.getMessage());
    //            }
                
                return list1;
                }
                

    public ArrayList<String> getonlyopenajax(String eid, String emptp,
                                             String brid, String open) throws ClassNotFoundException,
                                                                 SQLException
         
        {
        Connection connection = null;
        ArrayList<String> list1 = new ArrayList<String>();
        ResultSet resultset;
        String Query = "";
        String date = "";
        Map<String, String> myMap = new HashMap<String, String>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection =
            //       DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test",     "efu_gis", "test");
           
            DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
     //    DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
           // connection= getConner();
          
          
            Statement statement = connection.createStatement();


            if (emptp.equals("C") || emptp.equals("AC")||emptp.equals("D")) {
                if (!emptp.equals("AC")) {
                 if (open.equals("yes"))   { 
                 Query = Query + "select COMPLN_ID from COMPLN_MASTER   where (DEVLPR_ID=" +eid + "  or CORDNTR_ID=" + eid +") and COMPLAIN_TYPE='1'  " ;
                      Query = Query +   " AND  STATUS  in (3)  and BRANCH_ID=  " +brid + "     order  by   COMPLN_ID ASC ";   }
                 
                 
                   else  {    
                     Query = Query + "select COMPLN_ID from COMPLN_MASTER   where (DEVLPR_ID=" +eid + "  or CORDNTR_ID=" + eid +") and COMPLAIN_TYPE='1'  " ;
                      Query = Query +   " AND  STATUS  in (2)  and BRANCH_ID=  " +brid + "     order  by   COMPLN_ID ASC ";}
                
                }
                else {          if (open.equals("yes"))   {          Query = Query + "select COMPLN_ID from COMPLN_MASTER   where COMPLAIN_TYPE='1'  " ;
                      Query = Query +   " AND  STATUS  in (3)  and BRANCH_ID=  " +brid + "     order  by   COMPLN_ID ASC ";  }
                        else  {    
                          Query = Query + "select COMPLN_ID from COMPLN_MASTER   where  COMPLAIN_TYPE='1'  " ;
                           Query = Query +   " AND  STATUS  in (2)  and BRANCH_ID=  " +brid + "     order  by   COMPLN_ID ASC ";}
                    
                    }


            } else if (emptp.equals("H") || emptp.equals("AH") ||
                       emptp.equals("N")) {
                //                     if(!eid.equals("1344"))
                //                     {
                //                         Query=Query+"select COMPLN_ID from COMPLN_MASTER   where DEVLPR_ID="+eid+" AND  COMPLAIN_TYPE='2'   order  by   COMPLN_ID ASC " ;
                //                         }
                //
                //                     else
                // {
                if (open.equals("yes")) {  Query =
                        Query + "select COMPLN_ID from COMPLN_MASTER  where COMPLAIN_TYPE='2'  AND  STATUS  in (3)   and BRANCH_ID=  " +
                        brid + "   order  by   COMPLN_ID ASC ";}
                
                else {  Query =
                        Query + "select COMPLN_ID from COMPLN_MASTER  where COMPLAIN_TYPE='2'  AND  STATUS  in (2)   and BRANCH_ID=  " +
                        brid + "   order  by   COMPLN_ID ASC ";}
                //  }

            }
           else if(emptp.equals("U")||emptp.equals("P")) {        
                                   if (open.equals("yes")) {   
                Query =
         Query + "select COMPLN_ID from COMPLN_MASTER  where COMPLAIN_TYPE='1' AND  STATUS  in (3 )  and BRANCH_ID=  " +brid +  "  order  by   COMPLN_ID ASC ";
                                   }                                                                
                                   else{   
                Query =
        Query + "select COMPLN_ID from COMPLN_MASTER  where COMPLAIN_TYPE='1' AND  STATUS  in (2 )  and BRANCH_ID=   " +brid +  "  order  by   COMPLN_ID ASC ";
                                   }                                                                
                                                                                                 
                                                                                                 }


            resultset = statement.executeQuery(Query);
            // list1.add("1110");
            while (resultset.next()) {
                list1.add(resultset.getString("COMPLN_ID"));
                //myMap.put(resultset.getString(1),resultset.getString(2));
            }
        } catch (Exception exp) {
            list1.add("Error in getonlyopenajax( ) for it service side dropdown: " + exp.getMessage());
            // myMap.put("1", exp.getMessage());
        }
        try{
        connection.close();
        }
        catch(Exception ex)
        {
            list1.add(ex.getMessage());
            }
        return list1;
    }

    public ArrayList<String> getallajaxcompln(String eid, String emptp,
                                              String brid) throws ClassNotFoundException,
                                                                  SQLException
    // public ArrayList<String> getallcompln(String brid, String tp ) throws ClassNotFoundException, SQLException
    // public Map<String,String> getallcompln(String brid) throws ClassNotFoundException, SQLException
    {
        Connection connection = null;
        ArrayList<String> list1 = new ArrayList<String>();
        ResultSet resultset;
        String Query = "";
        String date = "";
        Map<String, String> myMap = new HashMap<String, String>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection =
                //    DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test",
                //                                "efu_gis", "test");
           DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
         //    DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
            Statement statement = connection.createStatement();
            if (emptp.equals("C") || emptp.equals("AC")||emptp.equals("D")) {
                if (!emptp.equals("AC")) {
                   Query = Query + "select COMPLN_ID from COMPLN_MASTER   where (DEVLPR_ID=" +eid + "  or CORDNTR_ID=" + eid +") and COMPLAIN_TYPE='1'  " ;
                     Query = Query +   "   and BRANCH_ID=  " +brid + "     order  by   COMPLN_ID ASC ";
              //  } 
//                else if (eid.equals("2494")) {
//                    Query =
//                            Query + "select COMPLN_ID from COMPLN_MASTER   where  (DEVLPR_ID=" +
//                            eid + " and BRANCH_ID= '" + brid +
//                            "') or (compln_sys=9 and BRANCH_ID=' " + brid +
//                            "' )  or ( CORDNTR_ID=" + eid +
//                            " and BRANCH_ID= '" + brid +
//                            "' )   order  by   COMPLN_ID ASC ";
//
               }
                else
                {                    Query =
                            Query + "select COMPLN_ID from COMPLN_MASTER   where BRANCH_ID=" + brid +
                            " and COMPLAIN_TYPE='1'   order  by   COMPLN_ID ASC ";}
                
              
            } else if (emptp.equals("H") || emptp.equals("AH") ||
                       emptp.equals("N")) {
                //                     if(!eid.equals("1344"))
                //                     {
                //                         Query=Query+"select COMPLN_ID from COMPLN_MASTER   where DEVLPR_ID="+eid+" AND  COMPLAIN_TYPE='2'   order  by   COMPLN_ID ASC " ;
                //                         }
                //
                //                     else
               // {
               Query =
                       Query + "select COMPLN_ID from COMPLN_MASTER   where (DEVLPR_ID=" +
                       eid + " and BRANCH_ID=" + brid +
                       ")  or ( CORDNTR_ID= " + eid +
                       " and BRANCH_ID=  " + brid +
                       " and COMPLAIN_TYPE='2')  order  by   COMPLN_ID ASC ";
               // }

            }
            else if(emptp.equals("U")||emptp.equals("P") ) {                    Query =
                            Query + "select COMPLN_ID from COMPLN_MASTER  where COMPLAIN_TYPE='1'     order  by   COMPLN_ID ASC ";}

            resultset = statement.executeQuery(Query);
            //  list1.add("1110");
            while (resultset.next()) {
                list1.add(resultset.getString("COMPLN_ID"));
                //myMap.put(resultset.getString(1),resultset.getString(2));
            }
        } catch (Exception exp) {
            list1.add("Error in getallajaxcompln( ) for it service side dropdown: " + exp.getMessage());
            // myMap.put("1", exp.getMessage());
        }
        connection.close();
        return list1;
    }

    
    
    public ArrayList<String> ticker(String str, String type ) throws ClassNotFoundException,
                                                                 SQLException
    //public ArrayList<String> ticker(String type, String empid) throws ClassNotFoundException,
     //                                                            SQLException
    {
        Connection connection = null;
        ArrayList<String> list1 = new ArrayList<String>();
        ResultSet resultset;
        String Query = "";
      

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection =
           //         DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test",
           //                                     "efu_gis", "test");
           DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
          //  DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
            Statement statement = connection.createStatement();


            // if (type.equals("AH")||type.equals("H")||type.equals("N"))    
             {
                Query =
                        Query + "select COMPLN_ID from COMPLN_MASTER  where COMPLAIN_TYPE='2'   ";
            Query =
                    Query +"AND  STATUS not in (1,2) and COMPLN_TIME >= sysdate - 30/(24*60)   order  by COMPLN_TIME DESC";
            
            }
          
           
           
           // else    if (empid.equals("429"))  
           //  {                   Query =
           //             Query + "select COMPLN_ID from COMPLN_MASTER  where COMPLAIN_TYPE='1'   ";
          //  Query =
          //          Query +"AND  STATUS not in (1,2) and COMPLN_TIME >= sysdate - 10/(24*60)   order  by COMPLN_TIME DESC";   }
            
          //  else    
          //   {                   Query =
          //              Query + "select COMPLN_ID from COMPLN_MASTER  where COMPLAIN_TYPE='1'   ";
          //              Query =Query +" AND ( CORDNTR_ID=" + empid + " OR  DEVLPR_ID=" + empid + " ) ";
           //  Query =
           //         Query +"AND  STATUS not in (1,2) and COMPLN_TIME >= sysdate - 10/(24*60)   order  by COMPLN_TIME DESC";   }
           
           
            resultset = statement.executeQuery(Query);
            // list1.add("1110");
            while (resultset.next()) {
                list1.add(resultset.getString("COMPLN_ID"));
                //myMap.put(resultset.getString(1),resultset.getString(2));
            }
        } catch (Exception exp) {
            list1.add("Error in ticker( ) for it service side dropdown: " + exp.getMessage());
            // myMap.put("1", exp.getMessage());
        }
        connection.close();
        return list1;
    }
    
     public String GetIso (String empid,String mon,String yr, String emptp)throws ClassNotFoundException,
                                               SQLException
     {
             Connection connection = null;
             List<ISOModel> employee=new ArrayList<ISOModel>();
            String ans="";
        ResultSet r;
       String probdtl="";
                        String Query=""; 
                        String time="";
            if (!mon.equals(""))
            {
                time= mon+"-"+yr ;
            }
             
             ArrayList<String> list1 = new ArrayList<String>();
                         try{
                            
                             Class.forName("oracle.jdbc.driver.OracleDriver");
                             connection =
                                 //    DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test",
                                  //                               "efu_gis", "test");
                          DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
                          //    DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
                             Statement st = connection.createStatement();

         Query =Query + "        select  cm.COMPLN_ID,TRIM(br.BRANCH_NM ) BRANCH_NM,sys_e.SYS_DESC System_Name,'Web App' Medium,TRIM(unm.EMPLOYEE_NAME) ";
            Query =Query + "     Person_name,to_char(cm.COMPLN_DATE,'dd-mm-yyyy')  COMPLN_DATE,(cm.COMPLN_DTL_01||cm.COMPLN_DTL_02||";
             Query =Query + "    cm.COMPLN_DTL_03||cm.COMPLN_DTL_04||cm.COMPLN_DTL_05) Problem, to_char(cm.CORDNTR_DATE,'dd-mm-yyyy') Resolved_on,  ";
            Query =Query + "     DECODE(cm.STATUS,'1','OK','2','Process','3','Pending' ) Status,";
            Query =Query + "     DECODE(cmp.EMP_TYPE,'C','Self','AC','Self','D','Developer','H','Engineer','AH','Engineer','N','Engineer') Resolved_by ,";
          Query =Query + "       TRIM(dv.EMPLOYEE_NAME) Resolver_D from COMPLN_MASTER cm";
             
               Query =Query + "  join  EFU_MAST.BRANCHES br on cm.BRANCH_ID=br.BRANCH_ID  ";
             Query =Query + "    AND  cm.COMP_ID=br.COMP_ID   ";
              Query =Query + "   join  efu_pis.vw_employee unm on cm.USER_ID=unm.EMP_NO ";
                Query =Query + " join COMPLN_EMP cmp on cm.DEVLPR_ID=cmp.EMPID ";
            Query =Query + "     join efu_pis.vw_employee dv on cm.DEVLPR_ID=dv.EMP_NO ";
               Query =Query + "  left join COMPLN_SYS_DESC sys_e on cm.COMPLN_SYS=sys_e.SYS_ID where";
          //   Query =Query + "    cmp.EMP_TYPE='"+emptp+"' and ";
                             if (empid.equals(""))
                             {  
                          
                           }
                             else 
                             {
                                Query =Query + "  (DEVLPR_ID='"+empid+"' or CORDNTR_ID= '"+empid+"')  and  ";  
                                 }
                             if (mon.equals(""))
                             {   
                             Query =Query + "    to_char(cm.COMPLN_DATE,'yyyy')='"+yr+"' ";
                         }
                             else {
                                     Query =Query + "    to_char(cm.COMPLN_DATE,'MON-yyyy')='"+time+"' ";
                                 
                                 }
                             Query =Query + " order  by   COMPLN_DATE DESC ";
                            
                             r = st.executeQuery(Query);
                             ans=ans+"<table  id='infotb' style=' border-collapse: separate;border-spacing: 0; border-top: 0;table-layout:fixed; width: 99.0%; background-color:White; font-family: arial, sans-serif;font-size: 12px;'><thead><tr>";
                             ans=ans+"<th style='width:7px;' class='field-style2'>index#</th>";
                             ans=ans+"<th style='width:7px;' class='field-style2'>SR#</th><th style='width:7px;' class='field-style2'>Branch / Dept.</th><th style='width:7px;' class='field-style2'>System</th><th style='width:7px;' class='field-style2'>Medium</th><th style='width:7px;' class='field-style2'>Person Name</th><th style='width:7px;' class='field-style2'>Intimated Date</th><th style='width:7px;' class='field-style2'>Problem</th><th style='width:7px;' class='field-style2'>Resolved On</th><th style='width:4px;' class='field-style2'> Status </th><th style='width:4px;' class='field-style2'>Resolved By</th></tr></thead>";
                            int i=0;
                             while (r.next()) {
//                                 ISOModel user = new ISOModel();  
//                            user.setBranch(rs.getString(""));
//                                 user.setIntimated_Date(rs.getString(""));
//                                 user.setMedium(rs.getString(""));
//                                 user.setPerson(rs.getString(""));
//                                 user.setProblem(rs.getString(""));
//                                 user.setResolved_On(rs.getString(""));
//                                 user.setStatus(rs.getString(""));
//                                 user.setSystem(rs.getString(""));
                                // list1.add(rs.getString(1)+"#"+rs.getString(2)+"#"+rs.getString(3)+"#"+rs.getString(4)+"#"+rs.getString(5)+"#"+rs.getString(6)+"#"+rs.getString(7)+"#"+rs.getString(8)+"#"+rs.getString(9)+"#"+rs.getString(10)+"#"+rs.getString(11)); 
                                probdtl= r.getString(7);
                                 if (probdtl.length()>60)
                                 {probdtl=probdtl.substring(0, 60);}
                     ans=ans+ "<tr class='clid'>";
                     ans=ans+ "<td style='width:1px;' class='field-style2'>"+(++i) + "</td>";
                ans=ans+ "<td style='width:4px;' class='field-style2'>"+r.getString(1) + "</td>";
                ans=ans + "<td style='width:4.5px;' class='field-style2'>"+r.getString(2)+"</td><td style='width:7px;' class='field-style2'>";
                ans=ans + r.getString(3)+"</td><td style='width:7px;' class='field-style2'>"+r.getString(4)+"</td><td style='width:7px;' class='field-style2'>";
                ans=ans + r.getString(5)+"</td><td style='width:7px;' class='field-style2'>"+r.getString(6)+"</td><td style='width:7px;' class='field-style2'>";
                ans=ans + probdtl+"</td><td style='width:7px;' class='field-style2'>"+r.getString(8)+"</td><td style='width:4px;' class='field-style2'>";
                ans=ans + r.getString(9)+"</td><td style='width:4px;' class='field-style2'>"+r.getString(10)+"</td></tr>";
                 }
                             ans=ans+ "</table>";   
                             
                            
                         }
                         
             catch (Exception exp) {
                             list1.add("Error in getISO( ) for it service side dropdown: " + exp.getMessage());
                             // myMap.put("1", exp.getMessage());
                         }
         connection.close();
          return ans;
         }
     
   
     
    public void close(String com, String complnid)throws ClassNotFoundException,
                                               SQLException
    
    {
          
            Connection connection = null;
            String Query = "";

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection =
                 //       DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test",
                 //                                   "efu_gis", "test");
           DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
                //      DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
                Statement st = connection.createStatement();

                Query="update EFU_GIS.COMPLN_MASTER set STATUS='1',COMPLN_DTL_05='"+com+"'";  
                                Query=Query+"  where COMPLN_ID='"+complnid+"' ";

               
                             st.execute(Query);  
               
                // for (int i=0; i<jon.size();i++){
                // if(jon.containsKey("COMPLN_ID")){
                //  list1.add(jon.get("COMPLN_ID"));
                //    }
                //    }
           
           
            } catch (Exception exp) {
                Query=("Error in close( ) for it service side : " + exp.getMessage());
                // myMap.put("1", exp.getMessage());
            }
            connection.close();
          
        }
    
    public void cancel(String complnid)throws ClassNotFoundException,
                                               SQLException
    
    {
            Connection connection = null;
            String Query = "";

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection =
                   //     DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test",
                  //                                  "efu_gis", "test");
            DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
                //    DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
                Statement st = connection.createStatement();

                Query="update EFU_GIS.COMPLN_MASTER set STATUS='1'  ";  
                                Query=Query+"  where COMPLN_ID='"+complnid+"' ";
               
                             st.execute(Query);  
               
                // for (int i=0; i<jon.size();i++){
                // if(jon.containsKey("COMPLN_ID")){
                //  list1.add(jon.get("COMPLN_ID"));
                //    }
                //    }
            } catch (Exception exp) {
                Query=("Error in cancel( ) for it service side : : " + exp.getMessage());
                // myMap.put("1", exp.getMessage());
            }
            connection.close();
        }
    
    

    public ArrayList<String> getdev() throws ClassNotFoundException,
                                             SQLException
    // public Map<String,String> getallcompln(String brid) throws ClassNotFoundException, SQLException
    {
        Connection connection = null;
        ArrayList<String> list1 = new ArrayList<String>();
        ResultSet resultset;
        String Query = "";
        String date = "";
        Map<String, String> myMap = new HashMap<String, String>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
          connection =
           //         DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test",  "efu_gis", "test");
         DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
             //    DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
            Statement statement = connection.createStatement();
            Query =
                    Query + "select TRIM(EMPID) EMPID,TRIM(EMP_NM) EMP_NM from COMPLN_EMP cm where TRIM(EMP_TYPE)='D' order by EMPID";

            resultset = statement.executeQuery(Query);

            while (resultset.next()) {
               list1.add(resultset.getString(1));
          //  list1.add(resultset.getString("EMPID") + "#" +        resultset.getString("EMP_NM"));
                // list1.add(resultset.getString(1)+"|"+resultset.getString(2));
                //myMap.put(resultset.getString(1),resultset.getString(2));
            }
        } catch (Exception exp) {
            list1.add("Error in getdev( ) for it service side : : " + exp.getMessage());
            // myMap.put("1", exp.getMessage());
        }
        connection.close();
        return list1;
    }


    public ArrayList<String> getcrd() throws ClassNotFoundException,
                                             SQLException
    // public Map<String,String> getallcompln(String brid) throws ClassNotFoundException, SQLException
    {
        Connection connection = null;
        ArrayList<String> list1 = new ArrayList<String>();
        ResultSet resultset;
        String Query = "";
        String date = "";
        Map<String, String> myMap = new HashMap<String, String>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection =
                 //   DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test",  "efu_gis", "test");
            
        DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
            //    DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
        // conner;
            Statement statement = connection.createStatement();
            Query =
                    Query + "select TRIM(EMPID) EMPID,TRIM(EMP_NM) EMP_NM from COMPLN_EMP cm where TRIM(EMP_TYPE)='C'";

            resultset = statement.executeQuery(Query);

            while (resultset.next()) {
            list1.add(resultset.getString(1));
           //  list1.add(resultset.getString("EMPID") + "#" +     resultset.getString("EMP_NM"));
                //myMap.put(resultset.getString(1),resultset.getString(2));
            }
        } catch (Exception exp) {
            list1.add("Error in getcrd( ) for it service side : : " + exp.getMessage());
            // myMap.put("1", exp.getMessage());
        }
        connection.close();
        return list1;
    }

    public ArrayList<String> geteng() throws ClassNotFoundException,
                                             SQLException
    // public Map<String,String> getallcompln(String brid) throws ClassNotFoundException, SQLException
    {
        Connection connection = null;
        ArrayList<String> list1 = new ArrayList<String>();
        ResultSet resultset;
        String Query = "";
        String date = "";
        Map<String, String> myMap = new HashMap<String, String>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection =
                  //  DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test",
                     //                           "efu_gis", "test");
            
      DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
              //    DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
            Statement statement = connection.createStatement();
            Query =
                    Query + "select TRIM(EMPID) EMPID,TRIM(EMP_NM) EMP_NM  from COMPLN_EMP cm where TRIM(EMP_TYPE)='H' or TRIM(EMP_TYPE)='AH'or TRIM(EMP_TYPE)='N'";

            resultset = statement.executeQuery(Query);

            while (resultset.next()) {
               list1.add(resultset.getString(1));
            // list1.add(resultset.getString("EMPID") + "#" +   resultset.getString("EMP_NM"));
                //myMap.put(resultset.getString(1),resultset.getString(2));
            }
        } catch (Exception exp) {
            list1.add("Error in geteng( ) for it service side : : " + exp.getMessage());
            // myMap.put("1", exp.getMessage());
        }
        connection.close();
        return list1;
    }
    
    
    
    public  String  getempnmonly( String type) throws ClassNotFoundException,
                                               SQLException
    // public Map<String,String> getallcompln(String brid) throws ClassNotFoundException, SQLException
    {
        Connection connection = null;
        ArrayList<String> list1 = new ArrayList<String>();
        String str="";
        ResultSet resultset;
        String Query = "";
        String date = "";
        Map<String, String> myMap = new HashMap<String, String>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection =
            //        DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test",  "efu_gis", "test");
         DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
            //   DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
            Statement statement = connection.createStatement();
            if(type.equals("H")||type.equals("AH"))
                 {
        Query =
    Query + "select TRIM(EMPID) EMPID,TRIM(EMP_NM) EMP_NM from COMPLN_EMP cm where TRIM(EMP_TYPE)='H' or TRIM(EMP_TYPE)='AH' or TRIM(EMP_TYPE)='N' order by EMPID";
                }
            else  if(type.equals("C")||type.equals("AC"))
                 {
        Query =
    Query + "select TRIM(EMPID) EMPID,TRIM(EMP_NM) EMP_NM from COMPLN_EMP cm where TRIM(EMP_TYPE)='C' or TRIM(EMP_TYPE)='AC'  order by EMPID";
                }
            else  {Query =
        Query + "select TRIM(EMPID) EMPID,TRIM(EMP_NM) EMP_NM from COMPLN_EMP cm where TRIM(EMP_TYPE)='"+type+"' order by EMPID";
                }
          
            resultset = statement.executeQuery(Query);
            str=str+"#";
            while (resultset.next()) {
               // list1.add(resultset.getString("EMP_NM"));
                str=str+resultset.getString("EMP_NM")+"&"+resultset.getString("EMPID")+"#";
                //  list1.add(resultset.getString(1)+" "+resultset.getString(2));
                // list1.add(resultset.getString(1)+"|"+resultset.getString(2));
                //myMap.put(resultset.getString(1),resultset.getString(2));
            }
        } catch (Exception exp) {
            
            str="Error in getempnmonly( ) for it service side : : " + exp.getMessage();
        }
        connection.close();
      //  return list1;
      return str;
    }
    public ArrayList<String> getempnm(String type) throws ClassNotFoundException,
                                               SQLException
    // public Map<String,String> getallcompln(String brid) throws ClassNotFoundException, SQLException
    {
        Connection connection = null;
        ArrayList<String> list1 = new ArrayList<String>();
        ResultSet resultset;
        String Query = "";
        String date = "";
        Map<String, String> myMap = new HashMap<String, String>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection =
                //    DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test", "efu_gis", "test");
            
      DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
              //    DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
            
            Statement statement = connection.createStatement();
           if (!type.equals("all")) { if(type.equals("H")||type.equals("AH"))
                 {
            Query =
            Query + "select TRIM(EMPID) EMPID,TRIM(EMP_NM) EMP_NM from COMPLN_EMP cm where TRIM(EMP_TYPE)='H' or TRIM(EMP_TYPE)='AH' or TRIM(EMP_TYPE)='N' order by EMPID";
                }
            else if (type.equals("C")||type.equals("AC"))
                 {
            Query =
            Query + "select TRIM(EMPID) EMPID,TRIM(EMP_NM) EMP_NM from COMPLN_EMP cm where TRIM(EMP_TYPE)='C' or TRIM(EMP_TYPE)='AC'  order by EMPID";
                }
            else  {Query =
            Query + "select TRIM(EMPID) EMPID,TRIM(EMP_NM) EMP_NM from COMPLN_EMP cm where TRIM(EMP_TYPE)='"+type+"' order by EMPID";
                }
                                      }
           else{
               Query =
                           Query + "select TRIM(EMPID) EMPID,TRIM(EMP_NM) EMP_NM from COMPLN_EMP cm  order by EMPID"; 
               
           }
               
            resultset = statement.executeQuery(Query);

            while (resultset.next()) {
                list1.add(resultset.getString("EMPID") + "#" +
                          resultset.getString("EMP_NM"));
                //  list1.add(resultset.getString(1)+" "+resultset.getString(2));
                // list1.add(resultset.getString(1)+"|"+resultset.getString(2));
                //myMap.put(resultset.getString(1),resultset.getString(2));
            }
        } catch (Exception exp) {
            list1.add("Error in getempnm( ) for it service side : : " + exp.getMessage());
            // myMap.put("1", exp.getMessage());
        }
        connection.close();
        return list1;
    }
    
   
    public String checkempl(String login) throws ClassNotFoundException,
                                                 SQLException

    {

        // ArrayList<String> list1 = new ArrayList<String>();
        String list1 = null;
        Connection connection = null;

        ResultSet resultset = null;
        String Query = "";
        String date = "";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
          connection=   
                //= DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
              //  DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test","efu_gis", "test");
    DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
                //    DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
          
            Statement statement = connection.createStatement();
            Query =
                    " select unique e.EMPLOYEE_NAME from efu_pis.vw_employee e   where e.EMP_NO='" +
                    login + "'  ";
            //     Query = " select unique e.EMPLOYEE_NAME,ce.EMP_TYPE from efu_pis.vw_employee e join COMPLN_EMP ce on e.EMP_NO=ce.EMPID   where e.EMP_NO='"+login+"'  ";
            //
            resultset = statement.executeQuery(Query);

            while (resultset.next()) {
                list1 = (resultset.getString("EMPLOYEE_NAME"));
                // list1.add(resultset.getString("EMP_TYPE")) ;
            }
        } catch (Exception exp) {
            list1 = ("Error in checkempl( ) for it service side : : " + exp.getMessage());
        }
        connection.close();
        resultset.close();
       // connection.close();

        return list1;
    }

    
     

    public String checkdeptnm(String brid) throws ClassNotFoundException,
                                                  SQLException

    {

        // ArrayList<String> list1 = new ArrayList<String>();
        String list1 = null;
        Connection connection = null;

        ResultSet resultset = null;
        String Query = "";
        String date = "";
   String type="";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection   =
                //=  DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
           
        //  DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test","efu_gis", "test");
          DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
         //      DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
            
            Statement statement2 = connection.createStatement();
            Query =
                    "select unique TRIM(ed.BRANCH_NAME),nvl(ce.EMP_TYPE,'U') from efu_pis.vw_employee ed ";
              Query=Query+  "left join COMPLN_EMP ce on ed.EMP_NO=ce.EMPID  where TRIM(ed.BRANCH_ID)="+brid  ;


            resultset = statement2.executeQuery(Query);

            while (resultset.next()) {
                list1 = (resultset.getString(1));
                type=resultset.getString(2);
            }
        } catch (Exception exp) {
            list1 = ("Error in checkdeptnm( )  " + exp.getMessage());
        }
       // if (type.equals("U")){
       connection.close();
       // }
        return list1;
    }
    
    
    public String checktp(String login) throws ClassNotFoundException,
                                               SQLException

    {

        // ArrayList<String> list1 = new ArrayList<String>();
        String list1 = null;
        Connection connection = null;

        ResultSet resultset = null;
        String Query = "";
        String date = "";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
           connection =  
             //       DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test","efu_gis", "test");
               
         DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
           //     DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
          
            Statement statement = connection.createStatement();
            Query =
                    " select  nvl(ce.EMP_TYPE,'P') EMP_TYPE from  COMPLN_EMP ce  where ce.EMPID ='" +
                    login + "'  ";


            resultset = statement.executeQuery(Query);

            while (resultset.next()) {
                list1 = (resultset.getString("EMP_TYPE"));
                // list1.add(resultset.getString("EMP_TYPE")) ;
            }
        } catch (NullPointerException exp) {
          //  list1 = ("There is something wrong: " + exp.getMessage());
            list1="P";
        }

        resultset.close();

         connection.close();
        return list1;
    }

    
    //start of tier 1 managing console data population functions



      // get lists for it tier 1

    public   ArrayList<String> checkdept( String eid, String emptp) throws ClassNotFoundException,
                                                SQLException

    {

        ArrayList<String> list1 = new ArrayList<String>();
        Connection connection = null;

        ResultSet resultset;
        String Query = "";
        String date = "";
   
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = 
          // conner=
               //     DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test",
               //                                 "efu_gis", "test");
         DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
           //     DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
           
            
            Statement statement = connection.createStatement();
           
           if (!emptp.equals("AC")&&!emptp.equals("AH"))  
           { 
             if (eid.equals("1541"))
             {
                   Query = " select unique cm.branch_id, br.BRANCH_NM from COMPLN_MASTER cm join EFU_MAST.BRANCHES br on cm.BRANCH_ID=br.BRANCH_ID where ";
                   Query = Query+ " cm.STATUS not in '1' and ( cm.DEVLPR_ID= "+eid+"  or cm.CORDNTR_ID = "+eid+" )" ;
                   Query = Query+ " and  cm.branch_id between 299 and 399 and cm.branch_id not in (359,372)   ";
               }
             
             else {
               if (emptp.equals("C")||emptp.equals("D"))
               { 
                   Query = " select unique cm.branch_id, br.BRANCH_NM from COMPLN_MASTER cm join EFU_MAST.BRANCHES br on cm.BRANCH_ID=br.BRANCH_ID where ";
                   Query = Query+ "  cm.STATUS not in '1' and ( cm.DEVLPR_ID= " + eid + "  or cm.CORDNTR_ID = " + eid + " ) and cm.complain_type ='1'   " ;
                   }
               
               if (emptp.equals("H")||emptp.equals("N"))
               { Query = " select unique cm.branch_id, br.BRANCH_NM from COMPLN_MASTER cm join EFU_MAST.BRANCHES br on cm.BRANCH_ID=br.BRANCH_ID where ";
               Query = Query+ "  cm.STATUS not in '1' and ( cm.DEVLPR_ID= " + eid + "  or cm.CORDNTR_ID= " + eid + ") and cm.complain_type ='2'    " ;
               }
             }
               
               }
           else {     
                   Query =   "select unique cm.branch_id, br.BRANCH_NM from COMPLN_MASTER cm join EFU_MAST.BRANCHES br on cm.BRANCH_ID=br.BRANCH_ID where ";
               if (emptp.equals("AC"))
               { 
                       if(eid.equals("658"))
                   { 
                       Query = Query+ "  cm.branch_id between 299 and 399 and cm.branch_id not in (359,372)  ";
                     }
                  
                   else{
                           Query = Query+ " cm.complain_type ='1'   "; 
                   }
                  
                   }
               else if(emptp.equals("AH"))
               {
                   Query = Query+ " cm.complain_type ='2'   ";
                   }
              //     else if(emptp.equals("U"))
             //  {
              //     Query = Query+ " cm.complain_type ='1' ";
              //     }
               
              
           }
           
            resultset = statement.executeQuery(Query);

            while (resultset.next()) {
                list1.add(resultset.getString(1) + "" +
                          resultset.getString(2));
            }
            if (!(list1.size()>0))
            {
                    list1.add("111-");
                }
        } catch (Exception exp) {
           // list1.add(Query);
            list1.add("Error in checkdept( )  " + exp.getCause());
           
        }
        connection.close();
        return list1;
    }


    public ArrayList<String> getallcompln(String eid,
                                          String emptp) throws ClassNotFoundException,
                                                               SQLException
    // public ArrayList<String> getallcompln(String brid, String tp ) throws ClassNotFoundException, SQLException
    // public Map<String,String> getallcompln(String brid) throws ClassNotFoundException, SQLException
    {
        Connection connection = null;
        ArrayList<String> list1 = new ArrayList<String>();
        ResultSet resultset;
        String Query = "";
        String date = "";
        Map<String, String> myMap = new HashMap<String, String>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection =
                  //  DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test",
                  //                              "efu_gis", "test");
           DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
             //      DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
           
            Statement statement = connection.createStatement();
            if (emptp.equals("C") || emptp.equals("AC")||emptp.equals("D")) {
                if (!emptp.equals("AC")) {
                
                     if (eid.equals("1541"))
                   {
                       Query =  "select COMPLN_ID  from COMPLN_MASTER  " ;
                       Query = Query+ "where branch_id between 299 and 399 and branch_id not in (359,372) ";
                       Query = Query+ " order by COMPLN_TIME DESC ";
                   }
                   
                  else  { Query =  "select COMPLN_ID from COMPLN_MASTER   where ( DEVLPR_ID=" +eid + "  or CORDNTR_ID=" + eid +" ) and COMPLAIN_TYPE='1'  " ;
                           //   Query = Query+ "and (branch_id not between 299 and 399 or branch_id  in (359,372)) ";
                               Query = Query+ " order by COMPLN_TIME DESC ";  }
                }
    //                else if (eid.equals("2494")) {
    //                    Query =
    //                            Query + "select COMPLN_ID from COMPLN_MASTER   where  (DEVLPR_ID=" +
    //                            eid + " or CORDNTR_ID=" + eid +
    //                            "  or compln_sys=9) and COMPLAIN_TYPE='1'  order  by   COMPLN_ID ASC ";
    //
    //                }
               // if admin coordinator branch automation
                else {
                      if (eid.equals("658"))
                  {
                    Query = "select COMPLN_ID from COMPLN_MASTER  WHERE ";
                    Query = Query+ " branch_id between 299 and 399 and branch_id not in (359,372) ";
                    Query = Query+ " order by COMPLN_TIME DESC ";
                  }
                  else  if (eid.equals("301"))
                    {
                        Query = "select COMPLN_ID from COMPLN_MASTER  WHERE   ";
                        Query = Query+ " order by COMPLN_TIME DESC ";
                    }
                  
                  else{ 
                        Query = "select COMPLN_ID from COMPLN_MASTER  WHERE   COMPLAIN_TYPE='1' ";
                      //  Query = Query+ " and (branch_id not between 299 and 399 ) ";
                      //  Query = Query+ " or branch_id  in (359,372) "; 
                        Query = Query+ " order by COMPLN_TIME DESC ";
                      
                      }
                
                }
            } else if (emptp.equals("H") || emptp.equals("AH") ||
                       emptp.equals("N")) {
                //                     if(!eid.equals("1344"))
                //                     {
                //                         Query=Query+"select COMPLN_ID from COMPLN_MASTER   where DEVLPR_ID="+eid+" AND  COMPLAIN_TYPE='2'   order  by   COMPLN_ID ASC " ;
                //                         }
                //
                //                     else
                {
                    Query = "select COMPLN_ID from COMPLN_MASTER  where COMPLAIN_TYPE='2'  ";
                    Query = Query+ " order by COMPLN_TIME DESC";
                }

            }
           else if(emptp.equals("U")||emptp.equals("P")) {                   
                          Query =  "select COMPLN_ID from COMPLN_MASTER  where COMPLAIN_TYPE='1' AND  STATUS not in (1,2) ";
                          Query = Query+ " order by COMPLN_TIME DESC";
           }

            resultset = statement.executeQuery(Query);
            //  list1.add("1110");
            while (resultset.next()) {
                list1.add(resultset.getString("COMPLN_ID"));
                //myMap.put(resultset.getString(1),resultset.getString(2));
            }
        } catch (Exception exp) {
            list1.add("Error in getallcompln( ) for it service side dropdown: " + exp.getMessage());
            // myMap.put("1", exp.getMessage());
        }
        
    //        try{
    //        connection.close();
    //        }
    //        catch(Exception ex)
    //        {
    //            list1.add(ex.getMessage());
    //            }
          connection.close();
        return list1;
    }
    
    public   ArrayList<String> getonlyprocess(String eid,
                                         String emptp) throws ClassNotFoundException,
                                                              SQLException
    
    {
        Connection connection = null;
        ArrayList<String> list1 = new ArrayList<String>();
        ResultSet resultset;
        String Query = "";
        String date = "";
        Map<String, String> myMap = new HashMap<String, String>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection =
                 //   DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test",    "efu_gis", "test");
           
          DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
               //      DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
             
            Statement statement = connection.createStatement();


            if (emptp.equals("C") || emptp.equals("AC")||emptp.equals("D")) {
                if (!emptp.equals("AC")) {
                    if (eid.equals("1541"))
                   
                    {
                        Query =  "select COMPLN_ID  from COMPLN_MASTER  " ;
                        Query = Query+ "where branch_id between 299 and 399 and branch_id not in (359,372) ";
                        Query = Query+ "  AND  STATUS  in (2)  order by COMPLN_TIME DESC ";
                    }
                    
                    else  { Query =  "select COMPLN_ID from COMPLN_MASTER   where ( DEVLPR_ID=" +eid + "  or CORDNTR_ID=" + eid +" ) and COMPLAIN_TYPE='1'  " ;
                         //      Query = Query+ " and (branch_id not between 299 and 399 or branch_id  in (359,372)) "; 
                               Query = Query+ "  AND  STATUS  in (2)  order by COMPLN_TIME DESC ";  }
          //   Query = "select COMPLN_ID from COMPLN_MASTER   where (DEVLPR_ID=" +eid + "  or CORDNTR_ID=" + eid +") and COMPLAIN_TYPE='1'  " ;
           //    Query = Query +   " AND  STATUS  in (2) order by COMPLN_TIME DESC ";
             
                }
     
                else 
                {
                   if (eid.equals("658"))
                   {
                     Query = "select COMPLN_ID, COMPLAIN_TYPE from COMPLN_MASTER  WHERE   STATUS  in (2) AND ";
                     Query = Query+ " branch_id between 299 and 399 and branch_id not in (359,372) ";
                     Query = Query+ " order by COMPLN_TIME DESC ";
                   }
                   else   if (eid.equals("301"))
                     {
                         Query = "select COMPLN_ID from COMPLN_MASTER  WHERE  STATUS  in (2) AND ";
                         Query = Query+ " order by COMPLN_TIME DESC ";
                     }
                   
                   else{ 
                         Query = "select COMPLN_ID from COMPLN_MASTER  WHERE STATUS  in (2) AND  COMPLAIN_TYPE='1' ";
                       //  Query = Query+ " and (branch_id not between 299 and 399 ) ";
                       //  Query = Query+ " or branch_id  in (359,372) "; 
                         Query = Query+ " order by COMPLN_TIME DESC ";
                       
                       }
                  //  Query = "select COMPLN_ID from COMPLN_MASTER  WHERE   STATUS  in (2) AND COMPLAIN_TYPE='1' order by COMPLN_TIME DESC ";
              
              
               }
            } else if (emptp.equals("H") || emptp.equals("AH") ||
                       emptp.equals("N")) {
               
                {
                    Query ="select COMPLN_ID from COMPLN_MASTER  where COMPLAIN_TYPE='2' AND  STATUS  in (2)  order by COMPLN_TIME DESC ";
                }

            }
            else if(emptp.equals("U")||emptp.equals("P") ) {                    
            	Query = "select COMPLN_ID from COMPLN_MASTER  where COMPLAIN_TYPE='1' AND  STATUS  in (2 )  order by COMPLN_TIME DESC ";}


            resultset = statement.executeQuery(Query);
            // list1.add("1110");
            while (resultset.next()) {
                list1.add(resultset.getString("COMPLN_ID"));
                //myMap.put(resultset.getString(1),resultset.getString(2));
            }
        } catch (Exception exp) {
            list1.add("Error in getonlyprocess( ) for it service side dropdown: " + exp.getMessage());
            // myMap.put("1", exp.getMessage());
        }
        
    //        try{
    //        connection.close();
    //        }
    //        catch(Exception ex)
    //        {
    //            list1.add(ex.getMessage());
    //            }
          connection.close();
        return list1;
    }
    
    
    
    public   ArrayList<String>  getalldone(String eid,
                                          String emptp) throws ClassNotFoundException,
                                                               SQLException
    // public ArrayList<String> getallcompln(String brid, String tp ) throws ClassNotFoundException, SQLException
    // public Map<String,String> getallcompln(String brid) throws ClassNotFoundException, SQLException
    {
        Connection connection = null;
        ArrayList<String> list1 = new ArrayList<String>();
        ResultSet resultset;
        String Query = "";
        String date = "";
        Map<String, String> myMap = new HashMap<String, String>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection =
                 //   DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test",
                  //                              "efu_gis", "test");
      DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
         
          //      DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
          
            Statement statement = connection.createStatement();
            if (emptp.equals("C") || emptp.equals("AC")||emptp.equals("D")) {
                if (!emptp.equals("AC")) {
                     if (eid.equals("1541"))
                   
                     {
                         Query =  "select COMPLN_ID  from COMPLN_MASTER  " ;
                         Query = Query+ " where branch_id between 299 and 399 and branch_id not in (359,372) ";
                         Query = Query+ "  AND  STATUS  in (1)  order by COMPLN_TIME DESC ";
                     }
                     
                     else  { Query =  "select COMPLN_ID from COMPLN_MASTER   where ( DEVLPR_ID=" +eid + "  or CORDNTR_ID=" + eid +" ) and COMPLAIN_TYPE='1'  " ;
                            //    Query = Query+ "and (branch_id not between 299 and 399 or branch_id  in (359,372)) "; 
                                Query = Query+ "  AND  STATUS  in (1)  order by COMPLN_TIME DESC ";  }
            
               //  Query = "select COMPLN_ID from COMPLN_MASTER   where (DEVLPR_ID=" +eid + "  or CORDNTR_ID=" + eid +") and COMPLAIN_TYPE='1'  " ;
              //  Query = Query +   " AND  STATUS  in (1)  order by COMPLN_TIME DESC ";
                 } 

               else {
                    if (eid.equals("658"))
                    {
                      Query = "select COMPLN_ID, COMPLAIN_TYPE from COMPLN_MASTER  WHERE   STATUS  in (1) AND ";
                      Query = Query+ " branch_id between 299 and 399 and branch_id not in (359,372) ";
                      Query = Query+ " order by COMPLN_TIME DESC ";
                    }
                    else  if (eid.equals("301"))
                      {
                          Query = "select COMPLN_ID from COMPLN_MASTER  WHERE  STATUS  in (1) AND ";
                          Query = Query+ " order by COMPLN_TIME DESC ";
                      }
                    
                    else{ 
                          Query = "select COMPLN_ID from COMPLN_MASTER  WHERE STATUS  in (1) AND  COMPLAIN_TYPE='1' ";
                       //   Query = Query+ " and (branch_id not between 299 and 399 ) ";
                       //   Query = Query+ " or branch_id  in (359,372) "; 
                          Query = Query+ " order by COMPLN_TIME DESC ";
                        
                        }
                }
            } else if (emptp.equals("H") || emptp.equals("AH") ||
                       emptp.equals("N")) {
                //                     if(!eid.equals("1344"))
                //                     {
                //                         Query=Query+"select COMPLN_ID from COMPLN_MASTER   where DEVLPR_ID="+eid+" AND  COMPLAIN_TYPE='2'   order  by   COMPLN_ID ASC " ;
                //                         }
                //
                //                     else
                
                    Query = "select COMPLN_ID from COMPLN_MASTER   where (DEVLPR_ID=" +
                            eid + " and COMPLAIN_TYPE='2'   AND  STATUS  in (1)    ) or( CORDNTR_ID=" + eid +
                            " and COMPLAIN_TYPE='2'   AND  STATUS  in (1)  )   order by COMPLN_TIME DESC ";
                

            }
            else if(emptp.equals("U")||emptp.equals("P") ) {               
            	Query = "select COMPLN_ID from COMPLN_MASTER  where COMPLAIN_TYPE='1' AND  STATUS   in (1 ) order by COMPLN_TIME DESC ";}

            resultset = statement.executeQuery(Query);
            //  list1.add("1110");
            while (resultset.next()) {
                list1.add(resultset.getString("COMPLN_ID"));
                //myMap.put(resultset.getString(1),resultset.getString(2));
            }
        } catch (Exception exp) {
            list1.add("Error in getalldone( ) for it service side dropdown: " + exp.getMessage());
            // myMap.put("1", exp.getMessage());
        }
   
    connection.close();
        return list1;
    }
    
    
    
       // also used by ticker
    public ArrayList<String> getonlyopen(String eid,String emptp) throws ClassNotFoundException,
                                                              SQLException
    
    {
        Connection connection = null;
        ArrayList<String> list1 = new ArrayList<String>();
        ResultSet resultset;
        String Query = "";
        String date = "";
        Map<String, String> myMap = new HashMap<String, String>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection =
                  //  DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test",  "efu_gis", "test");
                
    DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
               //     DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
            
            Statement statement = connection.createStatement();


            if (emptp.equals("C") ||emptp.equals("D") || emptp.equals("AC")) {
               if (!emptp.equals("AC")) {
                   
                    if (eid.equals("1541"))
                    {
                        Query =  "select COMPLN_ID  from COMPLN_MASTER  " ;
                        Query = Query+ " where branch_id between 299 and 399 and branch_id not in (359,372) ";
                        Query = Query+ "  AND  STATUS  in (3)  order by COMPLN_TIME DESC ";
                    }
                    
                    else  { Query =  "select COMPLN_ID from COMPLN_MASTER   where ( DEVLPR_ID=" +eid + "  or CORDNTR_ID=" + eid +" ) and COMPLAIN_TYPE='1'  " ;
                          //     Query = Query+ " and (branch_id not between 299 and 399 or branch_id  in (359,372)) ";
                                Query = Query+ "  AND  STATUS  in (3)  order by COMPLN_TIME DESC ";  }
                }
    //                else if (eid.equals("2494")) {
    //                    Query =
    //                            Query + "select COMPLN_ID from COMPLN_MASTER   where ( DEVLPR_ID=" +
    //                            eid +
    //                            " AND  STATUS not in (1)  and COMPLAIN_TYPE='1')   or ( CORDNTR_ID=" +
    //                            eid +
    //                            " AND  STATUS not in (1)  and COMPLAIN_TYPE='1') or compln_sys=9  order  by   COMPLN_ID ASC ";
    //
    //                }
    //                else if (eid.equals("2199")) {
    //    Query =Query + " select cm.COMPLN_ID from  COMPLN_MASTER cm  ";
    //             Query =Query + "left join COMPLN_CLASS cc on cm.COMPLN_ID=cc.COMPLN_CLASS_FK left join COMPLN_ACT ca on cm.COMPLN_ID=ca.COMPLN_ACT_FK ";
    //                                    Query =Query + " where ( cm.DEVLPR_ID=2199 AND  cm.STATUS not in (1)) ";
    //                                    Query =Query + " or ( cm.CORDNTR_ID=2199 AND  cm.STATUS not in (1) )  ";
    //                                    Query =Query + " or ((cm.COMPLN_SYS='1' ) and (cc.CLASS_ID='3' or  cc.CLASS_ID='4'   or   cc.CLASS_ID='5'  or  cc.CLASS_ID='6'   ) AND  cm.STATUS not in (1) ) ";
    //                                    Query =Query + " or ((cm.COMPLN_SYS='3') and (cc.CLASS_ID='3' or  cc.CLASS_ID='4'   or   cc.CLASS_ID='5'  or  cc.CLASS_ID='6'   ) AND  cm.STATUS not in (1) )";
    //                                   Query= Query + " or ( cm.COMPLN_SYS='2' and ca.ACT_TYPE='L' AND  cm.STATUS not in (1)) ";
    //                                    Query= Query +  " or ( cm.COMPLN_SYS='2' and ca.ACT_TYPE='L' AND  cm.STATUS not in (1))  ";
    //                                     Query= Query + " order  by   branch_id ASC ";
    //                                }
                else {
                    if (eid.equals("658"))
                    {
                      Query = "select COMPLN_ID from COMPLN_MASTER  WHERE   STATUS  in (3) AND ";
                      Query = Query+ " branch_id between 299 and 399 and branch_id not in (359,372) ";
                      Query = Query+ " order by COMPLN_TIME DESC ";
                    }
                    else if (eid.equals("301"))
                      {
                          Query = "select COMPLN_ID from COMPLN_MASTER  WHERE  STATUS  in (3) AND ";
                          Query = Query+ " order by COMPLN_TIME DESC ";
                      }
                    
                    else{ 
                          Query = "select COMPLN_ID from COMPLN_MASTER  WHERE STATUS  in (3) AND  COMPLAIN_TYPE='1' ";
                      //    Query = Query+ " and (branch_id not between 299 and 399 ) ";
                       //   Query = Query+ " or branch_id  in (359,372) "; 
                          Query = Query+ " order by COMPLN_TIME DESC ";
                        
                        }
                }
            } else if (emptp.equals("H") || emptp.equals("AH") ||emptp.equals("N"))
            {
                    Query = "select COMPLN_ID from COMPLN_MASTER  where (COMPLAIN_TYPE='2' AND  STATUS =3 ) OR (PROBLEM_TYPE='3' AND  STATUS =3)  order by COMPLN_TIME DESC ";
              
            }
            else if(emptp.equals("U")||emptp.equals("P")) {        
            	Query = "select COMPLN_ID from COMPLN_MASTER  where COMPLAIN_TYPE='1' AND  STATUS=3  order by COMPLN_TIME DESC ";}


            resultset = statement.executeQuery(Query);
            // list1.add("1110");
            while (resultset.next()) {
                list1.add(resultset.getString("COMPLN_ID"));
                //myMap.put(resultset.getString(1),resultset.getString(2));
            }
        } catch (Exception exp) {
            list1.add("Error in getonlyopen( ) for it service side dropdown: " + exp.getMessage());
            // myMap.put("1", exp.getMessage());
        }
        
    //        try{
    //        connection.close();
    //        }
    //        catch(Exception ex)
    //        {
    //            list1.add(ex.getMessage());
    //            }
         connection.close();
        return list1;
    }


    /**
     * @param login
     * @param pass
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public   String login(String login,
                        String pass) throws ClassNotFoundException,SQLException {
        String result = "false";
        String sql = "";
        Statement stm;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection conn2 =
         
       DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
         //last change //         DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
             // conn2 = DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test","efu_gis", "test");
            
            stm = conn2.createStatement();
          
         sql = " select  TRIM(au.USER_ID) USER_ID, TRIM(au.EMP_BR) BRANCH_ID, TRIM(au.USER_PASSWORD) USER_PASSWORD, REPLACE(TRIM(br.BRANCH_NM),'.','')  BRANCH_NM, " ;
        sql = sql +  " DECODE((SELECT  count(br.comp_id) ";
                     
               sql = sql +  " from app_users au " ;
              sql = sql +   " LEFT join  efu_pis.vw_employee ea on au.USER_ID=ea.EMP_NO " ;
              sql = sql +   " LEFT   JOIN  EFU_MAST.BRANCHES br on au.EMP_BR=br.BRANCH_ID ";
            sql = sql +    " where  trim(au.USER_ID)=('"+login + "') ),'2','Both','1','not both',null,'not both' ) company   ";
           sql = sql +    " , br.comp_id "; 
         //   sql = sql +    " ,CMP.EMP_NM";
            sql = sql +    " from app_users au ";
            sql = sql +   "  LEFT join  efu_pis.vw_employee ea on au.USER_ID=ea.EMP_NO " ;
            sql = sql +   " LEFT   JOIN  EFU_MAST.BRANCHES br on au.EMP_BR=br.BRANCH_ID ";
          //  sql = sql +  " FULL JOIN  COMPLN_EMP CMP on au.USER_ID=CMP.EMPID " ;
            sql = sql +   " where trim(au.USER_ID)=('"+login + "') and TRIM(au.USER_PASSWORD)=('" + pass + "') ";
           
           
            //sql=sql+" and up.MODULE_ID=985";
            ResultSet rst;
            rst = stm.executeQuery(sql);
            int j = 0;
            while (rst.next()) {
                result = "true|" + rst.getString(1) + "|" + rst.getString(2)+ "|" + rst.getString("company")+ "|" + rst.getString("comp_id");
          // result=result+"|" + rst.getString("EMP_NM");
            }
            //                if (!rst.next() )
            //                {result="false";
            //                    }
            //                else  {result="true";}

            conn2.close();
        }

        catch (Exception ex) {
            //  result="false";
            result = ex.getMessage();
        }

        return result;
    } 
    
    
    public  ArrayList<String> getAllapps(String empid ) throws ClassNotFoundException,
                                    SQLException {
    String syntax = "";

        ArrayList<String> list1 = new ArrayList<String>();
    Connection connection = null;
         ResultSet resultset;
    //        syntax =
    //                "select mn.FAVORITES,mn.Node,mn.WEB_URL, au.USER_NAME from USER_PERMISSION up JOIN app_users au  on au.USER_ID=up.USER_ID JOIN EFU_MAST.MENU mn  ON up.MODULE_ID=MN.S_ID ";
    //        syntax = syntax + "where  au.USER_ID='" + userid + "'  and mn.web_url is not null ";
            syntax = syntax +  "select  mn.WEB_URL,mn.APP_DESC  from  COMPLN_MENU mn left join COMPLN_MENU_ACCESS ama on mn.ACCESS_CODE=ama.ACCESS_CODE ";
            syntax = syntax +   " where ((mn.SPEC_ACCESS='Y' and ama.EMP_ID=' "+empid+ "') or mn.SPEC_ACCESS='N') AND mn.ACTIVE='A'  ";
    
                  try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
 
     // connection =DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test","efu_gis", "test");
     
     
        connection =
        //  DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
        DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/adg.efuinsurance.com","efu_gis","PRODgis");  
    Statement statement = connection.createStatement();


    resultset = statement.executeQuery(syntax);

      list1.add(",");
       while (resultset.next()) {
            list1.add( resultset.getString(1)+"+"+resultset.getString(2) );
    }
        list1.add(",");
    } catch (Exception exp) {
       list1.add("error in getAllapps()   : " + exp.getMessage());
       }

      return list1;
    }
}
