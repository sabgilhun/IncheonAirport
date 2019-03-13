package ado.sabgil.incheonariport.remote.openapi.response;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "items")
public class PassengerDeparturesWItems {

    @ElementList(inline = true, required = false)
    private ArrayList<PassengerDeparturesWItem> items;

    public ArrayList<PassengerDeparturesWItem> getItems() {
        return items;
    }
}
