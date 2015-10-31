package dboperation;

import common.OracleDB;
import common.OracleSQL;
import common.SQLiteDB;
import common.SQLiteSQL;
import model.Sbor;
import model.TTCPERSONAL;
import model.TWLMVALUES;
import model.TWLTCSTATUS;
import util.DataTransform;


import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UserData  {


    private List<Sbor> sbors;

    private Date dateSbor;
    private String idTrenirvki;
    private Time timeUpragnenie;
    private Integer puls;


    public static String getFrmtNumb(Integer nmber) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        //decimalFormat.setGroupingSize(3);
        symbols.setGroupingSeparator(' ');
        String pattern = "#,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        //System.out.println("nmber="+nmber);
        String number = decimalFormat.format(nmber);
        //System.out.println("getfmtnumb number="+number);
        return number;
    }

    public static String getFrmtLong(Long nmber) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        //decimalFormat.setGroupingSize(3);
        symbols.setGroupingSeparator(' ');
        String pattern = "#,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        //System.out.println("nmber="+nmber);
        String number = decimalFormat.format(nmber);
        //System.out.println("getfmtnumb number="+number);
        return number;
    }

    public static String getFrmtDcml(BigDecimal nmber) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        //decimalFormat.setGroupingSize(3);
        symbols.setGroupingSeparator(' ');
        String pattern = "###,###.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        //System.out.println("getfmt dec  Dcml nmber="+nmber);
        if (nmber == null) {
            return null;
        }
        String number = decimalFormat.format(nmber);

        //System.out.println("Dcml number="+number);
        return number;
    }


    public static String getRusCod(String engCod) {
        //System.out.println("engCod="+engCod);
        if (engCod.contains("JURRZ")) {
            //System.out.println(" ur lico   ");
            return "Юр.лицо    " + "\n" + " (резидент РК)";
        }
        if (engCod.contains("FIZRZ")) {
            return "Физ.лицо (резидент РК)";
        }
        if (engCod.contains("JURNN")) {
            return "Юр.лицо       \"+\"\\n\"+\" (нерезидент)";
        }
        if (engCod.contains("FIZNN")) {
            return "Физ.лицо (нерезидент)";
        }

        if (engCod.contains("STBNK")) {
            return "Банк второго уровня РК";
        }
        if (engCod.contains("INSOR")) {
            return "Страховая организация РК";
        }


        return engCod;
    }



    public static ArrayList<Sbor> getSborFromOracle() {

        ArrayList<Sbor> listSbor = new ArrayList<Sbor>();

        String SqlView = OracleSQL.getSQLselListSbor();


        System.out.println("SqlView=" + SqlView);

        Driver myDriver = new oracle.jdbc.driver.OracleDriver();
        String uRL = OracleDB.getSystemDb();
        String uSER = OracleDB.getDbUsername();
        String pASS = OracleDB.getDbPwd();

        try {
            DriverManager.registerDriver(myDriver);

            Connection conn = DriverManager.getConnection(uRL, uSER, pASS);

            PreparedStatement pS = conn.prepareStatement(SqlView);
            //pS.executeUpdate();
            ResultSet rs = pS.executeQuery(SqlView);
            System.out.println("   User SqlView.executeQ().......");
            conn.commit();
            int k = 0;

            while (rs.next()) {

                Sbor sbor = new Sbor();

                sbor.setName(rs.getString(1));
                sbor.setShort_name(rs.getString(2));
                k++;

                listSbor.add(sbor);

            };

/*
            String dt1Str;
            String dt2Str;
            //if (dt1==null){dt1Str="01/01/1000";}
            System.out.println(dt1);
            SimpleDateFormat dtF = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dt1Str = dtF.format(dt1);
            System.out.println("222 dt1Str=" + dt1Str);
            //Date dt2 = new Date();
            //dt2.setTime(dt1.getTime() + 1 * 24 * 60 * 60 * 1000);
            dt2Str = dtF.format(dt2);
            System.out.println("dt2str=" + dt2Str);
*/

            if (pS != null) {
                pS.close();
            }

            if (conn != null) {
                conn.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("-----------");
        //System.out.println(listTisr_non_market);

        return listSbor;

    }


    public static ArrayList<TWLTCSTATUS> getTWLTCSTATUSFromSQLite() {

        ArrayList<TWLTCSTATUS> listTWLTCSTATUS = new ArrayList<TWLTCSTATUS>();

        Connection conn = SQLiteDB.connectDB();
        String SqlView = SQLiteSQL.getSQLselListTWLTCSTATUS();

        System.out.println("SqlView SQLite=" + SqlView);
        if (conn==null){
            TWLTCSTATUS twltcstatus = new TWLTCSTATUS();
            twltcstatus.setName("connection not work");
            listTWLTCSTATUS.add(twltcstatus);
            return listTWLTCSTATUS;};

        try {

            Statement statement = conn.createStatement();


            ResultSet rs=statement.executeQuery(SqlView);
            System.out.println("   User SqlView.executeQ().......");
            //conn.commit();
            int k = 0;

            while (rs.next()) {

                TWLTCSTATUS twltcstatus = new TWLTCSTATUS ();

                twltcstatus.setId(rs.getString(1));
                twltcstatus.setvTCSTATUS(rs.getString(2));
                System.out.println("twltcstatus rs.get(2)=" + rs.getString(2));
                twltcstatus.setvTCSTATUS_RU(rs.getString(3));
                k++;

                listTWLTCSTATUS.add(twltcstatus);

            };

/*
            String dt1Str;
            String dt2Str;
            //if (dt1==null){dt1Str="01/01/1000";}
            System.out.println(dt1);
            SimpleDateFormat dtF = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dt1Str = dtF.format(dt1);
            System.out.println("222 dt1Str=" + dt1Str);
            //Date dt2 = new Date();
            //dt2.setTime(dt1.getTime() + 1 * 24 * 60 * 60 * 1000);
            dt2Str = dtF.format(dt2);
            System.out.println("dt2str=" + dt2Str);
*/

            if (statement != null) {
                statement.close();
            }

            if (conn != null) {
                conn.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("-----------");
        //System.out.println(listTisr_non_market);

        return listTWLTCSTATUS;

    }


    public static String insTWLTCSTATUS(String[] arrV) {

        String id="--";

        Connection conn = SQLiteDB.connectDB();
        String SqlView = SQLiteSQL.SQLselMaxIdTWLTCSTATUS();

        System.out.println("SqlView ins max SQLite=" + SqlView);
        if (conn==null){
            id="connection not work";
            return id;};

        try {

            Statement statement = conn.createStatement();
            ResultSet rs=statement.executeQuery(SqlView);
            System.out.println("   User Max SqlView.executeQ().......");
            //conn.commit();

            while (rs.next()) {
                id=rs.getString(1);
                System.out.println("id=="+id);
            };

            int id_=Integer.parseInt(id)+1;
            id=String.valueOf(id_);
            System.out.println("id max+1=="+id);

            if (statement != null) {
                statement.close();
            }

            if (conn != null) {
                conn.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        conn = SQLiteDB.connectDB();
        SqlView = SQLiteSQL.SQLinsTWLTCSTATUS();

        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("SqlView ins SQLite=" + SqlView);
        if (conn==null){
            id="connection not work";
            return id;};

        try {


            Statement stmt = null;
            stmt=conn.createStatement();

            String replOld;
            //String str;
            replOld="?1";
            SqlView=SqlView.replace(replOld, id);
            replOld="?2";
            SqlView=SqlView.replace(replOld, arrV[1]);

            replOld="?3";
            SqlView=SqlView.replace(replOld, arrV[2]);

            System.out.println("SqlView="+SqlView);
            //System.out.println("str="+str);

            stmt.executeUpdate(SqlView);

/*
            PreparedStatement pStatement = conn.prepareStatement(SqlView);
            pStatement.setString(1,id);
            pStatement.setString(2,arrV[1]);
            System.out.println("arrV[2].toString()="+arrV[2].toString());
            Date date= DataTransform.getStrToDate(arrV[2].toString());
            //YYYY-MM-DD
            //DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateFormat df = new SimpleDateFormat("YYYY-MM-DD");
            pStatement.setString(3, df.format(date));
            System.out.println("df.format(date)="+df.format(date));

            pStatement.executeUpdate(SqlView);
            System.out.println("   User SqlView.executeQ().......");
            pStatement.close();
*/

            conn.commit();

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("-----------");
        //System.out.println(listTisr_non_market);

        return id;

    }

    public static String updTWLTCSTATUS(String[] arrV) {

        String id=arrV[0];

        Connection conn = SQLiteDB.connectDB();
        String SqlView = SQLiteSQL.SQLupdTWLTCSTATUS();

        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("SqlView upd SQLite=" + SqlView);
        if (conn==null){
            id="connection not work";
            return id;};

        try {


            Statement stmt = null;
            stmt=conn.createStatement();

            String replOld;
            //String str;
            replOld="?3";
            SqlView=SqlView.replace(replOld, id);
            replOld="?1";
            SqlView=SqlView.replace(replOld, arrV[1]);

            replOld="?2";
            SqlView=SqlView.replace(replOld, arrV[2]);

            System.out.println("SqlView=" + SqlView);
            //System.out.println("str="+str);

            stmt.executeUpdate(SqlView);

            conn.commit();

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("-----------");
        //System.out.println(listTisr_non_market);

        return id;

    }

    public static String delTWLTCSTATUS(String[] arrV) {

        String id=arrV[0];

        Connection conn = SQLiteDB.connectDB();
        String SqlView = SQLiteSQL.SQLdelTWLTCSTATUS();

        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("SqlView del SQLite=" + SqlView);
        if (conn==null){
            id="connection not work";
            return id;};

        try {


            Statement stmt = null;
            stmt=conn.createStatement();

            String replOld;
            //String str;
            replOld="?1";
            SqlView=SqlView.replace(replOld, id);

            System.out.println("SqlView=" + SqlView);
            //System.out.println("str="+str);

            stmt.executeUpdate(SqlView);

            conn.commit();

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("-----------");
        //System.out.println(listTisr_non_market);

        return id;

    }

    public static ArrayList<TTCPERSONAL> getTTCPERSONALFromSQLite() {

        ArrayList<TTCPERSONAL> listTTCPERSONAL = new ArrayList<TTCPERSONAL>();

        Connection conn = SQLiteDB.connectDB();
        String SqlView = SQLiteSQL.getSQLselListTTCPERSONAL();

        System.out.println("SqlView SQLite=" + SqlView);
        if (conn==null){
            TTCPERSONAL tTCPERSONAL = new TTCPERSONAL();
            tTCPERSONAL.setId("connection not work");
            listTTCPERSONAL.add(tTCPERSONAL);
            return listTTCPERSONAL;};

        try {

            Statement statement = conn.createStatement();


            ResultSet rs=statement.executeQuery(SqlView);
            System.out.println("   User SqlView.executeQ().......");
            //conn.commit();
            int k = 0;

            while (rs.next()) {

                TTCPERSONAL tTCPERSONAL = new TTCPERSONAL ();

                tTCPERSONAL.setId(rs.getString(1));
                tTCPERSONAL.setvFIRSTNAME(rs.getString(3));
                System.out.println("tTCPERSONAL rs.get(2)=" + rs.getString(2));
                tTCPERSONAL.setvMIDDLENAME(rs.getString(4));
                tTCPERSONAL.setvSURNAME(rs.getString(2));
                k++;

                listTTCPERSONAL.add(tTCPERSONAL);

            };



            if (statement != null) {
                statement.close();
            }

            if (conn != null) {
                conn.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("-----------");
        //System.out.println(listTisr_non_market);

        return listTTCPERSONAL;

    }



    public static String insTTCPERSONAL(String[] arrV) {

        String id="--";

        Connection conn = SQLiteDB.connectDB();
        String SqlView = SQLiteSQL.SQLselMaxIdTTCPERSONAL();

        System.out.println("SqlView ins max SQLite=" + SqlView);
        if (conn==null){
            id="connection not work";
            return id;};

        try {

            Statement statement = conn.createStatement();
            ResultSet rs=statement.executeQuery(SqlView);
            System.out.println("   User Max SqlView.executeQ().......");
            //conn.commit();

            while (rs.next()) {
                id=rs.getString(1);
                System.out.println("id=="+id);
            };

            int id_=Integer.parseInt(id)+1;
            id=String.valueOf(id_);
            System.out.println("id max+1=="+id);

            if (statement != null) {
                statement.close();
            }

            if (conn != null) {
                conn.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        conn = SQLiteDB.connectDB();
        SqlView = SQLiteSQL.SQLinsTTCPERSONAL();

        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("SqlView ins SQLite=" + SqlView);
        if (conn==null){
            id="connection not work";
            return id;};

        try {


            Statement stmt = null;
            stmt=conn.createStatement();

            String replOld;
            //String str;
            replOld="?1";
            SqlView=SqlView.replace(replOld, id);
            replOld="?2";
            SqlView=SqlView.replace(replOld, arrV[1]);

            replOld="?3";
            SqlView=SqlView.replace(replOld, arrV[2]);

            replOld="?4";
            SqlView=SqlView.replace(replOld, arrV[3]);

            System.out.println("SqlView="+SqlView);
            //System.out.println("str="+str);

            stmt.executeUpdate(SqlView);


            conn.commit();

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("-----------");
        //System.out.println(listTisr_non_market);

        return id;

    }

    public static String updTTCPERSONAL(String[] arrV) {

        String id=arrV[0];

        Connection conn = SQLiteDB.connectDB();
        String SqlView = SQLiteSQL.SQLupdTTCPERSONAL();

        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("SqlView upd SQLite=" + SqlView);
        if (conn==null){
            id="connection not work";
            return id;};

        try {


            Statement stmt = null;
            stmt=conn.createStatement();

            String replOld;
            //String str;
            replOld="?4";
            SqlView=SqlView.replace(replOld, id);
            replOld="?1";
            SqlView=SqlView.replace(replOld, arrV[1]);

            replOld="?2";
            SqlView=SqlView.replace(replOld, arrV[2]);

            replOld="?3";
            SqlView=SqlView.replace(replOld, arrV[3]);

            System.out.println("SqlView="+SqlView);
            //System.out.println("str="+str);

            stmt.executeUpdate(SqlView);

            conn.commit();

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("-----------");
        //System.out.println(listTisr_non_market);

        return id;

    }

    public static String delTTCPERSONAL(String[] arrV) {

        String id=arrV[0];

        Connection conn = SQLiteDB.connectDB();
        String SqlView = SQLiteSQL.SQLdelTTCPERSONAL();

        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("SqlView del SQLite=" + SqlView);
        if (conn==null){
            id="connection not work";
            return id;};

        try {


            Statement stmt = null;
            stmt=conn.createStatement();

            String replOld;
            //String str;
            replOld="?1";
            SqlView=SqlView.replace(replOld, id);

            System.out.println("SqlView="+SqlView);
            //System.out.println("str="+str);

            stmt.executeUpdate(SqlView);

            conn.commit();

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("-----------");
        //System.out.println(listTisr_non_market);

        return id;

    }




    public static ArrayList<TWLMVALUES> getTWLMVALUESFromSQLite() {

        ArrayList<TWLMVALUES> listTWLMVALUES = new ArrayList<TWLMVALUES>();

        Connection conn = SQLiteDB.connectDB();
        String SqlView = SQLiteSQL.getSQLselListTWLMVALUES();

        System.out.println("SqlView SQLite=" + SqlView);
        if (conn==null){
            TWLMVALUES tTWLMVALUES = new TWLMVALUES();
            tTWLMVALUES.setId("connection not work");
            listTWLMVALUES.add(tTWLMVALUES);
            return listTWLMVALUES;};

        try {

            Statement statement = conn.createStatement();


            ResultSet rs=statement.executeQuery(SqlView);
            System.out.println("   User SqlView.executeQ().......");
            //conn.commit();
            int k = 0;

            while (rs.next()) {

                TWLMVALUES tTWLMVALUES = new TWLMVALUES ();


                //t.ID, t.vTCAMPID, t.vTCSID, t.vTCTID, t.vTRAININGDATE,t.vTTSEQUENCE,t.vTRAININGID
                //,t.vTRAININGDUR_V,t.vMPULSEP10S_b

                tTWLMVALUES.setId(rs.getString(1));
                tTWLMVALUES.setvTCAMPID(rs.getString(2));
                System.out.println("tTWLMVALUES rs.get(2)=" + rs.getString(2));
                tTWLMVALUES.setvTCSID(rs.getString(3));
                tTWLMVALUES.setvTCTID(rs.getString(4));
                tTWLMVALUES.setvTRAININGDATE(rs.getString(5));
                tTWLMVALUES.setvTTSEQUENCE(rs.getString(6));
                tTWLMVALUES.setvTRAININGID(rs.getString(7));
                tTWLMVALUES.setvTRAININGDUR_V(rs.getString(8));
                tTWLMVALUES.setvMPULSEP10S_b(rs.getString(9));



                k++;

                listTWLMVALUES.add(tTWLMVALUES);

            };



            if (statement != null) {
                statement.close();
            }

            if (conn != null) {
                conn.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("-----------");
        //System.out.println(listTisr_non_market);

        return listTWLMVALUES;

    }

    public static String insTWLMVALUES(String[] arrV) {

        String id="--";

        Connection conn = SQLiteDB.connectDB();
        String SqlView = SQLiteSQL.SQLselMaxIdTWLMVALUES();

        System.out.println("SqlView ins max SQLite=" + SqlView);
        if (conn==null){
            id="connection not work";
            return id;};

        try {

            Statement statement = conn.createStatement();
            ResultSet rs=statement.executeQuery(SqlView);
            System.out.println("   User Max SqlView.executeQ().......");
            //conn.commit();

            while (rs.next()) {
                id=rs.getString(1);
                System.out.println("id=="+id);
            };

            int id_=Integer.parseInt(id)+1;
            id=String.valueOf(id_);
            System.out.println("id max+1=="+id);

            if (statement != null) {
                statement.close();
            }

            if (conn != null) {
                conn.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        conn = SQLiteDB.connectDB();
        SqlView = SQLiteSQL.SQLinsTWLMVALUES();

        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("SqlView ins SQLite=" + SqlView);
        if (conn==null){
            id="connection not work";
            return id;};

        try {


            Statement stmt = null;
            stmt=conn.createStatement();

            String replOld;
            //String str;
            replOld="?1";
            SqlView=SqlView.replace(replOld, id);
            replOld="?2";
            SqlView=SqlView.replace(replOld, arrV[1]);

            replOld="?3";
            SqlView=SqlView.replace(replOld, arrV[2]);

            replOld="?4";
            SqlView=SqlView.replace(replOld, arrV[3]);

            System.out.println("SqlView="+SqlView);
            //System.out.println("str="+str);

            stmt.executeUpdate(SqlView);


            conn.commit();

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("-----------");
        //System.out.println(listTisr_non_market);

        return id;

    }

    public static String updTWLMVALUES(String[] arrV) {

        String id=arrV[0];

        Connection conn = SQLiteDB.connectDB();
        String SqlView = SQLiteSQL.SQLupdTWLMVALUES();

        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("SqlView upd SQLite=" + SqlView);
        if (conn==null){
            id="connection not work";
            return id;};

        try {


            Statement stmt = null;
            stmt=conn.createStatement();

            String replOld;
            //String str;
            replOld="?4";
            SqlView=SqlView.replace(replOld, id);
            replOld="?1";
            SqlView=SqlView.replace(replOld, arrV[1]);

            replOld="?2";
            SqlView=SqlView.replace(replOld, arrV[2]);

            replOld="?3";
            SqlView=SqlView.replace(replOld, arrV[3]);

            System.out.println("SqlView="+SqlView);
            //System.out.println("str="+str);

            stmt.executeUpdate(SqlView);

            conn.commit();

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("-----------");
        //System.out.println(listTisr_non_market);

        return id;

    }

    public static String delTWLMVALUES(String[] arrV) {

        String id=arrV[0];

        Connection conn = SQLiteDB.connectDB();
        String SqlView = SQLiteSQL.SQLdelTWLMVALUES();

        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("SqlView del SQLite=" + SqlView);
        if (conn==null){
            id="connection not work";
            return id;};

        try {


            Statement stmt = null;
            stmt=conn.createStatement();

            String replOld;
            //String str;
            replOld="?1";
            SqlView=SqlView.replace(replOld, id);

            System.out.println("SqlView="+SqlView);
            //System.out.println("str="+str);

            stmt.executeUpdate(SqlView);

            conn.commit();

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("-----------");
        //System.out.println(listTisr_non_market);

        return id;

    }





    public static ArrayList<Sbor> getSborFromSQLite() {

        ArrayList<Sbor> listSbor = new ArrayList<Sbor>();

        Connection conn = SQLiteDB.connectDB();
        String SqlView = SQLiteSQL.getSQLselListSbor();

        System.out.println("SqlView SQLite=" + SqlView);
if (conn==null){
    Sbor sbor = new Sbor();
    sbor.setName("connection not work");
    listSbor.add(sbor);
    return listSbor;};

        try {

            Statement statement = conn.createStatement();


            ResultSet rs=statement.executeQuery(SqlView);
            System.out.println("   User SqlView.executeQ().......");
            //conn.commit();
            int k = 0;

            while (rs.next()) {

                Sbor sbor = new Sbor();

                sbor.setId(rs.getString(1));
                sbor.setName(rs.getString(2));
                System.out.println("rs.getDate(2)=" + rs.getDate(3));
                sbor.setData_sbora(rs.getDate(3));
                k++;

                listSbor.add(sbor);

            };

/*
            String dt1Str;
            String dt2Str;
            //if (dt1==null){dt1Str="01/01/1000";}
            System.out.println(dt1);
            SimpleDateFormat dtF = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dt1Str = dtF.format(dt1);
            System.out.println("222 dt1Str=" + dt1Str);
            //Date dt2 = new Date();
            //dt2.setTime(dt1.getTime() + 1 * 24 * 60 * 60 * 1000);
            dt2Str = dtF.format(dt2);
            System.out.println("dt2str=" + dt2Str);
*/

            if (statement != null) {
                statement.close();
            }

            if (conn != null) {
                conn.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("-----------");
        //System.out.println(listTisr_non_market);

        return listSbor;

    }

    public static String insSborSQLite(String[] arrV) {

        String id="--";

        Connection conn = SQLiteDB.connectDB();
        String SqlView = SQLiteSQL.SQLselMaxIdSbor();

        System.out.println("SqlView ins max SQLite=" + SqlView);
        if (conn==null){
            id="connection not work";
            return id;};

        try {

            Statement statement = conn.createStatement();
            ResultSet rs=statement.executeQuery(SqlView);
            System.out.println("   User Max SqlView.executeQ().......");
            //conn.commit();

            while (rs.next()) {
               id=rs.getString(1);
                System.out.println("id=="+id);
            };

            int id_=Integer.parseInt(id)+1;
            id=String.valueOf(id_);
            System.out.println("id max+1=="+id);

            if (statement != null) {
                statement.close();
            }

            if (conn != null) {
                conn.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        conn = SQLiteDB.connectDB();
        SqlView = SQLiteSQL.getSQLinsSbor();

        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("SqlView ins SQLite=" + SqlView);
        if (conn==null){
            id="connection not work";
            return id;};

        try {


            Statement stmt = null;
            stmt=conn.createStatement();

            String replOld;
            //String str;
            replOld="?1";
            SqlView=SqlView.replace(replOld, id);
            replOld="?2";
            SqlView=SqlView.replace(replOld, arrV[1]);

            Date date= DataTransform.getStrToDate(arrV[2].toString());
            //YYYY-MM-DD
            //DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateFormat df = new SimpleDateFormat("YYYY-MM-DD");
            replOld="?3";
            SqlView=SqlView.replace(replOld, df.format(date));

            System.out.println("SqlView="+SqlView);
            //System.out.println("str="+str);

            stmt.executeUpdate(SqlView);

/*
            PreparedStatement pStatement = conn.prepareStatement(SqlView);
            pStatement.setString(1,id);
            pStatement.setString(2,arrV[1]);
            System.out.println("arrV[2].toString()="+arrV[2].toString());
            Date date= DataTransform.getStrToDate(arrV[2].toString());
            //YYYY-MM-DD
            //DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateFormat df = new SimpleDateFormat("YYYY-MM-DD");
            pStatement.setString(3, df.format(date));
            System.out.println("df.format(date)="+df.format(date));

            pStatement.executeUpdate(SqlView);
            System.out.println("   User SqlView.executeQ().......");
            pStatement.close();
*/

            conn.commit();

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("-----------");
        //System.out.println(listTisr_non_market);

        return id;

    }




    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    public String getDate() {
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        return dt1.format(new Date());
    }

}
