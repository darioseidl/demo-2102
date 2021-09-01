
# Problem description

Start the project and call http://localhost:8080/examples,
JSON serialization of the Example entity with Money will fail,
the following error will appear in the log:

> Resolved [org.springframework.http.converter.HttpMessageNotWritableException: Could not write JSON: Can not start an object, expecting field name (context: Object); nested exception is com.fasterxml.jackson.core.JsonGenerationException: Can not start an object, expecting field name (context: Object)]

Use `id("org.springframework.boot") version "2.3.4.RELEASE"` in `build.gradle.kts`
and start the project again, now the call to http://localhost:8080/examples works and returns:

```
{
  "_embedded" : {
    "examples" : [ {
      "price" : {
        "amount" : 123.00,
        "currency" : "EUR"
      },
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/examples/1"
        },
        "example" : {
          "href" : "http://localhost:8080/examples/1"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/examples"
    },
    "profile" : {
      "href" : "http://localhost:8080/profile/examples"
    }
  }
}
```
