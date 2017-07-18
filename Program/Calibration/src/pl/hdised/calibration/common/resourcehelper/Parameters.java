package pl.hdised.calibration.common.resourcehelper;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

class Parameters {
    @XmlElement(name = "parameter")
    List<Parameter> items;
}
