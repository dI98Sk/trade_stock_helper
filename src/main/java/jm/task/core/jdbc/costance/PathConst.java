package jm.task.core.jdbc.costance;

public enum PathConst {
    OILPATH("/Users/papaskakun/Desktop/GIT/proj/Hibernate/src/main/resources/ICE.BRN_01.01.2021-02.03.2023.csv"),
    GOLDPATH("/Users/papaskakun/Desktop/GIT/proj/Hibernate/src/main/resources/comex.GC_01.01.2021-02.03.2023.csv"),
    BTCPATH("/Users/papaskakun/Desktop/GIT/proj/Hibernate/src/main/resources/BTC_USD_01.01ю2021-02.03.2023.csv");
    private final String URL;

    PathConst(String url) {
        URL = url;
    }

    public String getURL() {
        return URL;
    }
}
