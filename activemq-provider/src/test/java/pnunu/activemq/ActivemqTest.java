package pnunu.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.util.Date;

/**
 * @Author: pnunu
 * @Date: 2020/3/2 10:20
 * @Description: Activemq 测试
 */
public class ActivemqTest {
    /*
     * 测试点对点模式：生产者
     */
    @Test
    public void testQueueProducer() throws JMSException {
        //1.创建一个连接工厂对象,需要指定服务的ip及端口号
        ActiveMQConnectionFactory factory =
                new ActiveMQConnectionFactory("tcp://10.30.17.52:61616");
        //2.使用工厂对象来创建一个Connection对象
        Connection connection = factory.createConnection();
        //3.开启连接，调用Connection对象的start方法
        connection.start();
        //4.创建一个Session对象
        //第一个参数:是否开启事务,如果开启事务true带二个参数无意义，一般不开启事务false
        //第二个参数:应答模式。一般自动应答或者手动应答。一般自动应答
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.使用Session对象创建一个Destination对象。两种形式queue,topic,现在应该使用queue
        Queue queue = session.createQueue("jt");
        //6.使用Session对象创建一个Producer对象
        MessageProducer producer = session.createProducer(queue);
        //7.创建一个Message对象，可以使用TextMessage
        Message message = session.createTextMessage("hello pnunu");
        //8.发送消息
        producer.send(message);
        //9.关闭资源
        producer.close();
        session.close();
        connection.close();
    }


    /*
     * 测试点对点模式：消费者
     */
    @Test
    public void testQueueConsumer() throws Exception {
        //1.创建连接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://10.30.17.52:61616");
        //2.创建连接
        Connection connection = factory.createConnection();
        //3.开启连接
        connection.start();
        //4.创建session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建目的地，需要跟生产者发送消息的目的地一致不然不能接收到消息
        Queue queue = session.createQueue("jt");
        //使用Session对象创建一个消费者对象
        MessageConsumer consumer = session.createConsumer(queue);
        //接收消息
        consumer.setMessageListener(new MessageListener() {

            public void onMessage(Message message) {

                TextMessage textMessage = (TextMessage) message;
                try {
                    String text = textMessage.getText();
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //等待接收消息,消费完了就没了
        System.in.read();//等待我们敲击键盘才会向下执行
        //关闭资源
        consumer.close();
        session.close();
        connection.close();
    }


    /*
     * 发布订阅模式：生产者
     */
    @Test
    public void testTopicProducer() throws Exception {
        //1.创建连接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://10.30.17.52:61616");
        //2.创建连接
        Connection connection = factory.createConnection();
        //3.开启连接
        connection.start();
        //4.创建session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建目的地Destination，该模式创建的是Topic
        Topic topic = session.createTopic("jt");
        //6.创建生产者
        MessageProducer producer = session.createProducer(topic);
        //7.创建消息
        TextMessage message = session.createTextMessage("hello topic jt");
        //8.发送消息
        producer.send(message);
        //9.关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    /*
     * 发布订阅模式：消费者
     */
    @SuppressWarnings("unused")
    @Test
    public void testTopicConsumer() throws Exception {
        //1.创建连接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://10.30.17.52:61616");
        //2.创建连接
        Connection connection = factory.createConnection();
        //3.开启连接
        connection.start();
        //4.创建session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建目的地Destination
        Topic topic = session.createTopic("jt");
        //6.创建消费者
        MessageConsumer consumer = session.createConsumer(topic);
        //7.接收消息
        consumer.setMessageListener(new MessageListener() {

            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                //获取信息并打印
                try {
                    String text = textMessage.getText();
                    System.out.println(text + new Date().toString());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("----消费者1准备就绪----");
        System.in.read();
        //9.关闭资源
        consumer.close();
        session.close();
        connection.close();
    }

    /*
     * 发布订阅模式：消费者
     */
    @SuppressWarnings("unused")
    @Test
    public void testTopicConsumer2() throws Exception {
        //1.创建连接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://10.30.17.52:61616");
        //2.创建连接
        Connection connection = factory.createConnection();
        //3.开启连接
        connection.start();
        //4.创建session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建目的地Destination
        Topic topic = session.createTopic("jt");
        //6.创建消费者
        MessageConsumer consumer = session.createConsumer(topic);
        //7.接收消息
        consumer.setMessageListener(new MessageListener() {

            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                //获取信息并打印
                try {
                    String text = textMessage.getText();
                    System.out.println(text + new Date().toString());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("----消费者2准备就绪----");
        System.in.read();
        //9.关闭资源
        consumer.close();
        session.close();
        connection.close();
    }

    /*
     * 发布订阅模式：消费者
     */
    @SuppressWarnings("unused")
    @Test
    public void testTopicConsumer3() throws Exception {
        //1.创建连接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://10.30.17.52:61616");
        //2.创建连接
        Connection connection = factory.createConnection();
        //3.开启连接
        connection.start();
        //4.创建session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建目的地Destination
        Topic topic = session.createTopic("jt");
        //6.创建消费者
        MessageConsumer consumer = session.createConsumer(topic);
        //7.接收消息
        consumer.setMessageListener(new MessageListener() {

            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                //获取信息并打印
                try {
                    String text = textMessage.getText();
                    System.out.println(text + new Date().toString());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("----消费者3准备就绪----");
        System.in.read();
        //9.关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
}
