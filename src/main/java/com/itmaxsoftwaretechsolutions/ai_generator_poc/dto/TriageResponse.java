package com.itmaxsoftwaretechsolutions.ai_generator_poc.dto;

public class TriageResponse {

    private String priority;
    private String category;
    private String impact;
    private String team;
    private String summary;
    private int slaMinutes;
    private double confidenceScore;
    private String recommendedAction;

    // getters and setters
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getSlaMinutes() {
        return slaMinutes;
    }

    public void setSlaMinutes(int slaMinutes) {
        this.slaMinutes = slaMinutes;
    }

    public double getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(double confidenceScore) {
        this.confidenceScore = confidenceScore;
    }

    public String getRecommendedAction() {
        return recommendedAction;
    }

    public void setRecommendedAction(String recommendedAction) {
        this.recommendedAction = recommendedAction;
    }
}
