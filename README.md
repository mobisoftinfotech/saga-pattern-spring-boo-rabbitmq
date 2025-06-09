# saga-pattern-spring-boo-rabbitmq
Using the Saga Pattern with Spring Boot &amp; RabbitMQ

Distributed transactions across microservices require a different approach than the traditional two-phase commit (2PC). In a monolithic system, 2PC can coordinate multiple operations into a single atomic transaction. However, in a microservices architecture, where each service owns its own database and communicates over the network, 2PC becomes complex, slow, and error-prone due to tight coupling and potential locking across services.

To address this, the Saga pattern has become a widely adopted solution. Instead of one global transaction, a Saga breaks the workflow into a series of local transactions, each managed by an individual service. Once a service completes its part, it publishes an event (e.g. "DebitCompleted"), which other services consume to trigger their own local operations.

This event-driven approach creates a loosely coupled, scalable system where each step in the process knows just enough to do its job. If all goes well, the saga flows through each service to completion.
But real systems aren’t perfect, failures happen. If any service fails during the process, the Saga pattern allows you to run compensating transactions, custom logic designed to undo the effects of previously completed steps. For example, if a credit operation fails, the debit service can be asked to roll back the money transfer.

This way, data consistency is preserved without distributed locks or tight coordination, making it ideal for resilient, scalable microservices.
