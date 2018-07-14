package nyxdev.hackatren.taralrt1.integration.network;

import java.io.IOException;

public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "No connectivity exception";
    }

}