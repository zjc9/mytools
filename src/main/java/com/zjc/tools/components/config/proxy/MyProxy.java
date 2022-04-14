package com.zjc.tools.components.config.proxy;

import java.net.Proxy;

public class MyProxy {
    public Proxy proxy;
    public boolean used;

    public Proxy getProxy() {
        return proxy;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public MyProxy(Proxy proxy, boolean used) {
        this.proxy = proxy;
        this.used = used;
    }

    @Override
    public String toString() {
        return
                "proxy="+proxy.address().toString().split("/")[1] +
                ", used=" + used;
    }
}
