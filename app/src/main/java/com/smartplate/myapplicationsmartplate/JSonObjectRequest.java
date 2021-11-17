package com.smartplate.myapplicationsmartplate;

import java.util.List;

public class JSonObjectRequest {


    private List<FoodFamilyBean> foodFamily;
    private List<FoodTypeBean> foodType;
    private int imageId;
    private ModelVersionsBean model_versions;
    private List<RecognitionResultsBean> recognition_results;

    public List<FoodFamilyBean> getFoodFamily() {
        return foodFamily;
    }

    public void setFoodFamily(List<FoodFamilyBean> foodFamily) {
        this.foodFamily = foodFamily;
    }

    public List<FoodTypeBean> getFoodType() {
        return foodType;
    }

    public void setFoodType(List<FoodTypeBean> foodType) {
        this.foodType = foodType;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public ModelVersionsBean getModel_versions() {
        return model_versions;
    }

    public void setModel_versions(ModelVersionsBean model_versions) {
        this.model_versions = model_versions;
    }

    public List<RecognitionResultsBean> getRecognition_results() {
        return recognition_results;
    }

    public void setRecognition_results(List<RecognitionResultsBean> recognition_results) {
        this.recognition_results = recognition_results;
    }

    public static class ModelVersionsBean {
        private String foodType;
        private String foodgroups;
        private String foodrec;
        private String ingredients;

        public String getFoodType() {
            return foodType;
        }

        public void setFoodType(String foodType) {
            this.foodType = foodType;
        }

        public String getFoodgroups() {
            return foodgroups;
        }

        public void setFoodgroups(String foodgroups) {
            this.foodgroups = foodgroups;
        }

        public String getFoodrec() {
            return foodrec;
        }

        public void setFoodrec(String foodrec) {
            this.foodrec = foodrec;
        }

        public String getIngredients() {
            return ingredients;
        }

        public void setIngredients(String ingredients) {
            this.ingredients = ingredients;
        }
    }

    public static class FoodFamilyBean {
        private int id;
        private String name;
        private double prob;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getProb() {
            return prob;
        }

        public void setProb(double prob) {
            this.prob = prob;
        }
    }

    public static class FoodTypeBean {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class RecognitionResultsBean {
        private int id;
        private String name;
        private double prob;
        private List<?> subclasses;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getProb() {
            return prob;
        }

        public void setProb(double prob) {
            this.prob = prob;
        }

        public List<?> getSubclasses() {
            return subclasses;
        }

        public void setSubclasses(List<?> subclasses) {
            this.subclasses = subclasses;
        }
    }
}
