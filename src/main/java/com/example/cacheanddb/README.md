```shell
docker run -d --name macsqlserver -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=Passw1rd' -e 'MSSQL_PID=Developer' -p 1433:1433 mcr.microsoft.com/mssql/server:latest
```

```sql
create table employee
(
    id         VARCHAR(40) PRIMARY KEY,
    first_name VARCHAR(50),
    last_name  VARCHAR(50),
    email      VARCHAR(50),
    gender     VARCHAR(50)
);
```

```shell
docker run --name my-redis -p 6379:6379 --restart always --detach redis
```