package ado.sabgil.incheonariport.remote.openapi.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "response")
public class DeparturesCongestionResponse {

    @Element(name = "header")
    private Header header;

    @Element(name = "body")
    private DeparturesCongestionBody body;

    public Header getHeader() {
        return header;
    }

    public DeparturesCongestionBody getBody() {
        return body;
    }
}
