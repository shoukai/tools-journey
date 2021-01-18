package org.apframework.restql;

import restql.core.RestQL;
import restql.core.config.ClassConfigRepository;
import restql.core.query.QueryOptions;
import restql.core.response.QueryItemResponse;
import restql.core.response.QueryResponse;


public class SimpleQuery {

    public static void main(String[] args) {

        ClassConfigRepository config = new ClassConfigRepository();
        config.put("cards", "http://api.magicthegathering.io/v1/cards");
        config.put("card", "http://api.magicthegathering.io/v1/cards/:id");

        RestQL restQL = new RestQL(config);

        String queryCardsAndDetails = "from cards as cardsList with type = ? \n"
                + "from card as cardWithDetails with id = cardsList.id";

        QueryOptions opts = new QueryOptions();
        opts.setDebugging(false);
        opts.setGlobalTimeout(10000);
        opts.setTimeout(3000);

        QueryResponse response = restQL.executeQuery(queryCardsAndDetails, "Artifact");

        System.out.println(response);

        QueryItemResponse queryItem = response.get("cardWithDetails", QueryItemResponse.class);

        System.out.println(queryItem.getDetails().getStatus());
    }
}