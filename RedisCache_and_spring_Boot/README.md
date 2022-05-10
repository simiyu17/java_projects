# Spring boot and redis cache

### To run
- Change directory to the project and run  `sh docker-runner.sh` which will spin up the database, redis server and the spring boot app.

## To test
- Open spring boot logs by running `docker logs --follow sample-redis`
- You can consume the API via `http://127.0.0.1:8080/api/v1/students/`.
- When you get student by id the first time, you will see sql logs on the terminal but on subsequent requests you won't logs as the student is being fetched from the redis cache.