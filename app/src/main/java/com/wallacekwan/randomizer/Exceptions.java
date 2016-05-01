package com.wallacekwan.randomizer;

/**
 * Created by wallacekwan on 2016-04-30.
 */

public class Exceptions {

}

class emptyInput extends Exception {
    public emptyInput(String message) {
        super(message);
    }
}

class throwEmptyInput {
    public String getSize(int x, String y) throws emptyInput {

        if (x == 0) {
            throw new emptyInput(y);
        }
        else {
            return null;
        }
    }
}

