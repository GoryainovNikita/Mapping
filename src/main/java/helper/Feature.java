package helper;

public enum Feature {
    Trailers("Trailers"),
    Commentaries("Commentaries"),
    DeletedScenes("Deleted Scenes"),
    BehindTheScenes("Behind the Scenes");


    private final String value;

    Feature(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Feature getFeatureByValue(String value){
        if(value == null || value.isEmpty()){
            return null;
        }

        Feature[] features = Feature.values();

        for(var elem : features){
            if(elem.value.equals(value)){
                return elem;
            }
        }
        return null;
    }
}
