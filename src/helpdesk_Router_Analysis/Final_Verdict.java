package helpdesk_Router_Analysis;

import helpdesk_router_model.Team;
import helpdesk_router_model.Ticket;


public class Final_Verdict{

        private Team team;
        private Ticket Final_Assigned_Ticket;

        Final_Verdict (Team team, Ticket Final_Ticket) {

            this.team = team;
            this.Final_Assigned_Ticket = Final_Ticket;

        }

        public Team getTeam(){

            return team;
        }

        public Ticket getFinal_Assigned_Ticket() {

            return Final_Assigned_Ticket;
        }


    }

