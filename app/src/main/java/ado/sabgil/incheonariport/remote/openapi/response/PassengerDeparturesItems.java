package ado.sabgil.incheonariport.remote.openapi.response;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "items")
public class PassengerDeparturesItems {

    @ElementList(inline = true, required = false)
    private ArrayList<PassengerDeparturesItem> items;

    public ArrayList<PassengerDeparturesItem> getItems() {
        return items;
    }
}
