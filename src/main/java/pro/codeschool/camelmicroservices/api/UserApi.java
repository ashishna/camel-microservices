package pro.codeschool.camelmicroservices.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pro.codeschool.camelmicroservices.model.ApiError;
import pro.codeschool.camelmicroservices.model.User;

@Component
public class UserApi extends RouteBuilder {

    @Value("${server.port}")
    int port;

    private final DataFormat errorDataFormat  = new JacksonDataFormat(new ObjectMapper(), String.class);

    @Override
    public void configure() throws Exception {

        configureServer();

        onException(Exception.class)
            .handled(true)
            .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
            .process( ex -> {
                ApiError error = new ApiError();
                error.setCode("400");
                error.setDescription("An error has occurred " + ex.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class).getMessage());
                ex.getIn().setBody(error);
            })
            .marshal(errorDataFormat)
        .end();

        rest("/users")
            .get("/{id}")
                .description("Gets the user from database given an ID")
                .param()
                    .name("id")
                    .type(RestParamType.path)
                    .description("The id of the user to get")
                    .dataType("number")
                .endParam()
                .produces("application/json")
                .outType(User.class)
                .to("direct:getUser")
            .delete("/{id}")
                .param()
                    .name("id")
                    .type(RestParamType.path)
                    .description("Deletes the user from database given an ID")
                    .dataType("number")
                .endParam()
                .produces("application/json")
                .outType(String.class)
                .to("direct:deleteUser")
            .post()
                .type(User.class)
                .outType(User.class)
                .description("Creates a user based on User data model")
                .consumes("application/json")
                .produces("application/json")
                .to("direct:createUser");

    }

    private void configureServer() {
        restConfiguration()
            .component("jetty")
            .bindingMode(RestBindingMode.json)
            .dataFormatProperty("json.in.disableFeatures", "FAIL_ON_UNKNOWN_PROPERTIES,ADJUST_DATES_TO_CONTEXT_TIME_ZONE")
            .contextPath("/api")
            .apiContextPath("/docs")
            .apiVendorExtension(true)
                .apiHost("localhost")
                .apiProperty("api.title", "User API")
                .apiProperty("api.version", "1.0.0")
                .apiProperty("cors", "true")
            .port(port);
    }
}
