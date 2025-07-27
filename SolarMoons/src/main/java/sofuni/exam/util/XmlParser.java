package sofuni.exam.util;

import jakarta.xml.bind.JAXBException;

public interface XmlParser {
    <T> T fromXml(String xml, Class<T> clazz) throws JAXBException;
}
