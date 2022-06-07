import amqp from 'amqplib';
import CONFIG from '../../../config/config';

const ProducerService = () => ({
  async sendMessage(queue, message) {
    const connection = await amqp.connect(CONFIG.RABBITMQ_SERVER);
    const channel = await connection.createChannel();
    await channel.assertQueue(queue, {
      durable: true,
    });
    await channel.sendToQueue(queue, Buffer.from(message));
    setTimeout(() => {
      connection.close();
    }, 1000);
  },

});

export default ProducerService;
