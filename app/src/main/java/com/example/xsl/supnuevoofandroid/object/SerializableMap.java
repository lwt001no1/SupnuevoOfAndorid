package com.example.xsl.supnuevoofandroid.object;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class SerializableMap implements Serializable {
    private Map<String, String> stringMap;
    private Map<String, ArrayList> arrMap;
    private Map<String, Map> mapMap;

    public Map<String, String> getStringMap() {
        return stringMap;
    }

    public void setStringMap(Map<String, String> stringMap) {
        this.stringMap = stringMap;
    }

    public Map<String, ArrayList> getArrMap() {
        return arrMap;
    }

    public void setArrMap(Map<String, ArrayList> arrMap) {
        this.arrMap = arrMap;
    }

    public Map<String, Map> getMapMap() {
        return mapMap;
    }

    public void setMapMap(Map<String, Map> mapMap) {
        this.mapMap = mapMap;
    }
}
