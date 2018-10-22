package cz.brmlab.wm.utils.LogMarker;

import lombok.Getter;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public enum LogMarker {

    SECRET("SECRET");

    @Getter
    private Marker marker;

    LogMarker(String markerText) {
        this.marker = MarkerFactory.getMarker(markerText);
    }
}
