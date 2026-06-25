package helpdesk_Router_Analysis;
import helpdesk_router_model.KeywordHolder;
import helpdesk_router_model.Team;
import helpdesk_router_model.Ticket;

import java.util.ArrayList;
import java.util.HashMap;


public class Ticket_Router_Engine {

    HashMap<Team, Integer> counter = new HashMap<>();

    public  Final_Verdict Ticket_Grader(Processed_Data data ) {

        counter.put(Team.HELP_DESK, 0);
        counter.put(Team.HELP_DESK_CALEB, 0);
        counter.put(Team.END_POINT, 0);
        counter.put(Team.APPLICATION_DEVELOPMENT, 0);
        counter.put(Team.SYSTEM_ADMINISTRATORS, 0);
        counter.put(Team.SECURITY, 0);
        counter.put(Team.CLASSROOM_AND_LABS, 0);
        counter.put(Team.RESNET, 0);
        counter.put(Team.UNIVERSITY_RELATIONS, 0);
        counter.put(Team.OUTREACH, 0);
        counter.put(Team.PURCHASING, 0);
        counter.put(Team.CAREER_SERVICES, 0);
        counter.put(Team.ISS, 0);
        counter.put(Team.HUMAN_REVIEW, 0);
        counter.put(Team.FAULTY_TRASH, 0);

        for( Team other : Team.values() ) { //for each Team constant within Team.values()

            for (KeywordHolder keyword : other.getKeywords()) {

                for (int i = 0; i < data.getTokenized_Description().length; i++) {

                    if (keyword.getKey_Word().equals(data.getTokenized_Description()[i])) {

                        int count = counter.get(other);
                        count += keyword.getScore();
                        counter.put(other, count);

                    }

                }

            }

        }

            int Highest_Score = 0;
            Team Winning_Team = Team.HUMAN_REVIEW;
            int  Team_Score = 0;
        ArrayList<Team> Tied_Teams = new ArrayList<>();


        for (Team other : counter.keySet()) {


                Team_Score = counter.get(other);

                if (Team_Score > Highest_Score) {

                    Tied_Teams.clear();
                    Highest_Score = Team_Score;
                    Winning_Team = other;
                    Tied_Teams.add(other);

                } else if (Team_Score == Highest_Score ) {

                    Tied_Teams.add(other);

                }
            }

        if (Highest_Score <= 0 ) {

            Winning_Team = Team.FAULTY_TRASH;

        }

        else if (Tied_Teams.size() > 1) {

            Winning_Team = Team.HUMAN_REVIEW;
        }

            Final_Verdict Final = new Final_Verdict(Winning_Team, data.getOriginal_Ticket());

            return Final;


        }
    }






