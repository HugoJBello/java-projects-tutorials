//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.03.29 a las 02:48:02 PM CEST 
//


package io.spring.guides.gs_producing_web_service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getOutput" type="{http://spring.io/guides/gs-producing-web-service}getOutput"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getOutput"
})
@XmlRootElement(name = "getOutputResponse")
public class GetOutputResponse {

    @XmlElement(required = true)
    protected GetOutput getOutput;

    /**
     * Obtiene el valor de la propiedad getOutput.
     * 
     * @return
     *     possible object is
     *     {@link GetOutput }
     *     
     */
    public GetOutput getGetOutput() {
        return getOutput;
    }

    /**
     * Define el valor de la propiedad getOutput.
     * 
     * @param getoutput
     *     allowed object is
     *     {@link GetOutput }
     *     
     */
    public void setGetOutput(GetOutput getoutput) {
        this.getOutput = getoutput;
    }

}
