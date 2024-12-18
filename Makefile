.PHONY: build up down load_data test

build:
	@docker-compose build

up:
	@docker-compose up -d

down:
	@docker-compose down

load_data:
	@docker-compose run loader

test:
	@docker-compose exec app mvn test
