package com.tj.sys.Entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
public class MenuFunc implements Serializable{

    private long funcId;
    private String funcName;
    private String funcUrl;
    private List<MenuFunc> child = new LinkedList<MenuFunc>();
    private boolean isChild;

    public long getFuncId() {
        return funcId;
    }

    public void setFuncId(long funcId) {
        this.funcId = funcId;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getFuncUrl() {
        return funcUrl;
    }

    public void setFuncUrl(String funcUrl) {
        this.funcUrl = funcUrl;
    }

    public List<MenuFunc> getChild() {
        return child;
    }

    public void setChild(List<MenuFunc> child) {
        this.child = child;
    }

    public void setIsChild(boolean isChild){
        this.isChild = isChild;
    }

    public boolean getIsChild(){
        return isChild;
    }

    public void addChild(MenuFunc func){
        child.add(func);
    }

    @Override
    public String toString() {
        return funcName+"/"+funcUrl+":"+child;
    }

}
