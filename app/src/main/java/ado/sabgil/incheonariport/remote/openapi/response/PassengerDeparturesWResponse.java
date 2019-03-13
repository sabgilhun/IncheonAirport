package ado.sabgil.incheonariport.remote.openapi.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "response")
public class PassengerDeparturesWResponse {

    @Element(name = "header")
    private Header header;

    @Element(name = "body", required = false)
    private PassengerDeparturesWBody body;

    public Header getHeader() {
        return header;
    }

    public PassengerDeparturesWBody getBody() {
        return body;
    }
}
