package pl.hdised.calibration.common;

import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.File;
import java.util.List;

/**
 * Created by Sebastian Oprzędek on 13.07.2017.
 */
public class ResourceHelper {
    private static Parameters parameters = JAXB.unmarshal(new File("src/pl/hdised/calibration/properties.xml"), Parameters.class);

    public static String getResource(String key){
        for(Parameter parameter : parameters.items)
            if(parameter.key.equals(key))
                return parameter.value;
        return null;
    }

    private static class Parameters {
        @XmlElement(name = "parameter")
        List<Parameter> items;
    }

    private static class Parameter {
        @XmlAttribute
        public String key;
        @XmlAttribute
        public String value;
    }
}