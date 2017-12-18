package ius.smartlab.POJO;

import android.app.Application;

/**
 * Created by Džana on 10/12/2017.
 */

public class GlobalVar extends Application {

    private String datum;

    public GlobalVar() {
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
}
