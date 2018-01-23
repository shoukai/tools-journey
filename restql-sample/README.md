# restQL

### What is restQL?

restQL is a microservice query language that makes easy to fetch information from multiple services in the most efficient manner.

```
from hero
    with
        name = "Restman"

from sidekick
    with
        hero = hero.id
    only
        skills
```

### 资源

* [HOME PAGE](http://restql.b2w.io/)
* [restQL-core-java](https://github.com/B2W-BIT/restQL-core)

