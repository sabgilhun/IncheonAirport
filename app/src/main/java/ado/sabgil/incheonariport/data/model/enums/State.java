package ado.sabgil.incheonariport.data.model.enums;

public enum State {

    LIGHT("원활"), NORMAL("보통"), BUSY("혼잡"), VERY_BUSY("매우 혼잡"), EMPTY2("오류"),
    EMPTY3("오류"), EMPTY4("오류"), EMPTY5("오류"), EMPTY6("오류"), CLOSE("운영 종료");

    final private String name;
    State(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
