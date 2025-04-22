package itu.projet.pharmacie.dto;


import itu.projet.pharmacie.model.produit.Produit;
import itu.projet.pharmacie.model.produit.trancheageproduit.ProduitTrancheage;
import itu.projet.pharmacie.model.produit.contreindication.ContreIndication;
import itu.projet.pharmacie.model.produit.genreproduit.ProduitGenre;
import itu.projet.pharmacie.model.produit.substanceproduit.ProduitSubstance;
import itu.projet.pharmacie.model.produit.symptomeproduit.ProduitSymptome;

import java.util.List;

import java.util.Map;

public class ProduitDetailsDTO {

    private Produit produit;
    private List<ProduitTrancheage> trancheages;
    private List<ContreIndication> contreIndications;
    private List<ProduitGenre> genres;
    private List<ProduitSubstance> substances;
    private List<ProduitSymptome> symptomes;

    private Map<Integer, String> trancheageMap;
    private Map<Integer, String> contreIndicationMap;
    private Map<Integer, String> genreMap;
    private Map<Integer, String> substanceMap;
    private Map<Integer, String> symptomeMap;

    // Getters and Setters
    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public List<ProduitTrancheage> getTrancheages() {
        return trancheages;
    }

    public void setTrancheages(List<ProduitTrancheage> trancheages) {
        this.trancheages = trancheages;
    }

    public List<ContreIndication> getContreIndications() {
        return contreIndications;
    }

    public void setContreIndications(List<ContreIndication> contreIndications) {
        this.contreIndications = contreIndications;
    }

    public List<ProduitGenre> getGenres() {
        return genres;
    }

    public void setGenres(List<ProduitGenre> genres) {
        this.genres = genres;
    }

    public List<ProduitSubstance> getSubstances() {
        return substances;
    }

    public void setSubstances(List<ProduitSubstance> substances) {
        this.substances = substances;
    }

    public List<ProduitSymptome> getSymptomes() {
        return symptomes;
    }

    public void setSymptomes(List<ProduitSymptome> symptomes) {
        this.symptomes = symptomes;
    }

    public Map<Integer, String> getTrancheageMap() {
        return trancheageMap;
    }

    public void setTrancheageMap(Map<Integer, String> trancheageMap) {
        this.trancheageMap = trancheageMap;
    }

    public Map<Integer, String> getContreIndicationMap() {
        return contreIndicationMap;
    }

    public void setContreIndicationMap(Map<Integer, String> contreIndicationMap) {
        this.contreIndicationMap = contreIndicationMap;
    }

    public Map<Integer, String> getGenreMap() {
        return genreMap;
    }

    public void setGenreMap(Map<Integer, String> genreMap) {
        this.genreMap = genreMap;
    }

    public Map<Integer, String> getSubstanceMap() {
        return substanceMap;
    }

    public void setSubstanceMap(Map<Integer, String> substanceMap) {
        this.substanceMap = substanceMap;
    }

    public Map<Integer, String> getSymptomeMap() {
        return symptomeMap;
    }

    public void setSymptomeMap(Map<Integer, String> symptomeMap) {
        this.symptomeMap = symptomeMap;
    }
}
