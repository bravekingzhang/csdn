package com.hoollyzhang.csdn.mode;

/**
 * Created by brzhang on 15/5/17.
 */
public class Bloger {
    private String blogerName;
    private String blogerPic;
    private String blogerSrc;

    public String getBlogerSrc() {
        return blogerSrc;
    }

    public void setBlogerSrc(String blogerSrc) {
        this.blogerSrc = blogerSrc;
    }

    public String getBlogerName() {
        return blogerName;
    }

    public void setBlogerName(String blogerName) {
        this.blogerName = blogerName;
    }

    public String getBlogerPic() {
        return blogerPic;
    }

    public void setBlogerPic(String blogerPic) {
        this.blogerPic = blogerPic;
    }


    @Override
    public String toString() {
        return "Bloger{" +
                "blogerName='" + blogerName + '\'' +
                ", blogerPic='" + blogerPic + '\'' +
                ", blogerSrc='" + blogerSrc + '\'' +
                '}';
    }

}
