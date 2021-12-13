package vea.kuc0277.project.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import vea.kuc0277.project.models.Route;

@Component
public class RouteConverter implements Converter<String, Route> {

    @Override
    public Route convert(String source) {
        String[] data = source.split(",");
        if (data == null || data.length == 0) {
            return null;
        } else {
            return new Route(
                    data[0],
                    Integer.parseInt(data[1]));
        }
    }
}
