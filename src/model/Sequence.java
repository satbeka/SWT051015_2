package model;

import java.util.Date;


public class Sequence {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
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
