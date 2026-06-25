package helpdesk_router_model;

public class Ticket {

    private final int Incident_Id;  //set as final so that they cant be altered later
    private final String Summary;
    private final String Description;
    private Team Assigned_Team;
    private int Routing_Score;

    public Ticket (int incident_id, String summary, String description) {

        this.Incident_Id = incident_id;
        this.Summary = summary;
        this.Description = description;
        this.Assigned_Team = Team.HUMAN_REVIEW;
        this.Routing_Score = 0;

    }

    public int getIncident_Id () {

        return Incident_Id;
    }

    public String getDescription (){

        return Description;
    }

    public String getSummary() {

        return Summary;
    }

    public Team getAssigned_Team () {

        return Assigned_Team;
    }

    public void setAssigned_Team (Team assigned_team) {

        this.Assigned_Team = assigned_team;

    }

    public int getRouting_Score () {

        return Routing_Score;
    }

    public void setRouting_Score (int routing_score) {

        this.Routing_Score = routing_score;

    }

}
