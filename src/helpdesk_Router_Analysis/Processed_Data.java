package helpdesk_Router_Analysis;

import helpdesk_router_model.Ticket;

public class Processed_Data {

        private final Ticket Original_Ticket;
        private final String[] Tokenized_Description;

        public Processed_Data (Ticket Original_Ticket, String[] Tokenized_description) {

            this.Original_Ticket = Original_Ticket;
            this.Tokenized_Description = Tokenized_description;

        }

        public Ticket getOriginal_Ticket () {

            return Original_Ticket;
        }

        public String[] getTokenized_Description() {

            return Tokenized_Description;
        }


    }

