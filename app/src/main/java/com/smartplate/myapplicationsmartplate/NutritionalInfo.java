package com.smartplate.myapplicationsmartplate;

import java.util.List;

public class NutritionalInfo {

    private List<FoodsBean> foods;

    public List<FoodsBean> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodsBean> foods) {
        this.foods = foods;
    }

    public static class FoodsBean {
        private String food_name;
        private Object brand_name;
        private int serving_qty;
        private String serving_unit;
        private int serving_weight_grams;
        private double nf_calories;
        private double nf_total_fat;
        private double nf_saturated_fat;
        private int nf_cholesterol;
        private double nf_sodium;
        private double nf_total_carbohydrate;
        private double nf_dietary_fiber;
        private double nf_sugars;
        private double nf_protein;
        private double nf_potassium;
        private double nf_p;
        private List<FullNutrientsBean> full_nutrients;
        private Object nix_brand_name;
        private Object nix_brand_id;
        private Object nix_item_name;
        private Object nix_item_id;
        private Object upc;
        private String consumed_at;
        private MetadataBean metadata;
        private int source;
        private int ndb_no;
        private TagsBean tags;
        private List<AltMeasuresBean> alt_measures;
        private Object lat;
        private Object lng;
        private int meal_type;
        private PhotoBean photo;
        private Object sub_recipe;
        private Object class_code;
        private Object brick_code;
        private Object tag_id;

        public String getFood_name() {
            return food_name;
        }

        public void setFood_name(String food_name) {
            this.food_name = food_name;
        }

        public Object getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(Object brand_name) {
            this.brand_name = brand_name;
        }

        public int getServing_qty() {
            return serving_qty;
        }

        public void setServing_qty(int serving_qty) {
            this.serving_qty = serving_qty;
        }

        public String getServing_unit() {
            return serving_unit;
        }

        public void setServing_unit(String serving_unit) {
            this.serving_unit = serving_unit;
        }

        public int getServing_weight_grams() {
            return serving_weight_grams;
        }

        public void setServing_weight_grams(int serving_weight_grams) {
            this.serving_weight_grams = serving_weight_grams;
        }

        public double getNf_calories() {
            return nf_calories;
        }

        public void setNf_calories(double nf_calories) {
            this.nf_calories = nf_calories;
        }

        public double getNf_total_fat() {
            return nf_total_fat;
        }

        public void setNf_total_fat(double nf_total_fat) {
            this.nf_total_fat = nf_total_fat;
        }

        public double getNf_saturated_fat() {
            return nf_saturated_fat;
        }

        public void setNf_saturated_fat(double nf_saturated_fat) {
            this.nf_saturated_fat = nf_saturated_fat;
        }

        public int getNf_cholesterol() {
            return nf_cholesterol;
        }

        public void setNf_cholesterol(int nf_cholesterol) {
            this.nf_cholesterol = nf_cholesterol;
        }

        public double getNf_sodium() {
            return nf_sodium;
        }

        public void setNf_sodium(double nf_sodium) {
            this.nf_sodium = nf_sodium;
        }

        public double getNf_total_carbohydrate() {
            return nf_total_carbohydrate;
        }

        public void setNf_total_carbohydrate(double nf_total_carbohydrate) {
            this.nf_total_carbohydrate = nf_total_carbohydrate;
        }

        public double getNf_dietary_fiber() {
            return nf_dietary_fiber;
        }

        public void setNf_dietary_fiber(double nf_dietary_fiber) {
            this.nf_dietary_fiber = nf_dietary_fiber;
        }

        public double getNf_sugars() {
            return nf_sugars;
        }

        public void setNf_sugars(double nf_sugars) {
            this.nf_sugars = nf_sugars;
        }

        public double getNf_protein() {
            return nf_protein;
        }

        public void setNf_protein(double nf_protein) {
            this.nf_protein = nf_protein;
        }

        public double getNf_potassium() {
            return nf_potassium;
        }

        public void setNf_potassium(double nf_potassium) {
            this.nf_potassium = nf_potassium;
        }

        public double getNf_p() {
            return nf_p;
        }

        public void setNf_p(double nf_p) {
            this.nf_p = nf_p;
        }

        public List<FullNutrientsBean> getFull_nutrients() {
            return full_nutrients;
        }

        public void setFull_nutrients(List<FullNutrientsBean> full_nutrients) {
            this.full_nutrients = full_nutrients;
        }

        public Object getNix_brand_name() {
            return nix_brand_name;
        }

        public void setNix_brand_name(Object nix_brand_name) {
            this.nix_brand_name = nix_brand_name;
        }

        public Object getNix_brand_id() {
            return nix_brand_id;
        }

        public void setNix_brand_id(Object nix_brand_id) {
            this.nix_brand_id = nix_brand_id;
        }

        public Object getNix_item_name() {
            return nix_item_name;
        }

        public void setNix_item_name(Object nix_item_name) {
            this.nix_item_name = nix_item_name;
        }

        public Object getNix_item_id() {
            return nix_item_id;
        }

        public void setNix_item_id(Object nix_item_id) {
            this.nix_item_id = nix_item_id;
        }

        public Object getUpc() {
            return upc;
        }

        public void setUpc(Object upc) {
            this.upc = upc;
        }

        public String getConsumed_at() {
            return consumed_at;
        }

        public void setConsumed_at(String consumed_at) {
            this.consumed_at = consumed_at;
        }

        public MetadataBean getMetadata() {
            return metadata;
        }

        public void setMetadata(MetadataBean metadata) {
            this.metadata = metadata;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public int getNdb_no() {
            return ndb_no;
        }

        public void setNdb_no(int ndb_no) {
            this.ndb_no = ndb_no;
        }

        public TagsBean getTags() {
            return tags;
        }

        public void setTags(TagsBean tags) {
            this.tags = tags;
        }

        public List<AltMeasuresBean> getAlt_measures() {
            return alt_measures;
        }

        public void setAlt_measures(List<AltMeasuresBean> alt_measures) {
            this.alt_measures = alt_measures;
        }

        public Object getLat() {
            return lat;
        }

        public void setLat(Object lat) {
            this.lat = lat;
        }

        public Object getLng() {
            return lng;
        }

        public void setLng(Object lng) {
            this.lng = lng;
        }

        public int getMeal_type() {
            return meal_type;
        }

        public void setMeal_type(int meal_type) {
            this.meal_type = meal_type;
        }

        public PhotoBean getPhoto() {
            return photo;
        }

        public void setPhoto(PhotoBean photo) {
            this.photo = photo;
        }

        public Object getSub_recipe() {
            return sub_recipe;
        }

        public void setSub_recipe(Object sub_recipe) {
            this.sub_recipe = sub_recipe;
        }

        public Object getClass_code() {
            return class_code;
        }

        public void setClass_code(Object class_code) {
            this.class_code = class_code;
        }

        public Object getBrick_code() {
            return brick_code;
        }

        public void setBrick_code(Object brick_code) {
            this.brick_code = brick_code;
        }

        public Object getTag_id() {
            return tag_id;
        }

        public void setTag_id(Object tag_id) {
            this.tag_id = tag_id;
        }

        public static class MetadataBean {
            private boolean is_raw_food;

            public boolean isIs_raw_food() {
                return is_raw_food;
            }

            public void setIs_raw_food(boolean is_raw_food) {
                this.is_raw_food = is_raw_food;
            }
        }

        public static class TagsBean {
            private String item;
            private Object measure;
            private String quantity;
            private int food_group;
            private int tag_id;

            public String getItem() {
                return item;
            }

            public void setItem(String item) {
                this.item = item;
            }

            public Object getMeasure() {
                return measure;
            }

            public void setMeasure(Object measure) {
                this.measure = measure;
            }

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }

            public int getFood_group() {
                return food_group;
            }

            public void setFood_group(int food_group) {
                this.food_group = food_group;
            }

            public int getTag_id() {
                return tag_id;
            }

            public void setTag_id(int tag_id) {
                this.tag_id = tag_id;
            }
        }

        public static class PhotoBean {
            private String thumb;
            private Object highres;
            private boolean is_user_uploaded;

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public Object getHighres() {
                return highres;
            }

            public void setHighres(Object highres) {
                this.highres = highres;
            }

            public boolean isIs_user_uploaded() {
                return is_user_uploaded;
            }

            public void setIs_user_uploaded(boolean is_user_uploaded) {
                this.is_user_uploaded = is_user_uploaded;
            }
        }

        public static class FullNutrientsBean {
            private int attr_id;
            private double value;

            public int getAttr_id() {
                return attr_id;
            }

            public void setAttr_id(int attr_id) {
                this.attr_id = attr_id;
            }

            public double getValue() {
                return value;
            }

            public void setValue(double value) {
                this.value = value;
            }
        }

        public static class AltMeasuresBean {
            private double serving_weight;
            private String measure;
            private int seq;
            private int qty;

            public double getServing_weight() {
                return serving_weight;
            }

            public void setServing_weight(double serving_weight) {
                this.serving_weight = serving_weight;
            }

            public String getMeasure() {
                return measure;
            }

            public void setMeasure(String measure) {
                this.measure = measure;
            }

            public int getSeq() {
                return seq;
            }

            public void setSeq(int seq) {
                this.seq = seq;
            }

            public int getQty() {
                return qty;
            }

            public void setQty(int qty) {
                this.qty = qty;
            }
        }
    }
}
