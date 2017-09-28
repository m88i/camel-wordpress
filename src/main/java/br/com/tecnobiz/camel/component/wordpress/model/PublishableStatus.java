package br.com.tecnobiz.camel.component.wordpress.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A named status for the object.
 */
public enum PublishableStatus {
    //@formatter:off
    publish, 
    future, 
    draft, 
    pending, 
    @JsonProperty("private")
    private_;
    //@formatter:on

    /***
     * @param arg
     * @return
     * @see <a href=
     *      "https://stackoverflow.com/questions/33357594/java-enum-case-insensitive-jersey-query-param-binding">Java:
     *      Enum case insensitive Jersey Query Param Binding</a>
     */
    public static final PublishableStatus fromString(String arg) {
        arg = "".concat(arg).toLowerCase();
        if (!arg.isEmpty() && arg.startsWith("private")) {
            return private_;
        }

        return valueOf(arg);
    }
}
