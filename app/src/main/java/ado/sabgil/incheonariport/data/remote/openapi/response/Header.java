package ado.sabgil.incheonariport.data.remote.openapi.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name = "header")
public class Header {

    @Element(name = "resultCode")
    private String resultCode;


    @Element(name = "resultMsg")
    private String resultMsg;

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }
}
