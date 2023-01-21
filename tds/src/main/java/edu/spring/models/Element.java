package edu.spring.models;

public class Element {

    private String nom;
    private int evaluation;
    public String getNom() {
        return nom;
    }
    public int getEvaluation() {
        return evaluation;
    }

    public Element(String nom, int evaluation) {
        this.nom = nom;
        this.evaluation = evaluation;
    }

    public void setEvaluation(int evaluation) {
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
