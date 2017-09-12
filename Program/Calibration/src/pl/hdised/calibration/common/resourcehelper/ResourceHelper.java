package pl.hdised.calibration.common.resourcehelper;

import javax.xml.bind.JAXB;
import java.io.File;

/**
 * Resource helper
 * Resources are configurable in properties.xml
 * Created by Sebastian Oprzędek on 13.07.2017.
 */
public class ResourceHelper {
    private Parameters parameters = JAXB.unmarshal(new File("src/pl/hdised/calibration/properties.xml"), Parameters.class);

    /**
     * Returns string resource for key
     *
     * @param key key
     * @return resource
     */
    public String get(String key) {
        for (Parameter parameter : parameters.items)
            if (parameter.key.equals(key))
                return parameter.value;
        return null;
    }
}