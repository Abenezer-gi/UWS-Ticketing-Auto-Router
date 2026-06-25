package helpdesk_Router_Analysis;
import helpdesk_Router_Ingestion.TicketFactory;
import helpdesk_router_model.Ticket;


public class Ticket_Pre_Processor {

    public void toString(Processed_Data other) {

        for (int i = 0 ; i < other.getOriginal_Ticket().getDescription().length() ; i++) {

            System.out.println(other.getTokenized_Description()[i]);
        }
    }


    public static Processed_Data Description_Cleaner (Ticket Unprocessed_Ticket) {



       String Intrim_String = Unprocessed_Ticket.getDescription();

       Intrim_String = Intrim_String.replaceAll("\\p{Punct}","").toLowerCase();

       String[] Tokenized_Description = Intrim_String.split(" ");

       Processed_Data data = new Processed_Data(Unprocessed_Ticket,Tokenized_Description);

       return data;

    }
}






