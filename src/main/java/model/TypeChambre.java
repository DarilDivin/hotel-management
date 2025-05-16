package model;

import java.io.*;
import java.util.*;

public class TypeChambre {

    private String type;
    private Vector<Chambre> list_chambre = new Vector<Chambre>();

    public TypeChambre(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public Vector<Chambre> getListChambre() {
        return this.list_chambre;
    }

    public void setType(String newType) {
        this.type = newType;
    }

}