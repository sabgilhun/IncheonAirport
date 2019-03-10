package ado.sabgil.incheonariport;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.StringDef;

import static ado.sabgil.incheonariport.Constant.OpenApiResponseCode.APPLICATION_ERROR;
import static ado.sabgil.incheonariport.Constant.OpenApiResponseCode.DB_ERROR;
import static ado.sabgil.incheonariport.Constant.OpenApiResponseCode.HTTP_ERROR;
import static ado.sabgil.incheonariport.Constant.OpenApiResponseCode.NORMAL_SERVICE;
import static ado.sabgil.incheonariport.Constant.OpenApiResponseCode.NO_DATA_ERROR;
import static ado.sabgil.incheonariport.Constant.OpenApiResponseCode.SERVICE_TIME_OUT_ERROR;

public class Constant {

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({NORMAL_SERVICE, APPLICATION_ERROR, DB_ERROR,
            NO_DATA_ERROR, HTTP_ERROR, SERVICE_TIME_OUT_ERROR})
    public @interface OpenApiResponseCode {
        String NORMAL_SERVICE = "00";
        String APPLICATION_ERROR = "01";
        String DB_ERROR = "02";
        String NO_DATA_ERROR = "03";
        String HTTP_ERROR = "04";
        String SERVICE_TIME_OUT_ERROR = "05";
    }
}
