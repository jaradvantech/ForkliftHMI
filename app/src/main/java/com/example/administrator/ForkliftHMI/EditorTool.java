package com.example.administrator.ForkliftHMI;

public class EditorTool {

    public String name;
    public int textureResource;

    EditorTool(){
    }

    EditorTool(String name, int textureResource) {
        this.name = name;
        this.textureResource = textureResource;
    }

    public boolean equals(EditorTool o) {
        if(o.name.equals(this.name)) {
            return true;
        } else {
            return false;
        }
    }

}
