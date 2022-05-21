.PHONY: default
default: all

.PHONY: all
demo: docker-up smoke docker-down

.PHONY: docker-up
docker-up:
	docker-compose up -d --build
	@sleep 2
	@docker logs apollo-gateway
	@sleep 2
	@echo -------------
	@docker logs subgraph1
	@echo -------------
	@echo docker logs -f gatling-perf-test
	@echo -------------

.PHONY: smoke
smoke:
	@scripts/smoke.sh

.PHONY: docker-down
docker-down:
	docker-compose down --remove-orphans

.PHONY: graph-api-env
graph-api-env:
	@scripts/graph-api-env.sh

.PHONY: docker-up-router
docker-up-router:
	docker-compose -f docker-compose-router.yml up -d --build

