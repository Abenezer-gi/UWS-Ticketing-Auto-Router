import helpdesk_Router_Analysis.Final_Verdict;
import helpdesk_Router_Analysis.Processed_Data;
import helpdesk_Router_Analysis.Ticket_Pre_Processor;
import helpdesk_Router_Analysis.Ticket_Router_Engine;
import helpdesk_Router_Ingestion.TicketFactory;
import helpdesk_router_model.Ticket;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Please Enter Description: ");
        String Raw_String = scan.nextLine();

        Ticket Processed_Ticket = TicketFactory.Ticket_Generator(Raw_String);

        Processed_Data data = Ticket_Pre_Processor.Description_Cleaner(Processed_Ticket);

        Ticket_Router_Engine engine = new Ticket_Router_Engine();

        Final_Verdict Result = engine.Ticket_Grader(data);

        System.out.println("==========================================");
        System.out.println("        TICKET ROUTING RESULT             ");
        System.out.println("==========================================");
        System.out.println("Ticket ID     : " + Result.getFinal_Assigned_Ticket().getIncident_Id());
        System.out.println("Summary       : " + Result.getFinal_Assigned_Ticket().getSummary());
        System.out.println("Description   : " + Result.getFinal_Assigned_Ticket().getDescription());
        System.out.println("Routed To     : " + Result.getTeam());
        System.out.println("==========================================");





    }
}
