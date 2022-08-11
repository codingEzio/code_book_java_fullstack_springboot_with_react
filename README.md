
> This is a copy of my note in [another repository](https://github.com/codingEzio/metarepo_blog/blob/master/043%20-%20%E9%98%85%E4%B9%A6%E7%AC%94%E8%AE%B0%20%E5%85%A8%E6%A0%88SpringBoot%20with%20React.md)

### Metadata

- *Hands-On Full Stack Development with Spring Boot 2.0 and React*
- Get the [*book*](https://www.amazon.com/Hands-Stack-Development-Spring-React-ebook/dp/B07DT9DTS1)
- Get the [*source code*](https://github.com/PacktPublishing/Hands-On-Full-Stack-Development-with-Spring-Boot-2.0-and-React)

### Preface

- Thoughts
    > A bit outdated for the frameworks being used. But in the mean time, I think this would be an excellent learning experience for me. For example migrating from old versions to the newer one, it wouldn't always be an easy task, eh?
- Third-party software I will be using
  - [Postman](https://www.postman.com/downloads/): An API platform for building and using APIs
  - [`mycli`](https://github.com/dbcli/mycli): Terminal client for MySQL that have auto-completion and syntax highlighting
  - [`http`](https://github.com/httpie/httpie): Human-friendly CLI HTTP client for the API era
  - [`jq`](https://github.com/stedolan/jq): Lightweight and flexible command-line JSON processor.

-----

### Spring Security

##### Testing

```bash
# The password would be shown at the console
http GET http://localhost:8080/api/cars --auth-type basic --auth user:PASSWORD
http GET http://localhost:8080/api/cars                   --auth user:PASSWORD
```

### Implementing *CRUD* and *Query*

##### *C*reate

```json
{
    // Open Postman (do the registeration and stuff beforehand!)
    // 1. Method: POST
    // 2. URL   : http://localhost:8080/api/cars
    // 3. Select Tab 'Body' -> raw -> JSON

    "brand": "Terminator",
    "model": "EOF",
    "color": "Black",
    "registerNumber": "TWN-0001",
    "date_year": 2021,
    "price": 25000,
    "owner": "http://localhost:8080/owners/1"
}
```

##### *R*ead

```bash
http GET http://localhost:8080/api

http GET http://localhost:8080/api/cars
http GET http://localhost:8080/api/cars/1
http GET http://localhost:8080/api/cars/1/owner

http GET http://localhost:8080/api/owners
http GET http://localhost:8080/api/owners/1
http GET http://localhost:8080/api/owners/1/cars
```

##### *U*pdate

```json
{
    // Open Postman
    // 1. Method: PUT or PATH
    // 2. URL   : http://localhost:8080/api/cars/4
    // 3. Select Tab 'Body' -> raw -> JSON

    "brand": "Honda",
    "model": "Young",
    "color": "Silver",
    "registerNumber": "YYN-0008",
    "dateYear": 2019,
    "price": 32000,
    "owner": "http://localhost:8080/owners/5"

    // PUT    require all fields (left blank -> update NULL)
    // PATCH  fields you wanna update (>= 0 fields)
}
```

##### *D*elete

```bash
http DELETE http://localhost:8080/api/cars/4
http DELETE http://localhost:8080/api/owners/6
```

-----

##### *Q*uery

```bash
# Further exploration around query parameters are needed
http GET "http://localhost:8080/api/cars/search/findByBrand?brand=Nissan" | jq "._embedded .cars"
```

### Miscellaneous

##### Interface

- `CrudRepository`

    ```java
    // Car  -> Stating this repositoy (CRUD functionality is for `Car`)
    // Long -> The type of the ID field is `Long`
    public interface .. extends CrudRepository<Car, Long> { }
    ```

### Issue

##### SQL Reserved Keyword

- Q: Can you use `year` as an attribute in your database?
- A: No, it's a [reserved](https://docs.microsoft.com/en-us/sql/t-sql/language-elements/reserved-keywords-transact-sql?view=sql-server-ver16) keyword, and JPA couldn't create it for you.

##### Import Not Resolved for `.. springframework.data.rest ..`

- Q: The IDE couldn't resolve the dependency I declared in the `pom.xml`
- A: Explicit versioning is needed according to other developers

    ```xml
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-rest</artifactId>

        <!-- New -->  
      <version>2.3.1.RELEASE</version>
  </dependency>
    ```
