package org.example;

public class FlagController implements FlagNotifier, FlagSettable {
    private volatile boolean flag = false;
    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public boolean getFlag() {
        return flag;
    }
}
