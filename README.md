# Sample Data API
This sample data API connects to a Postgres database, which is containing Adventureworks sample data set (originally Microsoft SQL server). This dataset has a typical DWH structure. This way a DWH modernization use case can be demonstrated.

### Start

The overall start script builds Postgres and Spring Boot Docker containers and starts them afterwards. Docker-Compose is used for that, so it has to be installed on your system.

```bash
docker-compose up --build
```

