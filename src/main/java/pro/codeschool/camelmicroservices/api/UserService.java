package pro.codeschool.camelmicroservices.api;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.codeschool.camelmicroservices.transformer.UserTransformer;

@Component
public class UserService extends RouteBuilder {

    @Autowired
    UserTransformer userTransformer;

    @Override
    public void configure() throws Exception {
        get();
        create();
        delete();
    }

    private void get() {
        from("direct:getUser")
            .routeId("getUserRoute")
            .errorHandler(noErrorHandler())
            .to("sql:select * from users where id = :#id?outputType=SelectOne")
            .choice()
                .when(simple("${body.isEmpty()}"))
                    .setHeader(Exchange.HTTP_RESPONSE_CODE, constant("404"))
            .end();
    }

    private void create() {
        from("direct:createUser")
            .routeId("createUserRoute")
            .errorHandler(noErrorHandler())
            .bean(userTransformer, "convert")
                .to("jpa:pro.codeschool.camelmicroservices.entity.UserEntity");
    }

    private void delete() {
        from("direct:deleteUser")
            .routeId("deleteUserRoute")
            .errorHandler(noErrorHandler())
            .to("sql:delete from users where id = :#id")
            .choice()
                .when(simple("${body.isEmpty()}"))
                    .setHeader(Exchange.HTTP_RESPONSE_CODE, constant("204"))
            .end();
    }
}
