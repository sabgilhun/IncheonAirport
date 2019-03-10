package ado.sabgil.incheonariport.remote.openapi.response;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "items")
public class DeparturesCongestionItems {

    @ElementList(inline = true, required = false)
    private ArrayList<DeparturesCongestionItem> items;

    public ArrayList<DeparturesCongestionItem> getItems() {
        return items;
    }
}
