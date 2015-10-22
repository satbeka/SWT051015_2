package common;


public class SQLiteSQL {

    private static String SQLselListSbor = null;
    private static String SQLselListTrenirvka=null;
    private static String SQLselListUpragnenie=null;
    private static String SQLselLitogo=null;
    private static String SQLinsTblForUser=null;
    private static String SQLupdTblForUser=null;
    private static String SQLselSprotsmens=null;



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

    public static String getSQLselLitogo()
    {
        if (SQLselLitogo == null) {
            SQLselLitogo = PropertiesBuilder.getProperty("SQLselLitogo");
        }
        return SQLselLitogo;
    }

    public static String getSQLinsTblForUser()
    {
        if (SQLinsTblForUser == null) {
            SQLinsTblForUser = PropertiesBuilder.getProperty("SQLinsTblForUser");
        }
        return SQLinsTblForUser;
    }
}
