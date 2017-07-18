package pl.hdised.calibration.common.resourcehelper;

import javax.xml.bind.annotation.XmlAttribute;

class Parameter {
    @XmlAttribute
    String key;
    @XmlAttribute
    String value;
}
