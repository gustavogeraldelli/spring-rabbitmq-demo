package dev.gustavo.proposta_app.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initialize(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    /**
     * Essas filas vão receber payload desse microsserviço
     * quando uma proposta for realizada.
     * As duas filas vão receber a mesma mensagem,
     * por isso o FanoutExchange.
     * cada uma das filas está bindada com a mesma exchange
     */

    @Bean
    public Queue createQueuePropostaPendenteMsAnaliseCredito() {
        return QueueBuilder.durable("proposta-pendente.ms-analisar-credito").build();
    }

    @Bean
    public Queue createQueuePropostaPendenteMsNotificacao() {
        return QueueBuilder.durable("proposta-pendente.ms-notificacao").build();
    }

    @Bean
    public FanoutExchange createFanoutExchangePropostaPendente() {
        return ExchangeBuilder.fanoutExchange("proposta-pendente.ex").build();
    }

    @Bean
    public Binding createBindingPropostaPendenteMsAnaliseCredito() {
        return BindingBuilder.bind(createQueuePropostaPendenteMsAnaliseCredito())
                .to(createFanoutExchangePropostaPendente());
    }

    @Bean
    public Binding createBindingPropostaPendenteMsNotificacao() {
        return BindingBuilder.bind(createQueuePropostaPendenteMsNotificacao())
                .to(createFanoutExchangePropostaPendente());
    }

    // ***************************************************************************************

    @Bean
    public Queue createQueuePropostaConcluidaMsProposta() {
        return QueueBuilder.durable("proposta-concluida.ms-proposta").build();
    }

    @Bean
    public Queue createQueuePropostaConcluidaMsNotificacao() {
        return QueueBuilder.durable("proposta-concluida.ms-notificacao").build();
    }

}
