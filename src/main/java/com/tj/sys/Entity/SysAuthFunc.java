package com.tj.sys.Entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class SysAuthFunc implements Serializable{
	private static final long serialVersionUID = 1L;
	private long funcId;
    private long parentId;
    private int isMenu;
	private String funcName;
    private String funcUrl;
    private List<SysAuthFunc> child = new LinkedList<SysAuthFunc>();
    private boolean isChild = false;

    public long getFuncId() {
        return funcId;
    }
    public void setFuncId(long funcId) {
        this.funcId = funcId;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public int getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(int isMenu) {
        this.isMenu = isMenu;
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

    public List<SysAuthFunc> getChild() {
        return child;
    }

    public void setChild(List<SysAuthFunc> child) {
        this.child = child;
    }

    public void setIsChild(boolean isChild){
        this.isChild = isChild;
    }

    public boolean getIsChild(){
        return isChild;
    }

    public void addChild(SysAuthFunc func){
        child.add(func);
    }

}
