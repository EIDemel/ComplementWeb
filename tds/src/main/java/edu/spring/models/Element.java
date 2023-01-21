package edu.spring.models;

public class Element {

    private String nom;
    private String evaluation;
    public String getNom() {
        return nom;
    }
    public String getEvaluation() {
        return evaluation;
    }

    public Element(String nom, String evaluation) {
        this.nom = nom;
        this.evaluation = evaluation;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Element el){
            return this.nom.equals(el.getNom());
        }
        return false;
    }
}
