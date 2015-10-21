package model;

import java.util.Date;

/**
 * Created by SAbdikalikov on 05.10.2015.
 */
public class Sbor {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    private String name;
    private String short_name;

    public Date getData_sbora() {
        return data_sbora;
    }

    public void setData_sbora(Date data_sbora) {
        this.data_sbora = data_sbora;
    }

    private Date data_sbora;

}
