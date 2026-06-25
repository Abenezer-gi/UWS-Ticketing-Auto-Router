
package helpdesk_Router_Ingestion;

import helpdesk_router_model.Team;
import helpdesk_router_model.Ticket;

public class TicketFactory {

    private static int ID_Number = 1000; //Start at a base number 1000 and build up

    public static int ID_Generator() { //The ID number given to each ticket is non-static but the generator itself belongs to the class

        ID_Number++;
        return ID_Number;

    }

    public static String Summary_Generator(String Raw_Ticket) {

        if (Raw_Ticket == null || Raw_Ticket.length() <= 0) {

            return "Error, No Description Provided";

        }

        String Summary = "Summary";
        for (int i = 0; i < Raw_Ticket.length(); i++) {

            if (Raw_Ticket.charAt(i) == '.' || Raw_Ticket.charAt(i) == '?' || Raw_Ticket.charAt(i) == '!') {

                Summary = Raw_Ticket.substring(0, i + 1);
                return Summary;

            }
        }

        if (Raw_Ticket.length() <= 50) {

            Summary = Raw_Ticket.substring(0, Raw_Ticket.length());
            return Summary;

        } else {

            Summary = Raw_Ticket.substring(0, 50);
            return Summary;
        }


    }

    public static Ticket Ticket_Generator (String Raw_Ticket) {

        Ticket ticket = new Ticket(TicketFactory.ID_Generator(), TicketFactory.Summary_Generator(Raw_Ticket), Raw_Ticket);

        return ticket;
    }
}




