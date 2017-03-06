package at.fhjoanneum.ippr.communicator;

import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableJms
public class ExternalCommunicatorApplication {

  private static final String BROKER_ADDRESS = "tcp://127.0.0.1:61616";

  private final static Logger LOG = LoggerFactory.getLogger(ExternalCommunicatorApplication.class);

  public static void main(final String[] args) throws Exception {
    startBroker();
    SpringApplication.run(ExternalCommunicatorApplication.class, args);
  }

  private static void startBroker() {
    final BrokerService broker = new BrokerService();
    try {
      broker.addConnector(BROKER_ADDRESS);
      broker.setPersistent(false);
      broker.start();
      LOG.info("Broker started [{}]", BROKER_ADDRESS);
    } catch (final Exception e) {
      LOG.error(e.getMessage());
    }
  }

  @RefreshScope
  @RestController
  class MessageRestController {

    @Value("${message:Hello default}")
    private String message;

    @RequestMapping("/message")
    String getMessage() {
      return this.message;
    }
  }
}
