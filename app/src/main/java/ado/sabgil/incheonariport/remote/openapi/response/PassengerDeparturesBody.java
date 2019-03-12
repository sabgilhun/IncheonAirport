package ado.sabgil.incheonariport.remote.openapi.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "body")
public class PassengerDeparturesBody {

    @Element(name = "items")
    private PassengerDeparturesItems items;

    public PassengerDeparturesItems getItems() {
        return items;
    }
}
