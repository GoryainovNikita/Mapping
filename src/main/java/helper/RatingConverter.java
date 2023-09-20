package helper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String> {
    @Override
    public String convertToDatabaseColumn(Rating rating) {
        return rating.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(String s) {
        Rating[] ratings = Rating.values();
        for(var elem : ratings){
            if(elem.getValue().equals(s)){
                return elem;
            }
        }
        return null;
    }
}
