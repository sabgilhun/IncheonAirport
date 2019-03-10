package ado.sabgil.incheonariport.remote.openapi.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "response")
public class DeparturesWeatherResponse {

    @Element(name = "header")
    private Header header;

    @Element(name = "body")
    private DeparturesWeatherBody body;

    public Header getHeader() {
        return header;
    }

    public DeparturesWeatherBody getBody() {
        return body;
    }
}
