package niall.receiver;

import niall.domain.Meeting;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.parser.AbstractContentHandler;
import org.apache.james.mime4j.parser.ContentHandler;
import org.apache.james.mime4j.parser.MimeStreamParser;
import org.apache.james.mime4j.stream.BodyDescriptor;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.MessageHandlerFactory;
import org.subethamail.smtp.RejectException;
import org.subethamail.smtp.server.SMTPServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by niall on 22/05/15.
 */
public class MeetingReceiver {

    public static void main(String args[]){
        MeetingReceiver meetingReceiver = new MeetingReceiver();
        meetingReceiver.receiveMeetings();
    }


    public void receiveMeetings(){

                MyMessageHandlerFactory myFactory = new MyMessageHandlerFactory() ;
                SMTPServer smtpServer = new SMTPServer(myFactory);
                smtpServer.setPort(25000);
                smtpServer.start();
        }

    private class MyMessageHandlerFactory implements MessageHandlerFactory {

        public MessageHandler create(MessageContext ctx) {
            return new Handler(ctx);
        }

        class Handler implements MessageHandler {
            MessageContext ctx;

            public Handler(MessageContext ctx) {
                this.ctx = ctx;
            }

            public void from(String from) throws RejectException {
                System.out.println("FROM:"+from);
            }

            public void recipient(String recipient) throws RejectException {
                System.out.println("RECIPIENT:"+recipient);
            }

            public void data(InputStream data) throws IOException {

                ContentHandler handler = new MyContentHandler();
                MimeStreamParser parser = new MimeStreamParser();
                parser.setContentHandler(handler);
                try {
                    parser.parse(data);
                } catch (MimeException e) {
                    e.printStackTrace();
                }
            }

            public class MyContentHandler extends AbstractContentHandler {

                public void body(BodyDescriptor bd, InputStream is)
                        throws MimeException, IOException {
                    String decodedText = convertStreamToString(is);
                    System.out.println(IcalHelper.decodeIcal(decodedText));
                }

            }

            public void done() {
                System.out.println("Finished");
            }

            public String convertStreamToString(InputStream is) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();

                String line = null;
                try {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return sb.toString();
            }

        }
    }
}
