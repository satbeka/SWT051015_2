package util;

import common.OracleDB;
import common.OracleSQL;
import common.SQLiteDB;
import common.SQLiteSQL;
import model.Sbor;
import model.Upragnenie;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DataTransform {


    private List<Sbor> sbors;
    private List<Upragnenie> upragnenies;
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



    public static ArrayList<String> getSborCsv(ArrayList<Sbor> sborArrayList) {

        ArrayList<String> arrRez=new ArrayList<>();
        //"Sbori","name","data_sbora"
        System.out.println("getSborScv=" + sborArrayList);

            int k = 0;
        Sbor sbor;
        String str;

        for (int i = 0; i < sborArrayList.size(); i++) {
            sbor=sborArrayList.get(i);
            str=sbor.getName()+";"+DataTransform.getDateToStr(sbor.getData_sbora());
            System.out.println("str="+str);
            arrRez.add(str);
        }



        System.out.println("-----------");
        //System.out.println(listTisr_non_market);

        return arrRez;

    }

    public static ArrayList<Upragnenie> getUpragnFromOracle() {

        ArrayList<Upragnenie> listUprg = new ArrayList<Upragnenie>();

        String SqlView = OracleSQL.getSQLselListUpragnenie();


        System.out.println("SqlView Up=" + SqlView);

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

                Upragnenie upragnenie = new Upragnenie();

                upragnenie.setName(rs.getString(1));
                upragnenie.setShort_name(rs.getString(2));
                k++;

                listUprg.add(upragnenie);

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

        return listUprg;

    }



    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    public static String getDateToStr(Date dt) {
        String rez=null;
        if (dt==null){rez="- - -";return rez;};
        SimpleDateFormat dt1 = new SimpleDateFormat("dd.MM.yyyy");
        rez=dt1.format(dt);
        return rez;
    }

}
