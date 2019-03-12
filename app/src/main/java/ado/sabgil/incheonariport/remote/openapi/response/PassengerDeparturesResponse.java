package ado.sabgil.incheonariport.remote.openapi.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "response")
public class PassengerDeparturesResponse {

    @Element(name = "header")
    private Header header;

    @Element(name = "body", required = false)
    private PassengerDeparturesBody body;

    public Header getHeader() {
        return header;
    }

    public PassengerDeparturesBody getBody() {
        return body;
    }
}
