package ado.sabgil.incheonariport.remote.openapi.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "body")
public class DeparturesCongestionBody {

    @Element(name = "items")
    private DeparturesCongestionItems items;

    @Element(name = "numOfRows")
    private int numOfRows;

    @Element(name = "pageNo")
    private int pageNo;

    @Element(name = "totalCount")
    private int totalCount;

    public DeparturesCongestionItems getItems() {
        return items;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
