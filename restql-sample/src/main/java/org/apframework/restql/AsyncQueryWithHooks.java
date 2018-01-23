package org.apframework.restql;

import restql.core.RestQL;
import restql.core.config.ClassConfigRepository;
import restql.core.hooks.AfterQueryHook;
import restql.core.hooks.AfterRequestHook;
import restql.core.hooks.QueryHook;
import restql.core.hooks.RequestHook;
import restql.core.query.QueryOptions;

import java.util.Map;


public class AsyncQueryWithHooks {

    public static class BeforeRequestHook extends RequestHook {

        public void execute() {
            System.out.println("[BEFORE REQUEST]");
            for (Map.Entry<String, Object> e : this.getData().entrySet()) {
                System.out.println(e.getKey() + " - " + e.getValue());
            }
        }
    }

    public static class SimpleAfterRequestHook extends AfterRequestHook {

        public void execute() {
            System.out.println("[AFTER REQUEST]");
            System.out.println(this.getUrl() + " => " + this.getTimeout() + " CODE: " + this.getResponseStatusCode()
                    + " (" + this.getReponseTime() + ")");

            if (this.isError())
                System.out.println("Error: " + this.getData().get("errordetail"));
            else {
                for (Map.Entry<String, String> e : this.getHeaders().entrySet()) {
                    System.out.println(e.getKey() + " = " + e.getValue());
                }
            }

        }
    }

    public static class BeforeQueryHook extends QueryHook {

        public void execute() {
            System.out.println("[BEFORE QUERY] " + this.getQuery());
        }
    }

    public static class SimpleAfterQueryHook extends AfterQueryHook {

        public void execute() {
            System.out.println("[AFTER QUERY] " + this.getQuery());
            System.out.println(this.getResult().toString());
        }
    }

    public static void main(String[] args) throws Exception {

        ClassConfigRepository config = new ClassConfigRepository();
        config.put("cards", "http://api.magicthegathering.io/v1/cards/:id");

        RestQL restQL = new RestQL(config);
        String query = "from cards as card with id = ?";
        QueryOptions opts = new QueryOptions();

        opts.setBeforeQueryHook(BeforeQueryHook.class);
        opts.setAfterQuerytHook(SimpleAfterQueryHook.class);
        opts.setBeforeRequestHook(BeforeRequestHook.class);
        opts.setAfterRequestHook(SimpleAfterRequestHook.class);

        restQL.executeQueryAsync(query, opts, System.out::println, "693f4d338e4f041ccf4dd158ccde0b14dfc51ee0");
        Thread.sleep(5000);
    }
}