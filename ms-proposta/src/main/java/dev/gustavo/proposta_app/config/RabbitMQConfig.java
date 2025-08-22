package dev.gustavo.proposta_app.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Esse serviço (proposta-ms)
     * vai publicar mensagens na (fanout) Exchange 'proposta-pendente.ex'
     *
     * que vai encaminhar para as filas 'proposta-pendente.ms-analisar-credito' e
     *                                      'proposta-pendente.ms-notificacao'
     *
     * que serão consumidas/ouvidas pelos microserviços 'ms-analisar-credito' e 'ms-notificacao', respectivamente
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

    // bind proposta-pendente.ex com proposta-pendente.ms-analisar-credito
    @Bean
    public Binding createBindingPropostaPendenteMsAnaliseCredito() {
        return BindingBuilder.bind(createQueuePropostaPendenteMsAnaliseCredito())
                .to(createFanoutExchangePropostaPendente());
    }

    // bind proposta-pendente.ex com proposta-pendente.ms-notificacao
    @Bean
    public Binding createBindingPropostaPendenteMsNotificacao() {
        return BindingBuilder.bind(createQueuePropostaPendenteMsNotificacao())
                .to(createFanoutExchangePropostaPendente());
    }

    /**
     * [] A lógia de criação caberia estar no serviço responsável por enviar as mensagens
     * O serviço 'ms-analisar-credito'
     * vai publicar mensagens na (fanout) Exchange 'proposta-concluida.ex'
     *
     * que vai encaminhar para as filas 'proposta-concluida.ms-proposta' (esse serviço) e
     *                                      'proposta-concluida.ms-notificacao'
     *
     * que serão consumidas/ouvidas pelos microserviços 'ms-proposta' (esse serviço) e 'ms-notificacao', respectivamente
     */

    @Bean
    public Queue createQueuePropostaConcluidaMsProposta() {
        return QueueBuilder.durable("proposta-concluida.ms-proposta").build();
    }

    @Bean
    public Queue createQueuePropostaConcluidaMsNotificacao() {
        return QueueBuilder.durable("proposta-concluida.ms-notificacao").build();
    }

    @Bean
    public FanoutExchange createFanoutExchangePropostaConcluida() {
        return ExchangeBuilder.fanoutExchange("proposta-concluida.ex").build();
    }

    // bind proposta-concluida.ex com proposta-concluida.ms-proposta (esse)
    @Bean
    public Binding createBindingPropostaConcluidaMsProposta() {
        return BindingBuilder.bind(createQueuePropostaConcluidaMsProposta())
                .to(createFanoutExchangePropostaConcluida());
    }

    // bind proposta-concluida.ex com proposta-concluida.ms-notificacao
    @Bean
    public Binding createBindingPropostaConcluidaMsNotificacao() {
        return BindingBuilder.bind(createQueuePropostaConcluidaMsNotificacao())
                .to(createFanoutExchangePropostaConcluida());
    }

}
