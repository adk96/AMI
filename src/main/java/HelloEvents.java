import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.ManagerEventListener;
import org.asteriskjava.manager.action.StatusAction;
import org.asteriskjava.manager.event.ManagerEvent;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.asteriskjava.manager.event.DialEvent;
import org.asteriskjava.manager.event.NewConnectedLineEvent;

public class HelloEvents implements ManagerEventListener {

    private ManagerConnection managerConnection;

    public HelloEvents() throws IOException
    {
        ManagerConnectionFactory factory = new ManagerConnectionFactory(
                "192.168.100.19", "vadim", "passwordami");

        this.managerConnection = factory.createManagerConnection();
    }

    public void run() throws IOException, AuthenticationFailedException,
            TimeoutException, InterruptedException, org.asteriskjava.manager.TimeoutException {
        
        managerConnection.addEventListener(this);

        managerConnection.login();

        managerConnection.sendAction(new StatusAction());

        Thread.sleep(100000);

        managerConnection.logoff();
    }

    @Override
    public void onManagerEvent(ManagerEvent event) {
        
        
        if(event instanceof NewConnectedLineEvent){
            
            NewConnectedLineEvent e = (NewConnectedLineEvent)event;

            e.getCallerIdName();
            
            System.out.println(event.getCallerIdNum());
        }
        
        if(event instanceof NewConnectedLineEvent){
            
            NewConnectedLineEvent e = (NewConnectedLineEvent)event;

            e.getCallerIdName();
            
            System.out.println(event.getCallerIdName());
        }
    }
    
   
    

    public static void main(String[] args) throws Exception
    {
        HelloEvents helloEvents;

        helloEvents = new HelloEvents();
        helloEvents.run();
    }
}
