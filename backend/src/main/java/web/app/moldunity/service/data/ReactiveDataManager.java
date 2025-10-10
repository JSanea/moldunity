package web.app.moldunity.service.data;


import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;

@Service
public record ReactiveDataManager(
    R2dbcEntityTemplate entityTemplate,
    DatabaseClient databaseClient,
    TransactionalOperator txOperator
){}
