package com.hoollyzhang.csdn.response;

import com.hoollyzhang.csdn.mode.Bloger;

import java.util.ArrayList;

/**
 * Created by brzhang on 15/5/19.
 */
public class BlogerResponse {
    private ArrayList<Bloger> blogers;

    public ArrayList<Bloger> getBlogers() {
        return blogers;
    }

    public void setBlogers(ArrayList<Bloger> blogers) {
        this.blogers = blogers;
    }
}
