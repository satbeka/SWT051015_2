package common;


public class SQLiteSQL {

    private static String SQLselListSbor = null;
    private static String SQLinsSbor=null;
    private static String SQLselMaxIdSbor=null;

    private static String SQLselListTrenirvka=null;
    private static String SQLselListUpragnenie=null;
    private static String SQLselLitogo=null;

    private static String SQLupdSbor=null;
    private static String SQLdelSbor=null;
    private static String SQLinsSprotsmens=null;
    private static String SQLupdSprotsmens=null;
    private static String SQLdelSprotsmens=null;

    private static String SQLselSprotsmens=null;


    public static String getSQLinsSbor()
    {
        if (SQLinsSbor == null) {
            SQLinsSbor = PropertiesBuilder.getProperty("SQLinsSbor");
        }
        return SQLinsSbor;
    }

    public static String SQLselMaxIdSbor()
    {
        if (SQLselMaxIdSbor == null) {
            SQLselMaxIdSbor = PropertiesBuilder.getProperty("SQLselMaxIdSbor");
        }
        return SQLselMaxIdSbor;
    }

    public static String SQLupdSbor()
    {
        if (SQLupdSbor == null) {
            SQLupdSbor = PropertiesBuilder.getProperty("SQLupdSbor");
        }
        return SQLupdSbor;
    }

    public static String SQLdelSbor()
    {
        if (SQLdelSbor == null) {
            SQLdelSbor = PropertiesBuilder.getProperty("SQLdelSbor");
        }
        return SQLdelSbor;
    }


    public static String getSQLselListSbor()
    {
        if (SQLselListSbor == null) {
            SQLselListSbor = PropertiesBuilder.getProperty("SQLselListSbor");
        }
        return SQLselListSbor;
    }

    public static String getSQLselSprotsmens()
    {
        if (SQLselSprotsmens == null) {
            SQLselSprotsmens = PropertiesBuilder.getProperty("SQLselSprotsmens");
        }
        return SQLselSprotsmens;
    }

    public static String getSQLselListTrenirvka()
    {
        if (SQLselListTrenirvka == null) {
            SQLselListTrenirvka = PropertiesBuilder.getProperty("SQLselListTrenirvka");
        }
        return SQLselListTrenirvka;
    }

    public static String getSQLselListUpragnenie()
    {
        if (SQLselListUpragnenie == null) {
            SQLselListUpragnenie = PropertiesBuilder.getProperty("SQLselListUpragnenie");
        }
        return SQLselListUpragnenie;
    }




}
