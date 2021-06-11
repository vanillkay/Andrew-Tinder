package Tinder.Messages;

public class Messages {
    public final boolean ISOWN;
    public final String TEXT;

    public boolean getISOWN() {
        return ISOWN;
    }

    public String getTEXT() {
        return TEXT;
    }



    public Messages(boolean isown, String text) {
        ISOWN = isown;
        TEXT = text;
    }
}
